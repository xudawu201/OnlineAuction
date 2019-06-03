package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoFactory;
import entity.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		myUser(request,response,Integer.valueOf(request.getParameter("user_id")));
	}
	//返回用户
		public void myUser(HttpServletRequest request, HttpServletResponse response, int user_id) throws ServletException, IOException{
			UserDao userdao =UserDaoFactory.getDaoInstance();
			User user = null;
			user = userdao.querUser(user_id);
			if(user != null){
				request.setAttribute("user", user);
				request.getRequestDispatcher("user.jsp").forward(request, response);
			}else{
				System.out.println("获取用户失败error");
			}
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		//修改用户信息等（头像）通过post请求
		/*
		 * SmartUpload smart = new SmartUpload(); smart.initialize(getServletConfig(),
		 * request, response); try { smart.upload(); } catch (SmartUploadException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } int user_id
		 * =Integer.valueOf(smart.getRequest().getParameter("user_id")); String
		 * user_icon = uploadIcon(request,response,smart,user_id); UserDao userdao = new
		 * UserDao(); if(userdao.setUserIcon(user_id, user_icon)){ //修改成功
		 * response.sendRedirect("UserServlet?user_id="+user_id+"&error=ok"); }else{
		 * //修改失败 response.sendRedirect("UserServlet?user_id="+user_id+"&error=error");
		 * } }
		 * 
		 * //修改用户头像，把上传的头像保存到uploads下的icon里 public String uploadIcon(HttpServletRequest
		 * request, HttpServletResponse response,SmartUpload smart,int user_id){
		 * //设置上传文件保存路径 String filePath = getServletContext().getRealPath("/") +
		 * "application/uploads/icon"; String filename =
		 * user_id+"."+smart.getFiles().getFile(0).getFileExt(); try {
		 * smart.getFiles().getFile(0).saveAs(filePath+java.io.File.separator+filename);
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (SmartUploadException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } return
		 * "uploads/icon/"+filename; }
		 */
	}

}
