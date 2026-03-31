import java.util.ArrayList;

public class program {
    public static void main(String[] args) {
        
        int arr[] = {1,2,2,3,3,4};

        ArrayList<Integer> list = new ArrayList<>();

        int num = arr[0];

        for(int i=1;i<arr.length-1;i++){

            if(arr[i] == num){
                list.add(arr[i]);
            }else{
                num = arr[i];
            }
        }

        for(int n: list){
            System.out.println(n);
        }
    }
}
