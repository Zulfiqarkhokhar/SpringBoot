package org.example.practice;

import java.util.Arrays;

public class ReverseArray {

    public int[] reverseArray(int[] arr){
        int end = arr.length-1;
        for(int i=0;i<arr.length/2;i++){
            int temp = arr[i];
            arr[i]=arr[end];
            arr[end] = temp;
            end--;
        }
        return arr;
    }

    static void main(String[] args) {

        ReverseArray obj = new ReverseArray();

        int[] result = obj.reverseArray(new int[]{1,2,3,4,5,6,7});
        System.out.println(Arrays.toString(result));

    }
}
