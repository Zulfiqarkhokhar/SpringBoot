package org.example.practice;

import java.util.Arrays;

public class ResizeArray {

    static void main(String[] args) {

        ResizeArray obj = new ResizeArray();

        int[] arr ={1,2,3,4,5};



        arr = obj.resize(arr,10);
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
    }

    private int[] resize(int[] arr, int j) {
        int[] temp = new int[j];
        for(int i=0; i<arr.length;i++){
            temp[i] = arr[i];
        }
        return temp;
    }
}
