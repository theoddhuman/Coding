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
 * 7. Is armstrong number
 * 8. Is primary number
 * 9. Power of a number (Recursion)
 * 10. Power of a number (Iterative)
 */
public class Basic {
    public static void main(String[] args) {
        System.out.println(isPrime(110));
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

    /**
     * Is armstrong number
     *
     * An Armstrong number is a number that is equal to the sum of its own digits each raised to the power of the number of digits.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean isArmstrong(int num) {
        int n = num;
        int length = String.valueOf(n).length();
        int sum = 0;
        while( n> 0) {
            int digit = n%10;
            sum += (int) Math.pow(digit, length);
            n /= 10;
        }
        return sum == num;
    }

    /**
     * Is primary number
     *
     * TC: O(n^1/2)
     * SC: O(1)
     */
    public static boolean isPrime(int n) {
        if(n == 0 || n==1) {
            return false;
        }
        for(int i=2; i<=Math.sqrt(n); i++) {
            if(n%i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Power of a number (Recursion)
     *
     * TC: O(logp)
     * sc: O(logp)
     */
    public static double myPow(double x, int p) {
        double n = p;
        if(n<0) {
            return 1/pow(x,-n);
        }
        return pow(x,n);
    }

    private static double pow(double x, double n) {
        if(n==0) {
            return 1;
        }
        if(n%2==0) {
            return pow(x*x, n/2);
        }
        return x*pow(x,n-1);
    }

    /**
     * Power of a number (Iterative)
     *
     * TC: O(logp)
     * sc: O(logp)
     */
    public static double myPowIterative(double x, int p) {
        double n = p;
        if(n<0) {
            return 1/pow(x,-n);
        }
        double ans = 1.0;
        while(n > 0) {
            if(n%2==0) {
                x = x*x;
                n /= 2;
            } else {
                ans *= x;
                n -= 1;
            }
        }
        if(p<0) {
            return 1/ans;
        }
        return ans;
    }
}
