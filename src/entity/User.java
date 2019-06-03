package entity;


/**
* @author rhythm
* @date 2019年5月12日 下午4:30:56
*  相关说明 
*/
public class User {
	private int id;//int类型
	private String name;
	private String password;
	private String phone;//注意是string类型
	private String address;
	private int bought_number;
	private int auction_number;
	private int admin;
	
	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBought_number() {
		return bought_number;
	}

	public void setBought_number(int bought_number) {
		this.bought_number = bought_number;
	}

	public int getAuction_number() {
		return auction_number;
	}

	public void setAuction_number(int auction_number) {
		this.auction_number = auction_number;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
