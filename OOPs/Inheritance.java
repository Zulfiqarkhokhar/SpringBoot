public class Inheritance {
    public static void main(String[] args) {
        
        EngineeringStudent es = new EngineeringStudent("Zulfiqar",28,101);
        es.markAttendence();
        es.attendLab();
        es.print();
    }
}

// parent -> child (is a) relationship
// child aquire all properties of parent except private

class Student{
    String name;
    int age;

    Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    void markAttendence(){
        System.out.println("Attendence marked");
    }

    void print(){
        System.out.println(name + " " + age);
    }

}
class EngineeringStudent extends Student{

    int rollNo;

    EngineeringStudent(String name, int age,int rollNo){
        super(name, age); // super is used to call parent constructor
        this.rollNo = rollNo;
    }

    void attendLab(){
        System.out.println("Lab attended");
    }
    void print(){
        super.print(); // also can call method of parent and variables as well
        System.out.println(rollNo);
    }
}