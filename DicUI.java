package dictionary;

import java.util.*;

public class DicUI {
    DicDTO dictDTO = new DicDTO();
    Scanner sc = new Scanner(System.in);
    public void intro() {
        System.out.println("한영사전 프로그램을 실행합니다.");
    }
    public void showMenu() {

        int find;
        String word, input;
        Map<String, String> dicKE = new HashMap<>();
        System.out.print("1.단어 찾기, 2.단어 등록, 3.영어단어 맞추기, 4.틀린 단어 재응시, 5.단어 수정, 6.단어삭제  0.종료 (0 ~ 6 사이의 수 입력) : ");
        input = sc.next();

        if (isIntCheck(input) != -1) {
            int menu = isIntCheck(input);
            if (menu == 1) {
                System.out.println("한글을 입력하면 그에 맞는 단어를 출력합니다.");
                System.out.print("한글단어 입력 : ");
                word = sc.next();

                find = dictDTO.select(word);
                if (find == 1) showMenu();
                else {
                    System.out.println(word + "단어를 찾지 못했습니다. 단어를 등록하시겠습니까?");
                    System.out.print("1.예, 2.아니요 (1 ~ 2 사이의 수 입력) : ");
                    input = sc.next();
                    if (isIntCheck(input) != -1) {
                        int sel = isIntCheck(input);
                        if (sel == 1) {
                            System.out.println("단어를 등록합니다.");
                            System.out.print("한글 단어 입력 : ");
                            String kor = sc.next();
                            System.out.print("영어 단어 입력 : ");
                            String eng = sc.next();
                            dicKE.put(kor, eng);
                            int result = dictDTO.insert(dicKE);
                            if (result == 1) System.out.println("성공적으로 반영되었습니다.");
                            else System.out.println("반영 실패");
                        } else if (sel == 2) {
                        } else
                            System.out.println("해당 단어는 한영사전에 등록되지 않은 단어입니다. 단어등록은 메뉴의 2번에서 하실 수 있습니다.");
                    }else System.out.println("정수를 입력하세요.");
                    showMenu();
                }
            } else if (menu == 2) {
                System.out.println("단어를 등록합니다.");
                System.out.print("한글 단어 입력 : ");
                word = sc.next();
                find = dictDTO.select(word);
                if (find == 1) {
                    System.out.println("해당 단어는 등록된 단어입니다.");
                    showMenu();
                } else {
                    System.out.print("영어 단어 입력 : ");
                    String eng = sc.next();
                    dicKE.put(word, eng);
                    int result = dictDTO.insert(dicKE);
                    if (result == 1) System.out.println("성공적으로 반영되었습니다.");
                    else System.out.println("반영 실패");
                    showMenu();
                }
            } else if (menu == 3) {
                if (dictDTO.selectAll() > 0) {
                    String rdKor = dictDTO.randKor();
                    System.out.println("한글 단어가 나오면 영어 단어를 입력해주세요.");
                    System.out.println("제시된 한글단어 : " + rdKor);
                    System.out.print("영어 단어 입력 : ");
                    String eng = sc.next();
                    find = dictDTO.searchEng(rdKor, eng);
                    if (find > 0) System.out.println("맞았습니다.");
                    else System.out.println("틀렸습니다.");

                    showMenu();
                } else {
                    System.out.println("등록된 한글, 영어 단어가 없습니다.");
                    showMenu();
                }

            } else if (menu == 4) {
                System.out.println("틀린 단어 재응시합니다.");
                if (dictDTO.randKor2() != null) {
                    String rdKor = dictDTO.randKor2();
                    System.out.println("한글 단어가 나오면 영어 단어를 입력해주세요.");
                    System.out.println("제시된 한글단어 : " + rdKor);
                    System.out.print("영어 단어 입력 : ");
                    String eng = sc.next();
                    dictDTO.searchEng(rdKor, eng);
                    System.out.println(dictDTO.updateWrong(eng));
                } else System.out.println("틀린 단어가 없습니다.");
                showMenu();
            } else if (menu == 5) {
                System.out.println("단어를 수정합니다. 입력하신 한글단어를 검색하여 영어단어를 수정합니다.");
                System.out.print("한글단어 입력 : ");
                String kor = sc.next();
                System.out.print("수정할 영어단어 입력 : ");
                String eng = sc.next();
                System.out.println(dictDTO.updateEng(kor, eng));
                showMenu();
            } else if (menu == 6) {
                System.out.println("단어를 삭제합니다.");
                System.out.print("삭제할 한글단어 입력 : ");
                String eng = sc.next();
                System.out.println(dictDTO.deleteKor(eng));
                showMenu();
            } else if (menu == 0) {
                System.out.println("한영사전을 종료합니다.");
            } else {
                System.out.println("0 ~ 6사이의 수 입력해주세요.");
                showMenu();
            }
        }else {
            System.out.println("정수를 입력하세요.");
            showMenu();
        }
    }
    int isIntCheck(String s){
        int number;
        try{
            number = Integer.parseInt(s);
        }
        catch (NumberFormatException ex){
            number = -1;
        }
        return number;
    }
}
