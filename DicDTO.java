package dictionary;

import quiz_program.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DicDTO {
    Connection conn = DBConn.getConnection();

    public int selectAll() {
        String query = "SELECT * FROM my_dictionary;";
        int find = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                find++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return find;
    }

    Set<String> wrongWord() {
        String query = "SELECT kor_word FROM my_dictionary WHERE remember = 0 ;";
        Set<String> korSet = new HashSet<>();
        int find = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                korSet.add(rs.getString("kor_word"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return korSet;
    }

    Map<String, String> selectAll2(){
        Map<String, String> tmp = new HashMap<>();
        String query = "SELECT kor_word, eng_word FROM my_dictionary;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                tmp.put(rs.getString("kor_word"), rs.getString("eng_word"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public String randKor() {
        Map<String, String> dicKE = selectAll2();
        Set<String> kor_words = dicKE.keySet();
        String[] rd_kor = new String[kor_words.size()];
        int i = 0;
        for(String k : kor_words) {
            rd_kor[i] = k;
            if(i < kor_words.size()) i++;
        }
        Random rd = new Random();
        int rdNum;
        rdNum = rd.nextInt(kor_words.size());
        return rd_kor[rdNum];
    }

    public String randKor2() {
        Set<String> kor_words = wrongWord();
        String[] rd_kor = new String[kor_words.size()];
        int i = 0;
        String returnStr = null;
        for(String k : kor_words) {
            rd_kor[i] = k;
            if(i < kor_words.size()) i++;
        }
        Random rd = new Random();
        int rdNum;
        if(kor_words.size() > 0) {
            rdNum = rd.nextInt(kor_words.size());
            returnStr = rd_kor[rdNum];
        }
        return returnStr;
    }

    public int select(String word) {
        String query = "SELECT eng_word FROM my_dictionary WHERE kor_word = ?;";
        String eng_word = null;
        int find;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, word);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                eng_word = rs.getString("eng_word");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(eng_word != null) {
            System.out.println(word + "의 영어단어는 "+ eng_word + "입니다.");
            find = 1;
        }
        else find = 0;
        return find;
    }

    public int searchEng(String word, String eng) {
        String query = "SELECT eng_word FROM my_dictionary WHERE kor_word = ?;";
        String eng_word = null;
        int find;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, word);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                if(rs.getString("eng_word").equals(eng)) {
                    eng_word = rs.getString("eng_word");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(eng_word != null) {
            updateRemember(eng);
            find = 1;
        }
        else find = 0;
        return find;
    }

    void updateRemember(String word){
        String query = "UPDATE my_dictionary SET remember = 1 WHERE eng_word = ?;";
        int result = 0;
        String alert;
        try(PreparedStatement stmt = conn.prepareStatement(query);){
            stmt.setString(1,word);
            result = stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insert(Map<String, String> dicKE) {
        int result = 0;
        String query = "INSERT INTO my_dictionary(kor_word, eng_word, remember) VALUES (?, ?, 0);";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            Set<String> keys = dicKE.keySet();
            for(String key : keys) {
                stmt.setString(1, key);
                stmt.setString(2, dicKE.get(key));
            }
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
