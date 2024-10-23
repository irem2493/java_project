package board_user_management;


import java.sql.Date;

public class BoardDTO {
    private int bno;
    private String title, contents, uid;
    private Date create_date;

    public BoardDTO(String title, String uid) {
        this.title = title;
        this.uid = uid;
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

    public Date getCreateDate() {
        return create_date;
    }

    public void setCreateDate(Date create_date) {
        this.create_date = create_date;
    }
}
