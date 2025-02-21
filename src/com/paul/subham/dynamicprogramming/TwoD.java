package com.paul.subham.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * @author subham.paul
 *
 * 1.*Ninja's Training (Memoization)
 * 2. Ninja's Training (Tabulation)
 * 3. Ninja's Training (Tabulation - Space optimized)
 * 4.*Count all possible paths from top left to bottom right of a matrix (Dynamic programming - memoization)
 * 5. Count all possible paths from top left to bottom right of a matrix (Dynamic programming - tabulation)
 * 6. Count all possible paths from top left to bottom right of a matrix (Dynamic programming - tabulation - space optimized)
 * 7. Count all possible paths from top left to bottom right of a matrix (Combinations)
 * 8.*Count all possible paths from top left to bottom right of a matrix of 0's and 1's (0 pass through)(dynamic Programming - Memoization)
 * 9. Count all possible paths from top left to bottom right of a matrix of 0's and 1's (1 pass through)(dynamic Programming - Tabulation)
 * 10. Minimum path sum in a grid (Memoization)
 * 11. Minimum path sum in a grid (Tabulation)
 * 12. Minimum path sum in a grid (Tabulation - Space optimized)
 * 13.*Minimum path sum in Triangular Grid (Memoization)
 * 14. Minimum path sum in Triangular Grid (Tabulation)
 * 15. Minimum path sum in Triangular Grid (Tabulation - Space optimized)
 * 16. Minimum falling path sum (Memoization)
 * 17. Minimum falling path sum (Tabulation)
 * 18. Minimum falling path sum (Tabulation - Space optimized)
 * 19. Ninja and his friends - chocolate pickup (Memoization)
 * 20. Ninja and his friends - chocolate pickup (Tabulation)
 * 21. Ninja and his friends - chocolate pickup (Tabulation - Space optimized)
 */
public class TwoD {
    public static void main(String[] args) {


    }

