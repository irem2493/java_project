package ribrary_program;

import dictionary.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryBookDAO {
    Connection conn = DBConn.getConnection();

    public ArrayList<LibraryBookDTO> selectBook(String title){
        ArrayList<LibraryBookDTO> rb = new ArrayList<>();
        String query = "SELECT bno, title, writer FROM library_book WHERE title like ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,"%"+title+"%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                LibraryBookDTO rbDto = new LibraryBookDTO(rs.getString("title"),rs.getString("writer"));
                rbDto.setBno(rs.getInt("bno"));
                rb.add(rbDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rb;
    }
}
