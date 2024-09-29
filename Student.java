package student_management_program;

import java.time.LocalDate;

public class Student {
    //학생 관리 프로그램을 만듭니다.
    static int no = 0;
    String sno;
    String name;
    String tel;
    String gender;
    int year;
    int month;
    int day;
    int age;
    int kor_score;
    int eng_score;
    int math_score;
    double avg;
    int rank;

    LocalDate now = LocalDate.now();
    int nowYear = now.getYear();

    public Student(String name, String tel, String birthday, int gender){
        no++;
        sno = nowYear + String.format("%04d",no);
        this.name = name;
        this.tel = tel;
        decideYMD_Age(birthday, gender);
        decideGender(gender);
    }
    //순위 setter
    public void setRank(int rank){
        this.rank = rank;
    }
    //생년월일 함수
    public void decideYMD_Age(String birthday, int gender){
        if(gender == 1 || gender == 2) year = Integer.parseInt("19"+birthday.substring(0,2));
        else if(gender == 3 || gender == 4) year = Integer.parseInt("20"+birthday.substring(0,2));
        month = Integer.parseInt(birthday.substring(2,4));
        day = Integer.parseInt(birthday.substring(4,6));
        age = nowYear - year;
    }
    // 성별 함수
    public void decideGender(int gender){
        if(gender == 1 || gender == 3) this.gender = "남자";
        else if(gender == 2 || gender == 4) this.gender = "여자";
        else this.gender = "";
    }
    //국어, 영어, 수학 점수 입력
    public void inputKEM_Score(int kor_score, int eng_score, int math_score){
        this.kor_score = kor_score;
        this.eng_score = eng_score;
        this.math_score = math_score;
        avg = calcAvg(kor_score, eng_score, math_score);
    }
    //평균 계산
    public double calcAvg(int kor_score, int eng_score, int math_score){
        int sum = kor_score + eng_score + math_score;
        double avg = sum / 3.0;
        return avg;
    }
    //학생 정보 출력 함수
    public void printInfo(){
        System.out.println("---------------------");
        System.out.println("학번 : "+sno);
        System.out.println("이름 : " + name);
        System.out.println("전화번호 : " + tel);
        System.out.println("생년월일 : "+ year + "년 " + month + "월 " + day + "일");
        System.out.println("나이 : " + age);
        System.out.println("학생의 국어점수 : " + kor_score);
        System.out.println("학생의 영어점수 : " + eng_score);
        System.out.println("학생의 수학점수 : " + math_score);
        System.out.println("학생의 평균 : " + avg);
    }
    //학생 순위 정보 출력함수
    public void printRank(){
        printInfo();
        System.out.println("학생의 순위 : " + rank);
    }
}
