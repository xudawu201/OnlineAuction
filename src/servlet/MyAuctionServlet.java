package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.BookDaoFactory;
import dao.StampDao;
import dao.StampDaoFactory;
import dao.WatchDao;
import dao.WatchDaoFactory;
import dao.WineDao;
import dao.WineDaoFactory;
import entity.Book;
import entity.Commodity;
import entity.Stamp;
import entity.Watch;
import entity.Wine;

/**
 * Servlet implementation class MyAuctionServlet
 */
@WebServlet("/MyAuctionServlet")
public class MyAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAuctionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		int user_id = Integer.valueOf(request.getParameter("user_id"));
		System.out.println("userId:"+user_id);
		List<Commodity> myGoodss = new ArrayList<Commodity>();
		myGoodss = getUserGoods(user_id);
		request.setAttribute("myGoodss", myGoodss);
		request.getRequestDispatcher("myAuction.jsp").forward(request, response);
	}

//	//获取用户的物品
	public List<Commodity> getUserGoods(int user_id){
		//创建一个list存商品信息
		List<Commodity> myGoodss = new ArrayList<Commodity>();
		//查询用户拥有的book
		BookDao newBookDao=BookDaoFactory.getBookDaoInstance();
		try {
			List<Book> BookList = newBookDao.getBookAll();
			for(int i=0;i<BookList.size();i++){
				if(BookList.get(i).getUserId() == user_id){
					Commodity goods = BookList.get(i);
					//获取商品id
					Book book=BookList.get(i);
					goods.setCommodityId(book.getId());
					myGoodss.add(goods);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询用户拥有的watch
		WatchDao newWatchDao=WatchDaoFactory.getWatchDaoInstance();
		try {
			List<Watch> thisList = newWatchDao.getWatchAll();
			for(int i=0;i<thisList.size();i++){
				if(thisList.get(i).getUserId() == user_id){
					Commodity goods = thisList.get(i);
					//获取商品id
					Watch book=thisList.get(i);
					goods.setCommodityId(book.getId());
					myGoodss.add(goods);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 查询用户拥有的stamp
		StampDao newStampDao = StampDaoFactory.getDaoInstance();
		try {
			List<Stamp> thisList = newStampDao.getListAll();
			for (int i = 0; i < thisList.size(); i++) {
				if (thisList.get(i).getUserId() == user_id) {
					Commodity goods = thisList.get(i);
					//获取商品id
					Stamp book=thisList.get(i);
					goods.setCommodityId(book.getId());
					myGoodss.add(goods);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 查询用户拥有的wine
		WineDao newWineDao = WineDaoFactory.getDaoInstance();
		try {
			List<Wine> thisList = newWineDao.getListAll();
			for (int i = 0; i < thisList.size(); i++) {
				if (thisList.get(i).getUserId() == user_id) {
					Commodity goods = thisList.get(i);
					// 获取商品id
					Wine book = thisList.get(i);
					goods.setCommodityId(book.getId());
					myGoodss.add(goods);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myGoodss;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
