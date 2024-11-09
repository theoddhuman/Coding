package com.paul.subham.recursion;

import java.util.*;

/**
 * 1. Word Search
 * 2. N-Queens Problem
 * 3. Rat in a maze
 * 4. Word break (Backtracking, hashing)
 * 5. Word break (Backtracking, hashing, Memoization)
 * 6. Word break (Backtracking, hashing, Tabulation)
 * 7. M-coloring graph
 */
public class Backtracking {
    public static void main(String[] args) {

    }

    /**
     * Word Search
     *
     * Given an m x n grid of characters board and a string word, return true if the word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cells,
     * where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
     * Input:
     * [
     * ["A", "B", "C", "E"],
     * ["S", "F", "C", "S"],
     * ["A", "D", "E", "E"]
     * ]
     * word = "ABCCED"
     * Output: true
     *
     * TC: O(m*n*4^k)
     * SC: O(k)
     */
    public static boolean exist(char[][] board, String word) {
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};
        int m = board.length;
        int n = board[0].length;
        for(int i=0; i<m; i++) {
            for(int j=0;j<n;j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(exist(board,word,i,j, 0, delRow, delCol)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean exist(char[][] board, String word, int i, int j, int k, int[] delRow, int[] delCol) {
        if(k==word.length()) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        if(i<0 || j<0 || i>=m || j>=n) {
            return false;
        }
        if(board[i][j] != word.charAt(k)) {
            return false;
        }
        board[i][j] = '!';
        for(int p=0;p<4;p++) {
            int ni = i+delRow[p];
            int nj = j+delCol[p];
            if(exist(board,word,ni,nj, k+1, delRow, delCol)) {
                return true;
            }
        }
        board[i][j]=word.charAt(k);
        return false;
    }

    /**
     * N-Queens Problem
     *
     * queens attack diagonally, row-wise and column-wise
     *
     * TC: O(n!)
     * SC: O(n^2)
     */
    public static List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i=0;i<n;i++) {
            Arrays.fill(board[i],'.');
        }
        List<List<String>> result = new ArrayList<>();
        solveNQueensUtil(board,n,0,result);
        return result;
    }

    private static void solveNQueensUtil(char[][] board, int n, int col, List<List<String>> result) {
        if(col==n) {
            result.add(construct(board,n));
            return;
        }
        for(int row=0; row<n; row++) {
            if(isSafe(board,row,col,n)) {
                board[row][col] = 'Q';
                solveNQueensUtil(board,n,col+1,result);
                board[row][col] = '.';
            }
        }
    }

    private static List<String> construct(char[][] board, int n) {
        List<String> finalBoard = new ArrayList<>();
        for(int i=0; i<n; i++) {
            String s = "";
            for(int j=0; j<n; j++) {
                s += board[i][j];
            }
            finalBoard.add(s);
        }
        return finalBoard;
    }

    private static boolean isSafe(char[][] board, int r, int c, int n) {
        for(int i=r,j=c;i>=0&&j>=0;i--,j--) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        for(int i=r,j=c;i<n&&j>=0;i++,j--) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        for(int j=c;j>=0;j--) {
            if(board[r][j]=='Q') {
                return false;
            }
        }
        return true;
    }

    /**
     * Rat in a maze
     *
     * Consider a rat placed at (0, 0) in a square matrix of order N * N. It has to reach the destination at (N - 1, N - 1).
     * Find all possible paths that the rat can take to reach from source to destination.
     * The directions in which the rat can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right).
     * Value 0 at a cell in the matrix represents that it is blocked and the rat cannot move to it while value 1 at a cell in the matrix
     * represents that rat can travel through it.
     *
     * m[][] = {{1, 0, 0, 0},
     *         {1, 1, 0, 1},
     *         {1, 1, 0, 0},
     *         {0, 1, 1, 1}}
     * Output: DDRDRR DRDDRR
     *
     * TC: O(3^mn), the rat can move in 3 directions except the direction he has come from.
     * SC: O(mn)
     */
    public static ArrayList<String> findPath(int[][] mat) {
        ArrayList<String> res = new ArrayList<>();
        if(mat[0][0] == 1) {
            int n = mat.length;
            boolean[][] visited = new boolean[n][n];
            visited[0][0] = true;
            findPath(mat,n,0,0,visited,"", res);
        }
        return res;
    }

    private static void findPath(int[][] a, int n, int i,int j, boolean[][] visited, String move, ArrayList<String> res) {
        if(i==n-1 && j == n-1) {
            res.add(move);
            return;
        }
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};
        String del = "URDL";
        for(int k=0;k<4;k++) {
            int ni = i + delRow[k];
            int nj = j + delCol[k];
            if(ni >= 0 && nj >= 0 && ni<n && nj<n && !visited[ni][nj] && a[ni][nj] == 1) {
                visited[ni][nj] = true;
                findPath(a,n,ni,nj,visited,move+del.charAt(k),res);
                visited[ni][nj] = false;
            }
        }
    }

