class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        
        //Starting steps (assigning groups to loners)
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }
        
        //Step-1 (Make itemGraph and itemIndegree)
        Map<Integer, List<Integer>> itemGraph = new HashMap<>();
        int[] itemIndegree = new int[n];
        for (int i = 0; i < n; ++i) {
            itemGraph.put(i, new ArrayList<>());
        }
        
        //Step-2 (Make groupGraph and groupIndegree)
        Map<Integer, List<Integer>> groupGraph = new HashMap<>();
        int[] groupIndegree = new int[m];
        for (int i = 0; i < m; ++i) {
            groupGraph.put(i, new ArrayList<>());
        }
        
        //Fill those graphs and indegrees
        for (int i = 0; i < n; i++) {
            
            for (int prev : beforeItems.get(i)) {
                
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;
                
                if (group[i] != group[prev]) {
                    int prevItemGroup = group[prev];
                    int currItemGroup = group[i];
                    
                    groupGraph.get(prevItemGroup).add(currItemGroup);  //prevItemGroup ----> currItemGroup
                    groupIndegree[currItemGroup]++;
                }
                
            }
        }
        
        //step - TopoSort call
        List<Integer> itemOrder  = topoSort(itemGraph, itemIndegree);
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree);
        

        Map<Integer, List<Integer>> groupToItemsInOrder = new HashMap<>();
        for (Integer item : itemOrder) {
            int itemGroup = group[item];
            groupToItemsInOrder.computeIfAbsent(itemGroup, k -> new ArrayList<>()).add(item);
        }
        
        List<Integer> answerList = new ArrayList<>();
        for (int groupIndex : groupOrder) {
            answerList.addAll(groupToItemsInOrder.getOrDefault(groupIndex, new ArrayList<>()));
        }
        
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private List<Integer> topoSort(Map<Integer, List<Integer>> adj, int[] indegree) {
        
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0;i<indegree.length;i++) {
            if (indegree[i] == 0) {
                que.add(i);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        while (!que.isEmpty()) {
            Integer curr = que.remove();
            result.add(curr);
            
            for (Integer v : adj.get(curr)) {
                indegree[v]--;
                
                if (indegree[v] == 0) {
                    que.add(v);
                }
            }
        }

        return result.size() == adj.size() ? result : new ArrayList<>();
    }
}