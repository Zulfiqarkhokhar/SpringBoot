// static and final keyword
public class Demo3 {
    public static void main(String[] args) {

        Employee e1 = new Employee(0, "Ali");
        Employee e2 = new Employee(1, "Khokhar");

        e1.print(); // both e1 and e2 has same share college
        e2.print();

        Employee.markAttendence(); // can be called now by class name we can also call it by object as well

        
    }
}

class Employee{
    int id;
    String name;
    static String college; // class variable or shared variable
    static final double PI; // final makes it constant 
    // other final uses will follow in other oop concepts

    Employee(int id, String name){
        this.id = id;
        this.name = name;
    }

    void print(){
        System.out.println(id + " " + name + " " + college);
    }

    static void markAttendence(){ // blongs to class now not by object
        System.out.println("Attendence marked");

        // rules
        // 1. can only call other static methods
        // 2. can only access static varriable
        // 3. can not have access to this keyword

    }

    // static block to initialize static variables

    static{
        college = "IBA";
        PI = 3.17;
    }
}