    /**
     * Word break (Backtracking, hashing)
     *
     * Given a string s and a dictionary of strings wordDict,
     * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
     * Input: s = "leetcode", wordDict = ["leet","code"]
     * Output: true
     *
     * TC: O(2^n * k), n length of string, k is the highest length of any string in dictionary
     * SC: O(n+mk), m is no of words in dictionary
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        return wordBreak(s,0,wordSet);
    }

    public static boolean wordBreak(String s, int i, Set<String> wordSet) {
        if(i==s.length()) {
            return true;
        }
        for(int k=i;k<s.length();k++) {
            if(wordSet.contains(s.substring(i,k+1))) {
                if(wordBreak(s,k+1,wordSet)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Word break (Backtracking, hashing, Memoization)
     *
     * TC: O(n^2 * k), n length of string, k is the highest length of any string in dictionary
     * SC: O(2n+mk), m is no of words in dictionary
     */
    public static boolean wordBreakMem(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Boolean[] dp = new Boolean[s.length()];
        return wordBreak(s,0,wordSet,dp);
    }

    public static boolean wordBreak(String s, int i, Set<String> wordSet, Boolean[] dp) {
        if(i==s.length()) {
            return true;
        }
        if(dp[i]!= null){
            return dp[i];
        }
        for(int k=i;k<s.length();k++) {
            if(wordSet.contains(s.substring(i,k+1))) {
                if(wordBreak(s,k+1,wordSet,dp)) {
                    return dp[i] = true;
                }
            }
        }
        return dp[i] = false;
    }

    /**
     * Word break (Backtracking, hashing, Tabulation)
     *
     * TC: O(n^2 * k), n length of string, k is the highest length of any string in dictionary
     * SC: O(n+mk), m is no of words in dictionary
     */
    public static boolean wordBreakTab(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[n] = true;
        for(int i=n-1;i>=0;i--) {
            for(int j=i;j<n;j++) {
                if(wordSet.contains(s.substring(i,j+1)) && dp[j+1]) {
                    dp[i] = true;
                }
            }
        }
        return dp[0];
    }

    /**
     * M-coloring graph
     *
     * You are given an undirected graph consisting of v vertices and a list of edges, along with an integer m.
     * Your task is to determine whether it is possible to color the graph using at most m different colors such that
     * no two adjacent vertices share the same color.
     *
     * TC: O((v^m * v) + v^2) = O(v^m), v^m -> trying m possibilities for v vertices, v for safe check operations,
     *                                  v^2 -> for creating graph
     * SC: O(v^2+v) = O(v^2), v^2 -> graph, v -> for recursion
     */
    public static boolean graphColoring(int v, List<int[]> edges, int m) {
        // code here
        List<List<Integer>> adj = new ArrayList<>(v);
        for(int i = 0; i<v; i++) {
            adj.add(new ArrayList<>());
        }
        for(int i=0; i<edges.size(); i++) {
            adj.get(edges.get(i)[0]).add(edges.get(i)[1]);
            adj.get(edges.get(i)[1]).add(edges.get(i)[0]);
        }
        int[] color = new int[v];
        Arrays.fill(color, -1);
        return gColoring(adj,0,m,color);
    }

    private static boolean gColoring(List<List<Integer>> adj, int s, int m, int[] color) {
        if(s==adj.size()) {
            return true;
        }
        for(int c=0; c<m; c++) {
            if(isSafe(adj,c,s,color)) {
                color[s] = c;
                if(gColoring(adj,s+1,m,color)) {
                    return true;
                }
                color[s] = -1;
            }
        }
        return false;
    }

    private static boolean isSafe(List<List<Integer>> adj, int c, int s, int[] color) {
        for(Integer i : adj.get(s)) {
            if(color[i] == c) {
                return false;
            }
        }
        return true;
    }
}
