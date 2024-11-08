package com.paul.subham.recursion;

/**
 * 1. Word Search
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
}
