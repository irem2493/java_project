package test1;

public class M_Student extends Test_Student{
    private static int mNum;
    private String gender_subject;
    private int gender_subject_score;
    private char grade;
    private double avg;
    public M_Student(String name, int year, char gender, int kor_score, int eng_score, int math_score) {
        super(name, year, gender, kor_score, eng_score, math_score);
        gender_subject = "기술";
        mNum++;
    }
    public void setGenderSub(int gender_subject_score) {this.gender_subject_score = gender_subject_score;}
    public void calcAvg(int kor_score, int eng_score, int math_score, int gender_subject_score) {
        int sum = kor_score + eng_score + math_score + gender_subject_score;
        avg = sum / 4.0;
        decideGrade();
    }
    public void decideGrade() {
        if(avg >= 90) grade = 'A';
        else if(avg >= 80) grade = 'B';
        else if(avg >= 70) grade = 'C';
        else if(avg >= 60) grade = 'D';
        else grade = 'F';
    }
    public void printInfo(){
        System.out.println("-----------------------------------");
        System.out.println("이름 : " + super.getName());
        System.out.println("학년 : " + super.getYear());
        System.out.println("성별 : " + getGender());
        System.out.println("국어점수 : " + super.getKor_score());
        System.out.println("영어점수 : " + super.getEng_score());
        System.out.println("수학점수 : " + super.getMath_score());
        System.out.println("기술점수 : " + gender_subject_score);
        calcAvg(super.getKor_score(), super.getEng_score(), super.getMath_score(), gender_subject_score);
        System.out.println("평균 : " + avg);
        System.out.println("학점 : " + grade);
    }
    @Override
    public char getGender() {
        return 'M';
    }

    public double getAvg(){
        return avg;
    }

    public int getCount() {
        return mNum;
    }
}
