package rocScissorsPaperGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameUi {
    Scanner sc = new Scanner(System.in);
    String id, pw;
    GameUserDAO userDao = new GameUserDAO();
    ScoreDAO scoreDao = new ScoreDAO();
    int menu = -1;
    int win_count = 0, draw_count = 0, lose_count = 0, score = 0;
    public void intro() {
        System.out.println("가위바위보 게임을 실행합니다.");
    }

    public void showMenu() {
        System.out.print("1.로그인, 2.회원가입, 0.종료 (0 ~ 2 사이의 수 입력) : ");
        menu = sc.nextInt();
        if(menu == 1) {
            win_count = draw_count = lose_count = score = 0;
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
        }else if(menu == 2) {
            System.out.println("회원등록을 합니다.");
            System.out.print("아이디 입력 : ");
            id = sc.next();
            while(userDao.checkId(id) > 0) {
                System.out.print("이미 등록된 아이디입니다. 아이디 입력 : ");
                id = sc.next();
            }
            System.out.print("패스워드 입력 : ");
            pw = sc.next();
            GameUserDTO uDto = new GameUserDTO(id, pw);
            System.out.println(userDao.joinUser(uDto));
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
        int input,sel = 0;
        input = -1;
        ScoreDTO scoreDto = new ScoreDTO();
        System.out.print("1.게임하기, 2.기록보기, 3.랭킹확인, 4.로그아웃 : ");
        sel = sc.nextInt();
        if(sel == 1){   //게임하기
            while(input != 0) {
                System.out.print("1.가위, 2.바위, 3.보입니다. 게임 종료는 0을 눌러주세요.\n0 ~ 3까지의 수를 입력해주세요 -사용자 :");
                input = sc.nextInt();
                if(input >= 1 && input <=3) {
                    int user = input;
                    Random rd = new Random();
                    int com, result = 0;
                    com = rd.nextInt(3)+1;
                    result = user - com;

                    if(result == -1 || result == 2){	//lose
                        score -= 10;
                        lose_count++;
                        System.out.println("Lose..");
                    }else if(result == 1 || result == -2){	//win
                        score += 10;
                        win_count++;
                        System.out.println("Win!");
                    }else {	//draw
                        draw_count++;
                        System.out.println("Draw");
                    }
                }else if(input == 0) {
                    System.out.println("게임을 종료합니다.");
                    scoreDto.setUid(id);
                    if(score <= 0) score = 0;
                    scoreDto.setScore(score);
                    scoreDto.setWin_count(win_count);
                    scoreDto.setDraw_count(draw_count);
                    scoreDto.setLose_count(lose_count);
                    if(scoreDao.selectScore(id) > 0) {//update
                        if(scoreDao.selectBestScore(scoreDto) < score){
                            if(scoreDao.updateScore(scoreDto) > 0) System.out.println("최고점을 달성하였습니다!");
                            else System.out.println("update : 점수 반영 실패");
                        }else{
                            System.out.println("현재 "+id+"님의 최고점 : " + scoreDao.selectBestScore(scoreDto));
                            System.out.println("현재 점수 : " + score);
                            System.out.println("최고점을 갱신해보세요!");
                        }
                    }else {//insert
                        System.out.println(scoreDao.insertScore(scoreDto));
                    }
                    loginMenu(id);
                }
                else {
                    System.out.println("0 ~ 3까지의 수를 입력해주세요.");
                    loginMenu(id);
                }
            }
        }
        else if(sel == 2) {
            if(scoreDao.selectGameRecord(id) == null) {
                System.out.println("게임 전적 확인 실패");
            }
            else {
                System.out.println(id + "님의 전적을 확인합니다.");
                System.out.println(scoreDao.selectGameRecord(id));
            }
            loginMenu(id);

        }else if(sel == 3) {
            int i = 0;
            System.out.println("랭킹을 확인합니다.");
            if(scoreDao.selectRank().size() == 0) System.out.println("조회할 랭킹 데이터가 없습니다.");
            else {
                ArrayList<ScoreDTO> scoreRecord = scoreDao.selectRank();
                for(ScoreDTO s : scoreRecord) {
                    System.out.println("--------------");
                    System.out.print("[ "+(++i)+ "위 ] ");
                    System.out.println(s);
                }
            }
            loginMenu(id);
        }
        else if(sel == 4){
            int result = userDao.updateLogoutUser(id);
            if(result > 0) System.out.println("로그아웃 되었습니다.");
            showMenu();
        }else{
            System.out.println("1 ~ 3 사이의 수 입력하세요.");
            loginMenu(id);
        }
    }
}
