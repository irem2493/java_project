package student_management_program;

import java.util.Scanner;

public class Student_Management_Program {
    public static void main(String[] args){
        String name, tel, birthday;
        int gender, kor_score, eng_score, math_score;
        Scanner sc = new Scanner(System.in);
        System.out.print("학생 이름 : ");
        name = sc.next();
        System.out.print("학생 전화번호 : ");
        tel = sc.next();
        System.out.print("학생 생년월일(6자리) : ");
        birthday = sc.next();
        System.out.print("학생 성별(남자 - 1 또는 3, 여자 -2 또는 4) : ");
        gender = sc.nextInt();

        Student student = new Student(name, tel, birthday, gender);
        student.printInfo();

        System.out.print("학생의 국어점수 : ");
        kor_score = sc.nextInt();
        System.out.print("학생의 영어점수 : ");
        eng_score = sc.nextInt();
        System.out.print("학생의 수학점수 : ");
        math_score = sc.nextInt();
        student.inputKEM_Score(kor_score, eng_score, math_score);
        student.printInfo();
    }
}
