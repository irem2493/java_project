package ribrary_program;

public abstract class LibraryUserDAO {

    public abstract String joinUser(LibraryUserDTO user);
    public abstract int checkId(String id);
    public abstract int loginUser(String id, String pw);

}
