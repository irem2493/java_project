package ribrary_program;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class LibraryUi {
    Scanner sc = new Scanner(System.in);
    LibraryUserDAO libraryUserDao = null;
    LibraryUserDTO libraryUserDto = null;
    LibraryBookDAO libraryBookDao = new LibraryBookDAO();
    RentBookDAO rentBookDao = new RentBookDAO();
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
            System.out.print("1.관리자 로그인, 2.일반 로그인, 0.메뉴이동 (0 ~ 2 사이의 수 입력) : ");
            int pick = sc.nextInt();
            if(pick == 1) libraryUserDao = new LibraryLibrarianDAO();
            else if(pick == 2) libraryUserDao = new LibraryMemberDAO();
            else if(pick == 3) showMenu();
            else{
                if(libraryUserDao == null){
                    System.out.println("0 ~ 2 사이의 수 입력해주세요.");
                    menu = 1;
                }
            }
            System.out.print("아이디 입력 : ");
            id = sc.next();
            System.out.print("패스워드 입력 : ");
            pw = sc.next();
            //관리자세요? 회원이세요? 회원로그인, 관리자 로그인
            int result = libraryUserDao.loginUser(id, pw);
            if(result > 0){
                if(pick == 1) loginMenuLibrarian(id);
                else if(pick == 2) loginMenuMember(id);
            }
            else {
                System.out.println("아이디, 패스워드를 확인해주세요.");
                showMenu();
            }
        }else if(menu == 2) {
            System.out.println("회원등록을 합니다.");
            System.out.print("1.관리자 회원가입, 2. 일반 회원가입, 0. 메뉴이동 (0 ~ 2 사이의 수 입력) : ");
            int pick = sc.nextInt();
            if(pick == 1){
                libraryUserDao = new LibraryLibrarianDAO();
                libraryUserDto = new LibraryUserDTO();

            }
            else if(pick == 2){
                libraryUserDao = new LibraryMemberDAO();
                libraryUserDto = new LibraryMemberDTO();
            }
            else if(pick == 3) showMenu();
            else{
                if(libraryUserDao == null){
                    System.out.println("0 ~ 2 사이의 수 입력해주세요.");
                    menu = 2;
                }
            }
            System.out.print("아이디 입력 : ");
            id = sc.next();
            while(libraryUserDao.checkId(id) > 0) {
                System.out.print("이미 등록된 아이디입니다. 아이디 입력 : ");
                id = sc.next();
            }
            System.out.print("패스워드 입력 : ");
            pw = sc.next();
            libraryUserDto.setId(id);
            libraryUserDto.setPw(pw);
            System.out.println(libraryUserDao.joinUser(libraryUserDto));
            showMenu();
        }else if(menu == 0) {
            System.out.println("도서관 프로그램을 종료합니다.");
        }
        else {
            System.out.println("0 ~ 2 사이의 수 입력해주세요.");
            showMenu();
        }
    }
    void loginMenuMember(String id){
        System.out.print("1.책 검색, 2.책 반납, 3.로그아웃 : (1 ~ 3 사이의 수 입력) : ");
        int sel = sc.nextInt();
        if(sel == 1){
            System.out.print("도서명 검색 : ");
            String title = sc.next();
            ArrayList<LibraryBookDTO> bookList = libraryBookDao.selectBook(title);
            if(bookList.size() > 0){
                ArrayList<Integer> bnoList = new ArrayList<>();
                for(LibraryBookDTO b: bookList) {
                    System.out.println("-----------------------------");
                    System.out.println(b);
                    System.out.println();
                    bnoList.add(b.getBno());
                }
                System.out.print("1.책 대출, 2. 메뉴이동 (1 ~ 2 사이의 수 입력) : ");
                int pick = sc.nextInt();
                if(pick == 1) rentBook(id, bnoList);

            }else {
                System.out.println("검색 결과가 없습니다.");
                loginMenuMember(id);
            }
        }else if(sel == 2) returnBook(id);
        else if(sel == 3) showMenu();
        else{
            System.out.println("1 ~ 2 사이의 수를 입력하세요.");
            loginMenuMember(id);
        }
    }
    void rentBook(String id, ArrayList<Integer> bnoList){
        int find = 0;
        System.out.print("대출하실 책 번호 입력 : ");
        int bno = sc.nextInt();
        if(rentBookDao.selectReturn_yn(bno).equals("대출중")){
            System.out.println("대출중인 도서입니다. 대출불가합니다.");
        }else{
            int result = 0;
            for(int i : bnoList){
                if(i == bno){
                    find++;
                    result= rentBookDao.insertRentBook(id,bno);
                }
            }
            if(result > 0) System.out.println("대출되었습니다.");
            if(find == 0) {
                System.out.println("책 번호를 확인해주세요.");
            }
        }
        loginMenuMember(id);
    }

    void returnBook(String id){
        ArrayList<RentBookDTO> rbList =  rentBookDao.showReturnBook(id);
        ArrayList<Integer> bnoList = new ArrayList<>();
        int j = 0, result = 0;
        if(rbList.size() > 0){
            System.out.println("대출 목록");
            for(RentBookDTO rentBookDTO : rbList){
                System.out.println("-----------------------------");
                System.out.println(rentBookDTO);
                bnoList.add(rentBookDTO.getBno());
            } System.out.println("-----------------------------");

            for(int i = 0; i < bnoList.size(); i++){
                result += rentBookDao.deleteRentBook(bnoList.get(j++));
            }if(result == bnoList.size())System.out.println("반납되었습니다.");
            else System.out.println("반납 처리 되지 않은 도서가 있습니다. 데스크에서 문의 바랍니다.");

        }else System.out.println("대출하신 책이 없습니다.");
    }

    void loginMenuLibrarian(String id){
        System.out.print("1.책 정보 관리 메뉴 이동, 2. 회원 대출/반납 관리 메뉴 이동, 3.로그아웃 : (1 ~ 3 사이의 수 입력) : ");
        int sel = sc.nextInt();
        if(sel == 1){
            loginMenuLibrarianBook(id);
        }
    }
    void loginMenuLibrarianBook(String id){
        int result = 0;
        System.out.print("1.책정보 등록, 2.책정보 수정, 3.책정보 삭제, 4.메뉴 이동  (1 ~ 4 사이의 수 입력) : ");
        int pick = sc.nextInt();
        if(pick == 1){
            System.out.println("책정보를 등록합니다.");
            System.out.print("책제목 : ");
            String title = sc.nextLine();
            sc.nextLine();
            System.out.print("책저자 : ");
            String writer = sc.nextLine();
            sc.nextLine();
            LibraryBookDTO libraryBookDto = new LibraryBookDTO(title, writer);
            result = libraryBookDao.insertBook(libraryBookDto);
            if(result > 0) System.out.println("책정보가 성공적으로 등록되었습니다.");
            else System.out.println("책정보 등록이 실패되었습니다.");

        }else if(pick == 2){
            int find = 0;
            System.out.println("책정보를 수정합니다.");
            System.out.print("도서명 검색 : ");
            String title = sc.next();
            ArrayList<LibraryBookDTO> bookList = libraryBookDao.selectBook(title);
            if(bookList.size() > 0){
                ArrayList<Integer> bnoList = new ArrayList<>();
                for(LibraryBookDTO b: bookList) {
                    System.out.println("-----------------------------");
                    System.out.println(b);
                    System.out.println();
                    bnoList.add(b.getBno());
                }
                System.out.println("수정할 책 번호 입력 :");
                int bno = sc.nextInt();
                for(int i : bnoList){
                    if(i == bno){
                        
                    }
                }

            }
        }
    }

    void searchBookTitle(){

    }
}
