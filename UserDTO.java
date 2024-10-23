package c0930;

import java.sql.Timestamp;
import java.util.Date;

public class UserDTO {
	static int uno = 0;
    String uid, upw;
    Date join_date, login_date, logout_date;

    public Date getJoin_date() {
        return join_date;
    }

    public UserDTO(String uid, String upw) {
        ++uno;
        this.uid = uid;
        this.upw = upw;
    }
    public int getUno(){return uno;}
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUpw() {
        return upw;
    }
    public void setUpw(String upw) {
        this.upw = upw;
    }
    public Date getLogin_date() {
        return login_date;
    }
    
    public Date getLogout_date() {
        return logout_date;
    }
   
}
