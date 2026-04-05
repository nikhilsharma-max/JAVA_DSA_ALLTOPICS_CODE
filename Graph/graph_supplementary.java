import java.util.*;

public class graph_supplementary {
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        kosaRaju(graph);
        tarjanBridge(graph);
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));
        graph[1].add(new Edge(1, 0));
        graph[2].add(new Edge(2, 1));
        graph[3].add(new Edge(3, 4));
    }

    public static void kosaRaju(ArrayList<Edge> graph[]){
    //Stack fill
        Stack<Integer> s = new Stack<>();
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                topSort(i,s,graph,vis);
            }
        }
        //Now the stack is filled

        //Step 2 -- Transpose
        ArrayList<Edge> transpose[] = new ArrayList[graph.length];
        for(int i = 0;i<graph.length;i++){
            transpose[i] = new ArrayList<>();
            vis[i] = false;
        }
        for(int i = 0;i<graph.length;i++){
            for(int j = 0;j<graph[i].size();j++){
                Edge e = graph[i].get(j);
                transpose[e.dest].add(new Edge(e.dest, e.src));
            }
        }

        while(!s.isEmpty()){
            int curr = s.pop();
            if(!vis[curr]){
                System.out.print("SCC-->");
                dfs(transpose,curr,vis);
                System.out.println();
            }
        }

    }

    public static void dfs(ArrayList<Edge> transpose[],int curr,boolean vis[]){
            vis[curr] = true;
            System.out.print(curr+" ");
            for(int i = 0;i<transpose[curr].size();i++){
                Edge e = transpose[curr].get(i);
                if(!vis[e.dest]){
                    dfs(transpose,e.dest,vis);
                }
            }

    }


    public static void topSort(int curr,Stack<Integer> s, ArrayList<Edge> graph[],boolean vis[]){
        vis[curr] = true;
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                topSort(e.dest,s,graph,vis);
            }
        }
        s.push(curr);
    }
    public static void dfs(ArrayList<Edge> graph[],int curr,int par,int dt[],int low[],boolean vis[],int time){
        vis[curr] = true;
        dt[curr] = low[curr] = ++time;
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            int neigh = e.dest;
            if(neigh==par){
                continue;
            }else if(!vis[neigh]){
                dfs(graph,neigh,curr,dt,low,vis,time);
                low[curr] = Math.min(low[curr], low[neigh]);
                if(dt[curr]<low[neigh]){
                    System.out.println("Bridge : "+ curr+"---"+neigh);
                }
                else{
                    low[curr] = Math.min(low[curr], dt[neigh]);
                }
            }
        }
    }


    public static void tarjanBridge(ArrayList<Edge> graph[]){
        int dt[] =new int[graph.length];
        int low[] = new int[graph.length];
        int V = graph.length;
        int time = 0;
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<V;i++){
            if(!vis[i]){
                dfs(graph,i,-1,dt,low,vis,time);
            }
        }
    }
}
