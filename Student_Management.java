package c0924;

import java.util.ArrayList;
import java.util.Scanner;

public class Student_Management {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name;
		int schoolYear, clazz, sno, korScore, engScore, mathScore, manu;
		Jdbc_Student_Management jdbc_s = new Jdbc_Student_Management();
		ArrayList<Jdbc_Student> studentList = new ArrayList<>();
		manu = -1;
		while(manu != 0) {
			System.out.print("1.등록, 2.조회, 0.종료 (0 ~ 2까지의 수 입력): ");
			manu = sc.nextInt();
			if(manu == 1) {
				System.out.print("이름 입력 : ");
				name = sc.next();
				System.out.print("학년 입력 : ");
				schoolYear = sc.nextInt();
				System.out.print("반 입력 : ");
				clazz = sc.nextInt();
				System.out.print("학번 입력 : ");
				sno = sc.nextInt();
				System.out.print("국어점수 입력 : ");
				korScore = sc.nextInt();
				System.out.print("영어점수 입력 : ");
				engScore = sc.nextInt();
				System.out.print("수학점수 입력 : ");
				mathScore = sc.nextInt();
				
				studentList.add(new Jdbc_Student(name, schoolYear, clazz, sno, korScore, engScore, mathScore));
				jdbc_s.insert(studentList);
			}else if(manu == 2) {
				//조회
				System.out.print("학년 입력 : ");
				schoolYear = sc.nextInt();
				ArrayList<Jdbc_Student> sList = jdbc_s.select(schoolYear);
				for(Jdbc_Student s : sList) {
					System.out.println("-------------------");
					System.out.println(s);
				}
				
			}
		}
	}
}
