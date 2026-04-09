package org.example;

public class GenericsMethods {

    public static void main(String[] args) {

        printData("Hello bro");
        printData(1234);

        System.out.println(getVal(1000));
        System.out.println(getVal("this is string"));

        System.out.println(getResult(10));
    }

    static <T> void printData(T data){
        System.out.println(data);
    }

    static <T> T getVal(T val){
        return val;
    }

    static <T extends Number> double getResult(T num){
        return num.doubleValue() * 10;
    }
}
