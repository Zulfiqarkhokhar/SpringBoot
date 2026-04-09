package org.example;

public class LearnGenerics {

    public static void main(String[] args) {

        Dog<String> d1 = new Dog<>("asdf12");

        System.out.println(d1.id);

        Dog<Integer> d2 = new Dog<>(12);
        System.out.println(d2.id);

        Cat<Integer,String> cat = new Cat<>(12,"Pussi Cat");

        System.out.println(cat.id + " " + cat.name);

        System.out.println(cat.getName());
    }
}

class Dog<E>{
    E id;

    public Dog(E id){
        this.id = id;
    }
}

class Cat<E,V>{
    E id;
    V name;
    public Cat(E id,V name){
        this.id = id;
        this.name = name;
    }

    V getName(){
        return name;
    }
}
