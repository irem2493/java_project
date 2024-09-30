package board_user_management;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BoardDTO {
    int bno;
    String title, contents, uid;
    Timestamp join_date;

    public BoardDTO(String title, String uid) {
        this.title = title;
        this.uid = uid;
        LocalDateTime dateTime = LocalDateTime.now();
        join_date = Timestamp.valueOf(dateTime);
    }
    public void setBno(int bno){this.bno = bno;}
    public int getBno(){return bno;}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Timestamp getCreateDate() {
        return join_date;
    }

    public void setCreateDate(Timestamp join_date) {
        this.join_date = join_date;
    }
}
