// unboxing/autoboxing

public class Demo4 {
    public static void main(String[] args) {
        
        // Autoboxing
        int a = 20;
        Integer b = a;
        //Integer b = Integer.valueOf(a);
        System.out.println(a);
        System.out.println(b);

        // Unboxing

        Integer x = 10;
        // Integer x = new Integer(10);
        int y = x;
        // int y = x.intValue();
        System.out.println(x);
        System.out.println(y);


        // == and .equals

        int c = 10;
        int d = 10;

        Integer e = 302;
        Integer f = 302;

        System.out.println(c == d); // true 

        System.out.println(e==f); // false because == compares references

        System.out.println(e.equals(f));// true


    }
}
