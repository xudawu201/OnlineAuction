package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Wine;

/**
* @author rhythm
* @date 2019年5月26日 上午10:01:59
*  相关说明 
*/
public interface WineDao {
	//查询全部数据
    public List getListAll();  
    //以id查询
  	public Wine getById(int id);
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
