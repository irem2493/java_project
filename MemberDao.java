package member_management_program;

import java.util.ArrayList;

public class MemberDao {
    ArrayList<Member> members = new ArrayList<>();
    ArrayList<Integer> sameNameCount = new ArrayList<>();
    int select, contactCount, find = 0;
	/*public Member getMemer(String name) {
		Member member = new Member();
		return member;
	}*/

    //몇개의 연락처 정보가 입력되었는지 확인하는 함수
    public int getSize() {return members.size();}

    //연락처 정보 삽입 함수
    public void insert(String name, String tel, String relationship) {
        //연락처 중복 체크
        if(members.size() > 0) {
            for(int i = 0; i < members.size(); i++) {
                if((name.equals(members.get(i).getName()) && tel.equals(members.get(i).getTel()) && relationship.equals(members.get(i).getRelationship()))!= true) {
                    members.add(new Member(name, tel, relationship));
                    break;
                }else System.out.println("이미 등록된 연락처입니다.");
            }
        }else members.add(new Member(name, tel, relationship));
    }

    //연락처 정보 전체 조회 함수
    public void selectAll() {
        for(int i = 0; i < members.size(); i++)
            System.out.println(members.get(i).toString());
    }

    //특정 연락처 검색 함수
    public void select(String name) {
        find = 0;
        for(int i = 0; i < members.size(); i++) {
            if(name.equals(members.get(i).getName())) {
                System.out.println(members.get(i).toString());
                find++;
            }
        }
        if(find == 0) System.out.println("등록되지 않은 연락처입니다.");
    }

    //특정 연락처 수정 함수
    public void update(String name) {
        find = 0;
        contactCount = 0;
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
                    String[] ntr = MemberUI.inputDate_NTR();
                    members.set(i,new Member(ntr[0], ntr[1], ntr[2]));
                }
            }System.out.println("연락처가 수정되었습니다.");
        }else{  //동명이인이 있을 경우
            System.out.print("같은 이름이 "+find+"개 존재합니다. 몇번째 연락처를 입력하시겠습니까? (1 ~ " + find +" 사이의 수를 입력) : ");
            select = MemberUI.inputInt();
            for(int i = 0; i < find; i++){
                if(select-1 == i){
                    String[] ntr = MemberUI.inputDate_NTR();
                    members.set(sameNameCount.get(i),new Member(ntr[0], ntr[1], ntr[2]));
                    contactCount++;
                }
            }
            if(contactCount > 0) System.out.println("연락처가 수정되었습니다.");
            else System.out.println("1 ~" + find + " 사이의 수를 입력해주세요.");
        }
    }

    //특정 연락처 삭제 함수
    public void delete(String name) {
        find = 0;
        contactCount = 0;
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
            System.out.print("같은 이름이 "+find+"개 존재합니다. 몇번째 연락처를 삭제하시겠습니까? (1 ~ " + find +" 사이의 수를 입력) : ");
            select = MemberUI.inputInt();
            for(int i = 0; i < find; i++) {
                if(select-1 == i) {
                    members.remove((int)sameNameCount.get(i));
                    contactCount++;
                }
            }
            if(contactCount > 0) System.out.println("연락처가 삭제되었습니다.");
            else System.out.println("1 ~ " + find +" 사이의 수를 입력해주세요.");
        }
    }
}
