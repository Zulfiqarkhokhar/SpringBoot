public class Abstraction {
    public static void main(String[] args) {
        Vehicle car = new Car();

        car.start();
        car.stop();
    }
}
abstract class Vehicle{

    String model;
    int mileage;

    void start(){};
    void stop(){};

}

class Car extends Vehicle{
    void start(){
        System.out.println("Engine start");
    }
    void stop(){
        System.out.println("Engine stops");
    }
}

