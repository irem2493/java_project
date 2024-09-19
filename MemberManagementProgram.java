package member_management_program;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberManagementProgram {
    public static void main(String[] args) {
        ArrayList<Member> members = new ArrayList<>();
        ArrayList<Integer> sameNameCount = new ArrayList<>();
        String name, tel, relationship;
        int find, select, manu = 1;
        Scanner sc = new Scanner(System.in);
        find = 0;

        System.out.println("연락처 관리 프로그램을 실행합니다.");
        while(manu != 0) {
            System.out.print("1.연락처 등록, 2.모든 연락처 조회, 3.연락처 검색, 4.연락처 수정, 5. 연락처 삭제 0.종료 (0 ~ 5 사이의 수 입력) : ");
            manu = sc.nextInt();
            if(manu == 1) {	// 추가
                System.out.println("연락처를 등록합니다.");
                System.out.print("이름 입력 : ");
                name = sc.next();
                System.out.print("연락처 입력 : ");
                tel = sc.next();
                System.out.print("관계 입력 : ");
                relationship = sc.next();
                //연락처 중복 체크
                if(members.size() > 0) {
                    for(int i = 0; i < members.size(); i++) {
                        if((name.equals(members.get(i).getName()) && tel.equals(members.get(i).getTel()) && relationship.equals(members.get(i).getRelationship()))!= true) {
                            members.add(new Member(name, tel, relationship));
                            break;
                        }else System.out.println("이미 등록된 연락처입니다.");
                    }
                }else members.add(new Member(name, tel, relationship));

            }else if(manu == 2) {	//모든 연락처 조회
                System.out.println("모든 연락처를 조회합니다.");
                for(int i = 0; i < members.size(); i++)
                    System.out.println(members.get(i).toString());
            }else if(manu == 3) {	//연락처 검색
                find = 0;
                if(members.size() > 0) {
                    System.out.print("연락처를 검색합니다. 이름 입력 : ");
                    name = sc.next();
                    for(int i = 0; i < members.size(); i++) {
                        if(name.equals(members.get(i).getName())) {
                            System.out.println(members.get(i).toString());
                            find++;
                        }
                    }
                    if(find == 0) System.out.println("등록되지 않은 연락처입니다.");
                }else System.out.println("등록된 연락처가 없습니다.");
            }else if(manu == 4) {	//수정
                find = 0;
                if(members.size() > 0) {
                    System.out.print("수정할 연락처를 검색합니다. 이름 입력 : ");
                    name = sc.next();
                    for(int i = 0; i < members.size(); i++) {
                        if(name.equals(members.get(i).getName())) {
                            System.out.println(members.get(i).toString());
                            sameNameCount.add(i);
                            find++;
                        }
                    }
                    if(find == 0) System.out.println("등록되지 않은 연락처입니다.");
                    else if(find == 1){
                        for(int i = 0; i < members.size(); i++) {
                            if(name.equals(members.get(i).getName())) {
                                System.out.print("이름 입력 : ");
                                name = sc.next();
                                System.out.print("연락처 입력 : ");
                                tel = sc.next();
                                System.out.print("관계 입력 : ");
                                relationship = sc.next();
                                members.set(i,new Member(name, tel, relationship));
                            }
                        }System.out.println("연락처가 수정되었습니다.");
                    }else{  //동명이인이 있을 경우
                        System.out.print("같은 이름이 "+find+"개 존재합니다. 몇번째 연락처를 입력하시겠습니까? (1 ~" + find +" 사이의 수를 입력) : ");
                        select = sc.nextInt();
                        for(int i = 0; i < find; i++){
                            if(select-1 == i){
                                System.out.print("이름 입력 : ");
                                name = sc.next();
                                System.out.print("연락처 입력 : ");
                                tel = sc.next();
                                System.out.print("관계 입력 : ");
                                relationship = sc.next();
                                members.set(sameNameCount.get(i),new Member(name, tel, relationship));
                            }
                        }System.out.println("연락처가 수정되었습니다.");
                    }
                }else System.out.println("수정할 연락처가 없습니다.");
            }else if(manu == 5) {	// 삭제
                find = 0;
                if(members.size() > 0) {
                    System.out.print("삭제할 연락처를 검색합니다. 이름 입력 : ");
                    name = sc.next();
                    for(int i = 0; i < members.size(); i++) {
                        if(name.equals(members.get(i).getName())) {
                            System.out.println(members.get(i).toString());
                            sameNameCount.add(i);
                            find++;
                        }
                    }
                    if(find == 0) System.out.println("등록되지 않은 연락처입니다.");
                    else if(find == 1){
                        for(int i = 0; i < members.size(); i++) {
                            if(name.equals(members.get(i).getName())) members.remove(i);
                        }System.out.println("연락처가 삭제되었습니다.");
                    }else{
                        System.out.print("같은 이름이 "+find+"개 존재합니다. 몇번째 연락처를 삭제하시겠습니까? (1 ~" + find +"사이의 수를 입력) : ");
                        select = sc.nextInt();
                        for(int i = 0; i < find; i++) {
                            if(select-1 == i) members.remove((int)sameNameCount.get(i));
                        }System.out.println("연락처가 삭제되었습니다.");
                    }

                }else System.out.println("삭제할 연락처가 없습니다.");
            }
        }
    }
}
