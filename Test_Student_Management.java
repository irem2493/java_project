package test1;

import java.util.Scanner;

public class Test_Student_Management {
    public static void main(String[] args){
        String name;
        int year, kor_score, eng_score, math_score, gender_subject_score, manu;
        char gender;
        int[] m_counts = {3,2,3};
        int[] f_counts = {2,3,2};
        int[] mCount = new int[3];
        int[] fCount = new int[3];
        int[] years_count = new int[3];

        Scanner sc = new Scanner(System.in);
        Test_Student[] students = new Test_Student[15];
        manu = -1;

        System.out.println("학생 성적 관리 프로그램을 실행합니다.");

        while(manu != 0){
            System.out.print("1.학생등록, 2.성적조회, 0. 프로그램 종료 (0 ~ 2 사이의 수 입력) : ");
            manu = sc.nextInt();
            
        }

    }
}
