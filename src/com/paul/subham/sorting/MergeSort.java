package com.paul.subham.sorting;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] a = {5,4,3,2,7, 11, 5, 2,1};
        sort(a, 0, a.length-1);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }

    /**
     * TC: Worst - O(nlogn)
     *     Average - O(nlogn)
     *     Best - O(nlogn)
     * SC: O(n)
     */
    static void sort(int[] a, int left, int right) {
        if(right > left) {
            int mid = (right-left)/2 + left;
            sort(a, left, mid);
            sort(a, mid+1, right);
            merge(a, left, mid+1, right);
        }
    }

    static void merge(int[] a, int left, int mid, int right) {
        int leftEnd = mid-1;
        int size = right - left + 1;
        int[] temp = new int[size];
        int tempPos = 0;
        while(left <= leftEnd && mid <= right) {
            if(a[left] <= a[mid]) {
                temp[tempPos] = a[left];
                tempPos++;
                left++;
            } else {
                temp[tempPos] = a[mid];
                tempPos++;
                mid++;
            }
        }
        while(left <= leftEnd) {
            temp[tempPos] = a[left];
            tempPos++;
            left++;
        }
        while(mid <= right) {
            temp[tempPos] = a[mid];
            tempPos++;
            mid++;
        }
        for(int i=size-1; i>=0; i--) {
            a[right] = temp[i];
            right--;
        }
    }
}
