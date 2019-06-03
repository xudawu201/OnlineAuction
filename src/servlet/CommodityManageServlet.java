package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommodityDao;
import dao.CommodityDaoFactory;
import dao.UserDao;
import dao.UserDaoFactory;
import entity.Commodity;
import entity.User;

/**
 * Servlet implementation class CommodityManageServlet
 */
@WebServlet("/CommodityManageServlet")
public class CommodityManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommodityManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		allGoods(request, response);
		//如果是管理员后台发来的请求，则加载到管理员后台的相关物品列表的页面
		if(request.getParameter("level") != null && request.getParameter("level").equals("guanliyuan")){
			request.getRequestDispatcher("admin/goods.jsp").forward(request,response);
		}else{
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
		
		
	}

	//查询所有拍卖品
	public void allGoods(HttpServletRequest request, HttpServletResponse response){
		CommodityDao goodsdao = CommodityDaoFactory.getDaoInstance();
		List<Commodity> goodss = goodsdao.getAllList();
		List<User> users = new ArrayList<User>();
		UserDao userDao=UserDaoFactory.getDaoInstance();
		for(int i=0;i<goodss.size();i++){
			//把所有者放一个集合
			int user_id = goodss.get(i).getUserId();
			users.add(userDao.getById(user_id));
		}
		request.setAttribute("goodss", goodss);
		request.setAttribute("users", users);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
