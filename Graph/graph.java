import java.util.*;
public class graph {
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
    public static void main(String[] args) {
        int V = 5;//total number of nodes
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);
        System.out.println("-------DFS------");
        bfs(graph);
        System.out.println( );
        System.out.println("-------BFS------");
        boolean vis[] = new boolean[graph.length];
        System.out.println();
        boolean arr[] = new boolean[graph.length];
        System.out.println(hasPath(graph, 0, 4, arr));
        dfs(graph, 0,vis);
            //negihbours of two
            for(int i =0;i<graph[2].size();i++){
                Edge e = graph[2].get(i);
                System.out.print(e.dest+" ");
            }
        }
        public static void createGraph(ArrayList<Edge>[] graph){
        for(int i =0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
            //0 vertex
            graph[0].add(new Edge(0, 1, 5));

            //1 vertex
            graph[1].add(new Edge(1, 0, 5));
            graph[1].add(new Edge(1, 2, 1));
            graph[1].add(new Edge(1, 3, 3));

            //2 vertex

            graph[2].add(new Edge(2, 1, 1));
            graph[2].add(new Edge(2, 3, 1));
            graph[2].add(new Edge(2, 4, 4));

            // 3 vertex

            graph[3].add(new Edge(3, 1, 3));
            graph[3].add(new Edge(3, 2, 1));


            // 4 vertex
            graph[4].add(new Edge(4, 2, 2));

        }
        public static void bfs(ArrayList<Edge>[] graph){
            boolean vis[] = new boolean[graph.length];
            for(int i=0;i<graph.length;i++){
                if(!vis[i]){
                    bfsUtil(graph, vis);
                }
            }
        }

        public static void bfsUtil(ArrayList<Edge>[] graph,boolean vis[]){
            Queue<Integer> q = new LinkedList<>();
            
            q.add(0);
            while(!q.isEmpty()){
                int curr = q.remove();
                if(!vis[curr]){
                    System.out.print(curr+" ");
                    vis[curr] = true;
                    for(int i =0;i<graph[curr].size();i++){
                        Edge e = graph[curr].get(i);
                        q.add(e.dest);
                    }
                }
            }
        }

        public static void dfs(ArrayList<Edge> graph[],int curr,boolean vis[]){
            System.out.print(curr+" ");
            vis[curr] = true;
            for(int i =0 ;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                if(!vis[e.dest]){
                    dfs(graph, e.dest, vis);
                }
            }
        }

        //Has path
        public static boolean hasPath(ArrayList<Edge>graph [],int src,int dest,boolean vis[]){
            if(src==dest){
                return true;
            }
            vis[src] = true;
            for(int i = 0;i<graph[src].size();i++){
                Edge e = graph[src].get(i);
                if(!vis[e.dest]&& hasPath(graph, e.dest, dest, vis)){
                    return true;
                }
            }
            return false;
        }


        //Dijkstra's Algorithm

        public static class Pair implements Comparable<Pair>{
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
        public static void Dijkstras(ArrayList<Edge>graph[],int src){
            boolean vis[] = new boolean[graph.length];
            int dist[] = new int[graph.length];
            for(int i= 0;i<dist.length;i++){
                if(i==src)continue;
                dist[i] = Integer.MAX_VALUE;
            }
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            pq.add(new Pair(src, 0));
            while(!pq.isEmpty()){
                Pair curr = pq.remove();
                if(!vis[curr.n]){
                    vis[curr.n]= true;
                    for(int i= 0;i<graph[curr.n].size();i++){
                        Edge e = graph[curr.n].get(i);
                        int u = e.src;
                        int v = e.dest;
                        int w = e.wt;
                        if(dist[u]+w <dist[v]){
                            dist[v] = dist[u]+w;
                            pq.add(new Pair(v, dist[v]));
                        }
                    }
                }
            }
            for(int i = 0;i<dist.length;i++){
                System.out.print(dist[i]+" ");
            }

        }
        
    }
