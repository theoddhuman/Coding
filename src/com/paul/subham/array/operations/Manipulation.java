package com.paul.subham.array.operations;

import com.paul.subham.mathematics.Basic;

import java.util.*;

/**
 * @author subham.paul
 * <p>
 *
 * 1. Reverse an array (Iterative)
 * 2. Reverse an array (Recursive)
 * 3. Merge k sorted arrays (By sorting)
 * 4. Merge k sorted arrays (Merging)
 * 5. Merge k sorted arrays (Using priority queue)
 * 6. Re-arrange an array such that a[i] = i
 * 7. Re-arrange an array such that a[i] = i (Efficient)
 * 8. Re-arrange an array such that a[i] = i (Hashing)
 * 9. Re-arrange an array such that a[i] = i (Swapping)
 * 10. Remove adjacent duplicates from an array
 * 11. Merge two sorted arrays
 * 12. Move zeros to end of an array
 * 13. Move zeros to end of an array (Single traversal)
 * 14. Delete array elements which are smaller than next or become smaller
 * 15. Delete array elements which are smaller than next or become smaller (Using stack)
 * 16. Next greater element with same set of digits
 * 17. Union of two sorted arrays
 * 18. Rearrange Array Elements by Sign I (Using Temporary array)
 * 19. Rearrange Array Elements by Sign I (Space optimized)
 * 20. Rearrange Array Elements by Sign II (Using Temporary array)
 */
public class Manipulation {
    public static void main(String[] args) {
        int[] a = {2,3,4,2,1};
//        int[] b = {3,4,11,23};
//        int[] res = merge(a, b);

        System.out.println(Arrays.toString(nextGreaterSameSetDigits(a)));
//        int[][] a = {{1, 3, 45, 46},
//                {21, 34, 35, 89},
//                {1, 2, 3, 11},
//                {4, 6, 7, 8}};
//        int[] output = mergeKSortedArraysPQ(a);
//        System.out.println(Arrays.toString(output));
    }

    /**
     * Reverse an array (Iterative)
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseIterative(int[] a, int low, int high) {
        int temp;
        while(low < high) {
            temp = a[low];
            a[low] = a[high];
            a[high] = temp;
            low++;
            high--;
        }
    }

    /**
     * Reverse an array (Recursive)
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseRecursive(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int temp = a[low];
        a[low] = a[high];
        a[high] = temp;
        reverseRecursive(a, low + 1, high - 1);
    }

    /**
     * Merge k sorted arrays (By sorting)
     * All arrays are of same size
     *
     * TC: O(nk*log(nk))
     * SC: O(nk)
     */
    public static int[] mergeKSortedArrays(int[][] a, int n, int k) {
        int[] output = new int[n*k];
        int index = 0;
        for(int i=0; i<k; i++) {
            for(int j=0; j<n; j++) {
                output[index] = a[i][j];
                index++;
            }
        }
        Arrays.sort(output);
        return output;
    }

    /**
     * Merge k sorted arrays (Merging)
     * All arrays are of same size
     *
     * TC: O(nk*log(k))
     * SC: O(nk*log(k))
     */
    public static int[] mergeKSortedArraysMerging(int[][] a, int n, int k) {
        return mergeKSortedArraysUtil(a, 0, k-1, n);
    }

    public static int[] mergeKSortedArraysUtil(int[][] a, int i, int j, int n) {
        if(i==j) {
            return a[i];
        }
        if(j-i==1) {
            return merge(a[i], a[j], n, n);
        }
        int mid = (i+j)/2;
        int[] output1 = mergeKSortedArraysUtil(a, 0, mid, n);
        int[] output2 = mergeKSortedArraysUtil(a, mid+1, j, n);
        return merge(output1, output2, output1.length, output2.length);
    }

