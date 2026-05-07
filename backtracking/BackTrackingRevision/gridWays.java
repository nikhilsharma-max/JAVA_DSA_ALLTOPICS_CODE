public class gridWays {
    public static void main(String[] args) {
        int n = 2;
        int m = 3;
        System.out.println(ways(0, 0, n, m));
    }
    public static int ways(int i,int j, int n, int m){
        if(i==n-1||j==m-1){
            return 1;
        }
        if(i==n || j==m){
            return 0;
        }
        int way1 = ways(i+1, j, n, m);
        int way2 = ways(i, j+1, n, m);
        return way1+way2;
    }
}