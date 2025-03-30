package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListGraph;

import java.util.*;

/**
 * 1. Counting no of tress in a forest (Using DFS)
 * 2. Counting no of tress in a forest (Using BFS)
 * 3. Rotting oranges (BFS)
 * 4. Flood fill (BFS)
 * 5. Flood fill (DFS)
 * 6. 0/1 Matrix (BFS)
 * 7. Surrounded Regions (DFS)
 * 8. Surrounded Regions (BFS)
 * 9. Number of Enclaves (flood fill implementation - multi source) (DFS)
 * 10. Word Ladder - I (BFS)
 * 11. Word Ladder - II (BFS+DFS)
 * 12. Number of Distinct Islands (Multi-source)(DFS)
 */
public class Traversal {
    public static void main(String[] args) {
        int[][] grid =  {
                {0, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 0}
        };
        System.out.println(numIslands(grid));
    }

    /**
     * Counting no of tress in a forest (Using DFS)
     *
     * TC: O(V+E)
     * SC: O(V)
     */
    public static int countTreesInForestDFS(AdjacencyListGraph graph) {
        int trees = 0;
        for (int i = 0; i < graph.vertex; i++) {
            if (!graph.visited[i]) {
                graph.DFS(i);
                trees++;
            }
        }
        return trees;
    }

    /**
     * Counting no of tress in a forest (Using BFS)
     *
     * TC: O(V+E)
     * SC: O(V)
     */
    public static int countTreesInForestBFS(AdjacencyListGraph graph) {
        int trees = 0;
        for (int i = 0; i < graph.vertex; i++) {
            if (!graph.visited[i]) {
                graph.BFS(i);
                trees++;
            }
        }
        return trees;
    }

