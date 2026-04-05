public class basicRevision {
    public static void main(String[] args) {
        
        int first = 0;
        int second = 1;
        int newFirst = 0;
        int newSecond = 0;
        //print nth fib
        System.out.print(first+" "+second+" ");
        int n = 8;
        newFirst = first;
        newSecond = second;
        for(int i = 2;i<=n;i++){

            int nextTerm = newFirst+newSecond;
            System.out.print(nextTerm+" ");
            newFirst = newSecond;
            newSecond = nextTerm;

        }
    }
}
