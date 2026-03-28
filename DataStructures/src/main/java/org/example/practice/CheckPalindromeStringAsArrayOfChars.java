package org.example.practice;

public class CheckPalindromeStringAsArrayOfChars {

    static void main(String[] args) {

        CheckPalindromeStringAsArrayOfChars obj = new CheckPalindromeStringAsArrayOfChars();

        String word1 = "madam";
        String word2 = "name";

        System.out.println(obj.checkPalindrome(word1));
        System.out.println(obj.checkPalindrome(word2));

    }

    private boolean checkPalindrome(String word) {

        char[] charArray = word.toCharArray();
        int end = charArray.length-1;
        for(int start=0;start<charArray.length/2;start++){
            if(charArray[start] != charArray[end]){
                return false;
            }
            end--;
        }
        return true;
    }
}
