package c0924;

public class Jdbc_Student {
	private int no;
	private String name;
	int schoolYear, clazz, sno, korScore, engScore, mathScore;
	public Jdbc_Student(String name, int schoolYear, int clazz, int sno, int korScore, int engScore, int mathScore) {
		this.name = name;
		this.schoolYear = schoolYear;
		this.clazz = clazz;
		this.sno = sno;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
	}
	
	public void setNo(int no) {this.no = no;}
	public int getNo() {return no;}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
	}
	public int getClazz() {
		return clazz;
	}
	public void setClazz(int clazz) {
		this.clazz = clazz;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getKorScore() {
		return korScore;
	}
	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}
	public int getEngScore() {
		return engScore;
	}
	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}
	public int getMathScore() {
		return mathScore;
	}
	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}
	
	public String toString() {
		String str = "번호 : "+no+
				"\n이름 : "+name+
				"\n학년 : "+schoolYear+
				"\n반 : "+clazz+
				"\n번호 : "+sno+
				"\n국어점수 : "+korScore+
				"\n영어 : "+engScore+
				"\n영어점수 : "+mathScore+
				"\n";
		return str;
	}
	
}
