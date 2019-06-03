package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Watch;

/**
* @author rhythm
* @date 2019年5月9日 下午9:08:07
*  相关说明 
*/
public interface WatchDao {
	//查询全部数据
    public List getWatchAll();  
    //以id查询
  	public Watch getById(int goods_id);
  	//根据id获取时间差
	public Long getSecond(int id) throws SQLException;
	public boolean updateMax_price(int goods_id, float max_price, int winner_id);
	//修改订单
	public boolean updateOrder(int goods_id, float max_price, int winner_id);
	//新增商品
	public boolean insert(int userId,Float price, String introduce,String imgUrl) throws Exception;
	//删除商品
	public boolean delete(int id);
}
