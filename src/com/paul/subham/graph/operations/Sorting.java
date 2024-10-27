package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListGraph;
import com.paul.subham.graph.implementation.AdjacencyListWeightedGraph;
import com.paul.subham.graph.implementation.Edge;

import java.util.*;

/**
 * 1. Topological Sort (BFS)(Kahn's Algorithm)
 * 2. Topological Sort (DFS)
 * 3. Topological Sort - weighted graph (DFS)
 * 4. Course Schedule I (DFS)
 * 5. Course Schedule II (BFS)
 * 6. Find Eventual Safe States (BFS - Topological Sort)
 * 7. Alien Dictionary - (Topological Sort)
 */
public class Sorting {
    public static void main(String[] args) {
        AdjacencyListWeightedGraph graph = new AdjacencyListWeightedGraph(10);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 6);
        topologicalSort(graph);
    }

    /**
     * Topological Sort (BFS)(Kahn's Algorithm)
     * TC: O(V+E)
     * SC: O(V)
     */
    public static void topologicalSort(AdjacencyListWeightedGraph graph) {
        int[] indegree = new int[graph.vertex];
        int[] rank = new int[graph.vertex];
        int counter = 0;
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> order = new ArrayList<>();
        for(int i=0; i<graph.vertex; i++) {
            List<Edge> adjList = graph.adjListArray[i];
            for(Edge edge : adjList) {
                indegree[edge.destination]++;
            }
        }
        for(int i=0; i<graph.vertex; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int current = queue.remove();
            rank[current] = ++counter;
            order.add(current);
            List<Edge> adjList = graph.adjListArray[current];
            for(Edge edge : adjList) {
                if(--indegree[edge.destination] == 0) {
                    queue.add(edge.destination);
                }
            }
        }

        if(counter != graph.vertex) {
            System.out.println("Cyclic Graph");
            return;
        }

        for(Integer i : order) {
            System.out.println(i);
        }
    }

    /**
     * Topological Sort (DFS)
     * TC: O(V+E)
     * SC: O(V)
     */
    static int[] topoSort(AdjacencyListGraph graph) {
        int v = graph.vertex;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<v; i++) {
            if(!graph.visited[i]) {
                DFS(i, stack, graph);
            }
        }
        int[] res = new int[v];
        int i = 0;
        while(!stack.isEmpty()) {
            res[i++] = stack.pop();
        }
        return res;
    }

    private static void DFS(int s, Stack<Integer> stack, AdjacencyListGraph graph) {
        graph.visited[s] = true;
        LinkedList<Integer> adjList = graph.adjListArray[s];
        for(Integer i: adjList) {
            if(!graph.visited[i]) {
                DFS(i, stack, graph);
            }
        }
        stack.push(s);
    }

    /**
     * Topological Sort - weighted graph (DFS)
     * TC: O(V+E)
     * SC: O(V)
     */
    public static int[] topoSort(AdjacencyListWeightedGraph graph) {
        int v = graph.vertex;
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];
        for(int i=0; i<v; i++) {
            if(!graph.visited[i]) {
                dfsTopo(graph, i, visited, stack);
            }
        }
        int[] res = new int[v];
        int i = 0;
        while(!stack.isEmpty()) {
            res[i++] = stack.pop();
        }
        return res;
    }

    public static void dfsTopo(AdjacencyListWeightedGraph graph, int s, boolean[] visited, Stack<Integer> stack) {
        visited[s] = true;
        for(Edge edge : graph.adjListArray[s]) {
            if(!visited[edge.destination]) {
                dfsTopo(graph,edge.destination,visited,stack);
            }
        }
        stack.push(s);
    }

    /**
     * Course Schedule I (DFS)
     *
     * There are a total of N tasks, labeled from 0 to N-1. Some tasks may have prerequisites,
     * for example, to do task 0 you have to first complete task 1, which is expressed as a pair: [0, 1]
     * Given the total number of tasks N and a list of prerequisite pairs P, find if it is possible to finish all tasks.
     *
     * TC: O(2V+(V+E))
     * SC: O(E+3V), E-> to create adjacency list, V-> visited, recStack and recursive stack
     */
    public static boolean canFinish(int numCourses, int[][] a) {
        List<List<Integer>> adjList = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        for(int i=0; i<numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i<a.length; i++) {
            adjList.get(a[i][0]).add(a[i][1]);
        }
        for(int i=0; i<numCourses; i++) {
            if(!visited[i]) {
                if(isCyclic(i, recStack, visited, adjList)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCyclic(int s, boolean[] recStack, boolean[] visited, List<List<Integer>> adjList) {
        visited[s] = true;
        recStack[s] = true;
        List<Integer> list = adjList.get(s);
        for(Integer i : list) {
            if(!visited[i]) {
                if(isCyclic(i, recStack, visited, adjList)) {
                    return true;
                }
            } else {
                if(recStack[i]) {
                    return true;
                }
            }
        }
        recStack[s] = false;
        return false;
    }

    /**
     * Course Schedule II (BFS)
     *
     * There are a total of n tasks you have to pick, labeled from 0 to n-1.
     * Some tasks may have prerequisites tasks, for example, to pick task 0 you have to first finish tasks 1, which is expressed as a pair: [0, 1]
     *
     * Given the total number of n tasks and a list of prerequisite pairs of size m.
     * Find the order of tasks you should pick to finish all tasks.
     *
     * TC: O(2V+(V+E))
     * SC: O(E+V), E-> to create adjacency list, V-> visited, recStack and recursive stack
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0;i<numCourses;i++) {
            adjList.add(new ArrayList<>());
        }
        for(int i=0;i<prerequisites.length;i++) {
            adjList.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        int[] indegree = new int[numCourses];
        for(int i=0; i<numCourses; i++) {
            List<Integer> list = adjList.get(i);
            for(Integer k : list) {
                indegree[k]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                queue.add(i);
            }
        }
        List<Integer> order = new ArrayList<>();
        while(!queue.isEmpty()) {
            int current = queue.remove();
            order.add(current);
            List<Integer> list = adjList.get(current);
            for(Integer i : list) {
                if(--indegree[i] == 0) {
                    queue.add(i);
                }
            }
        }
        if(order.size() != numCourses) {
            return new int[0];
        }
        return order.stream().mapToInt(i->i).toArray();
    }

    /**
     * Find Eventual Safe States (BFS - Topological Sort)
     *
     * A directed graph of V vertices and E edges is given in the form of an adjacency list adj.
     * Each node of the graph is labeled with a distinct integer in the range 0 to V - 1.
     * A node is a terminal node if there are no outgoing edges.
     * A node is a safe node if every possible path starting from that node leads to a terminal node.
     * You have to return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static List<Integer> eventualSafeNodes(int v, List<List<Integer>> adj1) {
        List<List<Integer>> adj = transpose(adj1,v);
        int[] indegree = new int[v];
        for(int i=0;i<v;i++) {
            List<Integer> list = adj.get(i);
            for(Integer k: list){
                indegree[k]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<v;i++){
            if(indegree[i]==0) {
                queue.add(i);
            }
        }
        List<Integer> order = new LinkedList<>();
        while(!queue.isEmpty()) {
            int current = queue.remove();
            order.add(current);
            List<Integer> list = adj.get(current);
            for(Integer i: list){
                if(--indegree[i] == 0){
                    queue.add(i);
                }
            }
        }
        Collections.sort(order);
        return order;
    }

    private static List<List<Integer>> transpose(List<List<Integer>> adj, int v) {
        List<List<Integer>> tAdj = new ArrayList<>();
        for(int i=0; i<v;i++) {
            tAdj.add(new ArrayList<>());
        }
        for(int i=0; i<v; i++) {
            List<Integer> list = adj.get(i);
            for(Integer k : list) {
                tAdj.get(k).add(i);
            }
        }
        return tAdj;
    }


    /**
     * Alien Dictionary - (Topological Sort)
     *
     * Given a sorted dictionary of an alien language having N words and k starting alphabets of a standard dictionary.
     * Find the order of characters in the alien language.
     * Input:  n = 5, k = 4, dict = {"baa","abcd","abca","cab","cad"}
     * Output: 1
     * Explanation: Here order of characters is 'b', 'd', 'a', 'c'
     * Note that words are sorted and in the given language "baa" comes before "abcd", therefore 'b' is before 'a' in output.
     * Similarly, we can find other orders.
     *
     * TC: O(n*len+K^2)
     * SC: O(k^2)
     */
    public static String findOrder(String[] dict, int n, int k) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<k;i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];
            int len = Math.min(s1.length(), s2.length());
            for (int ptr = 0; ptr < len; ptr++) {
                if (s1.charAt(ptr) != s2.charAt(ptr)) {
                    adj.get(s1.charAt(ptr) - 'a').add(s2.charAt(ptr) - 'a');
                    break;
                }
            }
        }
        List<Integer> topo = topoSort(k, adj);
        String ans = "";
        for (int it : topo) {
            ans = ans + (char)(it + (int)('a'));
        }
        return ans;
    }

    private static List<Integer> topoSort(int V, List<List<Integer>> adj) {
        int indegree[] = new int[V];
        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int current = q.remove();
            topo.add(current);
            for (int it : adj.get(current)) {
                if (--indegree[it] == 0) {
                    q.add(it);
                }
            }
        }
        return topo;
    }
}
