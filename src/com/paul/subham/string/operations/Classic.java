package com.paul.subham.string.operations;

/**
 * 1. Roman to integer
 * 2. Sum of beauty of all strings
 */
public class Classic {
    public static void main(String[] args) {

    }

    /**
     * Roman to integer
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int romanToInt(String s) {
        int num = 0;
        int ans = 0;
        for(int i=s.length()-1; i>=0; i--) {
            switch(s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if(4*num < ans) {
                ans -= num;
            } else {
                ans += num;
            }
        }
        return ans;
    }

    /**
     * Sum of beauty of all strings
     *
     * The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
     *
     * Given a string s, return the sum of beauty of all of its substrings.
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int beautySum(String s) {
        int result = 0;
        for(int i=0; i<s.length(); i++) {
            int[] freq = new int[26];
            int maxFreq = 0;
            for(int j=i; j<s.length(); j++) {
                int c = s.charAt(j)-'a';
                freq[c]++;
                maxFreq = Math.max(maxFreq, freq[c]);
                int minFreq = Integer.MAX_VALUE;
                for(int k=0; k<26; k++) {
                    if(freq[k] > 0) {
                        minFreq = Math.min(minFreq, freq[k]);
                    }
                }
                result += (maxFreq-minFreq);
            }
        }
        return result;
    }
}
