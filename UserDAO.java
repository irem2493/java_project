package board_user_management;

import dictionary.DBConn;

import java.sql.*;
import java.time.LocalDateTime;

public class UserDAO {
    Connection conn = DBConn.getConnection();

    public int checkId(String uid) {
        String query = "SELECT uid FROM userTable WHERE uid = ?;";
        int find = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, uid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                find++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }
    public int loginUser(String uid, String upw){
        String query = "SELECT upw FROM userTable WHERE uid = ?;";
        int find = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, uid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                if(rs.getString("upw").equals(upw)) {
                    System.out.println(uid + "님, 환영합니다.");
                    updateLoginUser(uid);
                    find++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }

    void updateLoginUser(String uid) {
        String query = "UPDATE userTable SET login_date = NOW() WHERE uid = ?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, uid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int updateLogoutUser(String uid) {
        String query = "UPDATE userTable SET logout_date = NOW() WHERE uid = ?;";
        int result = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, uid);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String joinUser(UserDTO user) {
        String alert = "회원가입을 실패하였습니다.";
        String query = "INSERT INTO userTable(uno, uid, upw, join_date) VALUES(?,?,?,NOW());";
        int result = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,user.getUno());
            stmt.setString(2, user.getUid());
            stmt.setString(3, user.getUpw());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result > 0) alert = "회원가입이 완료되었습니다.";
        return alert;

    }
}
