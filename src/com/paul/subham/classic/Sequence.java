package com.paul.subham.classic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author subham.paul
 *
 * 1. Print minimum number from given sequence
 * 2. Print minimum number from given sequence (Storing last index of I)
 * 3. Print minimum number from given sequence (Using stack)
 * 4. Print minimum number from given sequence (Using two pointer)
 * 5. Print minimum number from given sequence (Start with the smallest)
 * 6. Print minimum number from given sequence (Efficient)
 * 7. Print minimum number from given sequence (Substring reversal)
 */
public class Sequence {
    public static void main(String[] args) {
        System.out.println(minFromSequenceSubstringReversal("DDIDDIID"));
    }


/*
  Input: D        Output: 21
   Input: I        Output: 12
   Input: DD       Output: 321
   Input: II       Output: 123
   Input: DIDI     Output: 21435
   Input: IIDDD    Output: 126543
   Input: DDIDDIID Output: 321654798
 */
    /**
     * Print minimum number from given sequence
     *
     * Given a pattern containing only I’s and D’s.
     * I for increasing and D for decreasing.
     * Device an algorithm to print the minimum number following that pattern.
     * Digits from 1-9 and digits can’t repeat.
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static String minNumberFromSequence(String s) {
        String res = "";
        int countD;
        int max = 0;
        int last = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == 'I') {
                countD = 0;
                for(int j=i+1; j<s.length(); j++) {
                    if(s.charAt(j) != 'D') {
                        break;
                    }
                    countD++;
                }
                if(i==0) {
                    max = countD + 2;
                    res = res + 1 + max;
                } else {
                    max = countD + max + 1;
                    res = res + max;
                }
                last = max;
                for(int k = 0; k<countD; k++) {
                    res = res + (--last);
                    i++;
                }
            } else {
                if(i==0) {
                    countD = 0;
                    for(int j=i+1; j<s.length(); j++) {
                        if(s.charAt(j) != 'D') {
                            break;
                        }
                        countD++;
                    }
                    max = countD + 2;
                    res = res + max + (max-1);
                    last = max-1;
                } else {
                    res = res + (--last);
                }
            }
        }
        return res;
    }

    /**
     * Print minimum number from given sequence (Storing last index of I)
     *
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static String minFromSequence(String s) {
        List<Integer> list = new ArrayList<>();
        int minAvail = 0;
        int posI = 0;
        if(s.charAt(0) == 'D') {
            list.add(2);
            list.add(1);
            minAvail = 3;
        }
        if(s.charAt(0) == 'I') {
            list.add(1);
            list.add(2);
            minAvail = 3;
            posI = 1;
        }
        for(int i=1; i<s.length(); i++) {
            if(s.charAt(i) == 'I') {
                list.add(minAvail++);
                posI = i+1;
            } else {
                list.add(list.get(i));
                for(int j = posI; j<=i; j++) {
                    list.set(j, list.get(j) + 1);
                }
                minAvail++;
            }
        }
        return list.stream().map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * Print minimum number from given sequence (Using stack)
     *
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String minFromSequenceStack(String s) {
        Stack<Integer> stack = new Stack<>();
        String res = "";
        for(int i=0; i<=s.length(); i++) {
            stack.push(i+1);
            if(s.length() == i || s.charAt(i) == 'I') {
                while (!stack.isEmpty()) {
                    res = res + stack.pop();
                }
            }
        }
        return res;
    }

    /**
     * Print minimum number from given sequence (Using two pointer)
     *
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String minFromSequenceTwoPointer(String s) {
        char[] res = new char[s.length()+1];
        int count = 1;
        int lastIndexI = -1;
        for(int i=0; i<=s.length(); i++) {
            if(i==s.length() || s.charAt(i) == 'I') {
                for(int j=i; j>lastIndexI; j--) {
                    res[j] = (char)((int)'0' + count++);
                }
                lastIndexI = i;
            }
        }
        return new String(res);
    }

    /**
     * Print minimum number from given sequence (Start with the smallest)
     *
     * Start with the smallest number as the answer and keep shifting the digits when we encounter a D
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String minFromSequenceStartWithSmallest(String s) {
        List<Integer> list = new ArrayList<>(s.length()+1);
        for(int i=0; i<=s.length(); i++) {
            list.add(i+1);
        }
        String res = "";
        int posD = -1;
        for(int i=1; i<s.length(); i++) {
            if(s.charAt(i-1) == 'D') {
                if(posD < 0) {
                    posD = i-1;
                }
                int v = list.get(i);
                list.remove(i);
                list.add(posD, v);
            } else {
                posD = -1;
            }
        }
        for(Integer i : list) {
            res += i;
        }
        return res;
    }

    /**
     * Print minimum number from given sequence (Efficient)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static String minFromSequenceEfficient(String s) {
        String res = "";
        int countD = 0;
        int current = 1;
        int i = 0;
        int j;
        while(i<s.length()) {
            if(i==0 && s.charAt(i) == 'I') {
                res += current;
                current++;
            }
            if(s.charAt(i) == 'D') {
                countD++;
            }
            j=i+1;
            while(j<s.length() && s.charAt(j) == 'D') {
                countD++;
                j++;
            }
            int k = countD;
            while(countD >= 0) {
                res = res + (current + countD--);
            }
            current = current + k+1;
            countD = 0;
            i=j;
        }
        return res;
    }

    /**
     * Print minimum number from given sequence (Substring reversal)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static String minFromSequenceSubstringReversal(String s) {
        String res = "";
        int start = -1;
        int end = -1;
        for(int i=0; i<=s.length(); i++) {
            res += String.valueOf(i+1);
        }
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == 'D') {
                if(start == -1) {
                    start = i;
                }
                end = i;
            } else {
                if(start != -1) {
                    res = reverse(res, start, end+2);
                }
                start = end = -1;
            }
        }
        if(start != -1) {
            res = reverse(res, start, end+2);
        }
        return res;
    }

    private static String reverse(String str, int start, int end) {
        char[] arr = str.toCharArray();
        for (int i = start, j = end - 1; i < j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return new String(arr);
    }
}
