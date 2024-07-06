package com.paul.subham.mathematics;

/**
 * @author subham.paul
 *
 * 1. Binomial Coefficient
 * 2. Binomial Coefficient (Dynamic Programming)
 * 3. Binomial Coefficient (Space optimized)
 * 4. Binomial Coefficient (Memoization)
 */
public class Combination {
    public static void main(String[] args) {
        System.out.println(binomialCoefficientMemoization(2,2));
    }

    /**
     * Binomial Coefficient
     * (n C k) = (n-1 C k) + (n-1 C k-1), (n C 0) = 1, (n C n) = 1
     *
     * TC: O(2^n)
     * SC: O(1)
     */
    public static int binomialCoefficient(int n, int k) {
        if( k > n) {
            return 0;
        }
        if(k == 0 || k == n) {
            return 1;
        }
        return binomialCoefficient(n-1, k) + binomialCoefficient(n-1, k-1);
    }

    /**
     * Binomial Coefficient (Dynamic Programming)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static int binomialCoefficientDP(int n, int k) {
        int[][] binomialCoefficient = new int[n+1][k+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=Math.min(i, k); j++) {
                if(j==0 || j==i) {
                    binomialCoefficient[i][j] = 1;
                } else {
                    binomialCoefficient[i][j] = binomialCoefficient[i-1][j] + binomialCoefficient[i-1][j-1];
                }
            }
        }
        return binomialCoefficient[n][k];
    }

    /**
     * Binomial Coefficient (Space optimized)
     *
     * c[k] -> (n C k) -> C(n,k)
     * Start calculating from right c[k]
     *                      (0 C 0)
     *                      c[0] = 1
     *                  (1 C 0)     (1 C 1)
     *                  c[0] = 1    c[1]=c[1]+c[0]=1
     *           (2 C 0)       (2 C 1)          (2 C 2)
     *           c[0]=1      c[1]=c[1]+c[0]=2   c[2]=c[1]+c[0]=1
     *  (3 C 0)         (3 C 1)         (3 C 2)            (3 C 3)
     *  c[0]=1        c[1]=c[1]+c[0]=3  c[2]=c[2]+c[1]=3   c[3]=c[3]+c[2]=1
     *
     * TC: O(nk)
     * SC: O(k)
     */
    public static int binomialCoefficientSpaceOptimized(int n, int k) {
        int[] c = new int[k+1];
        c[0] = 1;
        for(int i=1; i<=n; i++) {
            for(int j = Math.min(i,k); j>0; j--) {
                c[j] = c[j] + c[j-1];
            }
        }
        return c[k];
    }

    /**
     * Binomial Coefficient (Memoization)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static int binomialCoefficientMemoization(int n, int k) {
        dp = new int[n+1][k+1];
        return bc(n,k);
    }
    private static int[][] dp;
    private static int bc(int n, int k) {
        if(dp[n][k] != 0) {
            return dp[n][k];
        }
        if(k==0 || k==n) {
            return dp[n][k] = 1;
        }
        return dp[n][k] = bc(n-1, k) + bc(n-1,k-1);
    }
}
