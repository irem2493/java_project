package test1;

public class F_Student extends Test_Student{

    String gender_subject;
    int gender_subject_score;
    char grade;
    double avg;
    public F_Student(String name, int year, char gender, int kor_score, int eng_score, int math_score) {
        super(name, year, gender, kor_score, eng_score, math_score);
        gender_subject = "가정";
    }

    public void setGenderSub(int gender_subject_score) {this.gender_subject_score = gender_subject_score;}
    public void calcAvg(int kor_score, int eng_score, int math_score, int gender_subject_score) {
        int sum = kor_score + eng_score + math_score + gender_subject_score;
        avg = sum / 4.0;
    }
    public void decideGrade() {
        if(avg >= 90) grade = 'A';
        else if(avg >= 80) grade = 'B';
        else if(avg >= 70) grade = 'C';
        else if(avg >= 60) grade = 'D';
        else grade = 'F';
    }

    @Override
    public char getGender() {
        return 'F';
    }
}
