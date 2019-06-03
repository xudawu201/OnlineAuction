package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import entity.Stamp;
import entity.Watch;
import entity.Wine;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class CommodityServlet
 */
@WebServlet("/CommodityServlet")
public class CommodityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommodityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

//		System.out.println("WatchPost");
		// 声明out对象
		PrintWriter out = response.getWriter();
		
		//获取商品类型
		String type=request.getParameter("type");
		
		System.out.println(type);
		// 通过DAO工厂获得DAO实现类实例
		if(type.equals("book"))
		{
		    out.print(getBook());// 返回json对象     {"stu1":stu1, "stu2":stu2,"stu3":stu3 }
//		System.out.println(Json);
		}
		else if (type.equals("watch")) {
			out.print(getWatch());
		}
		else if (type.equals("stamp")) {
			out.print(getStamp());
		}
		else if (type.equals("wine")) {
			out.print(getWine());
		}	
		out.close();
	}
	//获取book
	public JSONArray getBook() {
		BookDao thisDao = BookDaoFactory.getBookDaoInstance();
		List<Book> listAll = null;
		try {
			listAll = thisDao.getBookAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//实例化json
		JSONArray Json=new JSONArray();
		for (int i = 0; i < listAll.size(); i++) {
			// 实例化一个Book用来获取t_book中的信息
			Book bb = listAll.get(i);
//         	System.out.println(bb.getPicture());
			Json.add(i,bb);
//			System.out.println(WatchJson.getString(i));
		}
		return Json;
	}
	//获取watch
	public JSONArray getWatch() {
		WatchDao thisDao = WatchDaoFactory.getWatchDaoInstance();
		List<Watch> listAll = null;
		try {
			listAll = thisDao.getWatchAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//实例化json
		JSONArray Json=new JSONArray();
		for (int i = 0; i < listAll.size(); i++) {
			// 实例化一个Book用来获取t_book中的信息
			Watch bb = listAll.get(i);
//         	System.out.println(bb.getPicture());
			Json.add(i,bb);
//			System.out.println(WatchJson.getString(i));
		}
		return Json;
	}

	// 获取stamp
	public JSONArray getStamp() {
		StampDao thisDao = StampDaoFactory.getDaoInstance();
		List<Stamp> listAll = null;
		try {
			listAll = thisDao.getListAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 实例化json
		JSONArray Json = new JSONArray();
		for (int i = 0; i < listAll.size(); i++) {
			// 实例化一个Book用来获取t_book中的信息
			Stamp bb = listAll.get(i);
//	         	System.out.println(bb.getPicture());
			Json.add(i, bb);
//				System.out.println(WatchJson.getString(i));
		}
		return Json;
	}

	// 获取wine
	public JSONArray getWine() {
		WineDao thisDao = WineDaoFactory.getDaoInstance();
		List<Wine> listAll = null;
		try {
			listAll = thisDao.getListAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 实例化json
		JSONArray Json = new JSONArray();
		for (int i = 0; i < listAll.size(); i++) {
			// 实例化一个Book用来获取t_book中的信息
			Wine bb = listAll.get(i);
//		         	System.out.println(bb.getPicture());
			Json.add(i, bb);
//					System.out.println(WatchJson.getString(i));
		}
		return Json;
	}
}
