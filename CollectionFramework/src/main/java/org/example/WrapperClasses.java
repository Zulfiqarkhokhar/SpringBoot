package org.example;

public class WrapperClasses {

    public static void main(String[] args) {
//        Integer obj = new Integer(12); depricated way

        Integer obj2 = Integer.valueOf(13);

        Integer obj3 = 14;// this is autoboxing

        int age = obj3; // unboxing
    }
}
