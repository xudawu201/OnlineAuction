package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.UserDaoFactory;
import entity.User;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = getUserSession(request,response);
		List<User> users = new ArrayList<User>();
		request.setAttribute("users", users);
		if(user == null){
			//没登陆
		}else{
			request.getSession().setAttribute("user",user);
		}
		//注销的业务
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null){
			if(request.getParameter("login") != null && request.getParameter("login").toString().equals("no")){
				//清除session和cookie
				session.setAttribute("user",null);
				Cookie c = new Cookie("user",null);
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		
		request.getRequestDispatcher("NewIndex.jsp").forward(request, response);
		
	}
	//获取首页用户session信息的函数
	public User getUserSession(HttpServletRequest request, HttpServletResponse response){
		User user = null;
		HttpSession session = request.getSession();
		//看看session里有没有用户信息，如果没有，则获取cookie的用户信息
		if(session.getAttribute("user") == null){
			//如果session里没有用户信息,则从cookie里获取用户信息
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(Cookie cookie:cookies){
					if(cookie.getName().equals("user")){
						//cookie里有数据(账号密码),拿出来
						String[] userCookie;
						try {
							//解码
							userCookie = java.net.URLDecoder.decode(cookie.getValue(),"UTF-8").split(",");
							//用cookie里保存的用户信息（账号密码）进行登录验证，看数据是否正确
							user = loginCookie(userCookie[0], userCookie[1]);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if(user != null){
							//账号密码正确，session.setAttribute("username", )
							session.setAttribute("username", cookie.getValue());
							System.out.println("cookie里的数据正确");
						}else{
							//cookie里的数据错误（可能已经更改了密码之类的）,消除原来的cookie
							System.out.println("cookie清除");
							Cookie c = new Cookie(cookie.getName(),null);
							c.setMaxAge(0);
							response.addCookie(c);
						}
						
					}else{
						//如果cookie里也没有用户信息
					}
				}
			}
		}
		return user;
	}

	//自动登录
	private User loginCookie(String username,String password){
		//使用cookie里的用户信息与数据库匹配，如果正确，则返回user对象
		User user = null;
		UserDao userdao = UserDaoFactory.getDaoInstance();
		try {
			user = userdao.login(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
