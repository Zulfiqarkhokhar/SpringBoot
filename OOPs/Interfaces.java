public class Interfaces {
    public static void main(String[] args) {

        Car car = new FuelCar();
        car.start();
        car.stop();
        
    }
}

interface Car{
    void start();
    void stop();
}

class ElectricalCar implements Car{

    @Override
    public void start() {
        // TODO Auto-generated method stub
        System.out.println("Electric car 'start'");
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        System.out.println("Electric car 'stop'");
    }
    
}

class FuelCar implements Car{

    @Override
    public void start() {
        // TODO Auto-generated method stub
        System.out.println("Fuel car 'start'");
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        System.out.println("Fuel car 'stop'");
    }
    
}