package c0930;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import c0925.DBConn;

public class BoardDAO {
	 Connection conn = DBConn.getConnection();

	    public ArrayList<BoardDTO> selectBoard() {
	        String query = "SELECT bno, title, uid, DATE(create_date) create_date FROM boardTable;";
	        ArrayList<BoardDTO> boardList = new ArrayList<>();
	        try {
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            while(rs.next()) {
	                BoardDTO board = new BoardDTO(rs.getString("title"), rs.getString("uid"));
	                board.setBno(rs.getInt("bno"));
	                board.setCreateDate(rs.getDate("create_date"));
	                boardList.add(board);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return boardList;
	    }

	    public BoardDTO selectBoardContents(int bno){
	        String query = "SELECT bno, title, contents, uid, DATE(create_date) create_date FROM boardTable WHERE bno = ?;";
	        BoardDTO boardDTO = null;
	        try {
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setInt(1,bno);
	            ResultSet rs = stmt.executeQuery();
	            while(rs.next()){
	                boardDTO = new BoardDTO(rs.getString("title"), rs.getString("uid"));
	                boardDTO.setBno(rs.getInt("bno"));
	                boardDTO.setContents(rs.getString("contents"));
	                boardDTO.setCreateDate(rs.getDate("create_date"));
	            }

	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        return boardDTO;
	    }

	    public int insertBoard(BoardDTO boardDTO){
	        int result = 0;
	        String query = "INSERT INTO boardTable(bno, uid, title, contents, create_date) VALUES(NULL, ?,?,?,NOW());";

	        try {
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, boardDTO.getUid());
	            stmt.setString(2, boardDTO.getTitle());
	            stmt.setString(3,boardDTO.getContents());
	            result = stmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	        return result;
	    }

	    //댓글을 단 아이디가 맞는지 확인하는 함수
	    public int rightMember(int bno, String uid) {
	        String query = "SELECT uid FROM boardtable WHERE bno = ?;";
	        int result = 0;
	        try {
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            pstmt.setInt(1,bno);
	            ResultSet rs = pstmt.executeQuery();
	            while(rs.next()) {
	                if(rs.getString("uid").equals(uid)) result = 1;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }

	    //게시물 내용 수정
	    public int updateBoard(String contents, String uid, int bno){
	        int result = 0;
	        String query = "UPDATE boardtable SET contents = ?, modify_date = NOW() WHERE uid = ? and bno = ?;";
	        try {
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, contents);
	            pstmt.setString(2, uid);
	            pstmt.setInt(3, bno);
	            result = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	    
	    //게시판 내용 삭제
	    public int deleteBoard(String uid, int bno) {
	    	int result = 0;
	    	String query = "DELETE FROM boardtable WHERE uid = ? and bno = ?;";
	    	try {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, uid);
	            pstmt.setInt(2, bno);
	            result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return result;
	    }
}
