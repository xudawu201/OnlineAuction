package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.WineDao;
import dao.WineDaoFactory;
import entity.Wine;
import util.DbConnection;

/**
* @author rhythm
* @date 2019年5月26日 上午10:03:06
*  相关说明 
*/
public class WineDaoImpl implements WineDao{
	private Connection connection = null; // 定义连接的对象
	private PreparedStatement ps = null; // 定义预准备的对象
	private DbConnection jdbc = null; // 定义数据库连接对象

	public WineDaoImpl() {
		jdbc = new DbConnection();
		connection = jdbc.connection; // 利用构造方法取得数据库连接
	}

	public List getListAll(){
		// TODO Auto-generated method stub
		List<Wine> ListAll = new ArrayList<Wine>() ;  
        String sql = "select * from t_wine;" ;  
        // 下面是针对数据库的具体操作  
        try{  
            // 连接数据库  
            ps = connection.prepareStatement(sql) ;   
            // 进行数据库查询操作  
            ResultSet rs = ps.executeQuery() ;  
            while(rs.next()){  
                // 查询出内容，之后将查询出的内容赋值给book对象  
            	 Wine thisEntity = new Wine() ;  
            	 thisEntity.setId(rs.getInt(1));  
            	 thisEntity.setUserId(rs.getInt(2));  
            	 thisEntity.setType(rs.getString(3));  
            	 thisEntity.setWinnerId(rs.getInt(4));  
            	 thisEntity.setMaxPrice(rs.getFloat(5));
            	 thisEntity.setPrice(rs.getInt(6));
                 
                 
            	 thisEntity.setIntroduce(rs.getString(7));  
            	 thisEntity.setPicture(rs.getString(8));
            	 thisEntity.setDate(rs.getTimestamp(9)); 
            	 thisEntity.setState(rs.getInt(10)); 
                // 将查询出来的数据加入到List对象之中  
                 ListAll.add(thisEntity) ;  
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
        try {
			connection.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ListAll ;  
	}
	
	//根据id获取所有信息
	public Wine getById(int id) {
		Wine thisEntity = null;
		try {
			String sql = "select * from t_wine where id=" + id;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// 生成实体
				thisEntity = new Wine();
				thisEntity.setId(rs.getInt("id"));
				thisEntity.setDate(rs.getTimestamp(("date")));
				thisEntity.setIntroduce(rs.getString("introduce"));
				thisEntity.setMaxPrice((rs.getFloat("max_price")));
				thisEntity.setPicture((rs.getString("picture")));

				thisEntity.setPrice((rs.getFloat("price")));
				thisEntity.setType((rs.getString("category")));
				thisEntity.setUserId((rs.getInt("user_id")));

				thisEntity.setWinnerId((rs.getInt("winner_id")));
				thisEntity.setState((rs.getInt("state")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisEntity;
	}
	
	public Long getSecond(int id) {
		// TODO Auto-generated method stub
		long second = 0;
		String sql = "select * from t_wine where id=" + id;
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
		System.out.println(newDate);
		return second;
	}

	// 修改某个物品的当前最高价与最高价用户id
	public boolean updateMax_price(int goods_id, float max_price, int winner_id) {
		try {
			String sql = "update t_wine set max_price=" + max_price + ",winner_id=" + winner_id + " where id="
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
				String sql = "update t_wine set state=2,max_price=" + max_price + ",winner_id=" + winner_id + " where id="
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
			String sql = "delete from t_wine where id=" + id;
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
		WineDao thisDao=WineDaoFactory.getDaoInstance();
		List<Wine> cc = thisDao.getListAll();
			Wine ee = cc.get(0);
//			Stamp ee=thisDao.getById(2);
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

	@Override
	public boolean insert(int userId,Float price, String introduce,String imgUrl) throws Exception {
		// TODO Auto-generated method stub
		//改变条数
		int updateCount = 0;
		try {
			String sql = "insert into t_wine(user_id,price,introduce,picture) values(?,?,?,?);";
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
}
