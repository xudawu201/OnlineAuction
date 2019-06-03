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
import dao.CommodityDao;
import dao.CommodityDaoFactory;
import entity.Book;
import entity.Commodity;

/**
 * Servlet implementation class SerchServlet
 */
@WebServlet("/SerchServlet")
public class SerchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SerchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		String serchInput = request.getParameter("serchInput");
		System.out.println(serchInput);
		List<Commodity> thisEntity=new ArrayList<Commodity>();
		
		CommodityDao commodityDao=CommodityDaoFactory.getDaoInstance();
		thisEntity=commodityDao.fuzzySerch(serchInput);
		if (thisEntity.isEmpty()) {
			System.out.println("serchServlet thisEntity is empty");
		}else {
			System.out.println("serchServlet thisEntity is not empty");
			System.out.println(thisEntity.get(0).getPicture());
		}
		request.setAttribute("commodity", thisEntity);
		
		request.getRequestDispatcher("serch.jsp").forward(request, response);
	}
}
