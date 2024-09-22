package test1;

public abstract class Test_Student {
    /*
     * 학생은 번호, 이름, 성별, 학년, 국어, 영어, 수학점수, 등급으로 구성된다.				    --> 완료
     * 남학생 전용과목은 기술, 여학생 전용과목은 가정 과목이 있다.							--> 완료
     * 학년은 1, 2, 3학년이 존재한다.
     *
     * 각 학년은 5명 씩이다.
     * 1학년 남 : 3명, 여 : 2명,
     * 2학년 남 : 2명, 여 : 3명,
     * 3학년 남 : 3명, 여 : 2명
     *
     * 등급은 A, B, C, D, F로 구분된다.												---> 완료
     * 학생 번호는 자동으로 부여된다.													---> 완료
     * 학생 번호는 s로 시작되며 s1, s2, s3...식으로 부여된다.							    ---> 완료
     * 학생 성적처리 시스템은 학생의 성적을 평균으로 구하고 평균 점수에 따라
     * 90점 이상 A, 80점 이상 B, 70점 이상 C, 60점 이상 D, 60점 미만 F로 구분한다.		    ---> 완료
     * 본 시스템은 학생등록, 성적조회가 가능해야 한다.
     * 성적조회는 성별, 학년별 조회가 제공되며/ 학년별, 성별 60점 미만 학생 수를 조회할 수 있어야 한다.
     */

    private static int no;
    private String sno;
    private String name;
    private int year;
    private int kor_score, eng_score, math_score;

    public Test_Student(String name, int year, char gender, int kor_score, int eng_score, int math_score) {
        no++;
        sno = "s"+no;
        this.name = name;
        this.year = year;
        this.kor_score = kor_score;
        this.eng_score = eng_score;
        this.math_score = math_score;
    }

    public String getSno() {return sno;}
    public int getYear() {return year;}
    public abstract char getGender();
    public abstract void setGenderSub(int gender_subject_score);
    public abstract void decideGrade();
    public abstract void calcAvg(int kor_score, int eng_score, int math_score, int gender_subject_score);
}
