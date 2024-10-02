package c1001;

import java.sql.Timestamp;

public class GameUserDTO {
	private static int uno;
    private String uid, upw;
    private Timestamp join_date;

    public GameUserDTO(String uid, String upw) {
        ++uno;
        this.uid = uid;
        this.upw = upw;
    }

    public int getUno() {return uno;}

    public String getUid() {
        return uid;
    }

    public Timestamp getJoin_date() {return join_date;}

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpw() {
        return upw;
    }

    public void setUpw(String upw) {
        this.upw = upw;
    }
}
