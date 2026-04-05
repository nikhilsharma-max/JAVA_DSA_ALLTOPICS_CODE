import java.util.ArrayList;
import java.util.PriorityQueue;

public class graph_bellmanford {
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
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        bellman_ford(graph, 0);
        System.out.println();
        prims(graph);

    }

    public static void bellman_ford(ArrayList<Edge> graph[],int src){
        int dist[] = new int[graph.length];
        int V = graph.length;
        for(int i = 0;i<V;i++){
            if(i!=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0;i<V-1;i++){
            for(int j = 0;j<graph.length;j++){
                for(int k = 0;k<graph[j].size();k++){
                    Edge e = graph[j].get(k);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;
                    //Relexation
                    if(dist[u] != Integer.MAX_VALUE && dist[v]>dist[u]+wt){
                        dist[v]=dist[u]+wt;
                    }
                }
            }
        }
        for(int i = 0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 2, -4));
        
        graph[2].add(new Edge(2, 3, 2));

        graph[3].add(new Edge(3, 4, 4));

        graph[4].add(new Edge(4, 1, -1));
        

    }

    static class Pair implements Comparable<Pair>{
        int vertex;
        int cost;
        public Pair(int vertex,int cost){
            this.vertex = vertex;
            this.cost = cost;
        }
        @Override
        public int compareTo(Pair p2){
            return this.cost-p2.cost;
        }
    }
    public static void prims(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0));
        int finalcost = 0;
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!vis[curr.vertex]){
                vis[curr.vertex] = true;
                finalcost+=curr.cost;
                for(int i = 0;i<graph[curr.vertex].size();i++){
                    Edge e = graph[curr.vertex].get(i);
                    pq.add(new Pair(e.dest,e.wt)); 
                }
            }
        }
        System.out.println("Minimun cost of mst : "+finalcost);
    }
}
