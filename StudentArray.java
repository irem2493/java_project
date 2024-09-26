package student_management_program;

public class StudentArray {
    Student[] arr;
    int length;
    int current_idx;

    public StudentArray(){
        arr = new Student[5];
        length = 5;
    }

    //무한 배열 만드는 함수
    public void add(Student s){
        if(current_idx >= length){
            Student[] tmp = new Student[length + 5];
            for(int i = 0; i < length; i++)
                tmp[i] = arr[i];
            arr = tmp;
        }arr[current_idx++] = s;
    }

    //인덱스 값을 받아 삭제하는 함수
    public void deleteArr(int idx){
        if(idx < current_idx){
            for(int i = idx; i < current_idx; i++) arr[i] = arr[i+1];
        }current_idx--;
    }
    //위치값을 받아 해당되는 값 출력
    public Student get(int idx){
        if(idx < current_idx) return arr[idx];
        else return arr[arr.length];
    }

    //모든 학생 정보 출력
    public void getAll(){
        for(int i = 0; i < current_idx; i++){
            arr[i].printInfo();
        }
    }

}