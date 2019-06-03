package dao;

import java.sql.SQLException;
import java.util.List;

import entity.User;

/**
 * @author rhythm
 * @date 2019年5月12日 下午5:48:43 相关说明
 */
public interface UserDao {
	// 增加操作
	public void insert(User aa) throws Exception;

	// 上架商品后增加auction_number数量
	public boolean update(int userId) throws Exception;

	// 删除操作
	public boolean delete(int id) throws Exception;
	//修改用戶权限
	//变为管理员
	public boolean changeAdmin(int id);
	//变为普通用户
	public boolean changeOrdinary(int id);
	// 查询全部
	public List getAll();

	// 以id查询
	public User getById(int index);
	//登录操作
	public User login(String username,String password) throws Exception;
	// 注册操作
	public int register(String username, String password);
	//根据用户名查询用户
	public User GetByName(String username);
	//判断是否有重名
	public int JudgeName(String username) throws SQLException;
	//修改信息
	public boolean AlterUsername(int user_id,String username);
	public boolean AlterUserPassword(int user_id,String password);
	//查询特定用户
	public User querUser(int user_id);
	//更新信息
	public boolean addUserBoughtNumber(int user_id);
	public boolean addUserAuctionNumber(int user_id,int count);
	//减少用户的出售数量
	public boolean decreaseUserAuctionNumber(int user_id, int count);
	//减少用户的购买数量
	public boolean decreaseUserBoughtNumber(int user_id);
}
