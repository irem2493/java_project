package ribrary_program;

public class LibraryLibrarianDTO extends LibraryUserDTO{
    private static int lno;
    public LibraryLibrarianDTO() {
        ++lno;
    }

    @Override
    public int getNo() {
        return lno;
    }

    @Override
    public void setNo(int no) {
        this.lno = no;
    }
}
