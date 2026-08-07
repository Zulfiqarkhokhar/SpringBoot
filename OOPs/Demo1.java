// Constructors and this keyword
public class Demo1 {
    public static void main(String[] args) {

        Student s1 = new Student("Zulfiqar",30,101,"IBA");
        Student s2 = new Student("Sajjad",34);

        s1.print();
        s2.print();
        
    }
}

class Student {

    String name;
    int age;
    int rollNo;
    String college;

    // default constructor
    Student(){

    }

    // parameterized constructor
    Student(String name, int age, int rollNo, String college){
        this.name = name; // this keyword refer to the current object of class
        this.age = age;
        this.rollNo = rollNo;
        this.college = college;
    }

    // constructor chaning
    Student(String name, int age){
        this(name,age,0,"Unknown"); // calling parametirize constructor // here this refer to the current object constructor
    }

    // these are constructor overloading as well same name but different parameters as we do in method overloading
    
     void print(){
        System.out.println(name + " " + age + " " + rollNo + " " + college);
    }
}
