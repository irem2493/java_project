package board_user_management;

import java.util.ArrayList;
import java.util.Scanner;

public class System_Ui {
    Scanner sc = new Scanner(System.in);
    BoardDAO boradDao = new BoardDAO();
    UserDAO userDao = new UserDAO();
    ReplyDAO replyDao = new ReplyDAO();
    ArrayList<BoardDTO> boardList = new ArrayList<>();
    int menu = -1;
    public void intro() {
        System.out.println("시스템을 실행합니다.");
    }
    public void showMenu() {
        int sel;
        String id, pw;
        showBoard();
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
            if(boardList.size() > 0){
                System.out.print("확인 게시물 번호 입력 : ");
                int pick = sc.nextInt();
                showBoardContents(pick);
                showReply(pick);
            }else{
                System.out.println("게시물이 없습니다.");
            }

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
        BoardDTO boardDTO;
        showBoard();
        System.out.print("1.게시물 등록, 2.게시물 보기, 3.로그아웃 (1 ~ 3 사이의 수 입력) : ");
        sel = sc.nextInt();
        if(sel == 1){   //게시물 등록
            System.out.println("게시물을 등록합니다.");
            System.out.print("게시물 제목 : ");
            title = sc.next();
            System.out.print("게시물 내용 : ");
            contents = sc.next();
            boardDTO = new BoardDTO(title,id);
            boardDTO.setContents(contents);
            int result = boradDao.insertBoard(boardDTO);
            if(result > 0) System.out.println("게시물이 등록되었습니다.");
            else System.out.println("게시물 등록 실패");
            showBoard();
            showMenu();
        }else if(sel == 2){
            if(boardList.size() > 0) {
                System.out.print("확인 게시물 번호 입력 : ");
                int pick = sc.nextInt();
                int bno = showBoardContents(pick);
                showReply(bno);
                showReplyMenu(id, bno, pick);
            }
            else {
                System.out.println("게시물이 없습니다.");
                loginMenu(id);
            }
        }else if(sel == 3){
            int result = userDao.updateLogoutUser(id);
            if(result > 0) System.out.println("로그아웃 되었습니다.");
            showMenu();
        }else{
            System.out.println("1 ~ 3 사이의 수 입력하세요.");
            int result = userDao.updateLogoutUser(id);
            loginMenu(id);
        }
    }
    void showBoard(){
        System.out.println("-----------------------------------");
        System.out.println("번호    제목          작성자    작성일자");
        boardList = boradDao.selectBoard();
        if(boardList.size() > 0){
            for(int i = 0; i < boardList.size(); i++) {
                System.out.println("-----------------------------------");
                System.out.println(boardList.get(i).getBno() + "    " + boardList.get(i).getTitle() + "          " + boardList.get(i).getUid() + "    "+boardList.get(i).getCreateDate());
            }
            System.out.println("-----------------------------------");
        }else {
            System.out.println("게시물이 없습니다.");
            System.out.println("-----------------------------------");
        }
    }
    int showBoardContents(int sel){
        System.out.println("-----------------------------------");
        System.out.println("번호    제목          내용          작성자    작성일자");
        BoardDTO boardDTO = boradDao.selectBoardContents(sel);
        if(boardDTO != null){
            System.out.println("-----------------------------------");
            System.out.println(boardDTO.getBno() + "    " + boardDTO.getTitle() + "          " + boardDTO.getContents() +"          "+boardDTO.getUid() + "    "+boardDTO.getCreateDate());
        }
        System.out.println("-----------------------------------");
        return sel;
    }

    void showReplyMenu(String id, int bno, int sel){
        System.out.print("1.댓글달기, 2.댓글 수정, 3. 댓글 삭제, 4.메뉴이동 (1 ~ 4 사이의 수 입력) : ");
        int pick = sc.nextInt();
        int result = 0;
        if(pick == 1){
            System.out.print("댓글 입력 : ");
            String rcontents = sc.next();
            ReplyDTO replyDto = new ReplyDTO(bno, rcontents, id);
            if(replyDao.insertReply(replyDto) > 0) {
                System.out.println("댓글이 등록되었습니다.");
                showBoardContents(sel);
                showReply(bno);
            }
            else System.out.println("댓글 등록 실패");
            showReplyMenu(id, bno, pick);
        }else if(pick == 2) {
            System.out.println("댓글 내용을 수정합니다.");
            System.out.print("수정할 댓글 번호 입력 : ");
            int rno = sc.nextInt();
            //댓글의 개수 카운터해서 카운터 바깥 수가 입력되면 다시 숫자 입력하라고 하기
            
            int rst1 = replyDao.rightMember(rno, id);
            //입력된 댓글 번호로 조회한 결과의 id와 같으면 댓글 수정
            if(rst1 == 1){
                System.out.print("댓글 입력 : ");
                sc.nextLine();
                String rcontents = sc.nextLine();
                result = replyDao.updateReply(rno, id, rcontents);
                if(result > 0) System.out.println("댓글이 수정되었습니다.");
                else System.out.println("댓글 수정 실패");
            }
            else {
                System.out.println("댓글을 작성한 회원님만 수정할 수 있습니다.");
            }
            showReplyMenu(id, bno, pick);
        }
        else if(pick == 4){
            loginMenu(id);
        }else {
            System.out.println("1 ~ 4 사이의 수 입력해주세요.");
            showReplyMenu(id, bno, pick);
        }
    }

    void showReply(int bno){
        System.out.println("번호    작성자          댓글내용              작성일자");
        ArrayList<ReplyDTO> replyList = replyDao.selectReply(bno);
        if(replyList.size() > 0){
            for(int i = 0; i < replyList.size(); i++) {
                System.out.println("-----------------------------------");
                System.out.println(replyList.get(i).getRno() + "    " + replyList.get(i).getUid() + "          " + replyList.get(i).getRcontents() + "    "+replyList.get(i).getR_create_date());
            }
            System.out.println("-----------------------------------");
        }else {
            System.out.println("-----------------------------------");
            System.out.println("댓글이 없습니다.");
            System.out.println("-----------------------------------");
        }
    }
}
