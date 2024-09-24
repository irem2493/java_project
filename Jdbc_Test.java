package jdbc_test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Jdbc_Test {
    static ArrayList<Jdbc_Student> studentList = new ArrayList<>();
    public static void main(String[] args) {
        String driverName = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://localhost:3306/mydb";
        String uid = "root";
        String upw = "1234";

        Scanner sc = new Scanner(System.in);
        String name, qeury;
        int schoolYear, clazz, sno, korScore, engScore, mathScore, manu;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        manu = -1;
        while(manu != 0) {
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
                try {
                    Class.forName(driverName);
                    //1. DB연동
                    conn = DriverManager.getConnection(url, uid, upw);
                    qeury = "INSERT INTO student VALUES(null, ?,?,?,?,?,?,?);";
                    stmt = conn.prepareStatement(qeury);
                    stmt.setString(1, name);
                    stmt.setInt(2, schoolYear);
                    stmt.setInt(3, clazz);
                    stmt.setInt(4, sno);
                    stmt.setInt(5, korScore);
                    stmt.setInt(6, engScore);
                    stmt.setInt(7, mathScore);
                    Jdbc_Student student = new Jdbc_Student(name, schoolYear, clazz, sno, korScore, engScore, mathScore);
                    insert(student);
                    int result = stmt.executeUpdate();
                    if (result > 0) System.out.println("삽입 성공");
                    else System.out.println("삽입 실패");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else if(manu == 2){
                System.out.print("학년 : ");
                schoolYear = sc.nextInt();
                qeury = "SELECT * FROM student where schoolYear = ?";
                try {
                    stmt = conn.prepareStatement(qeury);
                    stmt.setInt(1,schoolYear);
                    rs = stmt.executeQuery();
                    while(rs.next()){
                        Jdbc_Student s = new Jdbc_Student(rs.getString("name"), rs.getInt("schoolYear"), rs.getInt("class"),
                                rs.getInt("sno"),rs.getInt("korScore"), rs.getInt("engScore"), rs.getInt("mathScore"));
                        s.setNo(rs.getInt("no"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        }
    }
    static void insert(Jdbc_Student s) {
        studentList.add(s);
    }
    static ArrayList<Jdbc_Student> select(int schoolYear){
        ArrayList<Jdbc_Student> sList = new ArrayList<>();
        for(Jdbc_Student s : studentList){
            if(s.getSchoolYear() == schoolYear) sList.add(s);
        }return sList;
    }


}
