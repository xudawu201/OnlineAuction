package dao;

import entity.Order;

/**
 * @author rhythm
 * @date 2019年5月17日 下午7:19:13 相关说明
 */
public interface OrderDao {
	// 添加竞拍
	public boolean addOrder(int commodityId,float price,int winnerId, int userId,String type);
	// 根据类型和商品id寻找订单id
	public int selectId(int id,String type);
	//删除订单
	public boolean deleteById(int id);
	//根据id寻找订单
	public Order getById(int id);
}
