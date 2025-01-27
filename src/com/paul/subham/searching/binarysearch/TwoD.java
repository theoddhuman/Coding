package com.paul.subham.searching.binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1. Row with maximum 1s
 * 2. Search in a 2D matrix
 * 3. Search in a row and column wise sorted matrix
 * 4. Peak element in a matrix
 */
public class TwoD {
    public static void main(String[] args) {

    }


    /**
     * Row with maximum 1s
     *
     * TC: O(mlogn)
     * SC: O(1)
     */
    public int rowWithMax1s(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int max = 0;
        int maxIndex = -1;
        for(int i=0; i<m; i++) {
            int index = OneD.firstOccurrenceIterative(arr[i], 1);
            if(index != - 1 && max < n-index) {
                max = n-index;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Search in a 2D matrix
     *
     * TC: O(log(mn))
     * SC: O(1)
     */
    public boolean searchMatrix(int[][] a, int target) {
        int m = a.length;
        int n = a[0].length;
        int low = 0;
        int high = m*n-1;
        while(low <= high) {
            int mid = low + (high-low)/2;
            int r = mid/n;
            int c = mid%n;
            if(a[r][c] == target) {
                return true;
            } else if(a[r][c] > target) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return false;
    }

    /**
     * Search in a row and column wise sorted matrix
     *
     * TC: O(m+n)
     * SC: O(1)
     */
    public static boolean searchMatrixII(int[][] a, int target) {
        int m = a.length;
        int n = a[0].length;
        int r = 0;
        int c = n-1;
        while(r<m && c>=0) {
            if(a[r][c] == target) {
                return true;
            } else if(a[r][c] < target) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }

    /**
     * Peak element in a matrix
     *
     * TC: O(nlogm)
     * SC: O(1)
     */
    public static int[] findPeakGrid(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int low = 0;
        int high = n-1;
        while(low <= high) {
            int mid = low + (high-low)/2;
            int maxRow = 0;
            int max = a[0][mid];
            for(int i=1; i<m; i++) {
                if(a[i][mid] > max) {
                    max = a[i][mid];
                    maxRow = i;
                }
            }
            int left = mid-1 >= 0 ? a[maxRow][mid-1] : -1;
            int right = mid+1 < n ? a[maxRow][mid+1] : -1;
            if(a[maxRow][mid] > left && a[maxRow][mid] > right) {
                return new int[]{maxRow, mid};
            } else if (a[maxRow][mid] < left) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * Median of row-wise sorted matrix
     *
     * TC: O(mn * (mn*log(mn))
     * SC: O(1)
     */
    public static int median(int a[][]) {
        int m = a.length;
        int n = a[0].length;
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                lst.add(a[i][j]);
            }
        }
        Collections.sort(lst);
        return lst.get((m * n) / 2);
    }
}
