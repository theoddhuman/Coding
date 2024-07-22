package com.paul.subham.matrix.operations;

/**
 * @author subham.paul
 * <p>
 * 1. Linear search in a matrix
 * 2. Linear search in a matrix (efficient)
 * 3. Binary search in a matrix (Typecasting 2D to 1D)
 * 4. Binary search in a matrix (Efficient)
 */
public class Searching {
    public static void main(String[] args) {
        int[][] a = {{0, 6, 8, 9, 11},
                {20, 22, 28, 29, 31},
                {36, 38, 50, 61, 63},
                {64, 66, 100, 122, 128}};
        System.out.println(binarySearchEfficient(a, 4, 5, 100));
    }

    /**
     * Linear search in a matrix
     * <p>
     * TC: O(n^2)
     * SC: O(1)
     */
    public static boolean searchLinear(int[][] a, int data) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == data) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Linear search in a matrix (efficient)
     * <p>
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean searchLinearEfficient(int[][] a, int data) {
        int i = 0;
        int j = a[0].length - 1;
        while (i < a.length && j >= 0) {
            if (a[i][j] == data) {
                return true;
            }
            if (a[i][j] > data) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    /**
     * Binary search in a matrix (Typecasting 2D to 1D)
     * <p>
     * TC: O(logm + logn)
     * SC: O(1)
     */
    public static boolean binarySearch(int[][] a, int m, int n, int data) {
        int iLow = 0;
        int iHigh = m - 1;
        int jMid = n / 2;
        // Condition to terminate the loop when the 2 desired rows are found
        while (iLow + 1 < iHigh) {
            int iMid = (iLow + iHigh) / 2;
            if (a[iMid][jMid] == data) {
                return true;
            }
            if (a[iMid][jMid] < data) {
                iLow = iMid;
            } else {
                iHigh = iMid;
            }
        }
        if (a[iLow][jMid] == data) {
            return true;
        } else if (a[iHigh][jMid] == data) {
            return true;
        }
        // first half first row
        if (data < a[iLow][jMid]) {
            return binarySearch(a, iLow, 0, jMid - 1, data);
        } else if (data > a[iLow][jMid] && data <= a[iLow][n - 1]) { // second half first row
            return binarySearch(a, iLow, jMid + 1, n - 1, data);
        } else if (data < a[iHigh][jMid]) { // first half second row
            return binarySearch(a, iHigh, 0, jMid - 1, data);
        } else { //second half second row
            return binarySearch(a, iHigh, jMid + 1, n - 1, data);
        }
    }

    private static boolean binarySearch(int[][] a, int i, int jLow, int jHigh, int data) {
        while (jLow <= jHigh) {
            int mid = (jLow + jHigh) / 2;
            if (a[i][mid] == data) {
                return true;
            }
            if (a[i][mid] < data) {
                jLow = mid + 1;
            } else {
                jHigh = mid - 1;
            }
        }
        return false;
    }

    /**
     * Binary search in a matrix (Efficient)
     * <p>
     * TC: O(log(m+n))
     * SC: O(1)
     */
    private static boolean binarySearchEfficient(int[][] a, int m, int n, int data) {
        int start = 0;
        int end = m * n - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int i = mid / n;
            int j = mid % n;
            if (a[i][j] == data) {
                return true;
            }
            if (a[i][j] < data) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
