package com.paul.subham.sorting;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] a = {5,4,3,2,1};
        sort(a);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }

    /**
     * TC: Worst - O(n^2)
     *     Average - O(nlogn)
     *     Best - O(nlogn)
     * SC: O(1)
     */
    static void sort(int[] a) {
        for(int gap = a.length/2; gap>0; gap/=2) {
            for(int i=gap; i<a.length; i++) {
                int v = a[i];
                int j = i;
                while(j>=gap && a[j-gap]>v) {
                    a[j] = a[j-gap];
                    j-=gap;
                }
                a[j] = v;
            }
        }
    }
}
