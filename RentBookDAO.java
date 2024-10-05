package ribrary_program;

import dictionary.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RentBookDAO {
    Connection conn = DBConn.getConnection();
    public int insertRentBook(String mid,int bno){
        int result = 0;
        String query = "INSERT INTO book_rent (mid, bno, rent_date,return_date, rent_yn, over_due_m) VALUES (?,?,NOW(),ADDDATE(NOW(), INTERVAL 14 day), '대출중','미연체');";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,mid);
            pstmt.setInt(2,bno);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
