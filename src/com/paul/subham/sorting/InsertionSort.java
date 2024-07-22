package com.paul.subham.sorting;

/**
 * 1. Insertion sort
 * 2. No fo shifts in insertion sort
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] a = {5,4,3,2,1};
//        sort(a);
//        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
        System.out.println(shiftCount(a));
    }

    /**
     * TC: Worst - O(n^2)
     *     Average - O(n^2)
     *     Best - O(n)
     * SC: O(1)
     */
    static void sort(int[] a) {
        int v;
        int j;
        for(int i=1; i<a.length; i++) {
            v = a[i];
            j = i;
            while(j>=1 && a[j-1] > v) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
    }

    /**
     * No fo shifts in insertion sort
     * 
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int shiftCount(int[] a) {
        return shiftCountUtil(a, new int[a.length], 0, a.length-1);
    }

    private static int shiftCountUtil(int[] a, int[] temp, int left, int right) {
        int shift = 0;
        if(left < right) {
            int mid = (left + right) / 2;
            shift += shiftCountUtil(a, temp, left, mid);
            shift += shiftCountUtil(a, temp, mid+1, right);
            shift += mergeShiftCount(a, temp, left, mid, right);
        }
        return shift;
    }

    private static int mergeShiftCount(int[] a, int[] temp, int left, int mid, int right) {
        int shift = 0;
        int l = left;
        int r = mid+1;
        int k = left;
        while(l <= mid && r <= right) {
            if(a[l] <= a[r]) {
                temp[k++] = a[l++];
            } else {
                temp[k++] = a[r++];
                shift += mid - l + 1;
            }
        }
        while(l <= mid) {
            temp[k++] = a[l++];
        }
        while(r <= right) {
            temp[k++] = a[r++];
        }
        for(int i=left; i <=right; i++) {
            a[i] = temp[i];
        }
        return shift;
    }
}
