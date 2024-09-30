package board_user_management;

import java.util.ArrayList;
import java.util.Scanner;

public class System_Ui {
    Scanner sc = new Scanner(System.in);
    BoardDAO boradDao = new BoardDAO();
    UserDAO userDao = new UserDAO();
    ArrayList<BoardDTO> boardList = new ArrayList<>();
    int menu = -1;
    public void intro() {
        System.out.println("시스템을 실행합니다.");
    }
    public void showMenu() {
        int sel;
        String id, pw;
        if(menu != 3) showBoard();
        System.out.print("1.로그인, 2.회원가입, 3.게시물 보기, 0.종료 (0 ~ 3 사이의 수 입력) : ");
        menu = sc.nextInt();

        if(menu == 1) { //로그인
            System.out.println("로그인을 합니다.");
            System.out.print("아이디 입력 : ");
            id = sc.next();
            System.out.print("패스워드 입력 : ");
            pw = sc.next();
            int result = userDao.loginUser(id, pw);
            if(result > 0) loginMenu(id);
            else {
                System.out.println("아이디, 패스워드를 확인해주세요.");
                showMenu();
            }
        }else if(menu == 2) {   //회원등록
            System.out.println("회원등록을 합니다.");
            System.out.print("아이디 입력 : ");
            id = sc.next();
            while(userDao.checkId(id) > 0) {
                System.out.print("이미 등록된 아이디입니다. 아이디 입력 : ");
                id = sc.next();
            }
            System.out.print("패스워드 입력 : ");
            pw = sc.next();
            UserDTO uDto = new UserDTO(id, pw);
            System.out.println(userDao.joinUser(uDto));
            showMenu();
        }
        else if(menu == 3) {    //게시물 보기
            showBoardContents();
            showMenu();
        }else if(menu == 0) System.out.println("시스템을 종료합니다.");
        else {
            System.out.println("0 ~ 3 사이의 수 입력해주세요.");
            showMenu();
        }
    }

    void loginMenu(String id){
        int sel;
        String title, contents;
        showBoard();
        System.out.print("1.게시물 등록, 2.게시물 보기, 3.로그아웃 (1 ~ 3 사이의 수 입력) : ");
        sel = sc.nextInt();
        if(sel == 1){   //게시물 등록
            System.out.println("게시물을 등록합니다.");
            System.out.print("게시물 제목 : ");
            title = sc.next();
            System.out.print("게시물 내용 : ");
            contents = sc.next();
            BoardDTO boardDTO = new BoardDTO(title,id);
            boardDTO.setContents(contents);
            int result = boradDao.insertBoard(boardDTO);
            if(result > 0) System.out.println("게시물이 등록되었습니다.");
            else System.out.println("게시물 등록 실패");
            showBoard();
        }else if(sel == 2){
            showBoardContents();
            //댓글 달거냐고 물어보기
        }else if(sel == 3){
            int result = userDao.updateLogoutUser(id);
            if(result > 0) System.out.println("로그아웃 되었습니다.");
            showMenu();
        }else{
            System.out.println("1 ~ 3 사이의 수 입력하세요.");
            int result = userDao.updateLogoutUser(id);
            if(result > 0) System.out.println("로그아웃되었습니다.");
            loginMenu(id);
        }
    }
    void showBoard(){
        System.out.println("-----------------------------------");
        System.out.println("번호    제목          작성자    작성일자");
        boardList = boradDao.selectBoard();
        for(int i = 0; i < boardList.size(); i++) {
            System.out.println(boardList.get(i).getBno() + "    " + boardList.get(i).getTitle() + "          " + boardList.get(i).getUid() + "    "+boardList.get(i).getCreateDate());
        }
        System.out.println("-----------------------------------");
    }
    void showBoardContents(){
        System.out.print("확인 게시물 번호 입력 : ");
        int sel = sc.nextInt();
        System.out.println("-----------------------------------");
        System.out.println("번호    제목          내용          작성자    작성일자");
        BoardDTO boardDTO = boradDao.selectBoardContents(sel);
        if(boardDTO != null){
            System.out.println(boardDTO.getBno() + "    " + boardDTO.getTitle() + "          " + boardDTO.getContents() +"          "+boardDTO.getUid() + "    "+boardDTO.getCreateDate());
        }
        System.out.println("-----------------------------------");
    }
}
