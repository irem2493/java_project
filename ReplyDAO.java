package board_user_management;

import dictionary.DBConn;

import java.sql.*;
import java.util.ArrayList;

public class ReplyDAO {
    Connection conn = DBConn.getConnection();
    public int insertReply(ReplyDTO replyDTO){
        String query = "INSERT INTO replytable(rcontents, bno, uid, r_create_date) VALUES(?, ?, ?, NOW());";
        int result = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,replyDTO.getRcontents());
            pstmt.setInt(2, replyDTO.getBno());
            pstmt.setString(3, replyDTO.getUid());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ReplyDTO> selectReply(int bno){
        ArrayList<ReplyDTO> r = new ArrayList<>();
        ReplyDTO replyDto;
        String query = "SELECT rno, uid, rcontents, r_create_date FROM replyTable WHERE bno = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,bno);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                replyDto = new ReplyDTO(rs.getInt("rno"), rs.getString("rcontents"), rs.getString("uid"));
                replyDto.setRno(rs.getInt("rno"));
                replyDto.setR_create_date(rs.getTimestamp("r_create_date"));
                r.add(replyDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }
}
