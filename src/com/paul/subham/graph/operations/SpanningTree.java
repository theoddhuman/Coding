package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListWeightedGraph;
import com.paul.subham.graph.implementation.Edge;
import com.paul.subham.set.implementation.QuickFindSet;
import com.paul.subham.set.implementation.QuickUnionWeightedSet;

import java.util.*;

/**
 * 1. Minimum Spanning Tree - Prim's Algorithm (Using Priority Queue)
 * 2. Minimum Spanning Tree - Prim's Algorithm (Using Array)
 * 3. Min Spanning Tree - Kruskal Algorithm
 * 4. Spanning Tree of an undirected graph
 * 5. Spanning Tree of an undirected graph (Using DFS)
 * 6. Spanning Tree of an undirected graph (Using BFS)
 * 7. Number of Operations to Make Network Connected
 * 8. Most Stones Removed with Same Row or Column
 * 9. Account Merge
 * 10. Number of Islands II
 */
public class SpanningTree {
    public static void main(String[] args) {
        AdjacencyListWeightedGraph graph = new AdjacencyListWeightedGraph(10);
        graph.addEdgeUndirected(1,2,2);
        graph.addEdgeUndirected(1,3,4);
        graph.addEdgeUndirected(2,4,6);
        graph.addEdgeUndirected(3,4,2);
        minSpanningTree(graph, 1);
    }

    /**
     * Minimum Spanning Tree - Prim's Algorithm (Using Priority Queue)
     * TC: Using PQ, V delete min + E updates, O(VlogV + ElogV) = O(ElogV),
     * If V = n, total possible edge = n*(n-1)/2, O(n^2logn)
     * TC: for adjacency matrix, O(V^2logV)
     * SC: O(V)
     */
    public static void minSpanningTree(AdjacencyListWeightedGraph graph, int s) {
        int[] distance = new int[graph.vertex];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[graph.vertex];
        Integer[] path = new Integer[graph.vertex];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(graph.vertex,
                Comparator.comparing(o -> distance[o]));
        distance[s] = 0;
        priorityQueue.add(s);
        while(!priorityQueue.isEmpty()) {
            int current = priorityQueue.remove();
            visited[current] = true;
            LinkedList<Edge> adjList = graph.adjListArray[current];
            for(Edge edge : adjList) {
                int next = edge.destination;
                int d = edge.weight;
                if (!visited[next] && distance[next] > d) {
                    priorityQueue.remove(next);
                    distance[next] = d;
                    path[next] = current;
                    priorityQueue.add(next);
                }
            }
        }
        for(int i=0; i<graph.vertex; i++) {
            System.out.println(i+" "+distance[i]+" "+path[i]);
        }
    }

    /**
     * Minimum Spanning Tree - Prim's Algorithm (Using Array)
     * TC: Using array, V min check + E updates, O(V^2 + E)
     * If V = n, total possible edge = n*(n-1)/2, O(n^2 + n*(n-1)/2) = O(n^2)
     * SC: O(V)
     */
    public static void minSpanningTreeUsingArray(AdjacencyListWeightedGraph graph, int s) {
        Integer[] distance = new Integer[graph.vertex];
        Integer[] path = new Integer[graph.vertex];
        distance[s] = 0;
        for(int i=0; i<graph.vertex-1; i++) {
            int current = minDistance(distance, graph.visited);
            if(current == -1) {
                break;
            }
            graph.visited[i] = true;
            LinkedList<Edge> adjList = graph.adjListArray[current];
            for(Edge edge : adjList) {
                int next = edge.destination;
                int d = edge.weight;
                if(!graph.visited[next] || distance[next] > d) {
                    distance[next] = d;
                    path[next] = current;
                }
            }
        }
        for(int i=0; i<graph.vertex; i++) {
            System.out.println(i + " " + distance[i] + " " + path[i]);
        }
    }

