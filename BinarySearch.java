package array;

import java.util.Random;

public class BinarySearch {
    public static void main(String[] args){
        int start, mid, end, tmp;
        int[] arr = new int[100];
        Random rd = new Random();
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(arr[j] != 0 && arr[i] == arr[j])
                    continue;
                arr[i] = rd.nextInt(100)+1;
            }
        }
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }

        for(int i = 0; i < arr.length; i++){
            System.out.println(i+", "+arr[i] + " ");
        }
    }
}
