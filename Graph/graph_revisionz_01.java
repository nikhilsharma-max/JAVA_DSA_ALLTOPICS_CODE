import java.util.*;
public class graph_revisionz_01 {
    static class Edge{
        int src;
        int dest;
        int wt;
        public Edge(int src,int dest,int wt){
            this.src = src;
            this.dest = dest;
            this.wt  = wt;
        }
    }
    public static void main(String[] args) {
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        // bfs(graph);
        // System.out.println();
        // dfs(graph);
        // System.out.println();
        // System.out.println(cycle(graph));
        // System.out.println();
        // System.out.println(bipartite(graph));
        kahnAlgo(graph);
        System.out.println();
        printAllPath(graph);
        System.out.println();
        dijkstras(graph, 0);
        System.out.println();
        bellmanFord(graph, 0);
        System.out.println();
        prims(graph);
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
            //0 vertex
            // graph[0].add(new Edge(0, 1, 5));

            //1 vertex
            // graph[1].add(new Edge(1, 0, 5));
            // graph[1].add(new Edge(1, 2, 1));
            // graph[1].add(new Edge(1, 3, 3));

            //2 vertex

            // graph[2].add(new Edge(2, 1, 1));
            graph[2].add(new Edge(2, 3, 1));
            // graph[2].add(new Edge(2, 4, 4));

            // 3 vertex

            graph[3].add(new Edge(3, 1, 1));
            // graph[3].add(new Edge(3, 2, 1));


            // 4 vertex
            graph[4].add(new Edge(4, 0, 1));
            graph[4].add(new Edge(4, 1, 1));  
            
            //vertex 5
            graph[5].add(new Edge(5, 0, 1));
            graph[5].add(new Edge(5, 2, 1));
    }


    //BFS
    public static void bfs(ArrayList<Edge> graph[]){
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[graph.length];
        q.add(0);
        while(!q.isEmpty()){
            int curr = q.remove();
            if(!vis[curr]){
                vis[curr] = true;
                System.out.print(curr+" ");
                for(int i = 0;i<graph[curr].size();i++){
                    Edge e = graph[curr].get(i);
                    if(!vis[e.dest]){
                        q.add(e.dest);
                    }
                }
            }
        }
        System.out.println();
    }

