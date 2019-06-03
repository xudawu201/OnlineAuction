package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Book;

/**
 * @author rhythm
 * @date 2019年5月8日 下午5:15:59 相关说明
 */

public interface BookDao {
	// 增加操作
	public boolean insert(int userId,Float price, String introduce,String imgUrl) throws Exception;

	// 修改操作
	public void update(Book book) throws Exception;

	// 删除操作
	public boolean delete(int id);

	// 查询全部
	public List getBookAll();

	// 以id获取商品信息
	public Book getById(int index) throws Exception;
	//根据id获取秒数
	public long getSecond(int id) throws SQLException;
	//修改最高当前
	public boolean updateMax_price(int goods_id, float max_price, int winner_id);
	//修改订单
	public boolean updateOrder(int goods_id, float max_price, int winner_id);
}
