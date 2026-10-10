class Solution {
    public int isEulerCircuit(int V, int[][] adj) {
        // code here
        int degree[] = new int[V];
        for(int i = 0;i<V;i++){
            degree[i] = adj[i].length;
        }
        boolean vis[] = new boolean[V];
        if(!isConnected(degree,vis,adj))return 0;
        int oddCount = 0;
        for(int i = 0;i<V;i++){
            if(degree[i]%2!=0)oddCount++;
        }
        if(oddCount==0)return 2;
        if(oddCount==2)return 1;
        return 0;
    }
    public boolean isConnected(int degree[],boolean vis[],int[][] adj){
        int curr = 0;
        for(int i = 0;i<degree.length;i++){
            if(degree[i]!=0){
                curr = i;
                break;
            }
        }
        dfs(curr,vis,adj);
        for(int i = 0;i<degree.length;i++){
            if(degree[i]!=0 && !vis[i])return false;
        }
        return true;
    }
    public void dfs(int curr,boolean vis[],int[][] adj){
        vis[curr] = true;
        int neigh[] = adj[curr];
        if(neigh!=null & neigh.length!=0){
            for(int ne:neigh){
                if(!vis[ne])dfs(ne,vis,adj);
            }
        }
    }
} 