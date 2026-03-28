package org.example.practice;

import java.util.Arrays;

public class MoveZerosToEndOfArray {

    static void main(String[] args) {

        MoveZerosToEndOfArray obj = new MoveZerosToEndOfArray();

        int[] result = obj.moveZeros(new int[]{2,3,0,1,0,4});
        System.out.println(Arrays.toString(result));
    }

    private int[] moveZeros(int[] ints) {

        int j=0;
        for(int i=0;i<ints.length;i++){
            if(ints[i] != 0 && ints[j] == 0){
                int temp = ints[i];
                ints[i] = ints[j];
                ints[j] = temp;
            }
            if(ints[j] != 0){
                j++;
            }
        }
        return ints;
    }
}
