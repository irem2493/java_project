package c0924;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Jdbc_Student_Management {
	private String driverName = "org.mariadb.jdbc.Driver";
	private String url = "jdbc:mariadb://localhost:3306/mydb";
	private String uid = "root";
	private String upw = "1234";
	private String query;
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private int result;
	
	public void insert(ArrayList<Jdbc_Student> sList) {
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, uid, upw);
			//삽입
			for(Jdbc_Student s : sList) {
				query = "INSERT INTO student VALUES (null, ?, ?, ?, ?, ?, ?, ?);";
				stmt = conn.prepareStatement(query);
				stmt.setString(1, s.getName());
				stmt.setInt(2, s.getSchoolYear());
				stmt.setInt(3, s.getClazz());
				stmt.setInt(4, s.getSno());
				stmt.setInt(5, s.getKorScore());
				stmt.setInt(6, s.getEngScore());
				stmt.setInt(7, s.getMathScore());
				result = stmt.executeUpdate();
				if(result > 0) System.out.println("삽입완료");
				else System.out.println("실패");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Jdbc_Student> select(int schoolYear) {
		ArrayList<Jdbc_Student> sList =new ArrayList<>();
		ResultSet rs = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, uid, upw);
			//조회	
			query = "SELECT * FROM student WHERE schoolYear = ?;";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, schoolYear);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Jdbc_Student s = new Jdbc_Student(rs.getString("name"), rs.getInt("schoolYear"), rs.getInt("class"), rs.getInt("sno"),
						rs.getInt("korScore"), rs.getInt("engScore"), rs.getInt("mathScore"));
				s.setNo(rs.getInt("no"));
				sList.add(s);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sList;
	}
}
