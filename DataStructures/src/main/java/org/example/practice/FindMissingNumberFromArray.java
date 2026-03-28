package org.example.practice;

public class FindMissingNumberFromArray {

    static void main(String[] args) {

        FindMissingNumberFromArray obj = new FindMissingNumberFromArray();

        int[] nums = {1,4,2,5,7,8,6};

        int result = obj.findMissing(nums);

        System.out.println("Missing Num: "+result);

    }

    private int findMissing(int[] nums) {

        int n = nums.length+1;
        int sum = (n*(n+1))/2;
        for(int i:nums){
            sum -=i;
        }
        return sum;
    }
}
