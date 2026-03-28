package org.example.practice;

public class FindMinValueInArray {

    static void main(String[] args) {

        FindMinValueInArray obj = new FindMinValueInArray();

        int result = obj.findMin(new int[]{23,45,34,66,11,33,44});

        System.out.println("Min Value: "+result);
    }

    private int findMin(int[] ints) {

        int min = ints[0];

        for(int i=1;i<ints.length;i++){
            if(min>ints[i]){
                min = ints[i];
            }
        }
        return min;
    }
}
