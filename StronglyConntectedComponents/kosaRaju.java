import  java.util.*;
class Solution {
    public int countSCC(int V, int[][] edges) {
        // code here
        //Step-1 Store order of dfs in stack
        //Top sort
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> adj[] = new ArrayList[V];
        for(int i = 0;i<V;i++){
            adj[i] = new ArrayList<>();
        }
        for(int ed[]:edges){
            adj[ed[0]].add(ed[1]);
        }
        boolean vis[] = new boolean[V];
        for(int i = 0;i<V;i++){
            if(!vis[i]){
                dfsFill(i,vis,adj,st);
            }
        }
        
        //Step 2 Make a reversed graph
        ArrayList<Integer> revAdj[] = new ArrayList[V];
        for(int i = 0;i<V;i++){
            revAdj[i] = new ArrayList<>();
        }
        for(int ed[]:edges){
            revAdj[ed[1]].add(ed[0]);
        }
        
        //Step 3 Call dfs with stack order
        vis = new boolean[V];
        int countScc = 0;
        while(!st.isEmpty()){
            int curr = st.pop();
            if(!vis[curr]){
                dfsTraversal(curr,vis,revAdj);
                countScc++;
            }
        }
        return countScc;
    }
    
    public void dfsTraversal(int curr,boolean vis[],ArrayList<Integer> revAdj[]){
        vis[curr] = true;
        ArrayList<Integer> neigh = revAdj[curr];
        if(neigh!=null && neigh.size()!=0)
        for(int ne:neigh){
            if(!vis[ne])
            dfsTraversal(ne,vis,revAdj);
        }
    }
    
    public void dfsFill(int i,boolean vis[], ArrayList<Integer> adj[],Stack<Integer> s){
        vis[i] = true;
        ArrayList<Integer> neigh = adj[i];
        if(neigh!=null && neigh.size()!=0)
        for(int ne:neigh){
            if(!vis[ne])
            dfsFill(ne,vis,adj,s);
        }
        s.push(i);
    }
    
}