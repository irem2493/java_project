package jdbc_test;

public class Jdbc_Student {
    int no;
    int schoolYear;
    int clazz;
    int sno;
    int korScore;
    int engScore;
    int mathScore;
    String name;
    Jdbc_Student(String name, int schoolYear, int clazz, int sno, int korScore, int engScore, int mathScore){
        this.name = name;
        this.schoolYear = schoolYear;
        this.clazz = clazz;
        this.sno = sno;
        this.korScore = korScore;
        this.engScore = engScore;
        this.mathScore = mathScore;
    }
    public int getNo() {
        return no;
    }
    public int getSchoolYear() {
        return schoolYear;
    }

    public int getClazz() {
        return clazz;
    }

    public int getSno() {
        return sno;
    }

    public int getKorScore() {
        return korScore;
    }

    public int getEngScore() {
        return engScore;
    }

    public int getMathScore() {
        return mathScore;
    }

    public String getName() {
        return name;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public void setKorScore(int korScore) {
        this.korScore = korScore;
    }

    public void setEngScore(int engScore) {
        this.engScore = engScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public void setName(String name) {
        this.name = name;
    }
}
