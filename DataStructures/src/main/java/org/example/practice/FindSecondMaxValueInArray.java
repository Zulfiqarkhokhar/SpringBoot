package org.example.practice;

public class FindSecondMaxValueInArray {

    static void main(String[] args) {

        FindSecondMaxValueInArray obj = new FindSecondMaxValueInArray();
        int result = obj.findSecondMax(new int[]{13,34,2,34,33,1});
        System.out.println("Second Max :"+result);

    }

    private int findSecondMax(int[] ints) {

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for(int i=0;i<ints.length;i++){
            if(ints[i] > max){
                secondMax = max;
                max = ints[i];
            } else if (ints[i] > secondMax && ints[i] != max) {
                secondMax = ints[i];

            }
        }
        return secondMax;

    }
}
