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
        sno = nowYear+""+no;
        this.name = name;
        this.tel = tel;
        decideYMD_Age(birthday, gender);
        decideGender(gender);
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
    //학생 정보 출력 함수
    public void printInfo(){
        System.out.println("학번 : "+sno);
        System.out.println("이름 : " + name);
        System.out.println("전화번호 : " + tel);
        System.out.println("생년월일 : "+ year + "년 " + month + "월 " + day + "일");
        System.out.println("나이 : " + age);
    }
}
