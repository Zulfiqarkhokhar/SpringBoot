// Immutable class
public class ImmutableClass {
    public static void main(String[] args) {

        College college = new College("IBA", "Sukkur");

        Student student = new Student("Zulfiqar", 28, college);

        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(student.getCollege().getName());
        System.out.println(student.getCollege().getAddress());

        student.getCollege().name = "NUST";
        System.out.println(student.getCollege().getName());
        
    }
}

final class Student{

    private String name;
    private int age;
    private College college;

    Student(String name, int age, College college){
        this.name = name;
        this.age = age;
        this.college = new College(college.name, college.address); // deep copy no exact college reference
    }

    // only getters

    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public College getCollege(){
        return new College(this.college.name, this.college.address);
    }

}

class College{
    String name;
    String address;

    College(String name,String address){
        this.name = name;
        this.address = address;
    }

    // getters

    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }

}