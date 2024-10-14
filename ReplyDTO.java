package c0930;

import java.sql.Timestamp;

public class ReplyDTO {
	private int rno, bno;
    private String rcontents, uid;
    private Timestamp r_create_date;
    private Timestamp r_update_date;

    public ReplyDTO(int bno, String rcontents, String uid){
        this.bno = bno;
        this.rcontents = rcontents;
        this.uid = uid;
    }

    public void setR_create_date(Timestamp r_create_date) {
        this.r_create_date = r_create_date;
    }

    public void setR_update_date(Timestamp r_update_date) {
        this.r_update_date = r_update_date;
    }
    public Timestamp getR_update_date() {
        return r_update_date;
    }

    public Timestamp getR_create_date() {
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
