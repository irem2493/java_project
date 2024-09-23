package test1;

import array.Array;

import java.util.ArrayList;

public class StudentScoreManagement {
    ArrayList<Test_Student> studentList = new ArrayList<>();

    //학생 정보 등록
    public void register(Test_Student s) {
        studentList.add(s);
    }

    public void getYear(int year){
        ArrayList<Test_Student> stduents = makeStudentYearList(year);
        for(Test_Student s : stduents){
            s.printInfo();
        }
    }

    ArrayList<Test_Student> makeStudentYearList(int year){
        ArrayList<Test_Student> students = new ArrayList<>();
        for(Test_Student s : studentList){
            if(s.getYear() == year){
                students.add(s);
            }
        }return students;
    }

    public void getGender(char gender){
        ArrayList<Test_Student> students = makeStudentGenderList(gender);
        for(Test_Student s : students){
            s.printInfo();
        }
    }

    ArrayList<Test_Student> makeStudentGenderList(char gender){
        ArrayList<Test_Student> students = new ArrayList<>();
        for(Test_Student s : studentList){
            if(s.getGender() == gender){
                students.add(s);
            }
        }return students;
    }

    public int getArrSize(){
        return studentList.size();
    }

    public ArrayList<Test_Student> getStudentList(){
        return studentList;
    }
}
