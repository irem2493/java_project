package member_management_program;

public class Member {
    String name;//이름
    String tel;//연락처
    String relationship;//관계

    public Member(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }
    public Member(String name, String tel, String relationship) {
        this.name = name;
        this.tel = tel;
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "이름 : " + name + ", 전화번호 : " + tel + ", 관계 : " + relationship;
    }
}
