import java.util.*;
public class dynamicProgramming {
    public static void main(String[] args) {
        // int n = 5;
        // int dp[] = new int[n+1];
        // Arrays.fill(dp, -1);
        // System.out.println(fibo(n, dp));

        //0-1 Knapsack
        knapSack();
        System.out.println(knapSackTabul());
        System.out.println(targetSum());

        String str1 = "abcde";
        String str2 = "ace";
        int dp[][] = new int[str1.length()+1][str2.length()+1];
        for(int i=0;i<dp.length;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(lcsMemo(str1, str2, str1.length(), str2.length(), dp));
        System.out.println();
        System.out.println("LAS by tabulation");

        lscTabul(str1, str2);
        lcss();
        System.out.println();
        System.out.println();
        catalanMemo(10);
        System.out.println();
        System.out.println(catalanTabul(7));

        //MAM
        int arr[] = {1,2,3,4,3};
        System.out.println("MCM");
        System.out.println(mcm(arr, 1, arr.length-1));
        mcmMemo();
        System.out.println(mcmTabul(arr));
        int jumps[] = {2,3,1,1,4};
        System.out.println("");
        System.out.println(minJumps(jumps));
        
    }

    public static void knapSack(){
        int val[] = {15,14,10,45,30};
        int wt[] = {2,5,1,3,4};
        int W = 7;
        int dp[][] = new int[val.length+1][W+1];
        for(int i = 0;i<dp.length;i++){
            Arrays.fill(dp[i],-1);
        }
        int profit = knapSackUtil(val,wt,W,val.length,dp);
        System.out.println(profit);
    }
    public static int knapSackUtil(int val[],int wt[],int W,int idx,int dp[][]){
        //base case
        if(W==0||idx==0){
            return 0;
        }
        if(dp[idx][W]!=-1){
            return dp[idx][W];
        }
        int val1 = 0,val2 = 0,val3 = 0;
        if(wt[idx-1]<=W){//valid
            //include
            val1 = val[idx-1]+knapSackUtil(val, wt, W-wt[idx-1], idx-1,dp);
            //exclude;
            val2 = knapSackUtil(val, wt, W, idx-1,dp);
            return dp[idx][W]=Math.max(val2, val1);
        }else{
            val3 = knapSackUtil(val, wt, W, idx-1,dp);
            return dp[idx][W]=val3;
        }
        
    }


    public static int fibo(int n,int dp[]){
        if(n==0||n==1){
            return n;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        return dp[n] = fibo(n-1,dp)+fibo(n-2,dp);
    }

    public static int knapSackTabul(){
        int val[] = {15,14,10,45,30};
        int wt[] = {2,5,1,3,4};
        int W = 7;
        int n = val.length;
        int dp[][] = new int[n+1][W+1];
        //initialization
        for(int i = 0;i<dp.length;i++){
            dp[i][0] = 0;
        }
        for(int i = 0;i<dp[0].length;i++){
            dp[0][i] = 0;
        }
        //bottom-up filling
        for(int i = 1;i<dp.length;i++){
            for(int j = 1;j<dp[0].length;j++){
                int v = val[i-1];
                int w = wt[i-1];
                if(w<=j){//valid
                    //include
                    int incProfit = v + dp[i-1][j-w];
                    //excluding
                    int excProfit = dp[i-1][j];
                    dp[i][j] = Math.max(incProfit, excProfit);
                }else{
                    int excProfit = dp[i-1][j];
                    dp[i][j] = excProfit;
                }
            }
            
        }
        return dp[n][W];
    }


    //Target sum--variation of 0-1 knapsack
    public static boolean targetSum(){
        int sum = 10;
        int arr[] = {4,2,7,1,3};
        int n = arr.length;
        boolean dp[][] = new boolean[n+1][sum+1];
        for(int i = 0;i<n+1;i++){
            dp[i][0] = true;
        }
        for(int i = 1;i<n+1;i++){
            for(int j = 1;j<sum+1;j++){
                int v = arr[i-1];
                //include
                if(v<=j && dp[i-1][j-v]==true){
                    dp[i][j] = true;
                }
                //exclude
                if(dp[i-1][j]){
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][sum];
    }



    //Longest common subsequence
    public static int lcsMemo(String str1,String str2,int n,int m,int dp[][]){
        if(n==0||m==0){
            return 0;
        }
        if(dp[n][m]!=-1){
            return dp[n][m];
        }
        if(str1.charAt(n-1)==str2.charAt(m-1)){
            return dp[n][m] = lcsMemo(str1, str2, n-1, m-1, dp)+1;
        }else{
            int ans1 = lcsMemo(str1, str2, n-1, m, dp);
            int ans2 = lcsMemo(str1, str2, n, m-1, dp);
            return dp[n][m] = Math.max(ans1, ans2);
        }
    }

    public static void lscTabul(String str1,String str2){
        int n = str1.length();
        int m = str2.length();
        int dp[][] = new int[n+1][m+1];
        //initialization
        for(int i = 0;i<dp.length;i++){
            Arrays.fill(dp[i],0);
        }
        for(int i = 1;i<n+1;i++){
            for(int j = 1;j<m+1;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    int ans1 = dp[i-1][j];
                    int ans2 = dp[i][j-1];
                    dp[i][j] = Math.max(ans1, ans2);
                }
            }
            
        }   
        System.out.println(dp[n][m]);
    }

    //Longest common substring
    public static void lcss(){
        String str1 = "ABCDE";
        String str2 = "ABGCE";
        int n = str1.length();
        int m = str2.length();
        int dp[][] = new int[n+1][m+1];
        //Initialization
        for(int i = 0;i<dp.length;i++){
            for(int j= 0;j<dp[0].length;j++){
                if(i==0||j==0){
                    dp[i][j] = 0;
                }
            }
        }
        int ans = 0;
        //bottom up filling of dp matrix
        for(int i = 1;i<dp.length;i++){
            for(int j = 1;j<dp[0].length;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = 0;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        System.out.println("Length of longest common substring is : "+ ans);
    }

    public static int catalanRec(int n){
        if(n==0||n==1)return 1;
        int ans = 0;
        for(int i=0;i<=n-1;i++){
            ans+=catalanRec(i)*catalanRec(n-i-1);
        }
        return ans;
    }

    public static void catalanMemo(int n){
        int dp[] = new int[n+1];
        Arrays.fill(dp,-1);
        int ans = catalanMemoUtil(n, dp);
        System.out.println(ans);
    }
   public static int catalanMemoUtil(int n,int dp[]){
    if(n==0||n==1)return 1;
    if(dp[n]!=-1){
        return dp[n];
    }
    int ans = 0;
    for(int i = 0;i<=n-1;i++){
        ans+=catalanMemoUtil(i, dp)*catalanMemoUtil(n-i-1, dp);
    }
    return dp[n] = ans;
   }


   public static int catalanTabul(int n){
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2;i<=n;i++){
            for(int j= 0;j<i;j++){
                dp[i]+=dp[j]*dp[i-j-1];
            }
        }
        return dp[n];

   }

   public static int mcm(int arr[],int i,int j){
        //base case
        if(i==j)return 0;
        int ans = Integer.MAX_VALUE;
        for(int k = i;k<j;k++){
            int cost1 = mcm(arr, i, k); //Ai...Ak ==> arr[i-1]*arr[k]
            int cost2 = mcm(arr, k+1, j);//A(k+1)...Aj ==> arr[k+1]*arr[j]
            int  cost3 = arr[i-1]*arr[k]*arr[j];
            ans = Math.min(ans, cost1+cost2+cost3);
        }
        return ans;
   }
   public static void mcmMemo(){
    int arr[] = {1,2,3,4,3};
    int n = arr.length;
    int dp[][] = new int[n][n];
    for(int i = 0;i<n;i++){
        Arrays.fill(dp[i], -1);
    }
    System.out.println(mcmMemoUtil(arr, 1, n-1, dp));
   }

   public static int mcmMemoUtil(int arr[],int i,int j,int dp[][]){
    //base case
    if(i==j)return 0;
    if(dp[i][j]!=-1){
        return dp[i][j];
    }
    int ans = Integer.MAX_VALUE;
    for(int k = i;k<j;k++){
        int cost1 = mcmMemoUtil(arr, i, k, dp);
        int cost2 = mcmMemoUtil(arr, k+1, j, dp);
        int cost3 = arr[i-1]*arr[k]*arr[j];
        int finalAns = cost1+cost2+cost3;
        ans = Math.min(ans, finalAns);
    }
    return dp[i][j] = ans;
   }

   public static int mcmTabul(int arr[]){
    //creating of dp matrix
    int n = arr.length;
    int dp[][] = new int[n][n];
    //initialization--diagonal = 0
    for(int i = 0;i<n;i++){
        dp[i][i] = 0;
    }
    //meaning assign == done
    //bottom up filling of dp matrix
    for(int len = 2;len<=n-1;len++){
        for(int i = 1;i<=n-len;i++){
            int j = i+len-1;
            dp[i][j] = Integer.MAX_VALUE;
            for(int k = i;k<=j-1;k++){
                int cost1 = dp[i][k];
                int cost2 = dp[k+1][j];
                int cost3 = arr[i-1]*arr[k]*arr[j];
                dp[i][j] = Math.min(dp[i][j],cost1+cost2+cost3);
            }
        }
    }
    print(dp);
    return dp[1][n-1];
   }
   public static void print(int dp[][]){
    for(int i = 0;i<dp.length;i++){
        for(int j= 0;j<dp.length;j++){
            System.out.print(dp[i][j]+"   ");
        }
        System.out.println();
    }
   }

   //Minimum jumps
   public static int minJumps(int arr[]){
    int n = arr.length;
    int dp[] = new int[n];
    Arrays.fill(dp, -1);
    int ans = 0;
    dp[n-1] = 0;
    for(int i = n-2;i>=0;i--){
        int steps = arr[i];
        for(int j = i+1;j<=i+steps && j<n;j++){
            if(dp[i]!=-1){
                ans = Math.min(ans,dp[j]+1);
            }
        }
            if(ans!=Integer.MAX_VALUE){
                dp[i] = ans;
            }
    }
    return dp[0];
   }
}