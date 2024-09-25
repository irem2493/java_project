package c0925;

import java.util.Scanner;

import c0924.Jdbc_Student;

public class Jdbc_UI {
	/*사용자 입력처리
	 * 학생정보 입력받기
	 * 입력받은 정보를 Student객체로 만든다.
	 * */
	StudentDAO sDao = new StudentDAO();
	Scanner sc = new Scanner(System.in);
	public void intro() {
		System.out.println("학생DB 프로그램을 실행합니다.");
	}
	
	public Jdbc_Student makeStudent() {
		int schoolYear, clazz, sno, korScore, engScore, mathScore;
		String name;
		System.out.println("학생을 등록합니다.");
		System.out.print("이름 입력 : ");
		name = sc.next();
		System.out.print("학년 입력 : ");
		schoolYear = sc.nextInt();
		System.out.print("반 입력 : ");
		clazz = sc.nextInt();
		System.out.print("번호 입력 : ");
		sno = sc.nextInt();
		System.out.print("국어점수 입력 : ");
		korScore = sc.nextInt();
		System.out.print("영어점수 입력 : ");
		engScore = sc.nextInt();
		System.out.print("수학점수 입력 : ");
		mathScore = sc.nextInt();
		
		Jdbc_Student js = new Jdbc_Student(name, schoolYear, clazz, sno, korScore, engScore, mathScore);
		return js;
	}
	
	public int searchNo() {
		System.out.print("조회를 합니다. 검색할 학생 고유번호를 입력하세요.\n");
		System.out.print("학생 고유번호 입력 : ");
		int no = sc.nextInt();
		return no;
	}
	
	public String searchName() {
		System.out.print("조회를 합니다. 이름을 입력하세요.\n");
		System.out.print("이름 입력 : ");
		String name = sc.next();
		return name;
	}
	
	public int inputSchoolYear() {
		System.out.println("입력된 학년을 삭제합니다.");
		System.out.print("학년 입력 : ");
		int schoolYear = sc.nextInt();
		return schoolYear;
	}
	
	public int inputScore() {
		System.out.print("점수 입력 : ");
		int avgScore = sc.nextInt();
		return avgScore;
	}
	
	public void showMenu() {
		System.out.print("메뉴를 선택하세요. 1.등록, 2.전체 조회, 3.이름 조회, 4.수료기준 수정, 5.학년 삭제, 6.평균 몇점 미만 조회, 0.종료 (0 ~ 4 사이의 수를 입력) : ");
		int menu = sc.nextInt();
		if(menu == 1) {
			System.out.println(sDao.register(makeStudent()));
			showMenu();
		}else if(menu == 2) {
			System.out.println(sDao.selectAll());
			showMenu();
		}else if(menu == 3) {
			System.out.println(sDao.selectName(searchName())); 
			showMenu();
		}else if(menu == 4) {
			System.out.println(sDao.changePassScore());
			System.out.println("수정된 수료기준 점수를 입력해주세요.");
			System.out.println(sDao.updatePass(inputScore()));
			System.out.println(sDao.selectPass());
			showMenu();
		}else if(menu == 5) {
			System.out.println(sDao.deleteSchoolYear(inputSchoolYear()));
			showMenu();
		}else if(menu == 6) {
			System.out.println(sDao.selectAvgUnder(inputScore())); 
			showMenu();
		}else if(menu == 0) System.out.println("학생DB 프로그램을 종료합니다.");
		else {
			System.out.println("0 ~ 6 사이의 수를 입력하세요.");
			showMenu();
		}
	}
	
}