    public static void dfs(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                dfsUtil(graph, 0, vis);
            }
        }
    }

    public static void dfsUtil(ArrayList<Edge> graph[],int curr,boolean vis[]){
        vis[curr] = true;
        System.out.print(curr+" ");
        for(int i = 0;i<graph[curr].size();i++){
            if(!vis[graph[curr].get(i).dest]){
                dfsUtil(graph, graph[curr].get(i).dest, vis);
            }
        }
    }

    public static boolean cycle(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]) {
                if(cycleUtil(graph, vis, i, -1)) {
                return true;
                }
            }
        }
        return false;
    }

    public static boolean cycleUtil(ArrayList<Edge> graph[],boolean vis[],int curr,int par){
        vis[curr] = true;
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            int neigh = e.dest;
            if(!vis[neigh]){
                if(cycleUtil(graph, vis, neigh, curr)){
                    return true;
                }
            }else if(vis[neigh] && neigh !=par){
                return  true;
            }

        }
        return false;
    }



    //Bipartite graph;-->ACG and even cycled graphs are bipartite
    public static boolean bipartite(ArrayList<Edge> graph[]){
        int col[] = new int[graph.length];
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(col,-1);
        for(int i = 0;i<graph.length;i++){
            if(col[i]==-1){
                q.add(i);
                col[i] = 0;
            }
            while(!q.isEmpty()){
                int curr = q.remove();
                for(int j = 0;j<graph[curr].size();j++){
                    Edge e = graph[curr].get(j);
                    int neigh = e.dest;
                    if(col[neigh]==-1){
                        int nextCol = col[curr]==0?1:0;
                        col[neigh] = nextCol;
                        q.add(neigh);
                    }else if(col[neigh]==col[curr]){
                        return false;
                    }
                }
            }
        }
return true;

    }


    //Cycle detection for undirected graph
    public static boolean isCyclic(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        boolean stack[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                if(isCyclicUtil(graph, i, vis, stack)){
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean isCyclicUtil(ArrayList<Edge> graph[],int curr,boolean vis[],boolean stack[]){
        vis[curr] = true;
        stack[curr] = true;
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
                if(stack[e.dest]){
                    return true;
                }else if(!vis[e.dest]){
                if(isCyclicUtil(graph, e.dest, vis, stack)){
                    return true;
                }
            }
            stack[curr] = false;
        }
        return false;
    }

    public static void topSort(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        Stack<Integer> s = new Stack<>();
        for(int i = 0;i<graph.length;i++){
            topSortUtil(graph,i,vis,s);
        }
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }
    }

    public static void topSortUtil(ArrayList<Edge> graph[],int curr, boolean vis[],Stack<Integer> s){
        vis[curr] = true;
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                topSortUtil(graph, e.dest, vis, s);
            }
        }
        s.push(curr);
    }


    //Graph part 3
    //Kahn's algorithm
    public static void kahnAlgo(ArrayList<Edge> graph[]){
        int indeg[] = new int[graph.length];
        for(int i = 0;i<graph.length;i++){
            for(int j = 0;j<graph[i].size();j++){
                Edge e = graph[i].get(j);
                indeg[e.dest]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i<indeg.length;i++){
            if(indeg[i]==0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int curr = q.remove();
            System.out.print(curr+" ");
            for(int i = 0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                indeg[e.dest]--;
                if(indeg[e.dest]==0){
                    q.add(e.dest);
                }
            }
        }
    }


    //print all paths

    public static  void printAllPath(ArrayList<Edge> graph[]){
        String path = "";
        printAllPathUtil(graph, 5,1,path);
    }
    public static void printAllPathUtil(ArrayList<Edge> graph[],int src, int dest,String path){
        if(src==dest){
            System.out.println(path+dest);
            return;
        }
        for(int i = 0;i<graph[src].size();i++){
            Edge e = graph[src].get(i);
            printAllPathUtil(graph, e.dest, dest, path+src);
        }
    }

    //Dijkstras

    static class Pair implements Comparable<Pair>{
        int node;
        int cost;
        public Pair(int node,int cost){
            this.node = node;
            this.cost = cost;
        }
        @Override
        public int compareTo(Pair p2){
            return this.cost-p2.cost;
        }
    }
    public static void dijkstras(ArrayList<Edge> graph[],int src){
        int dist[] = new int[graph.length];
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<dist.length;i++){
            if(i!=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!vis[curr.node]){
                vis[curr.node] = true;
                //Neighbours
                for(int i = 0;i<graph[curr.node].size();i++){
                    Edge e = graph[curr.node].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    if(dist[v]>dist[u]+wt){
                        dist[v]=dist[u]+wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }
        for(int i = 0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }
    }

    //Bellman's ford algorithm
    public static void bellmanFord(ArrayList<Edge> graph[],int src){
        int V = graph.length;
        int dist[] = new int[V];
        for(int i = 0;i<dist.length;i++){
            if(i!=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0;i<V-1;i++){
            for(int j = 0;j<graph.length;j++){
                for(int k = 0;k<graph[j].size();k++){
                    Edge e = graph[j].get(k);
                    int u = j;
                    int v = e.dest;
                    int wt = e.wt;
                    if(dist[u]!=Integer.MAX_VALUE && dist[v]>dist[u]+wt){
                        dist[v]=dist[u]+wt;
                    }
                }
            }
        }
        for(int i = 0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }
    }


    //Minimum spanning tree--Prims
    static class Info implements Comparable<Info>{
        int v;
        int c;
        public Info(int v,int c){
            this.c = c;
            this.v = v;
        }
        @Override
        public int compareTo(Info i2){
            return this.c-i2.c;
        }
    }
    public static void prims(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0, 0));
        int cost = 0;
        while(!pq.isEmpty()){
            Info curr = pq.remove();
            if(!vis[curr.v]){
                vis[curr.v] = true;
                cost+=curr.c;
                for(int i = 0;i<graph[curr.v].size();i++){
                    Edge e = graph[curr.v].get(i);
                    pq.add(new Info(e.dest, e.wt));
                }
            }
        }
        System.out.print("Minimum cost to connect all node is : "+cost);
    }

    //cheapest flight within k stops

}