    private static int[] merge(int[] a, int[] b, int m, int n) {
        int[] output = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i<m && j<n) {
            if(a[i] > b[j]) {
                output[k++] = b[j++];
            } else {
                output[k++] = a[i++];
            }
        }
        while (i < m) {
            output[k++] = a[i++];
        }
        while (j < n) {
            output[k++] = b[j++];
        }
        return output;
    }

    /**
     * Merge k sorted arrays (Using priority queue)
     * All arrays may be of different size
     *
     * TC: O(nk*log(k))
     * SC: O(k)
     */
    public static int[] mergeKSortedArraysPQ(int[][] a) {
        PriorityQueue<ArrayContainer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> a[o.aIndex][o.index]));
        int total = 0;
        for(int i=0; i<a.length; i++) {
            priorityQueue.add(new ArrayContainer(i, 0));
            total+= a[i].length;
        }
        int[] output = new int[total];
        int oIndex = 0;
        while(!priorityQueue.isEmpty()) {
            ArrayContainer arrayContainer = priorityQueue.poll();
            output[oIndex++] = a[arrayContainer.aIndex][arrayContainer.index];
            if(arrayContainer.index < a[arrayContainer.aIndex].length - 1) {
                priorityQueue.add(new ArrayContainer(arrayContainer.aIndex, arrayContainer.index+1));
            }
        }
        return output;
    }

    static class ArrayContainer {
        Integer aIndex;
        Integer index;
        ArrayContainer(Integer aIndex, Integer index) {
            this.aIndex = aIndex;
            this.index = index;
        }
    }

    /**
     * Re-arrange an array such that a[i] = i
     *
     * Given an array of elements of length N, ranging from 0 to N – 1. All elements may not be present in the array.
     * If the element is not present then there will be -1 present in the array.
     * Rearrange the array such that A[i] = i and if i is not present, display -1 at that place.
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void reArrange(int[] a) {
        for(int i=0; i<a.length; i++) {
            for(int j=0; j<a.length; j++) {
                if(a[j] == i) {
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                    break;
                }
            }
        }
        for(int i=0; i<a.length; i++) {
            if(a[i] != i) {
                a[i] = -1;
            }
        }
    }

    /**
     * Re-arrange an array such that a[i] = i (Efficient)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void reArrangeEfficient(int[] a) {
        for(int i=0; i<a.length; i++) {
            if(a[i] != -1 && a[i] != i) {
                int x = a[i];
                while(a[x] != -1 && a[x] != x) {
                    int y = a[x];
                    a[x] = x;
                    x = y;
                }
                a[x] = x;
            }
            if(a[i] != i) {
                a[i] = -1;
            }
        }
    }

    /**
     * Re-arrange an array such that a[i] = i (Hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void reArrangeHashing(int[] a) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<a.length; i++) {
            set.add(a[i]);
        }
        for(int i=0; i<a.length; i++) {
            if(set.contains(i)) {
                a[i] = i;
            } else {
                a[i] = -1;
            }
        }
    }

    /**
     * Re-arrange an array such that a[i] = i (Swapping)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void reArrangeSwap(int[] a) {
        for(int i=0; i<a.length;) {
            if(a[i] != -1 && a[i] != i) {
                int temp = a[a[i]];
                a[a[i]] = a[i];
                a[i] = temp;
            } else {
                i++;
            }
        }
    }

    /**
     * Remove adjacent duplicates from an array
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void removeAdjacentDuplicates(int[] a) {
        int stack = 0;
        for(int i=1; i<a.length; i++) {
            if(a[i] != a[stack]) {
                stack++;
                a[stack] = a[i];
            }
        }
        for(int i=stack+1; i<a.length; i++) {
            a[i] = -1;
        }
    }

    /**
     * Merge two sorted arrays
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] merge(int[] a, int[] b) {
        int l = 0;
        int r = 0;
        int[] temp = new int[a.length + b.length];
        int index = 0;
        while(l < a.length && r < b.length) {
            if(a[l] <= b[r]) {
                temp[index++] = a[l];
                l++;
            } else {
                temp[index++] = b[r];
                r++;
            }
        }
        while(l < a.length) {
            temp[index++] = a[l];
            l++;
        }
        while(r < b.length) {
            temp[index++] = b[r];
            r++;
        }
        return temp;
    }

    /**
     * Move zeros to end of an array
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void moveZerosEnd(int[] a) {
        int count = 0;
        for(int i=0; i<a.length; i++) {
            if(a[i] != 0) {
                a[count++] = a[i];
            }
        }
        while(count < a.length) {
            a[count++] = 0;
        }
    }

    /**
     * Move zeros to end of an array (Single traversal)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void moveZerosEndSingleTraversal(int[] a) {
        int count = 0;
        for(int i=0; i<a.length; i++) {
            if(a[i] != 0) {
                int temp = a[count];
                a[count] = a[i];
                a[i] = temp;
                count++;
            }
        }
    }

    /**
     * Delete array elements which are smaller than next or become smaller
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void deleteSmaller(int[] a, int n, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int value : a) {
            list.add(value);
        }
        int counter = 0;
        int i=0;
        while( i<n-1) {
            int pointer = 0;
            for(int j=1; j< list.size(); j++) {
                if(list.get(pointer) < list.get(j)) {
                    list.remove(pointer);
                    counter++;
                    break;
                } else {
                    pointer++;
                }
            }
            if(counter==k) {
                break;
            }
            i++;
        }
        list.forEach(val -> System.out.print(val + " "));
    }

    /**
     * Delete array elements which are smaller than next or become smaller (Using stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] deleteSmallerByStack(int[] a, int n, int k) {
        Stack<Integer> stack = new Stack<>();
        int counter = 0;
        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && stack.peek() < a[i] && counter < k) {
                stack.pop();
                counter++;
            }
            stack.push(a[i]);
        }
        int[] v = new int[stack.size()];
        for(int i=v.length-1; i>=0; i--) {
            v[i] = stack.pop();
        }
        return v;
    }

    /**
     * Next greater element with same set of digits (Next permutation)
     * 2,3,4,2,1 -> 2,4,1,2,3
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] nextGreaterSameSetDigits(int[] a) {
        int x = -1;
        int n = a.length;
        //find digit which is smaller than the next digit
        for (int i = n - 1; i > 0; i--) {
            if (a[i] > a[i - 1]) {
                x = i - 1;
                break;
            }
        }
        //swap a[x] till it is greater than next
        for (int i = n - 1; i > x && x != -1; i--) {
            if (a[i] > a[x]) {
                int temp = a[x];
                a[x] = a[i];
                a[i] = temp;
                break;
            }
        }
        //reverse later part of the x as it can be ascending
        int low = x + 1;
        int high = n - 1;
        while (low < high) {
            int temp = a[low];
            a[low] = a[high];
            a[high] = temp;
            low++;
            high--;
        }
        return a;
    }

    /**
     * Union of two sorted arrays
     *
     * TC: O(m+n)
     * SC: O(1)
     */
    public static ArrayList<Integer> findUnion(int a[], int b[]) {
        // add your code here
        ArrayList<Integer> list = new ArrayList<>();
        int l = 0;
        int r = 0;
        list.add(Math.min(a[0],b[0]));
        while(l<a.length && r<b.length) {
            if(a[l] <= b[r]) {
                if(list.get(list.size()-1) != a[l]) {
                    list.add(a[l]);
                }
                l++;
            } else {
                if(list.get(list.size()-1) != b[r]) {
                    list.add(b[r]);
                }
                r++;
            }
        }
        while(l<a.length) {
            if(list.get(list.size()-1) != a[l]) {
                list.add(a[l]);
            }
            l++;
        }
        while(r<b.length) {
            if(list.get(list.size()-1) != b[r]) {
                list.add(b[r]);
            }
            r++;

        }
        return list;
    }

    /**
     * Rearrange Array Elements by Sign I (Using Temporary array)
     *
     * There’s an array ‘A’ of size ‘N’ with an equal number of positive and negative elements.
     * Without altering the relative order of positive and negative elements,
     * you must return an array of alternately positive and negative values.
     *
     * Note: Start the array with positive elements.
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] rearrangeArray(int[] nums) {
        List<Integer> pList = new ArrayList<>();
        List<Integer> nList = new ArrayList<>();
        for(int i=0; i<nums.length; i++) {
            if(nums[i] < 0) {
                nList.add(nums[i]);
            } else {
                pList.add(nums[i]);
            }
        }
        for(int i=0; i<nums.length/2; i++) {
            nums[2*i] = pList.get(i);
            nums[2*i+1] = nList.get(i);
        }
        return nums;
    }

    /**
     * Rearrange Array Elements by Sign I (Space optimized)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] rearrangeArrayOpt(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int pIndex = 0;
        int nIndex = 1;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] < 0) {
                ans[nIndex] = nums[i];
                nIndex += 2;
            } else {
                ans[pIndex] = nums[i];
                pIndex += 2;
            }
        }
        return ans;
    }

    /**
     * Rearrange Array Elements by Sign II (Using Temporary array)
     *
     * There’s an array ‘A’ of size ‘N’ with positive and negative elements (not necessarily equal).
     * Without altering the relative order of positive and negative elements,
     * you must return an array of alternately positive and negative values.
     * The leftover elements should be placed at the very end in the same order as in array A.
     *
     * Note: Start the array with positive elements.
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] alternateNumbers(int []nums) {
        List<Integer> pList = new ArrayList<>();
        List<Integer> nList = new ArrayList<>();
        for(int i=0; i<nums.length; i++) {
            if(nums[i] < 0) {
                nList.add(nums[i]);
            } else {
                pList.add(nums[i]);
            }
        }
        int pIndex = 0;
        int nIndex = 0;
        int i = 0;
        while(pIndex < pList.size() && nIndex < nList.size()) {
            nums[i++] = pList.get(pIndex++);
            nums[i++] = nList.get(nIndex++);
        }
        while(pIndex < pList.size()) {
            nums[i++] = pList.get(pIndex++);
        }
        while(nIndex < nList.size()) {
            nums[i++] = nList.get(nIndex++);
        }
        return nums;
    }
}
