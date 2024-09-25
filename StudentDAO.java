package c0925;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import c0924.Jdbc_Student;

public class StudentDAO {
	//Student 관리
	Connection conn = DBConn.getConnection();
	
	//저장이 되었다면 메인에 알리기
	//DB저장
	public String register(Jdbc_Student s) {
		int result = 0;
		String alert;
		String query = "INSERT INTO student VALUES (null, ?, ?, ?, ?, ?, ?, ?,'');";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1,s.getName());
			stmt.setInt(2, s.getSchoolYear());
			stmt.setInt(3, s.getClazz());
			stmt.setInt(4, s.getSno());
			stmt.setInt(5, s.getKorScore());
			stmt.setInt(6, s.getEngScore());
			stmt.setInt(7, s.getMathScore());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result > 0) alert = "삽입 성공";
		else alert = "삽입 실패";
		return alert;
	}
	
	//전체 정보 출력함수
	public String selectAll() {
		ResultSet rs = null;
		String alert;
		ArrayList<Jdbc_Student> sList = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM student;";
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Jdbc_Student s = new Jdbc_Student(rs.getString("name"), rs.getInt("schoolYear"),
						rs.getInt("class"), rs.getInt("sno"), rs.getInt("korScore"), rs.getInt("engScore"),
						rs.getInt("mathScore"));
				s.setNo(rs.getInt("no"));
				sList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!sList.isEmpty()) {
			alert = "전체 정보를 조회하였습니다.";
			for(Jdbc_Student s : sList) {
				System.out.println(s);
			}
		}else alert = "전체 정보 조회 실패";
		return alert;
	}
	
	//고유 번호로 검색
	public Jdbc_Student selectNo(int no) {
		Jdbc_Student s = null;
		ResultSet rs = null;
		String query = "SELECT * FROM student WHERE no = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while(rs.next()) {
				s = new Jdbc_Student(rs.getString("name"), rs.getInt("schoolYear"),
						rs.getInt("class"), rs.getInt("sno"), rs.getInt("korScore"), rs.getInt("engScore"),
						rs.getInt("mathScore"));
				s.setNo(rs.getInt("no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	//이름으로 검색(검색된 이름이 포함된 학생 정보 전부 출력함수)
	public String selectName(String name) {
		ResultSet rs = null;
		Jdbc_Student s = null;
		ArrayList<Jdbc_Student> sList = new ArrayList<>();
		String alert;
		String query = "SELECT * FROM student WHERE name like ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, "%"+name+"%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				s = new Jdbc_Student(rs.getString("name"), rs.getInt("schoolYear"),
						rs.getInt("class"), rs.getInt("sno"), rs.getInt("korScore"), rs.getInt("engScore"),
						rs.getInt("mathScore"));
				s.setNo(rs.getInt("no"));
				sList.add(s);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(!sList.isEmpty()) {
			alert = "이름 조회 성공";
			for(Jdbc_Student std : sList) {
				System.out.println("-----------------");
				System.out.println(std);
			}
		}
		else alert = "이름 조회 실패";
		return alert;
	}
	
	//해당 학년 삭제
	public String deleteSchoolYear(int schoolYear) {
		int result = 0;
		String alert, query = "DELETE FROM student WHERE schoolYear = ?;";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, schoolYear);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result > 0) alert = schoolYear+"학년 정보가 삭제되었습니다.";
		else alert = "삭제 실패";
		return alert;
	}
	
	//수정기준치가 변경이 되면 수료된 컬럼 정보를 전부 미수료로 바꾼다.
	public String changePassScore() {
		int result = 0;
		String alert;
		try {
			Statement stmt = conn.createStatement();
			String query = "UPDATE student SET pass = '미수료' WHERE pass = '수료';";
			result = stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result > 0 ) alert = "수정기준치 변경 전 수료 컬럼 값 수정 완료";
		else alert = "수료된 정보 없음";
		return alert;
	}
	
	
	//평균이 기준치 이상이면 수료
	public String updatePass(int avgScore) {
		int result = 0;
		String alert = "";
		String query = "UPDATE student "
				+ "SET pass = '수료' "
				+ "WHERE no IN ("
				+ "SELECT no "
				+ "FROM ("
				+ "SELECT no, (korScore + engScore + mathScore) / 3.0 AS avgScore "
				+ "FROM student "
				+ "HAVING avgScore >= ?) AS subquery);";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, avgScore);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result > 0) alert = "학점 수정완료";
		else alert = "수정 실패";
		return alert;
		
	}
	
	//수료인 학생 출력 함수
	public String selectPass() {
		String alert;
		Jdbc_Student s = null;
		ArrayList<Jdbc_Student> sList = new ArrayList<Jdbc_Student>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM student WHERE pass = '수료';";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				s = new Jdbc_Student(rs.getString("name"), rs.getInt("schoolYear"),
						rs.getInt("class"), rs.getInt("sno"), rs.getInt("korScore"), rs.getInt("engScore"),
						rs.getInt("mathScore"));
				s.setNo(rs.getInt("no"));
				sList.add(s);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!sList.isEmpty()) {
			alert = "수료한 학생 조회";
			for(Jdbc_Student std : sList) {
				System.out.println(std);
			}
		}else alert = "수료 학생 조회 실패";
		return alert;
	}
	
	//평균이 입력받은 값보다 미만인 학생 조회
	public String selectAvgUnder(int score) {
		System.out.println("평균이 " + score+"점 미만인 학생을 조회합니다.");
		String alert;
		ResultSet rs = null;
		ArrayList<Jdbc_Student> sList = new ArrayList<>();
		String query = "SELECT * "
				+ "FROM student "
				+ "WHERE no IN("
				+ "	SELECT no"
				+ "	FROM ("
				+ "	SELECT NO, (korScore + engScore + mathScore) / 3.0 AS avgScore"
				+ "	FROM student"
				+ "	having avgScore < ?) AS subquery"
				+ ");";
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, score);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Jdbc_Student s = new Jdbc_Student(rs.getString("name"), rs.getInt("schoolYear"),
						rs.getInt("class"), rs.getInt("sno"), rs.getInt("korScore"), rs.getInt("engScore"),
						rs.getInt("mathScore"));
				s.setNo(rs.getInt("no"));
				sList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!sList.isEmpty()) {
			alert = "평균 60점 미만인 학생 조회 완료";
			for(Jdbc_Student s : sList)
				System.out.println(s);
		}else alert = "평균 60점 미만인 학생 없음";
		return alert;
	}
}
