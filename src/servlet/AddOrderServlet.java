package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BookDaoFactory;
import dao.OrderDao;
import dao.OrderDaoFactory;
import dao.StampDao;
import dao.StampDaoFactory;
import dao.UserDao;
import dao.UserDaoFactory;
import dao.WatchDao;
import dao.WatchDaoFactory;
import dao.WineDao;
import dao.WineDaoFactory;


/**
 * Servlet implementation class AddBidServlet
 */
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOrderServlet() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		// 竞拍时间到，生成竞拍订单
		request.setCharacterEncoding("UTF-8");
		int commodityId = Integer.parseInt(request.getParameter("goods_id"));
		Float price = Float.parseFloat(request.getParameter("bid_price"));
		int winnerId = Integer.parseInt(request.getParameter("winner_id"));
		int userId = Integer.parseInt(request.getParameter("user_id"));
		String type=String.valueOf(request.getParameter("type"));
		System.out.println("AddOrderServletDoPost:");
		System.out.println(type);
		//存入订单表
		OrderDao thisDao=OrderDaoFactory.getDaoInstance();
		thisDao.addOrder(commodityId, price, winnerId, userId,type);
		//更新用户表
		UserDao thisUserDao=UserDaoFactory.getDaoInstance();
		thisUserDao.addUserBoughtNumber(userId);
		thisUserDao.addUserAuctionNumber(winnerId, 1);
		if (type.equals("book")) {
			BookDao thisBookDao=BookDaoFactory.getBookDaoInstance();
			thisBookDao.updateOrder(commodityId, price, winnerId);
			System.out.println("竞拍成功");
		}
		if (type.equals("watch")) {
			WatchDao thisWatchDao=WatchDaoFactory.getWatchDaoInstance();
			thisWatchDao.updateOrder(commodityId, price, winnerId);
			System.out.println("竞拍成功");
		}
		if (type.equals("stamp")) {
			StampDao thisStampDao=StampDaoFactory.getDaoInstance();
			thisStampDao.updateOrder(commodityId, price, winnerId);
			System.out.println("竞拍成功");
		}
		if (type.equals("wine")) {
			WineDao thisWineDao=WineDaoFactory.getDaoInstance();
			thisWineDao.updateOrder(commodityId, price, winnerId);
			System.out.println("竞拍成功");
		}
	}

}