    /**
     * Rotting oranges (BFS)
     *
     * You are given an m x n grid where each cell can have one of three values:
     *
     * 0 representing an empty cell,
     * 1 representing a fresh orange, or
     * 2 representing a rotten orange.
     * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
     *
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<IndexPair> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new IndexPair(i, j));
                }
            }
        }
        int[] delRow = { -1, 0, 1, 0 };
        int[] delCol = { 0, 1, 0, -1 };
        int time = 0;
        while (!queue.isEmpty()) {
            int nodeCount = queue.size();
            while (nodeCount-- > 0) {
                IndexPair current = queue.remove();
                int r = current.r;
                int c = current.c;
                for (int i = 0; i < 4; i++) {
                    int nr = r + delRow[i];
                    int nc = c + delCol[i];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        queue.add(new IndexPair(nr, nc));
                    }
                }
            }
            if(!queue.isEmpty()) {
                time++;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return time;
    }

    /**
     * Flood fill (BFS)
     *
     * You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image.
     * You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].
     *
     * To perform a flood fill:
     * a. Begin with the starting pixel and change its color to color.
     * b. Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel,
     *    either horizontally or vertically) and shares the same color as the starting pixel.
     * c. Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color
     *    if it matches the original color of the starting pixel.
     * d. The process stops when there are no more adjacent pixels of the original color to update.
     *
     * Return the modified image after performing the flood fill.
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        Queue<IndexPair> queue = new LinkedList<>();
        int preColor = image[sr][sc];
        queue.add(new IndexPair(sr,sc));
        while(!queue.isEmpty()) {
            IndexPair current = queue.remove();
            int i = current.r;
            int j = current.c;
            if(image[i][j] == color) {
                continue;
            }
            image[i][j] = color;
            if(i-1>=0 && image[i-1][j] == preColor) {
                queue.add(new IndexPair(i-1,j));
            }
            if(i+1<m && image[i+1][j] == preColor) {
                queue.add(new IndexPair(i+1,j));
            }
            if(j-1>=0 && image[i][j-1] == preColor) {
                queue.add(new IndexPair(i,j-1));
            }
            if(j+1<n && image[i][j+1] == preColor) {
                queue.add(new IndexPair(i,j+1));
            }
        }
        return image;
    }

    /**
     * Flood fill (DFS)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int[][] floodFillDFS(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};
        dfs(image,color,image[sr][sc],delRow,delCol,sr,sc,m,n);
        return image;
    }
    private static void dfs(int[][] image, int color, int preColor, int[] delRow, int[] delCol, int i, int j, int m, int n) {
        image[i][j] = color;
        for(int k=0; k<4; k++) {
            int ni = i + delRow[k];
            int nj = j + delCol[k];
            if(ni>=0 && ni<m && nj>=0 && nj<n && image[ni][nj] == preColor && image[ni][nj] != color) {
                dfs(image,color,preColor,delRow,delCol,ni,nj,m,n);
            }
        }
    }

    /**
     * 0/1 Matrix (BFS)
     *
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     * The distance between two adjacent cells is 1.
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] distance = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<IndexPair> queue = new LinkedList<>();
        for(int i=0; i<m;i++) {
            for(int j=0; j<n;j++) {
                if(mat[i][j] == 0) {
                    queue.add(new IndexPair(i,j));
                    visited[i][j] = true;
                }
            }
        }
        int dist = 0;
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};
        while(!queue.isEmpty()) {
            int nodeCount = queue.size();
            while(nodeCount >0) {
                IndexPair current = queue.remove();
                int i = current.r;
                int j = current.c;
                distance[i][j] = dist;
                for(int k=0;k<4;k++) {
                    int ni = i+delRow[k];
                    int nj = j+delCol[k];
                    if(ni<m && ni>=0 && nj<n && nj>=0 && !visited[ni][nj]) {
                        queue.add(new IndexPair(ni,nj));
                        visited[ni][nj] = true;
                    }
                }
                nodeCount--;
            }
            dist++;
        }
        return distance;
    }

    /**
     * Surrounded Regions (DFS)
     *
     * Given a matrix mat of size M x N where every element is either ‘O’ or ‘X’.
     * Replace all ‘O’ with ‘X’ that is surrounded by ‘X’.
     * An ‘O’ (or a set of ‘O’) is considered to be surrounded by ‘X’ if there are ‘X’ at locations just below, just above just left, and just right of it.
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};
        for(int i=0; i<n; i++) {
            if(!visited[0][i] && board[0][i] == 'O') {
                dfs(board,0,i,visited,delRow,delCol,m,n);
            }
            if(!visited[m-1][i] && board[m-1][i] == 'O') {
                dfs(board,m-1,i,visited, delRow, delCol,m,n);
            }
        }
        for(int i=0; i<m; i++) {
            if(!visited[i][0] && board[i][0] == 'O') {
                dfs(board,i,0,visited,delRow,delCol,m,n);
            }
            if(!visited[i][n-1] && board[i][n-1] == 'O') {
                dfs(board,i,n-1,visited, delRow, delCol,m,n);
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {
                if(!visited[i][j] && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private static void dfs(char[][] board,int i, int j, boolean[][] visited, int[] delRow, int[] delCol, int m, int n) {
        visited[i][j]=true;
        for(int k=0;k<4;k++) {
            int ni = i+delRow[k];
            int nj = j+delCol[k];
            if(ni>=0 && nj>=0 && ni<m && nj<n && !visited[ni][nj] && board[ni][nj]=='O') {
                dfs(board,ni,nj,visited,delRow,delCol,m,n);
            }
        }
    }

    /**
     * Surrounded Regions (BFS)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static void solveBfs(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};
        for(int i=0; i<n; i++) {
            if(!visited[0][i] && board[0][i] == 'O') {
                visited[0][i]=true;
                bfs(board,0,i,visited,delRow,delCol,m,n);
            }
            if(!visited[m-1][i] && board[m-1][i] == 'O') {
                visited[m-1][i]=true;
                bfs(board,m-1,i,visited, delRow, delCol,m,n);
            }
        }
        for(int i=0; i<m; i++) {
            if(!visited[i][0] && board[i][0] == 'O') {
                visited[i][0]=true;
                bfs(board,i,0,visited,delRow,delCol,m,n);
            }
            if(!visited[i][n-1] && board[i][n-1] == 'O') {
                visited[i][n-1]=true;
                bfs(board,i,n-1,visited, delRow, delCol,m,n);
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {
                if(!visited[i][j] && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private static void bfs(char[][] board,int u, int v, boolean[][] visited, int[] delRow, int[] delCol, int m, int n) {
        Queue<IndexPair> queue = new LinkedList<>();
        queue.add(new IndexPair(u,v));
        while(!queue.isEmpty()){
            IndexPair current = queue.remove();
            int i = current.r;
            int j = current.c;
            for(int k=0;k<4;k++) {
                int ni = i+delRow[k];
                int nj = j+delCol[k];
                if(ni>=0 && nj>=0 && ni<m && nj<n && !visited[ni][nj] && board[ni][nj]=='O') {
                    queue.add(new IndexPair(ni,nj));
                    visited[ni][nj]=true;
                }
            }
        }
    }

    /**
     * Number of Enclaves (flood fill implementation - multi source) (DFS)
     *
     * You are given an M x N binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
     * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
     * Find the number of land cells in the grid for which we cannot walk off the boundary of the grid in any number of moves.
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};
        boolean[][] visited = new boolean[m][n];
        for(int i=0; i<m; i++) {
            if(!visited[i][0] && grid[i][0] == 1) {
                dfsEnclave(grid, i, 0, visited, delRow, delCol, m, n);
            }
            if(!visited[i][n-1] && grid[i][n-1] == 1) {
                dfsEnclave(grid, i, n-1, visited, delRow, delCol, m, n);
            }
        }
        for(int j=0; j<n; j++) {
            if(!visited[0][j] && grid[0][j] == 1) {
                dfsEnclave(grid, 0, j, visited, delRow, delCol, m, n);
            }
            if(!visited[m-1][j] && grid[m-1][j] == 1) {
                dfsEnclave(grid, m-1, j, visited, delRow, delCol, m, n);
            }
        }
        int count = 0;
        for(int i=0; i<m;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j] && grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfsEnclave(int[][] grid, int i, int j, boolean[][] visited, int[] delRow, int[] delCol, int m, int n) {
        visited[i][j] = true;
        for(int k=0; k<4; k++) {
            int ni= i+delRow[k];
            int nj = j+delCol[k];
            if(ni<m && nj<n && ni>=0 && nj>=0 && !visited[ni][nj] && grid[ni][nj]==1) {
                dfsEnclave(grid, ni, nj, visited, delRow, delCol, m, n);
            }
        }
    }

    /**
     * Word Ladder - I (BFS)
     *
     * Given are the two distinct words startWord and endWord, and a list denoting wordList of unique words of equal lengths. Find the length of the shortest transformation sequence from startWord to targetWord.
     *
     * In this problem statement, we need to keep the following conditions in mind:
     * a. A word can only consist of lowercase characters.
     * b. Only one letter can be changed in each transformation.
     * c. Each transformed word must exist in the wordList including the targetWord.
     * d. startWord may or may not be part of the wordList
     *
     * TC: O(26nk), k is word length
     * SC: O(n)
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        for(String s : wordList) {
            set.add(s);
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int transformCount = 1;
        while(!queue.isEmpty()) {
            int nodeCount = queue.size();
            while(nodeCount-- > 0) {
                String current = queue.remove();
                if(current.equals(endWord)) {
                    return transformCount;
                }
                for(int i=0; i<current.length(); i++) {
                    for(char c='a';c<='z';c++) {
                        char[] charArray = current.toCharArray();
                        charArray[i] = c;
                        String s = new String(charArray);
                        if(set.contains(s)) {
                            set.remove(s);
                            queue.add(s);
                        }
                    }
                }

            }
            transformCount++;
        }
        return 0;
    }

    /**
     * Word Ladder - II (BFS+DFS)
     *
     * Return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists
     *
     * TC: O(26nk + (26nk+n)n), k is word length
     * SC: O(n^2)
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for(String s : wordList) {
            set.add(s);
        }
        queue.add(beginWord);
        map.put(beginWord,1);
        set.remove(beginWord);
        int transformCount = 1;
        while(!queue.isEmpty()) {
            int nodeCount = queue.size();
            while(nodeCount-- > 0) {
                String current = queue.remove();
                if(current.equals(endWord)) {
                    break;
                }
                for(int i=0; i<current.length(); i++) {
                    for(char c='a';c<='z';c++) {
                        char[] charArray = current.toCharArray();
                        charArray[i] = c;
                        String s = new String(charArray);
                        if(set.contains(s)) {
                            set.remove(s);
                            queue.add(s);
                            map.put(s,transformCount+1);
                        }
                    }
                }
            }
            transformCount++;
        }
        List<List<String>> result = new ArrayList<>();
        System.out.println(map);
        if(map.containsKey(endWord)) {
            List<String> sequence = new ArrayList<>();
            sequence.add(endWord);
            dfs(endWord, beginWord, map, sequence, result);
        }
        return result;
    }

    private static void dfs(String word, String beginWord, Map<String, Integer> map, List<String> sequence, List<List<String>> result) {
        if(word.equals(beginWord)) {
            List<String> res = new ArrayList<>(sequence);
            Collections.reverse(res);
            result.add(res);
            return;
        }
        int level = map.get(word);
        for(int i=0; i<word.length(); i++) {
            for(char c = 'a'; c<='z';c++) {
                char[] charArray = word.toCharArray();
                charArray[i] = c;
                String s = new String(charArray);
                if(map.containsKey(s) && map.get(s) == level-1) {
                    sequence.add(s);
                    dfs(s, beginWord, map, sequence, result);
                    sequence.remove(sequence.size()-1);
                }
            }
        }
    }

    /**
     * Number of Distinct Islands (Multi-source)(DFS)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int numIslands(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};
        int count=0;
        for(int i=0; i<m; i++){
            for(int j=0;j<n;j++) {
                if(!visited[i][j] && a[i][j]==1) {
                    isIsland(a,i,j,visited,delRow,delCol);
                    count++;
                }
            }
        }
        return count;
    }

    private static void isIsland(int[][] a, int i, int j, boolean[][] visited, int[] delRow, int[] delCol) {
        int m = a.length;
        int n = a[0].length;
        for(int k=0; k<4; k++) {
            int ni = i+delRow[k];
            int nj = j+delCol[k];
            if(ni<m && nj<n && ni>=0 && nj>=0 && !visited[ni][nj] && a[ni][nj] == 1) {
                visited[ni][nj] = true;
                isIsland(a,ni,nj,visited,delRow,delCol);
            }
        }
    }
}

class IndexPair {
    int r;
    int c;

    IndexPair(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
