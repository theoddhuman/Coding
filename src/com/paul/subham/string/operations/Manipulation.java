package com.paul.subham.string.operations;

import java.util.HashMap;
import java.util.Map;

/**
 * @author subham.paul
 *
 * 1. Remove specified characters from a string
 */
public class Manipulation {
    public static void main(String[] args) {
        char[] res = removeSpecificChars("hellobaby".toCharArray(), new char[]{'l','o','a'});
        System.out.println(String.valueOf(res));
    }

    /**
     * Remove specified characters from a string
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static char[] removeSpecificChars(char[] str, char[] removeChars) {
        Map<Character, Boolean> removalMap = new HashMap<>();
        for (char removeChar : removeChars) {
            removalMap.put(removeChar, true);
        }
        int j = 0;
        for(int i=0; i<str.length; i++) {
            if(!removalMap.containsKey(str[i])) {
                str[j++] = str[i];
            }
        }
        char[] res = new char[j];
        System.arraycopy(str, 0, res, 0, j);
        return res;
    }
}
