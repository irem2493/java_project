package member_management_program;

import java.util.Scanner;

public class MemberUI {
    String name, tel, relationship;
    int manu = 1;
    MemberDao memberDao = new MemberDao();
    Scanner sc = new Scanner(System.in);

    //정보입력
    public static String[] inputDate_NTR() {
        String[] str = new String[3];
        Scanner scanner = new Scanner(System.in);
        System.out.print("이름 입력 : ");
        str[0] = scanner.next();
        System.out.print("연락처 입력 : ");
        str[1] = scanner.next();
        System.out.print("관계 입력 : ");
        str[2] = scanner.next();
        return str;
    }

    //정수 입력
    public static int inputInt() {
        Scanner scanner = new Scanner(System.in);
        int select = scanner.nextInt();
        return select;
    }

    //메뉴 입력
    public void intro() {
        System.out.println("연락처 관리 프로그램을 실행합니다.");
    }
    //정보 조작
    public void showMenu() {
        System.out.print("1.연락처 등록, 2.모든 연락처 조회, 3.연락처 검색, 4.연락처 수정, 5.연락처 삭제, 0.종료 (0 ~ 5 사이의 수 입력) : ");
        manu = sc.nextInt();
        switch(manu) {
            case 1:    //연락처 등록
                System.out.println("연락처를 등록합니다.");
                System.out.print("이름 입력 : ");
                name = sc.next();
                System.out.print("연락처 입력 : ");
                tel = sc.next();
                System.out.print("관계 입력 : ");
                relationship = sc.next();
                memberDao.insert(name, tel, relationship);
                showMenu();
                break;
            case 2:    //모든 연락처 조회
                System.out.println("모든 연락처를 조회합니다.");
                if (memberDao.getSize() > 0) {
                    memberDao.selectAll();
                } else System.out.println("등록된 연락처가 없습니다.");
                showMenu();
                break;
            case 3:    //특정 연락처 조회
                System.out.println("연락처를 조회합니다.");
                if (memberDao.getSize() > 0) {
                    System.out.print("연락처를 검색합니다. 이름 입력 : ");
                    name = sc.next();
                    memberDao.select(name);
                } else System.out.println("등록된 연락처가 없습니다.");
                showMenu();
                break;
            case 4:    //특정 연락처 수정
                System.out.println("연락처를 수정합니다.");
                if (memberDao.getSize() > 0) {
                    System.out.print("연락처를 검색합니다. 이름 입력 : ");
                    name = sc.next();
                    memberDao.update(name);
                } else System.out.println("등록된 연락처가 없습니다.");
                showMenu();
                break;
            case 5:    //특정 연락처 삭제
                System.out.println("연락처를 삭제합니다.");
                if (memberDao.getSize() > 0) {
                    System.out.print("연락처를 검색합니다. 이름 입력 : ");
                    name = sc.next();
                    memberDao.delete(name);
                } else System.out.println("삭제할 연락처가 없습니다.");
                showMenu();
                break;
            case 0:
                System.out.println("연락처 관리 프로그램을 종료합니다.");
                break;
            default:
                System.out.println("0 ~ 5 사이의 수 입력해주세요.");
                showMenu();
        }
    }
}
