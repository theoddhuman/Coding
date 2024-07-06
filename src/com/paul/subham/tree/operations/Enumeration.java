package com.paul.subham.tree.operations;

/**
 * @author subham.paul
 *
 * 1. Catalan number
 * 2. Catalan number (Dynamic programming)
 *
 *
 * A binary tree is labeled if every node is assigned a label and a binary tree is unlabeled if nodes are not assigned any label.
 *
 * No of binary trees with n nodes,
 * Let T(n) be count for n nodes.
 * T(0) = 1, 1 empty tree
 * T(1) = 1
 * T(2) = 2
 * T(3) = T(0)*T(2) + T(1)*T(1) + T(2)*T(0) = 1*2 + 1*1 + 2*1 = 5
 * T(4) = T(0)*T(3) + T(1)*T(2) + T(2)*T(1) + T(3)*T(0) = 1*5 + 1*2 + 2*1 + 5*1 = 14
 * T(n) = sum[(i=1 to n](T(i-1)*T(n-i)) = sum[i=0 to n-1](T(i)*T(n-i-1))
 * T(i) -> no of nodes of left child tree
 * T(n-1-i) -> no of nodes right child tree
 * T(n) = (1/n+1)(2n C n) = 2n!/((n+1)! * n!), This is called Catalan number
 *
 * No of labeled binary trees, T(n) = (2n!/((n+1)! * n!)) * n! = 2n! / (n+1)!
 *
 */

public class Enumeration {
    public static void main(String[] args) {
        System.out.println(catalanDp(4));
    }

    /**
     * Catalan number
     *
     * TC: O(2n!/((n+1)! * n!)) ~ O(3^n)
     * SC: O(1)
     */
    public static int catalan(int n) {
        if(n <= 1) {
            return 1;
        }
        int res = 0;
        for(int i=0; i<n; i++) {
            res += catalan(i) * catalan(n-1-i);
        }
        return res;
    }

    /**
     * Catalan number (Dynamic programming)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int catalanDp(int n) {
        int[] cat = new int[n+2];
        cat[0] = cat[1] = 1;
        for(int i=2; i<=n; i++) {
            cat[i] = 0;
            for(int j=0; j<i; j++) {
                cat[i] += cat[j] * cat[i-j-1];
            }
        }
        return cat[n];
    }
}
