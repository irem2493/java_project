package ribrary_program;

import dictionary.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryBookDAO {
    Connection conn = DBConn.getConnection();

    public ArrayList<LibraryBookDTO> selectRentBook(String title){
        ArrayList<LibraryBookDTO> rb = new ArrayList<>();
        String query = "SELECT b.bno, b.title, b.writer, r.rent_yn\n" +
                "FROM library_book b, book_rent r\n" +
                "WHERE r.bno = \n" +
                "(SELECT bno FROM library_book WHERE title LIKE ?);";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,"%"+title+"%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                LibraryBookDTO rbDto = new LibraryBookDTO(rs.getString("title"),rs.getString("writer"));
                rbDto.setBno(rs.getInt("bno"));
                rbDto.setRent_yn(rs.getString("rent_yn"));
                rb.add(rbDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rb;
    }

    public int insertBook(LibraryBookDTO rbDto){
        String query = "INSERT INTO library_book(title, writer, enroll_date) VALUES (?,?,NOW());";
        int result = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,rbDto.getTitle());
            pstmt.setString(2,rbDto.getWriter());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateBook(String title, String writer, int bno){
        String query = "UDPATE library_book SET title = ?, writer = ? where bno = ?;";
        int result = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, title);
            pstmt.setString(2,writer);
            pstmt.setInt(3, bno);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
