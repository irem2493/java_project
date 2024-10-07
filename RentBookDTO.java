package ribrary_program;

import java.sql.Timestamp;

public class RentBookDTO {
    private int rno;
    private int bno;
    private String lid;
    private String mid;
    private String rent_yn;
    private String over_due_m;
    private Timestamp rent_date, return_date;

    private String title;
    private String writer;

    public int getRno() {
        return rno;
    }

    public int getBno() {
        return bno;
    }

    public String getLid() {
        return lid;
    }

    public String getMid() {
        return mid;
    }

    public String getRent_yn() {
        return rent_yn;
    }

    public String getOver_due_m() {
        return over_due_m;
    }

    public Timestamp getRent_date() {
        return rent_date;
    }

    public Timestamp getReturn_date() {
        return return_date;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setRent_yn(String rent_yn) {
        this.rent_yn = rent_yn;
    }

    public void setOver_due_m(String over_due_m) {
        this.over_due_m = over_due_m;
    }

    public void setRent_date(Timestamp rent_date) {
        this.rent_date = rent_date;
    }

    public void setReturn_date(Timestamp return_date) {
        this.return_date = return_date;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    
}
