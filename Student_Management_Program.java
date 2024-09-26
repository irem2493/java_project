package student_management_program;

import java.util.Scanner;

public class Student_Management_Program {
    public static void main(String[] args){
        String name, tel, birthday;
        int gender, kor_score, eng_score, math_score, menu;
        StudentArray students = new StudentArray();
        Scanner sc = new Scanner(System.in);
        menu = -1;
        while(menu != 0){
            System.out.print("1.학생 등록, 2.모든 학생 조회, 0.종료 (0 ~ 2 사이의 수 입력) : ");
            menu = sc.nextInt();
            if(menu == 1){
                System.out.print("학생 이름 : ");
                name = sc.next();
                System.out.print("학생 전화번호 : ");
                tel = sc.next();
                System.out.print("학생 생년월일(6자리) : ");
                birthday = sc.next();
                System.out.print("학생 성별(남자 - 1 또는 3, 여자 -2 또는 4) : ");
                gender = sc.nextInt();
                Student s = new Student(name, tel, birthday, gender);
                students.add(s);

                System.out.print("학생의 국어점수 : ");
                kor_score = sc.nextInt();
                System.out.print("학생의 영어점수 : ");
                eng_score = sc.nextInt();
                System.out.print("학생의 수학점수 : ");
                math_score = sc.nextInt();
                students.get(0).inputKEM_Score(kor_score, eng_score, math_score);
                System.out.println("학생 정보가 등록되었습니다.");
                menu = -1;
            }else if(menu == 2){
                System.out.println("모든 학생 정보를 조회합니다.");
                students.getAll();
                menu = -1;
            }
        }

    }
}
