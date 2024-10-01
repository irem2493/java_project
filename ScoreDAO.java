package rocScissorsPaperGame;

import dictionary.DBConn;

import java.sql.*;
import java.util.ArrayList;

public class ScoreDAO {
    Connection conn = DBConn.getConnection();

    //insert 하기 전에 확인용
    public int selectScore(String uid) {
        String query = "SELECT * FROM game_scores WHERE uid = ?;";
        int find = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, uid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) find++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }

    //점수 삽입 함수
    public String insertScore(ScoreDTO scoreDto) {
        String query = "INSERT INTO game_scores(uid, score, win_count, draw_count, lose_count, create_date) VALUES(?,?,?,?,?, NOW());";
        String alert = "insert.반영 실패";
        int result = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, scoreDto.getUid());
            stmt.setInt(2, scoreDto.getScore());
            stmt.setInt(3, scoreDto.getWin_count());
            stmt.setInt(4, scoreDto.getDraw_count());
            stmt.setInt(5, scoreDto.getLose_count());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result > 0) alert = "성적이 성공적으로 반영되었습니다.";
        return alert;
    }

    //점수가 최고점인지 확인하는 함수
    public int selectBestScore(ScoreDTO scoreDto){
        String query = "SELECT * FROM game_scores WHERE uid = ?;";
        int bestScore = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,scoreDto.getUid());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                bestScore = rs.getInt("score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return bestScore;
    }

    //점수 갱신 함수
    public int updateScore(ScoreDTO scoreDto) {
        String query = "UPDATE game_scores SET score = ?, win_count = ?, draw_count = ?, lose_count = ?, update_date = NOW() WHERE uid = ?;";
        int result = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, scoreDto.getScore());
            stmt.setInt(2, scoreDto.getWin_count());
            stmt.setInt(3, scoreDto.getDraw_count());
            stmt.setInt(4, scoreDto.getLose_count());
            stmt.setString(5, scoreDto.getUid());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    //게임 전적 확인 함수
    public ScoreDTO selectGameRecord(String uid) {
        String query = "SELECT score, win_count, draw_count, lose_count FROM game_scores WHERE uid = ?;";
        ScoreDTO scoreDTO = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, uid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                scoreDTO = new ScoreDTO();
                scoreDTO.setUid(uid);
                scoreDTO.setScore(rs.getInt("score"));
                scoreDTO.setWin_count(rs.getInt("win_count"));
                scoreDTO.setDraw_count(rs.getInt("draw_count"));
                scoreDTO.setLose_count(rs.getInt("lose_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreDTO;
    }
    //게임 랭킹 확인 함수
    public ArrayList<ScoreDTO> selectRank() {
        String query = "SELECT uid, score, win_count, draw_count, lose_count, \n"
                + "dense_RANK() OVER (ORDER BY score DESC) AS ranking \n"
                + "FROM game_scores;";

        ScoreDTO scoreDto = null;
        ArrayList<ScoreDTO> rankRecord = new ArrayList<ScoreDTO>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                scoreDto = new ScoreDTO();
                scoreDto.setUid(rs.getString("uid"));
                scoreDto.setScore(rs.getInt("score"));
                scoreDto.setWin_count(rs.getInt("win_count"));
                scoreDto.setDraw_count(rs.getInt("draw_count"));
                scoreDto.setLose_count(rs.getInt("lose_count"));
                rankRecord.add(scoreDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rankRecord;
    }
}
