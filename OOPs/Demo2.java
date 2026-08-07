// Object copy deep and shallow
public class Demo2 {
    public static void main(String[] args) {

        Maths m1 = new Maths(4,5);
        Maths m2 = new Maths(m1); // -> This is deep copy of object // two different object just value are copied
        Maths m3 = m1; // -> This is shallow copy of object // one object but 2 reference variable pointing to same object in heap


        
    }
}
class Maths{
    int x;
    int y;

    Maths(int x, int y){
        this.x = x;
        this.y = y;
    }

    Maths(Maths m){
        this.x = m.x;
        this.y = m.y;
    }
}