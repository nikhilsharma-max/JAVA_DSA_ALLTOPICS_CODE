import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = sc.nextInt();
        System.out.println();
        String arr[] = new String[n];
        for(int i = 0;i<n;i++){
            System.out.print("Enter word number "+i +": ");
            arr[i] = sc.next();
        }
        printAbbreviations(arr);
    }
    public static void printAbbreviations(String words[]){
        for(String word:words){
            if(word.length()<=10){
                System.out.println(word);
                continue;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(word.charAt(0));
            int len = word.length()-2;
            sb.append(len);
            sb.append(word.charAt(word.length()-1));
            System.out.println(sb.toString());
        }
    }
}
