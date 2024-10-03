package ribrary_program;

import dictionary.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class LibraryMemberDAO extends LibraryUserDAO{
    Connection conn = DBConn.getConnection();

    public String joinUser(LibraryUserDTO user) {
        String alert = "회원가입을 실패하였습니다.";
        String query = "INSERT INTO libray_member(mno, mid, mpw, join_date) VALUES(?,?,?,now());";
        int result = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,LibraryUserDTO.getNo());
            stmt.setString(2, user.getId());
            stmt.setString(3, user.getPw());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result > 0) alert = "회원가입이 완료되었습니다.";
        return alert;

    }

    public int checkId(String id) {
        String query = "SELECT mid FROM libray_member WHERE mid = ?;";
        int find = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                find++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }
    public int loginUser(String id, String pw){
        String query = "SELECT COUNT(*) count FROM libray_member WHERE mid = ? AND mpw = ?;";
        int find = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.setString(2, pw);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                find = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }
}

