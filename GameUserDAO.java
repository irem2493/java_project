package rocScissorsPaperGame;

import dictionary.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameUserDAO {
    Connection conn = DBConn.getConnection();

    public String joinUser(GameUserDTO user) {
        String alert = "회원가입을 실패하였습니다.";
        String query = "INSERT INTO game_user(uno, uid, upw, join_date) VALUES(?,?,?,now());";
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

    public int checkId(String uid) {
        String query = "SELECT uid FROM game_user WHERE uid = ?;";
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
        String query = "SELECT COUNT(*) count FROM game_user WHERE uid = ? AND upw = ?;";
        int find = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, uid);
            stmt.setString(2, upw);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                find = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }

    void updateLoginUser(String uid) {
        String query = "UPDATE game_user SET login_date = NOW() WHERE uid = ?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, uid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int updateLogoutUser(String uid) {
        String query = "UPDATE game_user SET logout_date = NOW() WHERE uid = ?;";
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
}
