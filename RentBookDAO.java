package ribrary_program;

import dictionary.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public String selectReturn_yn(int bno){
        String return_yn = "대출 가능";
        String query = "SELECT rent_yn FROM book_rent WHERE bno = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bno);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                return_yn = rs.getString("rent_yn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return return_yn;
    }
    public ArrayList<RentBookDTO> showReturnBook(String id){
        ArrayList<RentBookDTO> rb = new ArrayList<>();
        String query = "SELECT rb.bno, lb.title, lb.writer, rb.rent_date, rb.return_date, rb.rent_yn, rb.over_due_m\n" +
                "FROM book_rent rb\n" +
                "INNER JOIN \n" +
                "(SELECT bno, title, writer\n" +
                "FROM library_book\n" +
                "WHERE bno IN (SELECT bno FROM book_rent WHERE MID = ?)) AS lb\n" +
                "ON rb.bno = lb.bno;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                RentBookDTO rbDto = new RentBookDTO();
                rbDto.setBno(rs.getInt("bno"));
                rbDto.setTitle(rs.getString("title"));
                rbDto.setWriter(rs.getString("writer"));
                rbDto.setRent_date(rs.getTimestamp("rent_date"));
                rbDto.setReturn_date(rs.getTimestamp("return_date"));
                rbDto.setRent_yn(rs.getString("rent_yn"));
                rbDto.setOver_due_m(rs.getString("over_due_m"));
                rb.add(rbDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return rb;
    }

    public int deleteRentBook(int bno){
        String query = "DELETE FROM book_rent WHERE bno = ?;";
        int result = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bno);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
