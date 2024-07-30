package com.paul.subham.matrix.operations;

/**
 * @author subham.paul
 * 
 * 1. Maximum size sub square matrix with all 1's
 * 2. Maximum size sub square matrix with all 1's (Space optimized)
 */
public class SubMatrix {
    public static void main(String[] args) {
        int[][] a = { { 0, 1, 1, 0, 1 }, { 1, 1, 0, 1, 0 },
                { 0, 1, 1, 1, 0 }, { 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0 } };
        System.out.println(maxSizeSubMatrixSpaceOptimized(a));
    }

    /**
     * Maximum size sub square matrix with all 1's
     * 
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static int maxSizeSubMatrix(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] sum = new int[m][n];
        int max = 0;
        for(int i=0; i<m; i++) {
            if(a[i][0] == 1) {
                sum[i][0] = a[i][0];
                max = 1;
            }
        }
        for(int i=0; i<n; i++) {
            if(a[0][i] == 1) {
                sum[0][i] = a[0][i];
                max = 1;
            }
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(a[i][j] == 1) {
                    sum[i][j] = Math.min(sum[i-1][j], Math.min(sum[i-1][j-1], sum[i][j-1])) + 1;
                    max = Math.max(max, sum[i][j]);
                }
            }
        }
        return max;
    }

    /**
     * Maximum size sub square matrix with all 1's (Space optimized)
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static int maxSizeSubMatrixSpaceOptimized(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] sum = new int[2][n];
        int maxLength = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                int x = a[i][j];
                if(x != 0 && j!= 0) {
                    x = Math.min(sum[0][j], Math.max(sum[0][j-1], sum[1][j-1])) + 1;
                }
                sum[0][j] = sum[1][j];
                sum[1][j] = x;
                maxLength = Math.max(maxLength, x);
            }
        }
        return maxLength;
    }
}
