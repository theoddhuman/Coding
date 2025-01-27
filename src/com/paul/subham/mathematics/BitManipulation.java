package com.paul.subham.mathematics;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Is kth bit set
 * 2. Setting kth bit
 * 3. Clearing kth bit
 * 4. Toggling kth bit
 * 5. Toggling rightmost one bit
 * 6. Isolating rightmost one bit
 * 7. Isolating rightmost zero bit
 * 8. Is the number power of two
 * 9. Counting number of 1 bit in a number (By processing bit)
 * 10. Counting number of 1 bit in a number (Toggling approach)
 * 11. Counting number of 1 bit in a number (Modulo approach)
 * 12. Masking trailing zeros
 * 13. Count no of set bits from  1 to n
 * 14. Set the rightmost unset bit
 * 15. Swap two numbers
 */

public class BitManipulation {

    public static void main(String[] args) {
        System.out.println(maskTrailingZeros(12));
    }

    /**
     * Is kth bit set
     */
    public static boolean isKthBitSet(int n, int k) {
        return  (n & (1 << k-1)) > 0;
    }

    /**
     * Setting kth bit
     */
    public static int setKthBit(int n, int k) {
        return n | (1 << k-1);
    }

    /**
     * Clearing kth bit
     */
    public static int clearKthBit(int n, int k) {
        return n & (~(1 << k-1));
    }

    /**
     * Toggling kth bit
     */
    public static int toggleKthBit(int n, int k) {
        return n ^ (1 << k -1);
    }

    /**
     * Toggling rightmost one bit
     */
    public static int toggleRightMostOneBit(int n) {
        return n & n-1;
    }

    /**
     * Isolating rightmost one bit
     */
    public static int isolateRightMostOneBit(int n) {
        return n & -n;
    }

    /**
     * Isolating rightmost zero bit
     */
    public static int isolateRightMostZeroBit(int n) {
        return ~n & n+1;
    }

    /**
     * Is the number power of two
     */
    public static boolean isPowerOfTwo(int n) {
        return (n & n-1) == 0;
    }

    /**
     * Counting number of 1 bit in a number (By processing bit)
     * TC: O(n)
     */
    public static int countOneByProcessingBit(int n) {
        int count = 0;
        while(n > 0) {
            count += n&1;
            n >>= 1;
        }
        return count;
    }

    /**
     * Counting number of 1 bit in a number (Toggling approach)
     * TC: O(n)
     */
    public static int countOneByToggling(int n) {
        int count = 0;
        while(n > 0) {
            count ++;
            n &= n-1;
        }
        return count;
    }

    /**
     * Counting number of 1 bit in a number (Modulo approach)
     * TC: O(n)
     */
    public static int countOneByModulo(int n) {
        int count = 0;
        while(n > 0) {
            if(n%2 == 1) {
                count++;
            }
            n /= 2;
        }
        return count;
    }

    /**
     * Masking trailing zeros
     */
    public static int maskTrailingZeros(int n) {
        return (n & -n) - 1;
    }

    /**
     * Count no of set bits from  1 to n
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int countSetBits(int n){
        int count = 0;
        n++;
        for(int i=1; i<n; i = i*2) {
            int quotient = n / (2*i);
            count += quotient * i;
            int remainder = n % (2*i);
            if(remainder > i) {
                count += remainder - i;
            }
        }
        return count;
    }

    /**
     * Set the rightmost unset bit
     *
     * TC: O(1)
     * SC: O(1)
     */
    static int setBit(int n) {
        return n | (n+1);
    }

    /**
     * Swap two numbers
     *
     * TC: O(1)
     * SC: O(1)
     */
    static List<Integer> get(int a, int b) {
        List<Integer> list = new ArrayList<>();
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        list.add(a);
        list.add(b);
        return list;
    }
}
