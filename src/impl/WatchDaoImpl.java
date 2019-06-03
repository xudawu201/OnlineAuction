package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.WatchDao;
import entity.Book;
import entity.Watch;
import util.DbConnection;

/**
* @author rhythm
* @date 2019年5月9日 下午9:10:06
*  相关说明 
*/
public class WatchDaoImpl implements WatchDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private DbConnection jdbc = null; // 定义数据库连接对象

	public WatchDaoImpl() {
		jdbc = new DbConnection();
		connection = jdbc.connection; // 利用构造方法取得数据库连接
	}
	public boolean insert(int userId,Float price, String introduce,String imgUrl) throws Exception {
		// TODO Auto-generated method stub
		//改变条数
		int updateCount = 0;
		try {
			String sql = "insert into t_watch(user_id,price,introduce,picture) values(?,?,?,?);";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, userId);
		    ps.setFloat(2, price);
		    ps.setString(3,introduce);
		    ps.setString(4, imgUrl);
		    updateCount = ps.executeUpdate();
		    connection.close();
		    //看看更新的条数
		    if(updateCount == 1){
		        return true;
		    }else{
		        return false;
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;    
	}
	@Override
	public List getWatchAll(){
		// TODO Auto-generated method stub
		List<Watch> WatchAll = new ArrayList<Watch>() ;  
        String sql = "select * from t_watch;" ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            ps = connection.prepareStatement(sql) ;   
            // 进行数据库查询操作  
            ResultSet rs = ps.executeQuery() ;  
            while(rs.next()){  
                // 查询出内容，之后将查询出的内容赋值给book对象  
            	 Watch book = new Watch() ;  
                 book.setId(rs.getInt(1));  
                 book.setUserId(rs.getInt(2));  
                 book.setType(rs.getString(3));  
                 book.setWinnerId(rs.getInt(4));  
                 book.setMaxPrice(rs.getFloat(5));
                 book.setPrice(rs.getInt(6));
                 
                 
                 book.setIntroduce(rs.getString(7));  
                 book.setPicture(rs.getString(8));
                 book.setDate(rs.getTimestamp(9)); 
                 book.setState(rs.getInt(10)); 
                // 将查询出来的数据加入到List对象之中  
                WatchAll.add(book) ;  
            }  
            rs.close() ;  
            ps.close() ;  
        }  
        catch (Exception e){  
            try {
				throw new Exception("操作出现异常") ;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
        }  
        finally{  
            // 关闭数据库连接  
            try {
				connection.close() ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }  
        return WatchAll ;  
	}
	
	//根据id获取watch所有信息
	public Watch getById(int goods_id) {
		Watch goods = null;
		try {
			String sql = "select * from t_watch where id=" + goods_id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// 生成实体
				goods = new Watch();
				goods.setId(rs.getInt("id"));
				goods.setDate(rs.getTimestamp(("date")));
				goods.setIntroduce(rs.getString("introduce"));
				goods.setMaxPrice((rs.getFloat("max_price")));
				goods.setPicture((rs.getString("picture")));

				goods.setPrice((rs.getFloat("price")));
				goods.setType((rs.getString("category")));
				goods.setUserId((rs.getInt("user_id")));

				goods.setWinnerId((rs.getInt("winner_id")));
				goods.setState((rs.getInt("state")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	
	

	@Override
	public Long getSecond(int id) {
		// TODO Auto-generated method stub
		long second = 0;
		String sql = "select * from t_watch where id=" + id;
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date newDate = null;
		try {
			newDate = rs.getTimestamp("date");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date nowDate=new Date();
		
		//3天259200000毫秒
		second=newDate.getTime()+259200000-nowDate.getTime();
		// rs进行读取一次 判断是否有数据
		// rs默认指向第0条数据，next实际上为第一条数据
		// 第6行为图片url
		System.out.println(newDate);
		return second;
	}
	// 修改某个物品的当前最高价与最高价用户id
	public boolean updateMax_price(int goods_id, float max_price, int winner_id) {
		try {
			String sql = "update t_watch set max_price=" + max_price + ",winner_id=" + winner_id + " where id="
					+ goods_id;
			Statement statement = connection.createStatement();
			int updateCount = statement.executeUpdate(sql);
			if (updateCount == 1) {
				// 修改成功
				return true;
			}
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean updateOrder(int goods_id, float max_price, int winner_id) {
		try {
			String sql = "update t_watch set state=2,max_price=" + max_price + ",winner_id=" + winner_id + " where id="
					+ goods_id;
			Statement statement = connection.createStatement();
			int updateCount = statement.executeUpdate(sql);
			if (updateCount == 1) {
				// 修改成功
				return true;
			}
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	// 删除物品
	public boolean delete(int id) {
		try {
			String sql = "delete from t_watch where id=" + id;
			Statement statement = connection.createStatement();
			int updateCount = statement.executeUpdate(sql);
			if (updateCount == 1) {
				// 一条更新成功，删除成功
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		WatchDaoImpl ss = new WatchDaoImpl();
//		List<Watch> cc = ss.getWatchAll();
//			Watch ee = cc.get(4);
		Watch ee = ss.getById(3);
		System.out.println(ee.getPicture());
		System.out.println(ee.getId());
		System.out.println(ee.getIntroduce());
		System.out.println(ee.getMaxPrice());
		System.out.println(ee.getPrice());
		System.out.println(ee.getType());
		System.out.println(ee.getUserId());
		System.out.println(ee.getWinnerId());
		System.out.println(ee.getDate());
		System.out.println(ee.getState());
	}
}
