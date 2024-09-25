package c0925;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private final static String driverName = "org.mariadb.jdbc.Driver";
	private final static String url = "jdbc:mariadb://localhost:3306/mydb";
	private final static String uid = "root";
	private final static String upw = "1234";
	public static Connection getConnection() {
		Connection conn  = null;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, uid, upw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 if(conn == null)
			System.out.println("연결에 실패하였습니다. null인 Connection객체를 반환합니다.");
		return conn;	
	}
}
