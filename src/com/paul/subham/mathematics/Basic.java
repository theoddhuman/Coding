package com.paul.subham.mathematics;

/**
 * @author subham.paul
 *
 * 1. Find GCD (Naive approach)
 * 2. Find GCD (Euclidean Algorithm)
 * 3. Find GCD (Euclidean Algorithm Optimized)
 * 4. Find GCD (Division)
 * 5. Find GCD (Division, Iterative)
 * 6. Decimal to binary
 */
public class Basic {
    public static void main(String[] args) {
        System.out.println(decimalToBinary(14));
    }


    /**
     * Find GCD (Naive approach)
     * TC: O(min(a,b))
     * SC: O(1)
     */
    public static int gcdNaive(int a, int b) {
        int gcd = Math.min(a, b);
        while (gcd > 0) {
            if (a % gcd == 0 && b % gcd == 0) {
                break;
            }
            gcd--;
        }
        return gcd;
    }

    /**
     * Find GCD (Euclidean Algorithm)
     * TC: O(min(a,b))
     * SC: O(1)
     */
    public static int gcdEuclidean(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a > b) {
            return gcdEuclidean(a - b, b);
        } else {
            return gcdEuclidean(a, b - a);
        }
    }

    /**
     * Find GCD (Euclidean Algorithm Optimized)
     * Optimized by checking divisibility
     * TC: O(min(a,b))
     * SC: O(1)
     */
    public static int gcdEuclideanOptimized(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a > b) {
            if (a % b == 0) {
                return b;
            }
            return gcdEuclideanOptimized(a - b, b);
        }
        if (b % a == 0) {
            return a;
        }
        return gcdEuclideanOptimized(a, b - a);
    }

    /**
     * Find GCD (Division)
     * TC: O(log(min(a,b)))
     * SC: O(log(min(a,b)))
     */
    public static int gcdUsingDivision(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcdUsingDivision(b, a % b);
        }
    }

    /**
     * Find GCD (Division, Iterative)
     * TC: O(log(min(a,b)))
     * SC: O(log(min(a,b)))
     */
    public static int gcdUsingDivisionIterative(int a, int b) {
        while (a > 0 && b > 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        if (b == 0) {
            return a;
        }
        return b;
    }

    /**
     * Decimal to binary
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static String decimalToBinary(int n) {
        if(n == 0) {
            return "0";
        }
        StringBuffer s = new StringBuffer();
        while(n > 0) {
            s.append(n%2);
            n /= 2;
        }
        return s.reverse().toString();
    }
}