    private static int minDistance(Integer[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i=0; i<distance.length; i++) {
            if(Objects.nonNull(distance[i]) && !visited[i] && distance[i] < min) {
                min = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * Min Spanning Tree - Kruskal Algorithm
     * TC: E insertions in priority queue + 2*(V-1) find operations + V-1 union operations
     * TC: ElogE + 2V + VlogV ~ ElogE + VlogV, E can be at most V^2
     * TC: O(ElogE), When V=n, TC: O(n^2logn)
     * SC: O(V+E) = O(E) = O(n^2)
     */
    public static void minSpanningTreeKruskal(AdjacencyListWeightedGraph graph) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(graph.edgeCount(), Comparator.comparingInt(o -> o.weight));
        for(int i=0; i<graph.vertex; i++) {
            LinkedList<Edge> adjList = graph.adjListArray[i];
            for(Edge edge : adjList) {
                if(!priorityQueue.contains(edge)) {
                    priorityQueue.add(edge);
                }
            }
        }

        List<Edge> result = new ArrayList<>();
        QuickFindSet set = new QuickFindSet();
        set.makeSet(graph.vertex);
        while(!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.remove();
            int source = edge.source;
            int destination = edge.destination;
            if(set.find(source) != set.find(destination)) {
                result.add(edge);
                set.union(source, destination);
            }
        }
        result.forEach(edge -> System.out.println(edge.source +" "+ edge.destination+" "+edge.weight));
    }

    /**
     * Spanning Tree of an undirected graph
     *
     * TC: O(E)
     * SC: O(V)
     */
    public static void spanningTree(AdjacencyListWeightedGraph graph) {
        Set<Edge> spanningTree = new LinkedHashSet<>();
        for(int i=0; i<graph.vertex; i++) {
            LinkedList<Edge> list = graph.adjListArray[i];
            for(Edge edge : list) {
                if(!(graph.visited[edge.source] && graph.visited[edge.destination])) {
                    spanningTree.add(edge);
                    graph.visited[edge.source] = true;
                    graph.visited[edge.destination] = true;
                }
            }
        }
        for(Edge edge : spanningTree) {
            System.out.println(edge.source +" "+edge.destination);
        }
    }

    /**
     * Spanning Tree of an undirected graph (Using DFS)
     *
     * TC: O(E)
     * SC: O(V)
     */
    public static void spanningTreeDFS(AdjacencyListWeightedGraph graph) {
        Set<Edge> spanningTree = new LinkedHashSet<>();
        for(int i=0; i<graph.vertex; i++) {
            if(!graph.visited[i]) {
                graph.visited[i] = true;
                spanningTreeDFSUtil(i, graph, spanningTree);
            }
        }

        for(Edge edge : spanningTree) {
            System.out.println(edge.source +" "+edge.destination);
        }
    }

    private static void spanningTreeDFSUtil(int s, AdjacencyListWeightedGraph graph, Set<Edge> set) {
        List<Edge> list = graph.adjListArray[s];
        for(Edge e: list) {
            if(!graph.visited[e.destination]) {
                graph.visited[e.destination] = true;
                set.add(e);
                spanningTreeDFSUtil(e.destination, graph, set);
            }
        }
    }

    /**
     * Spanning Tree of an undirected graph (Using BFS)
     *
     * TC: O(E)
     * SC: O(V)
     */
    public static void spanningTreeBFS(AdjacencyListWeightedGraph graph) {
        Set<Edge> spanningTree = new LinkedHashSet<>();
        for(int i=0; i<graph.vertex; i++) {
            if(!graph.visited[i]) {
                graph.visited[i] = true;
                spanningTreeBFSUtil(i, graph, spanningTree);
            }
        }

        for(Edge edge : spanningTree) {
            System.out.println(edge.source +" "+edge.destination);
        }
    }

    private static void spanningTreeBFSUtil(int s, AdjacencyListWeightedGraph graph, Set<Edge> spanningTree) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while(!queue.isEmpty()) {
            Integer current = queue.remove();
            graph.visited[current] = true;
            List<Edge> list = graph.adjListArray[current];
            for(Edge edge : list) {
                if(!graph.visited[edge.destination]) {
                    spanningTree.add(edge);
                    graph.visited[edge.destination] = true;
                    queue.add(edge.destination);
                }
            }
        }
    }

    /**
     * Number of Operations to Make Network Connected
     *
     * You are given a graph with n vertices and m edges.
     * You can remove one edge from anywhere and add that edge between any two vertices in one operation.
     * Find the minimum number of operations that will be required to make the graph connected.
     * If it is not possible to make the graph connected, return -1.
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static int makeConnected(int n, int[][] connections) {
        QuickUnionWeightedSet set = new QuickUnionWeightedSet();
        set.makeSet(n);
        int e = connections.length;
        int extraEdge = 0;
        for(int i=0; i<e; i++) {
            int s = connections[i][0];
            int d = connections[i][1];
            if(set.find(s) != set.find(d)) {
                set.unionByWeight(s,d);
            } else {
                extraEdge++;
            }
        }
        int components=0;
        for(int i=0; i<n; i++) {
            if(set.s[i] == i) {
                components++;
            }
        }
        if(components - 1 <= extraEdge) {
            return components-1;
        }
        return -1;
    }

    /**
     * Most Stones Removed with Same Row or Column
     *
     * There are n stones at some integer coordinate points on a 2D plane. Each coordinate point may have at most one stone.
     * You need to remove some stones.
     * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
     * Given an array of stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
     * return the maximum possible number of stones that you can remove.
     *
     * TC: O(klogk + n), k is maxRow+maxCol+2
     * SC: O(k)
     */
    public static int removeStones(int[][] stones) {
        int n = stones.length;
        int maxRow = 0;
        int maxCol = 0;
        for(int i=0; i<n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }
        int size = maxRow+maxCol+2;
        QuickUnionWeightedSet set = new QuickUnionWeightedSet();
        set.makeSet(size);
        Set<Integer> involvedNodes = new HashSet<>();
        for(int i=0; i<n; i++) {
            int r = stones[i][0];
            int c = stones[i][1] + maxRow+1;
            set.unionByRank(r,c);
            involvedNodes.add(r);
            involvedNodes.add(c);
        }
        int components = 0;
        for(Integer i : involvedNodes) {
            if(set.s[i] == i) {
                components++;
            }
        }
        return n-components;
    }

    /**
     * Account Merge
     *
     * Given a list of accounts where each element account [ i ] is a list of strings,
     * where the first element account [ i ][ 0 ]  is a name, and the rest of the elements are emails representing emails of the account.
     *
     * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts.
     * Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
     * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
     *
     * After merging the accounts, return the accounts in the following format:
     * the first element of each account is the name, and the rest of the elements are emails in sorted order.
     *
     * N -> no of accounts
     * E -> avg no of emails/account
     * TC: O(NE*logNE) + O(NE) + O(N*ElogE + NE)
     * SC: O(NE)
     */
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        QuickUnionWeightedSet set = new QuickUnionWeightedSet();
        set.makeSet(n);
        Map<String, Integer> mailMap = new HashMap<>();
        for(int i=0; i<n; i++) {
            List<String> list = accounts.get(i);
            for(int j=1; j<list.size(); j++) {
                String mail = list.get(j);
                if(mailMap.containsKey(mail)) {
                    set.unionByRank(i, mailMap.get(mail));
                } else {
                    mailMap.put(mail, i);
                }
            }
        }
        List<List<String>> mergedMails = new ArrayList<>();
        for(int i=0; i<n; i++) {
            mergedMails.add(new ArrayList<>());
        }
        for(Map.Entry<String, Integer> entry : mailMap.entrySet()) {
            mergedMails.get(set.find(entry.getValue())).add(entry.getKey());
        }
        List<List<String>> res = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(mergedMails.get(i).isEmpty()) {
                continue;
            }
            List<String> list = new ArrayList<>();
            list.add(accounts.get(i).get(0));
            Collections.sort(mergedMails.get(i));
            list.addAll(mergedMails.get(i));
            res.add(list);
        }
        return res;
    }

    /**
     * Number of Islands II
     *
     * ou are given an n, m which means the row and column of the 2D matrix, and an array of size k denoting the number of operations.
     * Matrix elements are 0 if there is water or 1 if there is land.
     * Originally, the 2D matrix is all 0 which means there is no land in the matrix.
     * The array has k operator(s) and each operator has two integers A[i][0],
     * A[i][1] means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island.
     * Return how many islands are there in the matrix after each operation. You need to return an array of size k.
     *
     * TC: O(q), q is no of queries.
     * SC: O(mn)
     */
    public static List<Integer> numOfIslands(int m, int n, int[][] operators) {
        boolean[][] visited = new boolean[m][n];
        List<Integer> ans = new ArrayList<>();
        QuickUnionWeightedSet set = new QuickUnionWeightedSet();
        set.makeSet(m*n);
        int count = 0;
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};
        for(int i=0; i<operators.length; i++) {
            int r = operators[i][0];
            int c = operators[i][1];
            if(visited[r][c]) {
                ans.add(count);
                continue;
            }
            visited[r][c] = true;
            count++;
            int setIndex = r*n + c;
            for(int k=0; k<4; k++) {
                int nr = r + delRow[k];
                int nc = c + delCol[k];
                if(nr >= 0 && nc >= 0 && nr < m && nc < n && visited[nr][nc]) {
                    int nSetIndex = nr * n + nc;
                    if(set.find(nSetIndex) != set.find(setIndex)) {
                        count--;
                        set.unionByWeight(setIndex, nSetIndex);
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }
}
