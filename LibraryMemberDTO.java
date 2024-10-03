package ribrary_program;

public class LibraryMemberDTO extends LibraryUserDTO{

    private static int mno;
    public LibraryMemberDTO() {
        ++mno;
    }

    @Override
    public int getNo() {
        return mno;
    }

    @Override
    public void setNo(int no) {
        this.mno = no;
    }
}
