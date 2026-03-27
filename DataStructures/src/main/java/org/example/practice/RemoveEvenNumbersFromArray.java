package org.example.practice;

public class RemoveEvenNumbersFromArray {

    public int[] removeEven(int[] arr){
       int countOdd = 0;
       for(int i=0; i<arr.length;i++){
           if(arr[i]%2 != 0){
               countOdd++;
           }

       }

       int[] oddArr = new int[countOdd];
       int index =0;
       for(int i=0; i<arr.length;i++){
           if(arr[i]%2 != 0){
               oddArr[index] = arr[i];
               index++;
           }
       }

       return oddArr;
    }

    static void main(String[] args) {

        RemoveEvenNumbersFromArray obj = new RemoveEvenNumbersFromArray();
        int[] arr = obj.removeEven(new int[]{2,4,5,6,1,7,9,0});

        for(int n:arr){
            System.out.println(n);
        }
    }
}
