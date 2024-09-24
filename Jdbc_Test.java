package jdbc_test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Jdbc_Test {
    static String driverName = "org.mariadb.jdbc.Driver";
    static String url = "jdbc:mariadb://localhost:3306/mydb";
    static String uid = "root";
    static String upw = "1234";
    static Connection conn = null;
    static PreparedStatement stmt = null;
    public static void main(String[] args) {Scanner sc = new Scanner(System.in);
        String name;
        int schoolYear, clazz, sno, korScore, engScore, mathScore, manu;
        manu = -1;
        while(manu != 0) {
            System.out.print("1.학생등록, 2.학년조회, 0.종료 (0 ~ 2사이의 수 입력) : ");
            manu = sc.nextInt();
            if(manu == 1) {
                System.out.print("이름 : ");
                name = sc.next();
                System.out.print("학년 : ");
                schoolYear = sc.nextInt();
                System.out.print("반 : ");
                clazz = sc.nextInt();
                System.out.print("학번 : ");
                sno = sc.nextInt();
                System.out.print("국어점수 : ");
                korScore = sc.nextInt();
                System.out.print("영어점수 : ");
                engScore = sc.nextInt();
                System.out.print("수학점수 : ");
                mathScore = sc.nextInt();

                    Jdbc_Student student = new Jdbc_Student(name, schoolYear, clazz, sno, korScore, engScore, mathScore);
                    insert(student);


            }else if(manu == 2){
                System.out.print("학년 : ");
                schoolYear = sc.nextInt();
                ArrayList<Jdbc_Student> sList = select(schoolYear);
                for(Jdbc_Student student : sList) {
                    System.out.println("----------------");
                    System.out.println(student);
                }
            }
        }
    }
    static void insert(Jdbc_Student s) {
        try {
            Class.forName(driverName);
            //1. DB연동
            conn = DriverManager.getConnection(url, uid, upw);
            String qeury = "INSERT INTO student VALUES(null, ?,?,?,?,?,?,?);";
            stmt = conn.prepareStatement(qeury);
            stmt.setString(1, s.getName());
            stmt.setInt(2, s.getSchoolYear());
            stmt.setInt(3, s.getClazz());
            stmt.setInt(4, s.getSno());
            stmt.setInt(5, s.getKorScore());
            stmt.setInt(6, s.getEngScore());
            stmt.setInt(7, s.getMathScore());
            int result = stmt.executeUpdate();
            if (result > 0) System.out.println("삽입 성공");
            else System.out.println("삽입 실패");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static ArrayList<Jdbc_Student> select(int schoolYear){
        ArrayList<Jdbc_Student> sList = new ArrayList<>();
        ResultSet rs = null;
        String qeury = "SELECT * FROM student where schoolYear = ?";
        try {
            stmt = conn.prepareStatement(qeury);
            stmt.setInt(1,schoolYear);
            rs = stmt.executeQuery();
            while(rs.next()){
                Jdbc_Student s = new Jdbc_Student(rs.getString("name"), rs.getInt("schoolYear"), rs.getInt("class"),
                        rs.getInt("sno"),rs.getInt("korScore"), rs.getInt("engScore"), rs.getInt("mathScore"));
                s.setNo(rs.getInt("no"));
                sList.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return sList;
    }


}
