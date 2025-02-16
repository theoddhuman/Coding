package com.paul.subham.string.operations;

/**
 * 1. Roman to integer
 * 2. Sum of beauty of all strings
 * 3. Minimum add to make a parenthesis valid
 * 4. Count and say
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

    /**
     * Minimum add to make a parenthesis valid
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int minAddToMakeValid(String s) {
        int leftCount = 0;
        int rightCount = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                leftCount++;
            } else {
                if(leftCount > 0) {
                    leftCount--;
                } else {
                    rightCount++;
                }
            }
        }
        return leftCount + rightCount;
    }

    /**
     * Count and say
     *
     * Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical characters
     * (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters
     * (length of the run). For example, to compress the string "3322251" we replace "33" with "23", replace "222" with "32",
     * replace "5" with "15" and replace "1" with "11". Thus the compressed string becomes "23321511".
     *
     * Given a positive integer n, return the nth element of the count-and-say sequence.
     *
     * Input: n = 4
     * Output: "1211"
     * Explanation:
     * countAndSay(1) = "1"
     * countAndSay(2) = RLE of "1" = "11"
     * countAndSay(3) = RLE of "11" = "21"
     * countAndSay(4) = RLE of "21" = "1211"
     *
     * TC: O(4^(n/3)
     * SC: O(4^(n/3)
     */
    public static String countAndSay(int n) {
        String s = "1";
        while(--n > 0) {
            String t = "";
            char current = s.charAt(0);
            int count = 1;
            for(int i=1; i<s.length(); i++) {
                if(s.charAt(i) == current) {
                    count++;
                } else {
                    t = t + count + current;
                    current = s.charAt(i);
                    count=1;
                }
            }
            t = t + count + current;
            s = t;
        }
        return s;
    }
}
