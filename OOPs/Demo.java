public class Demo{
    public static void main(String[] args) {
        
        Student s1 = new Student();
        Student s2 = new Student();

        s1.name = "Zulfiqar";
        s1.age = 28;
        s1.rollNo = 101;
        s1.college = "IBA";

        s2.name = "Sajjad";
        s2.age = 32;
        s2.rollNo = 102;
        s2.college = "NUST";

        s1.markAttendence();
        s2.markAttendence();

        s1.print();
        s2.print();

    }
}

class Student{
    String name; // intance variables
    int age;
    int rollNo;
    String college;

    void markAttendence(){ // instance methods
        System.err.println("Attendenc marked by "+ name);
    }

    void print(){
        System.out.println(name + " " + age + " " + rollNo + " " + college);
    }
}