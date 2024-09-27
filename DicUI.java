package dictionary;

import java.util.*;

public class DicUI {
    DicDTO dictDTO = new DicDTO();
    public void intro() {
        System.out.println("한영사전 프로그램을 실행합니다.");
    }
    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        int find, menu = -1; String word;
        Map<String, String> dicKE = new HashMap<>();
        System.out.print("1.단어 찾기, 2.단어 등록, 3.영어단어 맞추기, 4.틀린 단어 재응시, 0.종료 (0 ~ 2 사이의 수 입력) : ");
        menu = sc.nextInt();

        if(menu == 1) {
            System.out.println("한글을 입력하면 그에 맞는 단어를 출력합니다.");
            System.out.print("한글단어 입력 : ");
            word = sc.next();

            find = dictDTO.select(word);
            if(find == 1) showMenu();
            else {
                System.out.println(word + "단어를 찾지 못했습니다. 단어를 등록합니다.");
                System.out.print("한글 단어 입력 : ");
                String kor = sc.next();
                System.out.print("영어 단어 입력 : ");
                String eng = sc.next();
                dicKE.put(kor, eng);
                int result = dictDTO.insert(dicKE);
                if(result == 1) System.out.println("성공적으로 반영되었습니다.");
                else System.out.println("반영 실패");
                showMenu();
            }
        }else if(menu == 2) {
            System.out.println("단어를 등록합니다.");
            System.out.print("한글 단어 입력 : ");
            word = sc.next();
            find = dictDTO.select(word);
            if(find == 1) {
                System.out.println("해당 단어는 등록된 단어입니다.");
                showMenu();
            }
            else {
                System.out.print("영어 단어 입력 : ");
                String eng = sc.next();
                dicKE.put(word, eng);
                int result = dictDTO.insert(dicKE);
                if(result == 1) System.out.println("성공적으로 반영되었습니다.");
                else System.out.println("반영 실패");
                showMenu();
            }
        }else if(menu == 3) {
            if(dictDTO.selectAll() > 0) {
                System.out.println("한글 단어가 나오면 영어 단어를 입력해주세요.");
                System.out.println("제시된 한글단어 : " + dictDTO.randKor());
                System.out.print("영어 단어 입력 : ");
                String eng = sc.next();
                find = dictDTO.searchEng(dictDTO.randKor(),eng);
                if(find > 0) System.out.println("맞았습니다.");
                else {
                    System.out.println("틀렸습니다.");
                }
                showMenu();
            }else {
                System.out.println("등록된 한글, 영어 단어가 없습니다.");
                showMenu();
            }

        }else if(menu == 4) {
            System.out.println("틀린 단어 재응시합니다.");
            if(dictDTO.randKor2() != null){
                System.out.println("한글 단어가 나오면 영어 단어를 입력해주세요.");
                System.out.println("제시된 한글단어 : " + dictDTO.randKor2());
                System.out.print("영어 단어 입력 : ");
                String eng = sc.next();
                dictDTO.searchEng(dictDTO.randKor2(),eng);
            }else System.out.println("틀린 단어가 없습니다.");
            showMenu();
        }else if(menu == 0) {
            System.out.println("한영사전을 종료합니다.");
        }
        else {
            System.out.println("0 ~ 3사이의 수 입력해주세요.");
            showMenu();
        }

    }
}
