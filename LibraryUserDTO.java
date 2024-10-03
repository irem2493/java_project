package ribrary_program;

import java.sql.Timestamp;

public class LibraryUserDTO {

    private static int no;
    private String id, pw;
    private Timestamp join_date, update_date;

    public LibraryUserDTO() {
        ++no;
    }
    public static int getNo() {
        return no;
    }

    public static void setNo(int no) {
        LibraryUserDTO.no = no;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Timestamp getJoin_date() {
        return join_date;
    }
    public Timestamp getUpdate_date() {
        return update_date;
    }
    public void setJoin_date(Timestamp join_date) {
        this.join_date = join_date;
    }

    public void setUpdate_date(Timestamp update_date) {
        this.update_date = update_date;
    }
}
