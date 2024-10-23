package c0930;

import java.util.Date;

public class ReplyDTO {
	private int rno, bno;
    private String rcontents, uid;
    private Date r_create_date;

    public ReplyDTO(int bno, String rcontents, String uid){
        this.bno = bno;
        this.rcontents = rcontents;
        this.uid = uid;
    }

    public void setR_create_date(Date r_create_date) {
        this.r_create_date = r_create_date;
    }

    public Date getR_create_date() {
        return r_create_date;
    }
    public int getRno() {
        return rno;
    }

    public int getBno() {
        return bno;
    }

    public String getRcontents() {
        return rcontents;
    }

    public String getUid() {
        return uid;
    }
    public void setRno(int rno) {
        this.rno = rno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public void setRcontents(String rcontents) {
        this.rcontents = rcontents;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
