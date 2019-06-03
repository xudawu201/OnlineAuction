package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.UserDaoFactory;
import entity.User;

/**
 * Servlet implementation class AlertUserServlet
 */
@WebServlet("/AlterUserServlet")
public class AlterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		doPost(request, response);
		System.out.println("toGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println("toPost");
		request.setCharacterEncoding("UTF-8");
		//判断是修改密码还是修改用户名
		String modify = request.getParameter("modify");
		System.out.println(modify);
		if(modify.equals("password")){
			modifyPassword(request, response,Integer.valueOf(request.getParameter("user_id")), request.getParameter("password"));
		}
//		if(modify.equals("username")){
//			modifyUsername(request, response,Integer.valueOf(request.getParameter("user_id")), request.getParameter("username"));
//		}
	}
//	
	//修改密码业务
		public void modifyPassword(HttpServletRequest request, HttpServletResponse response,int user_id,String password) throws IOException{
			UserDao userdao = UserDaoFactory.getDaoInstance();
			if(passwordIsOk(user_id, request.getParameter("yuanpassword")) && userdao.AlterUserPassword(user_id, password)){
				if (request.getParameter("yuanpassword").equals(password)) {
					System.out.println("修改失败，新密码和原密码相同");
					response.sendRedirect("alterUser.jsp?user_id="+request.getParameter("user_id")+"&error=newPassWord=oldPassWord");
				}
				else if(!request.getParameter("yuanpassword").equals(password)){
				System.out.println("修改成功");
				response.sendRedirect("UserServlet?user_id="+request.getParameter("user_id"));
				}
				else{
					//修改失败
					System.out.println("修改失败,系统错误");
					response.sendRedirect("alterUser.jsp?user_id="+request.getParameter("user_id")+"&error=systemError");
				}
			}
			else{
				//修改失败
				System.out.println("修改失败");
				response.sendRedirect("alterUser.jsp?user_id="+request.getParameter("user_id")+"&error=oloPasswordError");
			}
		}
		//查询原密码是否正确
		public boolean passwordIsOk(int user_id,String password){
			UserDao userdao =UserDaoFactory.getDaoInstance();
			User user = userdao.querUser(user_id);
			if(user.getPassword().equals(password)){
				return true;
			}
			return false;
		}
//		//修改用户名
//		public void modifyUsername(HttpServletRequest request, HttpServletResponse response,int user_id,String username) throws IOException{
//			UserDao userdao = UserDaoFactory.getDaoInstance();
//			
//			if(userdao.AlterUsername(user_id, username)){
//				//修改成功
//				System.out.println("修改成功");
//				//重新把用户信息存入session
//				HttpSession session = request.getSession();
//				User user = userdao.querUser(user_id);
//				session.setAttribute("user",user);
//				response.sendRedirect("UserServlet?user_id="+request.getParameter("user_id"));
//			}else{
//				//修改失败
//				System.out.println("修改失败");
//				response.sendRedirect("UserServlet?user_id="+request.getParameter("user_id")+"&error=error");
//			}
//		}
}
