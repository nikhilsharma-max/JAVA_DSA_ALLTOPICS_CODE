import java.util.*;
public class lcaUsingbinaryLifting {
    static final int MAX_LOG = 20;
    static List<List<Integer>> adj;
    static int[][] dp;
    static int[] level;
    public static void main(String[] args) {
        dp = new int[100001][MAX_LOG];
        level = new int[100001];
        adj = new ArrayList<>();
        for(int i = 0;i<=100000;i++){
            adj.add(new ArrayList<>()); 
        }

    }
    public void dfs(int u,int p){
        level[u] = level[p]+1;
        dp[u][0] = p;
        for(int i = 1;i<MAX_LOG;i++){
            dp[u][i] = dp[dp[u][i-1]][i-1];
            for(int v:adj.get(u)){
                if(v!=p){
                    dfs(v,u);
                }
            }
        }   

    }
    int lca(int u,int v){
        if(level[u]<level[v]){
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = level[u] - level[v];
        for(int i = 0;i<MAX_LOG;i++){
            if((diff & (1<<i))!=0){
                u = dp[u][i];
            }
        }
        if(u==v) return u;
        for(int i = MAX_LOG-1;i>=0;i--){
            if(dp[u][i]!=dp[v][i]){
                u = dp[u][i];
                v = dp[v][i];
            }
        }
        return dp[u][0];    
}
