import java.util.*;
public class graphImplement{
    public static class Edge{
        int src;
        int dest;
        int wt;
        public Edge(int src,int dest,int wt){
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }
    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        //adding vertices

        // 0 vertex
        graph[0].add(new Edge(0, 1, 5));

        // 1 vertex
        graph[1].add(new Edge(1, 0, 5)); 
        graph[1].add(new Edge(1, 2, 1)); 
        graph[1].add(new Edge(1, 3, 3));
        
        // 2 vertex
        graph[2].add(new Edge(2, 1, 1));
        graph[2].add(new Edge(2, 3, 1));
        graph[2].add(new Edge(2, 4, 4));

        //3 vertex
        graph[3].add(new Edge(3, 1, 3));
        graph[3].add(new Edge(3, 2, 1));

        //4 vertex
        graph[4].add(new Edge(4, 2, 2));

        //2's all neighbours
        for(int i = 0;i<graph[2].size();i++){
            Edge e = graph[2].get(i);
            System.out.print(e.dest+" ");
        }
        
    }

    //Breadth first search--O(n)
    //bfs
    public static void bfs(ArrayList<Edge>[] graph){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                bfsUtil(graph, vis);
            }
        }
    }
    //BfsUtil
    public static void bfsUtil(ArrayList<Edge>[] graph,boolean  vis[]){
        Queue<Integer> q = new LinkedList<>();
        q.add(0);//source
        //Adjacency list se bfs ki technique m O(V+E) complexity aati h or agr adjacency matrix use krte to O(V^2) aati
        while(!q.isEmpty()){
            int curr = q.remove();
            if(!vis[curr]){
                System.out.print(curr+" ");
                vis[curr] = true;
                for(int i = 0;i<graph[curr].size();i++){
                    Edge e = graph[curr].get(i);
                    // if(!vis[e.dest]){
                        q.add(e.dest);
                    // }
                }
            }
        }

    }

    //dfs
    public static void dfs(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        for(int i= 0;i<graph.length;i++){
            dfsUtil(graph, i, vis);
        }
    }
    //dfsUtil
    public static void dfsUtil(ArrayList<Edge>[] graph,int curr, boolean vis[]){
        //visit curr node
        System.out.print(curr+" ");
        vis[curr] = true;
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                dfsUtil(graph, e.dest, vis);
            }
        }
    }

    //hasPath
    public static boolean hasPath(ArrayList<Edge>[] graph,int src,int dest,boolean vis[]){
        if(src==dest)return true;
        vis[src]=true;
        for(int i = 0;i<graph[src].size();i++){
            Edge e = graph[src].get(i);
            if(!vis[e.dest] && hasPath(graph, e.dest, dest, vis)){
                return true;
            }
        }
        return false;
    }
  
    //Detect cycle
    public static boolean detectCycle(ArrayList<Edge>[] graph){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                //argument(graph,vis,curr,parent)
               if(detectCycleUtil(graph,vis,i,-1)){
                return true;
               } 
            }
        }
        return false;
    }

    //detectcycleUtil
    public static boolean detectCycleUtil(ArrayList<Edge>[] graph,boolean vis[],int curr,int par){
        vis[curr] = true;
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                if(detectCycleUtil(graph, vis, e.dest, curr)){
                    return true;
                }
            }
            else if(vis[e.dest] && e.dest!=par)return true;
            
        }
        return false;
    }

    //cycle detection for unidirected graph
    public static void  cycleDetection(ArrayList<Edge>[] graph){
        boolean vis[] = new boolean[graph.length];
        boolean stack[] = new boolean[graph.length];
        if(cycleDetectionUtil(graph,vis,stack, 0)){System.out.println("Cycle exists");}
        else{
            System.out.println("Cycle does not exists");
        }
     
    }
   
    public static boolean cycleDetectionUtil(ArrayList<Edge>[] graph,boolean vis[],boolean stack[],int curr){
        vis[curr] = true;
        stack[curr] = true;
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(stack[e.dest])return true;
            if(!vis[e.dest]){
                if(cycleDetectionUtil(graph, vis, stack, e.dest))return true;
            }
        }
        stack[curr] = false;
        return false;
    }
   
   //Topological sorting
   public static void topSort(ArrayList<Edge>[] graph){
    boolean vis[] = new boolean[graph.length];
    Stack<Integer> s = new Stack<>();
    for(int i =0;i<graph.length;i++){
        if(!vis[i]){
            topSortUtil(graph,i,s,vis);
        }
    }
    while(!s.isEmpty()){
        System.out.print(s.pop()+" ");
    }
   }
   public static void topSortUtil(ArrayList<Edge>[] graph,int curr,Stack<Integer> s,boolean vis[]){
        vis[curr]=true;
        for(int i =0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                topSortUtil(graph, curr, s, vis);
            }
        }
        s.push(curr);
   }    
   
   //Topsort using bfs
   public static void topSortBfs(ArrayList<Edge>[] graph){
    Queue<Integer> q = new LinkedList<>();
    int indeg[] = new int[graph.length];
    //calculating indeg
    for(int i = 0;i<graph.length;i++){
        for(int j = 0;j<graph[i].size();j++){
            Edge e = graph[i].get(j);
            indeg[e.dest]++;
        }
    }
    for(int i = 0;i<indeg.length;i++){
        if(indeg[i]==0)q.add(i);
    }
    while(!q.isEmpty()){
        int curr = q.remove();
        System.out.print(curr+" ");
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            indeg[e.dest]--;
            if(indeg[e.dest]==0)q.add(e.dest);
        }
    }
   }
   

   //All paths from source to target in directed graph
   public static void printAllPath(ArrayList<Edge> graph[],int src,int dest,String path){
    if(src==dest){
        System.out.println(path+dest);
    }

    for(int i = 0;i<graph[src].size();i++){
        Edge e = graph[src].get(i);
        printAllPath(graph, e.dest, dest, path+src);
    }
   }

    //Dijkstras
    static class Pair implements Comparable<Pair>{
        int n;
        int path;
        public Pair(int n,int path){
            this.n = n;
            this.path = path;
        }
        @Override
        public int compareTo(Pair p2){
            return this.path-p2.path;
        }
    }
    public static void dijkstras(ArrayList<Edge> graph[],int src){
        int dist[] = new int[graph.length];//dist[i] = source to i distance
        for(int i = 0;i<graph.length;i++){
            if(i!=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0));//source to source tk ka distance ka path = 0 hoga
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!vis[curr.n]){
                vis[curr.n]=true;
                //neighbours
                for(int i = 0;i<graph[curr.n].size();i++){
                    Edge e = graph[curr.n].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    if(dist[u]+wt<dist[v]){
                        dist[v] = dist[u]+wt;
                        pq.add(new Pair(v,dist[v]));
                    }
                }
            }
        }
        //print all source to vertices path
        for(int i=0 ;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }

    //Bellman's frod
    public static void bellManFord(ArrayList<Edge> graph[],int src){
        int dist[] = new int[graph.length];
        for(int i = 0;i<dist.length;i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        int V = graph.length;
        for(int i = 0;i<V-1;i++){
            //edges
            for(int j = 0;j<graph.length;j++){
                for(int k = 0;k<graph[j].size();k++){
                    Edge e = graph[j].get(k);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    if( dist[u]!= Integer.MAX_VALUE && dist[u]+wt<dist[v]){
                        dist[v] = dist[u]+wt;
                    }
                }
            }

        }
        for(int i = 0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }
    }

    //Prims--to find minimum spanning tree(MST)
    public static class Pair2 implements Comparable<Pair2>{
        int v;
        int cost;
        public Pair2(int v,int cost){
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(Pair2 p2){
            return this.cost - p2.cost;
        }
    }
    public static void prims(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Pair2> pq = new PriorityQueue<>();
        pq.add(new Pair2(0, 0));
        int finalCost = 0;
        while(!pq.isEmpty()){
            Pair2 curr = pq.remove();
            if(!vis[curr.v]){
                vis[curr.v] = true;
                finalCost+=curr.cost;
                for(int i = 0;i<graph[curr.v].size();i++){
                    Edge e = graph[curr.v].get(i);
                    pq.add(new Pair2(e.dest, e.wt));
                }
            }
        }
        System.out.println("Final min cost for mst is : "+finalCost);


    }
   

    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge>[] graph = new ArrayList[V];
        //Abhi array hk hr idx pr null store h(null mtlb arraylist nhi, define hi nhi h ki us index pr kya h), to ab hr index pr ek epmty arraylist store kr vaige
        createGraph(graph);
        System.out.println();
        bfs(graph);
        System.out.println();
        System.out.println("DFS");
        dfs(graph);
        System.out.println();
        System.out.print("Has path from 0 to 43 ");
        System.out.println(hasPath(graph, 0, 4, new boolean[V]));
        System.out.println();
        System.out.println("Detect cycle");
        System.out.println(detectCycle(graph));
        System.out.println();
        System.out.println("Prims");
        prims(graph);
    }
}