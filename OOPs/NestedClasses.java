public class NestedClasses {
    public static void main(String[] args) {
        
        // as inner class is static which blogs to outer class so object will be
        Outer.Inner in = new Outer.Inner();

        in.fun();

        // as inner class is simple not static so object will be created as
        // first outer object
        Outer1 outer = new Outer1();
        Outer1.Inner1 inner = outer.new Inner1();

        inner.fun();

        // Local class method

        Outer2 outer2 = new Outer2();
        outer2.greet();

        // annonymous class

        Person p1 = new Person() {
        @Override
        void sayHello() {
          System.out.println("I am Guest");
            
        }
        };
        p1.sayHello();



    }
}

// static nested class

class Outer{

    static int x = 10;

    // can access only static members of outer class

    static class Inner{
        public void fun(){
            System.out.println(x);
        }
    }
}

// Inner class

class Outer1{

    int x = 10;

    class Inner1{

        int x = 20;

        // this inner class keeps reference of outer class

        void fun(){
            System.out.println("Inner class");
            System.out.println(x);
            System.out.println(Outer1.this.x);
        }
    }
}

// local nested class

class Outer2{
    void greet(){
        class Local{
            void print(){
                System.out.println("Local Class");
            }

        }

        Local local = new Local();
        local.print();
    }
}

abstract class Person{
    abstract void sayHello();
}
