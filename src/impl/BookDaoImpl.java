package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import dao.BookDao;
import dao.BookDaoFactory;
import entity.Book;
import util.DbConnection;

/**
* @author rhythm
* @date 2019年5月8日 下午5:21:50
*  相关说明
*/
public class BookDaoImpl implements BookDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private DbConnection jdbc = null; // 定义数据库连接对象

	public BookDaoImpl() {
		jdbc = new DbConnection();
		connection = jdbc.connection; // 利用构造方法取得数据库连接
	}
	
	@Override
	public boolean insert(int userId,Float price, String introduce,String imgUrl) throws Exception {
		// TODO Auto-generated method stub
		//改变条数
		int updateCount = 0;
		try {
			String sql = "insert into t_book(user_id,price,introduce,picture) values(?,?,?,?);";
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
	public void update(Book book) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// 删除物品
	public boolean delete(int id) {
		try {
			String sql = "delete from t_book where id=" + id;
			Statement statement =connection.createStatement();
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

	//查询所有数据
	@Override
	public List getBookAll(){
		// TODO Auto-generated method stub
		List<Book> BookAll = new ArrayList<Book>() ;  
        String sql = "select * from t_book;" ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            ps = connection.prepareStatement(sql) ;   
            // 进行数据库查询操作  
            ResultSet rs = ps.executeQuery() ;  
            while(rs.next()){  
                // 查询出内容，之后将查询出的内容赋值给book对象  
                Book book = new Book() ;  
                book.setId(rs.getInt(1));  
                book.setUserId(rs.getInt(2));  
                book.setType(rs.getString(3));  
                book.setWinnerId(rs.getInt(4));  
                book.setMaxPrice(rs.getInt(5));
                book.setPrice(rs.getFloat(6));
                
                
                book.setIntroduce(rs.getString(7));  
                book.setPicture(rs.getString(8));
                book.setDate(rs.getTimestamp(9)); 
                book.setState(rs.getInt(10)); 
                // 将查询出来的数据加入到List对象之中  
                BookAll.add(book) ;  
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
        return BookAll ;  
	}

	// 获取特定商品
	public Book getById(int goods_id) {
		Book goods = null;
		try {
			String sql = "select * from t_book where id=" + goods_id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// 生成实体
				goods = new Book();
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

	public long getSecond(int id) throws SQLException {
		
		long second = 0;
		String sql = "select * from t_book where id=" + id;
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		rs.next();
		Date newDate = rs.getTimestamp("date");
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
			String sql = "update t_book set max_price=" + max_price + ",winner_id=" + winner_id + " where id="
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
			String sql = "update t_book set state=2,max_price=" + max_price + ",winner_id=" + winner_id + " where id="
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
	public static void main(String[] args) throws Exception {
		BookDao bb = BookDaoFactory.getBookDaoInstance();
//		long dd = bb.getSecond(1);
//		System.out.println(dd);
//		List<Book> cc= bb.getBookAll();
		Book dd=bb.getById(5);
//		Book dd=cc.get(1);
		System.out.println(dd.getId());
		System.out.println(dd.getUserId());
		System.out.println(dd.getType());
		System.out.println(dd.getWinnerId());

		System.out.println(dd.getMaxPrice());
		System.out.println(dd.getPrice());
		System.out.println(dd.getPicture());
		System.out.println(dd.getIntroduce());
		System.out.println(dd.getDate());
		System.out.println(dd.getState());
	}
}
