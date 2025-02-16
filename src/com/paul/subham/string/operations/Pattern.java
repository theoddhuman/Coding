package com.paul.subham.string.operations;

/**
 * 1. Searching Pattern
 * 2. Searching Pattern (Rabin Karp Algorithm)
 * 3. Searching Pattern (RKnuth-Morris-Pratt (KMP) algorithm)
 * 4. Repeated String Match (Rabin-karp)
 * 5. Shortest Palindrome (Two pointer)
 */
public class Pattern {
    public static void main(String[] args) {
        System.out.println(searchRK("abcdgdhjd", "gdh"));
    }

    /**
     * Searching Pattern
     *
     * TC: O(m*(n-m+1))
     * SC: O(1)
     */
    public static int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        for(int i=0; i<=n-m; i++) {
            for(int j=0; j<m; j++) {
                if(pattern.charAt(j) != text.charAt(i+j)) {
                    break;
                }
                if(j==m-1) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Searching Pattern (Rabin Karp Algorithm)
     *
     * hash = (hash - (text[i - pattern_length] * (b^pattern_length - 1)) % p) * b + text[i]
     *
     * TC: O(mn), worst case
     * TC: O(m+n), avg case
     * SC: O(1)
     */
    public static int searchRK(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int q = 101;
        int d = 256;
        int hashP = 0;
        int hashT = 0;
        int h = 1;
        for(int i=0; i<m-1; i++) {
            h = (h*d)%q;
        }
        for(int i=0; i<m; i++) {
            hashT = (hashT*d + text.charAt(i)) % q;
            hashP = (hashP*d + pattern.charAt(i)) % q;
        }
        for(int i=0; i<=n-m; i++) {
            if(hashT == hashP) {
                for(int j= 0; j<m; j++) {
                    if(text.charAt(j+i) != pattern.charAt(j)) {
                        break;
                    }
                    if(j == m-1) {
                        return i;
                    }
                }
            }
            if(i < n-m) {
                hashT = ((hashT - text.charAt(i)*h)*d + text.charAt(i+m))%q;
                if(hashT < 0) {
                    hashT += q;
                }
            }
        }
        return -1;
    }

    /**
     * Searching Pattern (RKnuth-Morris-Pratt (KMP) algorithm)
     *
     *
     * TC: O(m+n)
     * SC: O(m)
     */
    public int serchPatternKMP(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] pTable = new int[m];
        int j = 0;
        int i = 1;
        while(i < m) {
            if(pattern.charAt(j) == pattern.charAt(i)) {
                pTable[i++] = ++j;
            } else {
                if (j != 0) {
                    j = pTable[j-1];
                } else {
                    pTable[i++]=0;
                }
            }
        }
        i = 0;
        j=0;
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
                if (j == m) {
                    return i - m;
                }
            } else {
                if (j != 0) {
                    j = pTable[j-1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    /**
     * Repeated String Match (Rabin-karp)
     *
     * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it.
     * If it is impossible for b to be a substring of a after repeating it, return -1.
     *
     * TC: O(m+n)
     * SC: O(1)
     */
    public static int repeatedStringMatch(String a, String b) {
        if(a.equals(b)) {
            return 1;
        }
        int count = 1;
        String source = a;
        while(source.length() < b.length()) {
            count++;
            source += a;
        }
        if(source == b) {
            return count;
        }
        if(searchRK(source, b) != -1) {
            return count;
        }
        if(searchRK(source+a, b) != -1) {
            return count+1;
        }
        return -1;
    }

    /**
     * Shortest Palindrome (Two pointer)
     *
     * You are given a string s. You can convert s to a palindrome by adding characters in front of it.
     * Return the shortest palindrome you can find by performing this transformation.
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static String shortestPalindrome(String s) {
        int n = s.length();
        if(n==0) {
            return s;
        }
        int left = 0;
        int right = n-1;
        while(right >= 0) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
            }
            right--;
        }
        if(left == n) {
            return s;
        }
        String nonPalindromeSuffix = s.substring(left);
        StringBuilder reverseSuffix = new StringBuilder(nonPalindromeSuffix).reverse();
        return reverseSuffix
                .append(shortestPalindrome(s.substring(0,left)))
                .append(nonPalindromeSuffix)
                .toString();
    }
}
