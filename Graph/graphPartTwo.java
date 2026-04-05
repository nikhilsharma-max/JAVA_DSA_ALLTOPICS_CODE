import java.util.*;
public class graphPartTwo {

    public static class Edge{
        int src;
        int dest;
        public Edge(int src,int dest){
            this.src = src;
            this.dest = dest;
        }
    }
    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i =0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        // graph[0].add(new Edge(0, 1));
        // graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        // graph[1].add(new Edge(1, 0));
        // graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 3));
        // graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 1));
        // graph[3].add(new Edge(3, 4));

        // graph[3].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));

        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));

    }
    public static void main(String[] args) {
        int V = 6;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);
        System.out.println(detectCycle(graph));
        System.out.println(isBipartite(graph));
        topSort(graph);
        System.out.println();
        topSortBfs(graph);
        System.out.println();
        allPaths(graph);
    }

    //Cycle detection

    public static boolean detectCycle(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                if(detectCycleUtil(graph,vis,i,-1)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean detectCycleUtil(ArrayList<Edge>[] graph,boolean vis[],int curr,int par){
        vis[curr] = true;
        for(int i =0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            
            if(!vis[e.dest]){
                if(detectCycleUtil(graph, vis, e.dest, curr)){
                    return true;
                }
            }
            if(vis[e.dest] && e.dest!=par){
                return true;
            }
        }
        return false;

    }


    //Bipartite Graph

    //All acyclic and even cyclic graph are bipartite
    public static boolean isBipartite(ArrayList<Edge> graph[]){
        int col[] = new int[graph.length];
        Arrays.fill(col,-1);
        Queue<Integer> q = new LinkedList<>();
        for(int i= 0;i<graph.length;i++){
            if(col[i]==-1){
                q.add(i);
                col[i] = 0;//yellow
                while(!q.isEmpty()){
                    int curr = q.remove();
                    for(int j = 0;j<graph[curr].size();j++){
                        Edge e = graph[curr].get(j);
                        if(col[e.dest]==-1){
                            int nextCol = col[curr]==0?1:0;
                            col[e.dest] = nextCol;
                            q.add(e.dest);
                        }else if(col[e.dest]==col[curr]){
                            return false;
                        }
                    }
                }
            }
            
        }
        return false;
    }


    //Cycle detection for directed graph
    public static boolean isCyclic(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        boolean stack[] = new boolean[graph.length];
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                if(isCyclicUtil(graph,i,vis,stack)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isCyclicUtil(ArrayList<Edge> graph[],int curr, boolean vis[],boolean stack[]){
        vis[curr] = true;
        stack[curr] = true;
        for(int i =0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(stack[e.dest]){
                return true;
            }
        }
        stack[curr] = false;
        return false;
    }

    //Topological sorting in graph
    //For DAG's only

    public static void topSort(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        Stack<Integer> s = new Stack<>();
        for(int i = 0;i<graph.length;i++){
            if(!vis[i]){
                topSortUtil(graph,i,vis,s);
            }
        }
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }
    }
    public static void topSortUtil(ArrayList<Edge> graph[],int curr, boolean vis[],Stack<Integer>s){
        vis[curr] = true;
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                topSortUtil(graph, e.dest, vis, s);
            }
        }
        s.push(curr);
    }


    //Topological sort using bfs
    public static void topSortBfs(ArrayList<Edge> graph[]){
        int indeg[] = new int[graph.length];
        Queue<Integer> q = new LinkedList<>();
        calCulateIndeg(indeg,graph);
        for(int i = 0;i<indeg.length;i++){
            if(indeg[i]==0){
                q.add(i);
            }
        }
        //bfs
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

    public static void calCulateIndeg(int indeg[],ArrayList<Edge> graph[]){
        Arrays.fill(indeg,0);
        for(int i = 0;i<graph.length;i++){
            for(int j = 0;j<graph[i].size();j++){
                Edge e = graph[i].get(j);
                indeg[e.dest]++;
            }
        }
    }


    //All paths from source to destination
    public static void allPaths(ArrayList<Edge> graph[]){
        int src = 1;
        int dest = 5;
        // StringBuilder path = new StringBuilder("");
        String path = "";
        allPathUtil(graph,src,dest,path);
    }

    public static void allPathUtil(ArrayList<Edge> graph[],int src,int dest, String path){
        if(src == dest){
            // sb.append(dest);
            System.out.println(path+dest);
            
            return;
        }
        for(int i = 0;i<graph[src].size();i++){
            Edge e = graph[src].get(i);
            allPathUtil(graph, e.dest, dest, path+src);
            
        }
    }
}
