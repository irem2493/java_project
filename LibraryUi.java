package ribrary_program;

import java.util.Scanner;

public class LibraryUi {
    Scanner sc = new Scanner(System.in);
    LibraryUserDAO libraryUserDao;
    public void intro() {
        System.out.println("도서관 프로그램을 실행합니다.");
    }
    public void showMenu() {
        int menu = -1;
        String id, pw;
        System.out.print("1.로그인, 2.회원가입, 0.종료 (0 ~ 2 사이의 수 입력) : ");
        menu = sc.nextInt();
        if(menu == 1) {
            System.out.println("로그인을 합니다.");
            System.out.print("아이디 입력 : ");
            id = sc.next();
            System.out.print("패스워드 입력 : ");
            pw = sc.next();
            //관리자세요? 회원이세요? 회원로그인, 관리자 로그인
            int result = libraryUserDao.loginUser(id, pw);
            if(result > 0) loginMenu(id);
            else {
                System.out.println("아이디, 패스워드를 확인해주세요.");
                showMenu();
            }
        }else if(menu == 2) {
            System.out.println("회원등록을 합니다.");
            System.out.print("아이디 입력 : ");
            id = sc.next();
            while(libraryUserDao.checkId(id) > 0) {
                System.out.print("이미 등록된 아이디입니다. 아이디 입력 : ");
                id = sc.next();
            }
            System.out.print("패스워드 입력 : ");
            pw = sc.next();
            LibraryUserDTO uDto = new LibraryUserDTO(id, pw);
            System.out.println(libraryUserDao.joinUser(uDto));
            showMenu();
        }else if(menu == 0) {
            System.out.println("게임을 종료합니다.");
        }
        else {
            System.out.println("0 ~ 2 사이의 수 입력해주세요.");
            showMenu();
        }
    }
    void loginMenu(String id){
        int sel = -1;

    }
}
