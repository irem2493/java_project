package test1;

import java.util.Scanner;

public class Test_Student_UI {
    private int manu = -1;
    private String name;
    private int year, kor_score, eng_score, math_score, gender_subject_score, s_count, sel;
    private char gender;
    private int m_under60, f_under60;

    private int[] m_counts = {3,2,3};
    private int[] f_counts = {2,3,2};
    private int[] mCount = new int[3];
    private int[] fCount = new int[3];
    private int[] years_count = new int[3];
    private int[] years_under60 = new int[3];
    StudentScoreManagement scoreManagement = new StudentScoreManagement();
    Scanner sc = new Scanner(System.in);

    public void intro(){
        System.out.println("학생 성적 관리 프로그램을 실행합니다.");
    }
    public void showManu() {
        manu = -1;
        while(manu != 0){
            System.out.print("1.학생등록, 2.성적조회, 0. 프로그램 종료 (0 ~ 2 사이의 수 입력) : ");
            manu = sc.nextInt();
            if(manu == 1){
                if(scoreManagement.getArrSize() < 15){
                    System.out.print("이름 : ");
                    name = sc.next();
                    System.out.print("학년 (1 ~ 3 사이의 수 입력) : ");
                    year = sc.nextInt();
                    while(!(year >= 1 && year <= 3)) {
                        System.out.print("학년 (1 ~ 3 사이의 수 입력) : ");
                        year = sc.nextInt();
                    }
                    System.out.print("성별 (M 또는 F입력) : ");
                    gender = sc.next().charAt(0);
                    while(gender != 'M' && gender != 'F') {
                        System.out.println("성별은 M 또는 F를 입력해주세요.");
                        System.out.print("성별 (M 또는 F입력) : ");
                        gender = sc.next().charAt(0);
                    }
                    for(int i = 0; i < years_count.length; i++){
                        if(year-1 == i && years_count[year-1] < 6){
                            if(gender == 'M' && mCount[i] < m_counts[i]){
                                System.out.print("국어점수 : ");
                                kor_score = sc.nextInt();
                                System.out.print("영어점수 : ");
                                eng_score = sc.nextInt();
                                System.out.print("수학점수 : ");
                                math_score = sc.nextInt();
                                M_Student m = new M_Student(name, year, gender, kor_score, eng_score, math_score);
                                System.out.print("기술점수 : ");
                                gender_subject_score = sc.nextInt();
                                m.setGenderSub(gender_subject_score);
                                m.calcAvg(kor_score, eng_score, math_score,gender_subject_score);
                                scoreManagement.register(m);
                                years_count[year-1]++;
                                mCount[i]++;
                                showManu();
                            }else if(gender == 'F' && fCount[i] < f_counts[i]){
                                System.out.print("국어점수 : ");
                                kor_score = sc.nextInt();
                                System.out.print("영어점수 : ");
                                eng_score = sc.nextInt();
                                System.out.print("수학점수 : ");
                                math_score = sc.nextInt();
                                F_Student f = new F_Student(name, year, gender, kor_score, eng_score, math_score);
                                System.out.print("가정점수 : ");
                                gender_subject_score = sc.nextInt();
                                f.setGenderSub(gender_subject_score);
                                f.calcAvg(kor_score, eng_score, math_score,gender_subject_score);
                                scoreManagement.register(f);
                                years_count[year-1]++;
                                fCount[i]++;
                                showManu();
                            }else {
                                System.out.println("현재 "+year + "학년의 남학생 수는 " + mCount[i] + "명, 여힉생의 수는 " + fCount[i]+"명 입니다. " +
                                        + year + "학년의 남학생 정원은 " + m_counts[i]+"명이고, 여학생 정원은 " +  f_counts[i]+"명입니다.");
                                showManu();
                            }
                        }
                    }
                }else System.out.println("더 이상 학생을 등록할 수 없습니다.");
            }else if(manu == 2){
                System.out.print("1.성별 성적 조회, 2.학생별 성적 조회(1 ~ 2 사이의 수 입력) : ");
                sel = sc.nextInt();
                while(!(sel >= 1 && sel <= 2)) {
                    System.out.print("학년 (1 ~ 2 사이의 수 입력) : ");
                    sel = sc.nextInt();
                }
                if(sel == 1) {
                    System.out.print("성별 (M 또는 F입력) : ");
                    gender = sc.next().charAt(0);
                    while (gender != 'M' && gender != 'F') {
                        System.out.println("성별은 M 또는 F를 입력해주세요.");
                        System.out.print("성별 (M 또는 F입력) : ");
                        gender = sc.next().charAt(0);
                    }
                    if (gender == 'M' && !(scoreManagement.getStudentList().isEmpty()) && scoreManagement.getStudentList().get(0).getCount() > 0) {
                        scoreManagement.getGender('M');
                        for(int i = 0; i < scoreManagement.getArrSize(); i++){
                            if(scoreManagement.getStudentList().get(i).getGender() == 'M' ){
                                if(scoreManagement.getStudentList().get(i).getAvg() < 60) m_under60++;
                            }
                        }System.out.println("남학생 중 평균이 60점 미만인 학생 수는 " + m_under60 + "명입니다.");
                        showManu();
                    } else if (gender == 'F' && !(scoreManagement.getStudentList().isEmpty()) && scoreManagement.getStudentList().get(0).getCount() > 0) {
                        scoreManagement.getGender('F');
                        for(int i = 0; i < scoreManagement.getArrSize(); i++){
                            if(scoreManagement.getStudentList().get(i).getGender() == 'F' ){

                                if(scoreManagement.getStudentList().get(i).getAvg() < 60) f_under60++;
                            }
                        }System.out.println("여학생 중 평균이 60점 미만인 학생 수는 " + f_under60 + "명입니다.");
                        showManu();
                    }else {
                        System.out.println("조회할 성별의 데이터가 없습니다.");
                        showManu();
                    }
                }else if(sel == 2){
                    System.out.print("학년 (1 ~ 3 사이의 수 입력) : ");
                    year = sc.nextInt();
                    while (!(year >= 1 && year <= 3)) {
                        System.out.print("학년 (1 ~ 3 사이의 수 입력) : ");
                        year = sc.nextInt();
                    }
                    if (years_count[year - 1] > 0) {
                        for (int i = 0; i < scoreManagement.getArrSize(); i++) {
                            if (year == scoreManagement.getStudentList().get(i).getYear()) {
                                scoreManagement.getStudentList().get(i).printInfo();
                                if (scoreManagement.getStudentList().get(i).getAvg() < 60) years_under60[year - 1]++;
                            }
                        }
                        System.out.println(year + "학년 중 평균이 60점 미만인 학생 수는 " + years_under60[year - 1] + "명입니다.");
                        showManu();
                    } else {
                        System.out.println("조회할 " + year + "학년의 데이터가 없습니다.");
                        showManu();
                    }
                }
            }
        }
        System.out.println("학생 성적 관리 프로그램을 종료합니다.");
    }
}
