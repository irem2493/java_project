package student_management_program;

public class Student {
    //학생 관리 프로그램을 만듭니다.
    static int no;
    int sno;
    String name;
    String tel;
    char gender;
    int age;
    int birthday;
    int kor_score;
    int eng_score;
    int math_score;
    double avg;
    int rank;

    public Student(String name, String tel, int birthday){
        no++;
        sno = no;
        this.name = name;
        this.tel = tel;

    }
}
