public class EnumDemo {
    public static void main(String[] args) {

        Status[] s = Status.values();

        for(Status status:s){
            System.out.println(status.name());
        }

        for(Status status: s){
            System.out.println(status.ordinal());
        }

        Status status = Status.valueOf("PENDING");
        System.out.println(status);
        
    }
}

enum Status{
    // what compiler itself create
    // public static final SUCCESS = new Status();
    // we can add virable and methods
    // we can add abstract methods but each object must over-ride with anonymous class
    SUCCESS,
    PROCESSING,
    PENDING,
    FAILED;

}
