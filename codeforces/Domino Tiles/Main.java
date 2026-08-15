package codeforces.Domino Tiles;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        int t = fs.nextInt();
        while(t-->0){
            int n = fs.nextInt();
            String s = fs.next();
            solve(n,s);
        }
    }

}  
static class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");
    String next() {
        while (!st.hasMoreTokens())
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }
}