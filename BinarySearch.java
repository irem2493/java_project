package array;

import java.util.Random;
import java.util.Scanner;

public class BinarySearch {
    static int searchCount = 0;
    public static void main(String[] args){
        int start, end, tmp, fNum, idx;
        int[] arr = new int[100];
        Random rd = new Random();
        Scanner sc = new Scanner(System.in);

        //중복제거 코드
        for(int i = 0; i < arr.length; i++){
            arr[i] = rd.nextInt(100)+1;
            for(int j = 0; j < i; j++){
                if(arr[i] == arr[j]) i--;
            }
        }
        
        //오름차순으로 정렬
       for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }

       //이진탐색
        start = 0; end = arr.length-1;
        System.out.print("찾을 값을 입력해주세요 : ");
        fNum = sc.nextInt();
        binarySearch(arr, start, end, fNum);
    }

    static void binarySearch(int[] arr,int start, int end, int fNum){
        int mid = (start + end) / 2;
        if(start > end){
            System.out.println("값을 찾지 못했습니다.");
        }
        else if(arr[mid] == fNum){
            searchCount++;
            System.out.println("값을 찾았습니다. " + mid + "번째 위치하고 있습니다. " + searchCount+ "번에 찾았습니다.");
        }else if(arr[mid] > fNum) {
            searchCount++;
            end = mid - 1;
            binarySearch(arr, start, end, fNum);
        } else {
            searchCount++;
            start = mid + 1;
            binarySearch(arr, start, end, fNum);
        }
    }
}
