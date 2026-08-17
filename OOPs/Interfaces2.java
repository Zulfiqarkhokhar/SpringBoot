public class Interfaces2 {
    public static void main(String[] args) {
        
        MathConstant m = new A();
        m.fun();
        System.out.println(MathConstant.PI_VALUE);
        System.out.println(MathConstant.DEVIATION);

        m.fun2();

        MathConstant.fun3();

    }
}

interface MathConstant{
    double PI_VALUE = 3.17; // public static final by default
    int DEVIATION = 10;

    void fun(); //public abstract by default

    // after java 8 we have deafult and static methods as well

    default void fun2(){
        System.out.println("This is default method");
        fun4();
    }

    static void fun3(){
        System.out.println("This is static method");
    }

    // after java 9 we have private methods as well which can be accessible only in interface

    private void fun4(){
        System.out.println("This is private method");
    }
}

class A implements MathConstant{
    
    @Override
    public void fun(){ // you must make this public 
        System.out.println("I am having fun with interfaces for about " + DEVIATION +" hours");
    }

    // if neccessary override the default method other wise no need
}
