public class ObjectClassDemo { // by Default extends Object class here
    public static void main(String[] args) {
        
        Student s1 = new Student(21, "Ali");
        Student s2 = new Student(21, "Ali");

        // methods of Object class
        
        System.out.println(s1.equals(s2)); // equals method compare references

        System.out.println(s1.hashCode() == s2.hashCode());
        System.out.println(s1.hashCode());

        System.out.println(s1 instanceof Student);


    }
}

class Student{// by Default extends Object class here
    int age;
    String name;

    Student(int age, String name){
        this.age = age;
        this.name = name;
    }
}