    /**
     * Ninja's Training (Memoization)
     *
     * A Ninja has an ‘N’ Day training schedule.
     * He has to perform one of these three activities each day.
     * There are merit points associated with performing an activity each day.
     * The same activity can’t be performed on two consecutive days.
     * We need to find the maximum merit points the ninja can attain in N Days.
     *
     * n=3 and arr[]= [[1,2,5],[3,1,1],[3,3,3]]
     * output: 11
     *
     * TC: O(3n)
     * SC: O(3n)
     */
    public int maximumPoints(int[][] arr, int N) {
        int[][] dp = new int[N][4];
        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }
        return maximumPoints(arr, N-1, -1, dp);
    }

    int maximumPoints(int[][] a, int i, int last, int[][] dp) {
        if(i<0) {
            return 0;
        }
        if(dp[i][last+1] != -1) {
            return dp[i][last+1];
        }
        int max = 0;
        for(int k=0; k<3; k++) {
            if(k != last) {
                max = Math.max(max, a[i][k] + maximumPoints(a, i-1, k, dp));
            }
        }
        return dp[i][last+1] = max;
    }

    /**
     * Ninja's Training (Tabulation)
     *
     * TC: O(3n)
     * SC: O(3n)
     */
    public static int maximumPointsTab(int[][] a, int N) {
        int[][] dp = new int[N][3];
        for(int i=0; i<3; i++) {
            dp[0][i] = a[0][i];
        }
        for(int i=1; i<N; i++) {
            for(int k=0; k<3; k++) {
                dp[i][k] = a[i][k] + Math.max(dp[i-1][(k+1)%3], dp[i-1][(k+2)%3]);
            }
        }
        return Math.max(dp[N-1][0], Math.max(dp[N-1][1], dp[N-1][2]));
    }

    /**
     * Ninja's Training (Tabulation - Space optimized)
     *
     * TC: O(3n)
     * SC: O(3*2)
     */
    public int maximumPointsTabSpace(int[][] a, int N) {
        int[] current = new int[3];
        int[] prev = new int[3];
        for(int i=0; i<3; i++) {
            prev[i] = a[0][i];
        }
        for(int i=1; i<N; i++) {
            for(int k=0; k<3; k++) {
                current[k] = a[i][k] + Math.max(prev[(k+1)%3], prev[(k+2)%3]);
            }
            int[] temp = prev;
            prev = current;
            current = temp;
        }
        return Math.max(prev[0], Math.max(prev[1], prev[2]));
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix (Dynamic programming - memoization)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int pathCountMemoization(int m, int n){
        int[][] dp = new int[m+1][n+1];
        return pathCount(m, n, dp);
    }

    private static int pathCount(int m, int n, int[][] dp) {
        if(m==1 || n==1) {
            return dp[m][n] = 1;
        }
        if(dp[m][n] != 0) {
            return dp[m][n];
        }
        return dp[m][n] = pathCount(m-1, n, dp) + pathCount(m, n-1, dp);
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix (Dynamic programming - tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    private static int pathCountTabulation(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++) {
            dp[i][0] = 1;
        }
        for(int i=0; i<n; i++) {
            dp[0][i] = 1;
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix (Dynamic programming - tabulation - space optimized)
     *
     * TC: O(mn)
     * SC: O(n)
     */
    private static int pathCountSpaceOptimized(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=0; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix (Combinations)
     *
     * can go to right n-1 ways
     * can go to down m-1 ways
     *
     * total possible ways
     * = ((m-1)+(n-1))! / ((m-1)! * (n-1)!)
     * = (m+n-2)!/ ((m-1)! * (n-1)!) = (m+n-2 C n-1)
     *
     *   1 * 2 * 3 * 4 * ....(n-1) * n * (n+1) * (n+2) * ...... * (m+n-2)
     * = -----------------------------------------------------------------
     *   1 * 2 * .... * (n-1) * (m-1)!
     *
     *    n * (n+1) * (n+2) * ...... * (m+n-2)
     * = ---------------------------------------
     *    1 * 2 * 3 * ... * (m-1)
     *
     * TC: O(m)
     * SC: O(1)
     */
    private static int pathCountCombinations(int m, int n) {
        int pathCount = 1;
        for(int i=1; i<=m-1; i++) {
            pathCount = pathCount * (i+n-1) / i;
        }
        return pathCount;
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix of 0's and 1's (0 pass through)(dynamic Programming - Memoization)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public int uniquePathsWithObstacles(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        return uniquePath(a, m-1, n-1, dp);
    }

    private int uniquePath(int[][] a, int i, int j, int[][] dp) {
        if(i==0 && j==0) {
            return 1 - a[i][j];
        }
        if(a[i][j] == 1) {
            return 0;
        }
        if(dp[i][j] != 0) {
            return dp[i][j];
        }
        if(i == 0) {
            return dp[i][j] = uniquePath(a, i, j-1, dp);
        }
        if(j==0) {
            return dp[i][j] = uniquePath(a, i-1, j, dp);
        }
        return dp[i][j] = uniquePath(a, i-1, j, dp) + uniquePath(a, i, j-1, dp);
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix of 0's and 1's (0 pass through)(dynamic Programming - Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int countPath1Tabulation(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1 - a[0][0];
        for(int i=1; i<m; i++) {
            if(a[i][0] == 0 && dp[i-1][0] == 1) {
                dp[i][0] = 1;
            }
        }
        for(int i=1; i<n; i++) {
            if(a[0][i] == 0 && dp[0][i-1] == 1) {
                dp[0][i] = 1;
            }
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(a[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * Minimum path sum in a grid (Memoization)
     *
     * Given an m x n grid filled with non-negative numbers, find a path from top left to bottom right,
     * which minimizes the sum of all numbers along its path.
     * only move either down or right at any point in time.
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int minPathSumMem(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        return minPathSum(a, m-1, n-1, dp);
    }

    private static int minPathSum(int[][] a, int i, int j, int[][] dp) {
        if(i==0 && j== 0) {
            return a[i][j];
        }
        if(dp[i][j] != 0) {
            return dp[i][j];
        }
        int childSum;
        if(i==0) {
            childSum =  minPathSum(a, i, j-1, dp);
        } else if(j==0) {
            childSum = minPathSum(a, i-1, j, dp);
        } else {
            childSum =  Math.min(minPathSum(a,i-1, j, dp), minPathSum(a, i, j-1, dp));
        }
        return dp[i][j] = a[i][j] + childSum;
    }

    /**
     * Minimum path sum in a grid (Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int minPathSumTab(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = a[0][0];
        for(int i=1; i<m; i++) {
            dp[i][0] = a[i][0] + dp[i-1][0];
        }
        for(int i=1; i<n; i++) {
            dp[0][i] = a[0][i] + dp[0][i-1];
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = a[i][j]+Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * Minimum path sum in a grid (Tabulation - Space optimized)
     *
     * TC: O(mn)
     * SC: O(n)
     */
    public int minPathSumSpaceOpt(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] dp = new int[n];
        dp[0] = a[0][0];
        for(int i=1; i<n; i++) {
            dp[i] = a[0][i] + dp[i-1];
        }
        for(int i=1; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(j==0) {
                    dp[j] = a[i][j] + dp[j];
                } else {
                    dp[j] = a[i][j]+Math.min(dp[j],dp[j-1]);
                }
            }
        }
        return dp[n-1];
    }

    /**
     * Minimum path sum in Triangular Grid (Memoization)
     *
     * Given a triangle array, return the minimum path sum from top to bottom.
     * if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
     *        2
     *       3 4
     *      6 5 7
     *     4 1 8 3
     *     min sum = 11 = 2 + 3 + 5 + 1
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static int minimumTotalMem(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        return minimumTotal(triangle, 0, 0, n, dp);
    }

    public static int minimumTotal(List<List<Integer>> triangle, int i, int j, int n, int[][] dp) {
        if(i==n-1) {
            return triangle.get(i).get(j);
        }
        if(dp[i][j] != 0) {
            return dp[i][j];
        }
        return dp[i][j] = triangle.get(i).get(j)
                + Math.min(minimumTotal(triangle, i+1, j, n, dp), minimumTotal(triangle, i+1, j+1, n, dp));
    }

    /**
     * Minimum path sum in Triangular Grid (Tabulation)
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public int minimumTotalTab(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for(int i=0; i<n; i++) {
            dp[n-1][i] = triangle.get(n-1).get(i);
        }
        for(int i=n-2;i>=0; i--) {
            for(int j=triangle.get(i).size()-1; j>=0; j--) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i+1][j], dp[i+1][j+1]);
            }
        }
        return dp[0][0];
    }

    /**
     * Minimum path sum in Triangular Grid (Tabulation - Space optimized)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public int minimumTotalTabSpaceOpt(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for(int i=0; i<n; i++) {
            dp[i] = triangle.get(n-1).get(i);
        }
        for(int i=n-2;i>=0; i--) {
            for(int j=0; j<triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }
        return dp[0];
    }

    /**
     * Minimum falling path sum (Memoization)
     *
     * We are given an m x n matrix. We need to find the minimum path sum from any cell of the first row to any cell of the last row
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int minFallingPathSum(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            min = Math.min(min, minFallingPathSum(a, m-1, i, dp));
        }
        return min;
    }

    private static int minFallingPathSum(int[][] a, int i, int j, int[][] dp) {
        if(j == -1 || j == a.length) {
            return Integer.MAX_VALUE;
        }
        if(i==0) {
            return a[i][j];
        }
        if(dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }
        return dp[i][j] = a[i][j] + Math.min(minFallingPathSum(a, i-1, j-1, dp),
                Math.min(minFallingPathSum(a,i-1, j, dp), minFallingPathSum(a, i-1, j+1, dp)));
    }

    /**
     * Minimum falling path sum (Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int minFallingPathSumTab(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        for(int i=0; i<n; i++) {
            dp[0][i] = a[0][i];
        }
        for(int i=1; i<m; i++) {
            for(int j=0; j<n; j++) {
                int childSum;
                if(j == 0) {
                    childSum = Math.min(dp[i-1][j], dp[i-1][j+1]);
                } else if (j==n-1) {
                    childSum = Math.min(dp[i-1][j-1], dp[i-1][j]);
                } else {
                    childSum = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i-1][j+1]));
                }
                dp[i][j] = a[i][j] + childSum;
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            min = Math.min(min, dp[m-1][i]);
        }
        return min;
    }

    /**
     * Minimum falling path sum (Tabulation - Space optimized)
     *
     * TC: O(mn)
     * SC: O(n)
     */
    public static int minFallingPathSumTabSpaceOpt(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] prev = new int[n];
        int[] curr = new int[n];
        for(int i=0; i<n; i++) {
            prev[i] = a[0][i];
        }
        for(int i=1; i<m; i++) {
            for(int j=0; j<n; j++) {
                int childSum;
                if(j == 0) {
                    childSum = Math.min(prev[j], prev[j+1]);
                } else if (j==n-1) {
                    childSum = Math.min(prev[j-1], prev[j]);
                } else {
                    childSum = Math.min(prev[j-1], Math.min(prev[j], prev[j+1]));
                }
                curr[j] = a[i][j] + childSum;
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            min = Math.min(min, prev[i]);
        }
        return min;
    }

    /**
     * Ninja and his friends - chocolate pickup (Memoization)
     *
     * We are given an ‘M*N’ matrix. Every cell of the matrix has some chocolates on it, a[i][j] gives us the number of chocolates.
     * We have two friends ‘Alice’ and ‘Bob’. initially, Alice is standing on the cell(0,0) and Bob is standing on the cell(0, M-1).
     * Both of them can move only to the cells below them in these three directions: to the bottom cell (↓),
     * to the bottom-right cell(↘), or to the bottom-left cell(↙).
     *
     * When Alica and Bob visit a cell, they take all the chocolates from that cell with them.
     * It can happen that they visit the same cell, in that case, the chocolates need to be considered only once.
     *
     * They cannot go out of the boundary of the given matrix,
     * we need to return the maximum number of chocolates that Bob and Alice can together collect.
     *
     * TC: O(9mn^2)
     * SC: O(mn^2)
     */
    public static int maximumChocolates(int r, int c, int[][] a) {
        // Write your code here.
        int[][][] dp = new int[r][c][c];
        return max(a, 0, 0, c-1, r,c, dp);
    }

    private static int max(int[][] a, int i, int aj, int bj, int m, int n, int[][][] dp) {
        if(aj < 0 || aj > n-1 || bj < 0 || bj > n-1) {
            return 0;
        }
        if(i==m-1) {
            if(aj==bj) {
                return a[i][aj];
            } else {
                return a[i][aj] + a[i][bj];
            }
        }
        if(dp[i][aj][bj] != 0) {
            return dp[i][aj][bj];
        }
        int max = Integer.MIN_VALUE;
        for(int p=-1; p<=1; p++) {
            for(int q=-1; q<=1; q++) {
                max = Math.max(max, max(a, i+1, aj+p, bj+q, m, n, dp));
            }
        }

        if(aj==bj) {
            return dp[i][aj][bj] = a[i][aj] + max;
        }
        return dp[i][aj][bj] = a[i][aj] + a[i][bj] + max;
    }

    /**
     * Ninja and his friends - chocolate pickup (Tabulation)
     *
     * TC: O(9mn^2)
     * SC: O(mn^2)
     */
    public static int maximumChocolatesTab(int m, int n, int[][] a) {
        int[][][] dp = new int[m][n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i==j) {
                    dp[m-1][i][j] = a[m-1][i];
                } else {
                    dp[m-1][i][j] = a[m-1][i] + a[m-1][j];
                }
            }
        }

        for(int i=m-2; i>=0; i--) {
            for(int aj=0; aj<n; aj++) {
                for(int bj=n-1; bj>=0; bj--) {
                    int max = Integer.MIN_VALUE;
                    for(int p=-1; p<=1; p++) {
                        for(int q=-1; q<=1; q++) {
                            if((aj+p <0) || (aj+p >= n) || (bj+q < 0) || (bj+q >= n)) {
                                max = Math.max(max, Integer.MIN_VALUE);
                            } else {
                                max = Math.max(max, dp[i+1][aj+p][bj+q]);
                            }
                        }
                    }
                    if(aj == bj) {
                        dp[i][aj][bj] = a[i][aj] + max;
                    } else {
                        dp[i][aj][bj] = a[i][aj] + a[i][bj] + max;
                    }
                }
            }
        }
        return dp[0][0][n-1];
    }

    /**
     * Ninja and his friends - chocolate pickup (Tabulation - Space optimized)
     *
     * TC: O(9mn^2)
     * SC: O(n^2)
     */
    public static int maximumChocolatesTabSpaceOpt(int m, int n, int[][] a) {
        int[][] pre = new int[n][n];
        int[][] cur = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i==j) {
                    pre[i][j] = a[m-1][i];
                } else {
                    pre[i][j] = a[m-1][i] + a[m-1][j];
                }
            }
        }

        for(int i=m-2; i>=0; i--) {
            for(int aj=0; aj<n; aj++) {
                for(int bj=n-1; bj>=0; bj--) {
                    int max = Integer.MIN_VALUE;
                    for(int p=-1; p<=1; p++) {
                        for(int q=-1; q<=1; q++) {
                            if((aj+p <0) || (aj+p >= n) || (bj+q < 0) || (bj+q >= n)) {
                                max = Math.max(max, Integer.MIN_VALUE);
                            } else {
                                max = Math.max(max, pre[aj+p][bj+q]);
                            }
                        }
                    }
                    if(aj == bj) {
                        cur[aj][bj] = a[i][aj] + max;
                    } else {
                        cur[aj][bj] = a[i][aj] + a[i][bj] + max;
                    }
                }
            }
            for(int aj=0; aj<n; aj++) {
                for(int bj=0; bj<n; bj++) {
                    pre[aj][bj] = cur[aj][bj];
                }
            }
        }
        return pre[0][n-1];
    }
}
