package board_user_management;

import java.util.ArrayList;
import java.util.Scanner;

public class System_Ui {
    Scanner sc = new Scanner(System.in);
    BoardDAO boradDao = new BoardDAO();
    UserDAO userDao = new UserDAO();
    ReplyDAO replyDao = new ReplyDAO();
    ArrayList<BoardDTO> boardList = new ArrayList<>();
    ArrayList<Integer> bnoList = new ArrayList<>();
    ArrayList<Integer> rnoList = new ArrayList<>();
    ArrayList<ReplyDTO> replyList;
    int menu = -1;
    int sel = -1;
    
    public void intro() {
        System.out.println("시스템을 실행합니다.");
    }
    public void showMenu() {
        String id, pw;
        if(menu != 3)showBoard();
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
            int find = 0;
            if(boardList.size() > 0){
                System.out.print("확인 게시물 번호 입력 : ");
                int pick = sc.nextInt();
                for(int i : bnoList){
                    if(i == pick) find++;
                }
                if(find > 0){
                    showBoardContents(pick);
                    showReply(pick);
                }else System.out.println("게시판 번호를 확인해주세요.");
            }else System.out.println("게시물이 없습니다.");
            showMenu();
        }else if(menu == 0) System.out.println("시스템을 종료합니다.");
        else {
            System.out.println("0 ~ 3 사이의 수 입력해주세요.");
            showMenu();
        }
    }

    void loginMenu(String id){
        int bno_find;
        String title, contents;
        BoardDTO boardDTO;
        showBoard();
        System.out.print("1.게시물 등록, 2.게시물 보기, 3.게시물 수정, 4.게시물 삭제, 5.로그아웃 (1 ~ 5 사이의 수 입력) : ");
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
            loginMenu(id);
        }else if(sel == 2){
            bno_find = 0;
            if(boardList.size() > 0) {
                System.out.print("확인 게시물 번호 입력 : ");
                int pick = sc.nextInt();
                for(int i : bnoList){
                    if(i == pick) bno_find++;
                }
                if(bno_find > 0){
                    int bno = showBoardContents(pick);
                    showReply(bno);
                    showReplyMenu(id, bno, pick);
                }else System.out.println("게시판 번호를 확인해주세요.");
            }
            else System.out.println("게시물이 없습니다.");
           loginMenu(id);
        }else if(sel == 3){
            bno_find = 0;
            if(boardList.size() > 0) {
                System.out.println("게시물을 수정합니다.");
                System.out.print("게시물 번호 입력 : ");
                int bno = sc.nextInt();
                for(int i : bnoList){
                    if(i == bno) bno_find++;
                }
                if(bno_find > 0){
                    int rsl1 = boradDao.rightMember(bno,id);
                    if(rsl1 > 0){
                        System.out.print("수정할 게시물 내용 : ");
                        contents = sc.next();
                        int result = boradDao.updateBoard(contents, id, bno);
                        if(result > 0) System.out.println("게시물이 수정되었습니다.");
                        else System.out.println("게시물 수정 실패");
                    }else System.out.println("게시물을 작성한 회원님만 수정할 수 있습니다.");
                }else System.out.println("게시판 번호를 확인해주세요.");
            }else System.out.println("게시물이 없습니다.");
            loginMenu(id);
        }else if(sel == 4) {
            bno_find = 0;
            if(boardList.size() > 0) {
                System.out.println("게시물을 삭제합니다.");
                System.out.print("게시물 번호 입력 : ");
                int bno = sc.nextInt();
                for(int i : bnoList){
                    if(i == bno) bno_find++;
                }
                if(bno_find > 0){
                    int rsl1 = boradDao.rightMember(bno,id);
                    if(rsl1 > 0){
                        int result = boradDao.deleteBoard(id, bno);
                        if(result > 0) System.out.println("게시물이 삭제되었습니다.");
                        else System.out.println("게시물 삭제 실패");
                    }else System.out.println("게시물을 작성한 회원님만 삭제할 수 있습니다.");
                }else System.out.println("게시판 번호를 확인해주세요.");
            }else System.out.println("게시물이 없습니다.");
            loginMenu(id);
        }
        else if(sel == 5){
            int result = userDao.updateLogoutUser(id);
            if(result > 0) System.out.println("로그아웃 되었습니다.");
            showMenu();
        }else{
            System.out.println("1 ~ 5 사이의 수 입력하세요.");
            userDao.updateLogoutUser(id);
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
                bnoList.add(boardList.get(i).getBno());
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
        int rno_find = 0;
        System.out.print("1.댓글달기, 2.댓글 수정, 3.댓글 삭제, 4.메뉴이동 (1 ~ 4 사이의 수 입력) : ");
        int pick = sc.nextInt();
        int result = 0;
        if(pick == 1){
            System.out.print("댓글 입력 : ");
            String rcontents = sc.next();
            ReplyDTO replyDto = new ReplyDTO(bno, rcontents, id);
            if(replyDao.insertReply(replyDto) > 0) System.out.println("댓글이 등록되었습니다.");
            else System.out.println("댓글 등록 실패");
            showBoardContents(sel);
            showReply(bno);
            if(boardList.size() > 0) showReplyMenu(id, bno, sel);
        }else if(pick == 2) {
            rno_find = 0;
            System.out.println("댓글 내용을 수정합니다.");
            if(replyList.size() > 0) {
                System.out.print("수정할 댓글 번호 입력 : ");
                int rno = sc.nextInt();
                for (int r : rnoList) {
                    if (r == rno) rno_find++;
                }
                if (rno_find == 0) System.out.println("댓글 번호를 확인해주세요.");
                else {
                    int rst1 = replyDao.rightMember(rno, id);
                    if (rst1 == 1) {
                        System.out.print("댓글 입력 : ");
                        sc.nextLine();
                        String rcontents = sc.nextLine();
                        result = replyDao.updateReply(rno, id, rcontents);
                        if (result > 0) System.out.println("댓글이 수정되었습니다.");
                        else System.out.println("댓글 수정 실패");
                    } else System.out.println("댓글을 작성한 회원님만 수정할 수 있습니다.");
                }
            }else System.out.println("수정할 댓글이 없습니다.");
            showBoardContents(sel);
            showReply(bno);
            if(boardList.size() > 0) showReplyMenu(id, bno, sel);
        }else if(pick == 3){
            rno_find = 0;
            System.out.println("댓글을 삭제합니다.");
            if(replyList.size() > 0){
                System.out.print("삭제할 댓글 번호 입력 : ");
                int rno = sc.nextInt();
                for(int r : rnoList){
                    if(r == rno) rno_find++;
                }
                if(rno_find == 0) System.out.println("댓글 번호를 확인해주세요.");
                else{
                    int rst1 = replyDao.rightMember(rno, id);
                    if(rst1 > 0){
                        result = replyDao.deleteReply(rno, id);
                        if(result > 0) System.out.println("댓글이 삭제되었습니다.");
                        else System.out.println("댓글 삭제 실패");
                    }else System.out.println("댓글을 작성한 회원님만 삭제할 수 있습니다.");
                }
            }else System.out.println("삭제할 댓글이 없습니다.");
            showBoardContents(sel);
            showReply(bno);
            if(boardList.size() > 0) showReplyMenu(id, bno, sel);
        }
        else if(pick == 4) loginMenu(id);
        else {
            System.out.println("1 ~ 4 사이의 수 입력해주세요.");
            showBoardContents(sel);
            showReply(bno);
            if(boardList.size() > 0) showReplyMenu(id, bno, sel);
        }
    }

    void showReply(int bno){
        System.out.println("번호    작성자          댓글내용              작성일자");
        replyList = replyDao.selectReply(bno);
        if(replyList.size() > 0){
            for(int i = 0; i < replyList.size(); i++) {
                System.out.println("-----------------------------------");
                System.out.println(replyList.get(i).getRno() + "    " + replyList.get(i).getUid() + "          " + replyList.get(i).getRcontents() + "    "+replyList.get(i).getR_create_date());
                rnoList.add(replyList.get(i).getRno());
            }
            System.out.println("-----------------------------------");
        }else {
            System.out.println("-----------------------------------");
            System.out.println("댓글이 없습니다.");
            System.out.println("-----------------------------------");
        }
    }
}
