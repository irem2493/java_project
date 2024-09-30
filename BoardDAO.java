package board_user_management;

import dictionary.DBConn;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardDAO {
    Connection conn = DBConn.getConnection();

    public ArrayList<BoardDTO> selectBoard() {
        String query = "SELECT bno, title, uid, create_date FROM boardTable;";
        ArrayList<BoardDTO> boardList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                BoardDTO board = new BoardDTO(rs.getString("title"), rs.getString("uid"));
                board.setBno(rs.getInt("bno"));
                board.setCreateDate(rs.getTimestamp("create_date"));
                boardList.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardList;
    }

    public BoardDTO selectBoardContents(int bno){
        String query = "SELECT bno, title, contents, uid, create_date FROM boardTable WHERE bno = ?;";
        BoardDTO boardDTO = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,bno);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                boardDTO = new BoardDTO(rs.getString("title"), rs.getString("uid"));
                boardDTO.setBno(rs.getInt("bno"));
                boardDTO.setContents(rs.getString("contents"));
                boardDTO.setCreateDate(rs.getTimestamp("create_date"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return boardDTO;
    }

    public int insertBoard(BoardDTO boardDTO){
        int result = 0;
        String query = "INSERT INTO boardTable(bno, title, contents, uid, create_date) VALUES(NULL, ?,?,?,?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, boardDTO.getUid());
            stmt.setString(2, boardDTO.getTitle());
            stmt.setString(3,boardDTO.getContents());
            stmt.setTimestamp(4,boardDTO.getCreateDate());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
