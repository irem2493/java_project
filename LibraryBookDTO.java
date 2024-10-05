package ribrary_program;


import java.sql.Timestamp;

public class LibraryBookDTO {

    private int bno;
    private String title;
    private String writer;
    private Timestamp enroll_date, b_update_date;
    private String rent_yn;

    public LibraryBookDTO(String title, String writer ){
        this.title = title;
        this.writer = writer;
    }
    public int getBno() {
        return bno;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public Timestamp getEnroll_date() {
        return enroll_date;
    }

    public Timestamp getB_update_date() {
        return b_update_date;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setEnroll_date(Timestamp enroll_date) {
        this.enroll_date = enroll_date;
    }

    public void setB_update_date(Timestamp b_update_date) {
        this.b_update_date = b_update_date;
    }

    public String toString(){
        return "책 번호 : " + bno + "\n"+
                "책 이름 : " + title + "\n"+
                "책 저자 : " + writer + "\n"+
                "대여 여부 : " + rent_yn;
    }

    public String getRent_yn() {
        return rent_yn;
    }
    public void setRent_yn(String rent_yn) {
        this.rent_yn = rent_yn;
    }
}
