package c1016;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Diary {
	private LocalDateTime today;
	private String comment;
	
	public Diary(String comment) {
		this.comment = comment;
		today = LocalDateTime.now();
	}
	public String getTitle() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return dtf.format(today);
	}
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String date = dtf.format(today);
		System.out.println(date + "\n\n" + comment);
		return date + "\n\n" + comment;
				
	}
	
	
	
}
