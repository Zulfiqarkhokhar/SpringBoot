public class Abstraction {
    public static void main(String[] args) {
        Vehicle car = new Car();

        car.start();
        car.stop();
        car.print();
    }
}
abstract class Vehicle{

    String model;
    int mileage;

    void print(){
        System.out.println("This is abstract class");
    }

    abstract void start();
    abstract void stop();

}

class Car extends Vehicle{

    @Override
    void start(){
        System.out.println("Engine start");
    }

    @Override
    void stop(){
        System.out.println("Engine stops");
    }
}

// Q1. Can abstract class have constructor -> yes
// Q2. Can abstract class be final -> no
// Q3. Can abstract class have static methods -> yes
// Q4. Can abstract class have private methods -> yes but non abstract methods
// Q5. Can abstract class have final methods -> yes but non abstract

