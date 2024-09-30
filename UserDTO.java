package board_user_management;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserDTO {
    static int uno = 0;
    String uid, upw;
    Timestamp join_date, login_date, logout_date;

    public Timestamp getJoin_date() {
        return join_date;
    }

    public UserDTO(String uid, String upw) {
        ++uno;
        this.uid = uid;
        this.upw = upw;
        LocalDateTime dateTime = LocalDateTime.now();
        join_date = Timestamp.valueOf(dateTime);
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
    public Timestamp getLogin_date() {
        return login_date;
    }
    public void setLogin_date(Timestamp login_date) {
        this.login_date = login_date;
    }
    public Timestamp getLogout_date() {
        return logout_date;
    }
    public void setLogout_date(Timestamp logout_date) {
        this.logout_date = logout_date;
    }
}
