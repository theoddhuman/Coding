package com.paul.subham.tree.operations.generic;

/**
 * @author subham.paul
 * 
 * 1. Depth or height of array representation of generic tree
 * 2. Depth or height of array representation of generic tree (Dynamic Programming)
 */
public class Structure {
    public static void main(String[] args) {
        int[] a = {-1, 0, 1, 6, 6, 0, 0, 2, 7};
        System.out.println(depthFromGenericArrayDP(a));
    }

    /**
     * Depth or height of array representation of generic tree
     * 
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int depthFromGenericArray(int[] a) {
        int max = Integer.MIN_VALUE;
        int count = 0;
        int j;
        for(int i=0; i<a.length; i++) {
            count = 0;
            j = i;
            while(a[j] != -1) {
                count++;
                j = a[j];
            }
            max = Math.max(count, max);
        }
        return max;
    }

    /**
     * Depth or height of array representation of generic tree (Dynamic Programming)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int depthFromGenericArrayDP(int[] a) {
        int max = Integer.MIN_VALUE;
        int j;
        int[] c = new int[a.length];
        for(int i=0; i<a.length; i++) {
            j = i;
            while(a[j] != -1) {
                if(c[j] > 0) {
                    c[i] += c[j];
                    break;
                }
                c[i]++;
                j = a[j];
            }
            max = Math.max(c[i], max);
        }
        return max;
    }
}
