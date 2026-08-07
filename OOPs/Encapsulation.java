// encapsulation
public class Encapsulation {
    public static void main(String[] args) {
        
        BankAccount ba1 = new BankAccount();

        System.out.println(ba1.getBalance());
        ba1.deposite(10000.0);
        System.out.println(ba1.getBalance());
        ba1.withdraw(5000.50);
        System.out.println(ba1.getBalance());


    }
}

// encapsulation is oop principle which say bind data and behavior into single unit called class
// and restrict direct access to data and provide setter and getter methods for that.

class BankAccount{
    private double balance; // private access modifier restrict access only visible to same class

    public void deposite(double amount){
        balance += amount;
    }
    public void withdraw(double amount){
        balance -= amount;
    }

    public double getBalance(){
        return balance;
    }
}
