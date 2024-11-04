package com.paul.subham.matrix.operations;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author subham.paul
 *
 * 1. Set entire matrix row and columns as zero (Marking elements)
 * 2. Set entire matrix row and columns as zero (Marking rows and columns)
 * 3. Set entire matrix row and columns as zero (Efficient)
 * 4. Rotate matrix clockwise by one element
 * 5. Transpose of a matrix
 * 6. Rotate matrix clockwise 90 degree (Extra space)
 * 7. Rotate matrix clockwise 90 degree (Rotate one by one)
 * 8. Rotate matrix clockwise 90 degree (Transpose and reverse row)
 */
public class Manipulation {
    public static void main(String[] args) {
        int[][] a = { { 0, 1, 2, 0 },
                      { 3, 4, 5, 2 },
                      { 1, 3, 1, 5 },
                      { 4, 6, 7, 8}};
        rotateMatrixClockWise90(a);
        for(int i=0; i<a.length;i++) {
            for(int j=0; j<a[0].length;j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Set entire matrix row and columns as zero (Marking elements)
     *
     * if an element is zero set the whole column and row of that element as zero.
     *
     * TC: O(m*n(m+n) + m*n) = O(n^3)
     * SC: O(1)
     */
    public static void setZeros(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    //mark row
                    for (int k = 0; k < n; k++) {
                        if(a[i][k] != 0) {
                            a[i][k] = -1;
                        }
                    }
                    //mark column
                    for (int k = 0; k < m; k++) {
                        if(a[k][j] != 0) {
                            a[k][j] = -1;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == -1) {
                    a[i][j] = 0;
                }
            }
        }
    }

    /**
     * Set entire matrix row and columns as zero (Marking rows and columns)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void setZerosMarkingRowColumn(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        Set<Integer> rows = new LinkedHashSet<>();
        Set<Integer> columns = new LinkedHashSet<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(a[i][j]==0) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
        for(Integer i: rows) {
            for(int j=0; j<n; j++) {
                a[i][j] = 0;
            }
        }
        for(Integer i: columns) {
            for(int j=0;j<m;j++) {
                a[j][i] = 0;
            }
        }
    }

    /**
     * Set entire matrix row and columns as zero (Efficient)
     *
     * 1st column is used to track 0 in all rows.
     * 1st row is used to track 0 in all columns, but as we are considering first element at (0,0) twice, so to track 1st column
     * take a variable c0.
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public void setZeroes(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int c0 = 1;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(a[i][j]==0) {
                    a[i][0] = 0;
                    if(j==0) {
                        c0 = 0;
                    } else {
                        a[0][j] = 0;
                    }
                }
            }
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(a[i][0] == 0 || a[0][j]==0) {
                    a[i][j] = 0;
                }
            }
        }
        if(a[0][0] == 0) {
            for(int i=0;i<n;i++) {
                a[0][i] = 0;
            }
        }
        if(c0==0) {
            for(int i=0;i<m;i++) {
                a[i][0]=0;
            }
        }
    }

    /**
     * Rotate matrix clockwise by one element
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void rotateByOneClockwise(int[][] a) {
        int row = 0, col = 0;
        int prev, curr;
        int m=a.length-1;
        int n = a[0].length-1;

        /*
        row - Starting row index
        m - ending row index
        col - starting column index
        n - ending column index
        */
        while (row < m && col < n) {
            prev = a[row + 1][col];
            for (int i = col; i <= n; i++) {
                curr = a[row][i];
                a[row][i] = prev;
                prev = curr;
            }
            row++;
            for (int i = row; i <= m; i++) {
                curr = a[i][n];
                a[i][n] = prev;
                prev = curr;
            }
            n--;
            for (int i = n; i >= col; i--) {
                curr = a[m][i];
                a[m][i] = prev;
                prev = curr;
            }
            m--;
            for (int i = m; i >= row; i--) {
                curr = a[i][col];
                a[i][col] = prev;
                prev = curr;
            }
            col++;
        }
    }

    /**
     * Transpose of a matrix
     *
     * TC: O(m*n)
     * SC: O(1)
     */
    public static int[][] transpose(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] transpose = new int[n][m];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                transpose[j][i] = a[i][j];
            }
        }
        return transpose;
    }

    /**
     * Rotate matrix clockwise 90 degree (Extra space)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int[][] rotateMatrixClock90(int[][] a) {
        int n = a.length;
        int[][] r = new int[n][n];
        for(int i=0; i<n;i++) {
            for(int j=0; j<n; j++) {
                r[j][n-1-i] = a[i][j];
            }
        }
        return r;
    }

    /**
     * Rotate matrix clockwise 90 degree (Rotate one by one)
     *
     * TC: O(n^3)
     * SC: O(1)
     */
    public void rotate(int[][] a) {
        int start = 0;
        int end = a[0].length-1;
        int temp;
        while(start < end) {
            for(int j=0; j<end-start; j++) {
                temp = a[end][end];
                for(int i=end;i>start; i--) {
                    a[i][end] = a[i-1][end];
                }
                for(int i=end; i>start; i--) {
                    a[start][i] = a[start][i-1];
                }
                for(int i=start; i<end; i++) {
                    a[i][start] = a[i+1][start];
                }
                for(int i=start; i<end-1; i++) {
                    a[end][i] = a[end][i+1];
                }
                a[end][end-1] = temp;
            }
            start++;
            end--;
        }
    }

    /**
     * Rotate matrix clockwise 90 degree (Transpose and reverse row)
     *
     * TC: O(n^3)
     * SC: O(1)
     */
    public static void rotateMatrixClockWise90(int[][] a) {
        int n = a.length;
        int temp;
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<n/2; j++) {
                temp = a[i][j];
                a[i][j] = a[i][n-j-1];
                a[i][n-j-1] = temp;
            }
        }
    }





}
