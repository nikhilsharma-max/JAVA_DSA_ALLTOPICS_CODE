import java.util.Scanner;

public class Question2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =145;
        int num = n;
        int sum = 0;
        while(num>0){
            sum+=fact(num%10);
            num/=10;

        }
        if(sum==n)System.out.println("true");

        
    }
    public static int fact(int n){
          int sum=1;
        for(int i=n;i>=1;i--){
            sum*=i;
        }
        return sum;
    }
}
