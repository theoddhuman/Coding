package com.paul.subham.matrix.operations;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Print a matrix in spiral order
 */
public class Traversal {
    public static void main(String[] args) {

    }

    /**
     * Print a matrix in spiral order
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public List<Integer> spiralOrder(int[][] a) {
        int m = a.length-1;
        int n = a[0].length-1;
        int row = 0;
        int col = 0;
        List<Integer> list = new ArrayList<>();
        while(row <= m && col <= n) {
            for(int i=col;i<=n;i++) {
                list.add(a[row][i]);
            }
            row++;
            for(int i=row;i<=m;i++) {
                list.add(a[i][n]);
            }
            n--;
            if(row <= m) {
                for(int i=n;i>=col;i--) {
                    list.add(a[m][i]);
                }
                m--;
            }
            if(col <= n) {
                for(int i=m;i>=row;i--) {
                    list.add(a[i][col]);
                }
                col++;
            }
        }
        return list;
    }
}
