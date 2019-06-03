package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LoginServlet_doPost");
		login(request, response);
		
	}

	// 验证登录
	public void login(HttpServletRequest request, HttpServletResponse response) {
//		response.setContentType("text/html");
		
		try {
			request.setCharacterEncoding("UTF-8");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
//			System.out.println(password);
//			System.out.println(username);
			User user = null;
//			PrintWriter out = response.getWriter();
			
			UserDao userdao = UserDaoFactory.getDaoInstance();
			user = userdao.login(username, password);
			if (user != null) {
				// 登陆成功
				// System.out.println("yes");
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				// 中文无法直接保存在cooki中，要先转码
				Cookie cookie = new Cookie("user", URLEncoder.encode(username + "," + password, "UTF-8"));
				//cookie两分钟过期
				cookie.setMaxAge(120);
				response.addCookie(cookie);
				response.sendRedirect("loginSuccess.jsp");
			} else {
				// System.out.println("no");
				response.sendRedirect("login.jsp?login=error");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
