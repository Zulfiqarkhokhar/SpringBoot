package org.example.arrays;

import java.util.Arrays;

public class ArrayDemo1 {
    static void main(String[] args) {

        // declairing and initializing array
        int[] arr1 = new int[5]; // all index take value 0
        int[] arr2 = {1,2,3,4,5};

        // printing array
        System.out.println(Arrays.toString(arr2));

        for(int i=0;i<arr2.length;i++){
            System.out.println(arr2[i]);
        }

        // adding and updated array
        arr1[0] = 10;
        arr1[1] = 20;
        arr1[2] = 30;
        arr1[3] = 40;
        arr1[4] = 50;
        //arr1[5] = 60; index out of bounds

        //updating

        arr1[3] = 300;

        for(int a:arr1){
            System.out.println(a);
        }

        // assign value with array

        int[] arr3 = new int[5];

        int i=10;

        for(int a:arr3){
            a=i*10;
            System.out.println(a);
            i++;
        }

    }
}
