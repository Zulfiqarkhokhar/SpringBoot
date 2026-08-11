public class Polymorphism {
    public static void main(String[] args) {

        Maths m = new Maths();
        m.add(10, 10f);
        m.add(10, 10);

        Animal a = new Dog();
        a.run();
        
    }
}

class Maths{

    // method overloading
    // compile time polymorphism

    void add(int a, int b){
        System.out.println(a+b);
    }
    void add(int a,float b){
        System.out.println(a+b);
    }
}

// method over-riding
// run-time polymorphism

abstract class Animal{
    abstract void run();
}

class Dog extends Animal{
    void run(){
        System.out.println("Dog is Running");
    }
}
class Cat extends Animal{
    void run(){
        System.out.println("Cat is Running");
    }
}