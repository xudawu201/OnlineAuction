package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.activemq.filter.function.regexMatchFunction;
import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;

import dao.OrderDao;
import dao.OrderDaoFactory;
import entity.Book;
import entity.Commodity;
import entity.Order;
import util.DbConnection;

/**
* @author rhythm
* @date 2019年5月17日 下午7:20:52
*  相关说明 
*/
public class OrderDaoImpl implements OrderDao{
	
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private DbConnection jdbc = null; // 定义数据库连接对象

	public OrderDaoImpl() {
		jdbc = new DbConnection();
		connection = jdbc.connection; // 利用构造方法取得数据库连接
	}
	public boolean addOrder(int commodityId,float price,int winnerId, int userId,String type){
		boolean state = false;
		try {
			
			String sql = "insert into t_order(commodity_id,price,winner_id,user_id,type) values(?,?,?,?,?);";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, commodityId);
			ps.setFloat(2, price);
			ps.setInt(3, winnerId);
			ps.setInt(4, userId);
			ps.setString(5, type);
			int updateCount = ps.executeUpdate();
			if(updateCount == 1){
				state = true;
			}
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	// 根据类型和id寻找订单id
	public int selectId(int id,String type) {
		int orderId = 0;
		String sql = "select * from t_order where commodity_id= '"+ id+"' and category='"+type+"'";
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.next();
			orderId=rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderId;
	}
	//删除订单
	public boolean deleteById(int id) {
		boolean state = false;
		String sql = "delete from t_order where id= '"+ id+"'";
		try {
			ps = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//更新
		int updateCount = 0;
		try {
			updateCount = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(updateCount);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 看看更新的条数
		if (updateCount == 1) {
			state =true;
		} 
		return state;
	}

	// 获取特定商品
	public Order getById(int id) {
		Order order = null;
		try {
			String sql = "select * from t_order where id=" + id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// 生成实体
				order = new Order();
				order.setId(rs.getInt("id"));
				order.setDate(rs.getTimestamp(("date")));
				order.setCommodityId(rs.getInt("commodity_id"));
				order.setPrice((rs.getFloat("price")));
				order.setUserId((rs.getInt("user_id")));
				
				order.setType((rs.getString("category")));
				order.setWinnerId((rs.getInt("winner_id")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	public static void main(String[] args) {
		OrderDao thisDao = OrderDaoFactory.getDaoInstance();
//		System.out.println(thisDao.addOrder(10,20,10,10,"book"));
		System.out.println(thisDao.getById(1).getId());
		System.out.println(thisDao.getById(1).getCommodityId());
		System.out.println(thisDao.getById(1).getPrice());
		
		System.out.println(thisDao.getById(1).getType());
		System.out.println(thisDao.getById(1).getUserId());
		System.out.println(thisDao.getById(1).getWinnerId());
		System.out.println(thisDao.getById(1).getDate());
	}
}
