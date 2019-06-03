package util;
 
import java.sql.Connection;
import java.sql.DriverManager;
 
public class DbConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/auction2019_05_22?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";
    public Connection connection = null;
    
    public DbConnection() {
		try {
			Class.forName(DRIVER).newInstance(); // 加载数据库驱动
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 加载数据库
		} catch (Exception e) {
			System.out.println("数据库加载失败");
		}
	}

}