package member_management_program;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberManagementProgram {
    public static void main(String[] args) {
        MemberUI memberUI = new MemberUI();
        memberUI.intro();
        memberUI.showMenu();
    }
}
