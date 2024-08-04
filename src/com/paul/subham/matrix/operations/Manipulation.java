package com.paul.subham.matrix.operations;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author subham.paul
 *
 * 1. Set entire matrix row and columns as zero (Marking elements)
 * 2. Set entire matrix row and columns as zero (Marking rows and columns)
 * 3. Set entire matrix row and columns as zero (Efficient)
 */
public class Manipulation {
    public static void main(String[] args) {
        int[][] a = { { 0, 1, 2, 0 },
                { 3, 4, 5, 2 },
                { 1, 3, 1, 5 } };
        setZeros(a);
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
     * SC: O(n)
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

}
