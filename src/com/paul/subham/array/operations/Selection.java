package com.paul.subham.array.operations;

import com.paul.subham.tree.implementation.binarysearch.BSTNode;
import com.paul.subham.tree.implementation.binarysearch.BinarySearchTree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author subham.paul
 * 1. The largest K elements in an array (Using tree sorting)
 * 2. The smallest K elements in an array (Using tree sorting)
 * 3. The largest K elements in an array (Using tree sorting - space optimized)
 * 4. The smallest K elements in an array (Using tree sorting - space optimized)
 * 5. The largest K elements in an array (Using sorting)
 * 6. The smallest K elements in an array (Using sorting)
 * 7. The largest K elements in an array (Bubble sort)
 * 8. The smallest K elements in an array (Bubble sort)
 * 9. The largest K elements in an array (Selection sort)
 * 10. The smallest K elements in an array (Selection sort)
 * 11. The largest K elements in an array (Priority Queue)
 * 12. The smallest K elements in an array (Priority Queue)
 * 13. The largest K elements in an array (Binary search)
 * 14. The smallest K elements in an array (Binary search)
 * 15. The largest K elements in an array (Quick sort)
 * 16. The smallest K elements in an array (Quick sort)
 * 17. Kth smallest elements in an array (Using sorting)
 * 18. Kth largest elements in an array (Using sorting)
 * 19. Kth largest elements in an array (Binary Search)
 * 20. Kth smallest elements in an array (Binary Search)
 * 21. Kth largest elements in an array (Priority Queue)
 * 22. Kth smallest elements in an array (Priority Queue)
 * 23. Kth largest elements in an array (Quick sort)
 * 24. Kth smallest elements in an array (Quick sort)
 * 25. Kth smallest elements in an array (Counting sort)
 * 26. Kth largest elements in an array (Counting sort)
 * 27. Kth smallest elements in two sorted arrays
 * 28. Kth smallest elements in two sorted arrays (Space optimized)
 * 29. Kth smallest elements in two sorted arrays (Binary Search)
 */

public class Selection {
    public static void main(String[] args) {
        int[] a = {7,11,12};
        int[] b = {4,5,9};

        //kLargestElementsBinarySearch(a, 3);
        System.out.println(kthSmallestTwoSortedArraysBinarySearch(a, b,4));
    }


    /**
     * The largest K elements in an array (Using tree sorting)
     *
     * TC: O(nlogn), average case
     * SC: O(n)
     */
    public static void kLargestElementsTreeSort(int[] a, int k) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for(int i=0; i<a.length; i++) {
            binarySearchTree.insert(a[i]);
        }
        inOrderReverse(binarySearchTree.getRoot(), k);
    }
    private static int count = 0;
    private static void inOrderReverse(BSTNode node, int k) {
        if(node != null) {
            inOrderReverse(node.right, k);
            if(count < k) {
                System.out.print(node.data + " ");
                count++;
            }
            inOrderReverse(node.left, k);
        }
    }

    /**
     * The smallest K elements in an array (Using tree sorting)
     *
     * TC: O(nlogn), average case
     * SC: O(n)
     */
    public static void kSmallestElementsTreeSort(int[] a, int k) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for(int i=0; i<a.length; i++) {
            binarySearchTree.insert(a[i]);
        }
        inOrder(binarySearchTree.getRoot(), k);
    }
    private static void inOrder(BSTNode node, int k) {
        if(node != null) {
            inOrder(node.left, k);
            if(count < k) {
                System.out.print(node.data + " ");
                count++;
            }
            inOrder(node.right, k);
        }
    }

    /**
     * The largest K elements in an array (Using tree sorting - space optimized)
     *
     * TC: O(nlogk), average case
     * SC: O(k)
     */
    public static void kLargestElementsTreeSortEfficient(int[] a, int k) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int i = 0;
        for(;i<k;i++) {
            binarySearchTree.insert(a[i]);
        }
        for(;i<a.length;i++) {
            int min = binarySearchTree.minRecursive();
            if(a[i] > min) {
                binarySearchTree.delete(min);
                binarySearchTree.insert(a[i]);
            }
        }
        binarySearchTree.inOrder();
    }

    /**
     * The smallest K elements in an array (Using tree sorting - space optimized)
     *
     * TC: O(nlogk), average case
     * SC: O(k)
     */
    public static void kSmallestElementsTreeSortEfficient(int[] a, int k) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int i = 0;
        for(;i<k;i++) {
            binarySearchTree.insert(a[i]);
        }
        for(;i<a.length;i++) {
            int max = binarySearchTree.maxRecursive();
            if(a[i] < max) {
                binarySearchTree.delete(max);
                binarySearchTree.insert(a[i]);
            }
        }
        binarySearchTree.inOrder();
    }

    /**
     * The largest K elements in an array (Using sorting)
     *
     * TC: O(nlogn), average case
     * SC: O(1)
     */
    public static void kLargestElementsSorting(int[] a, int k) {
        Arrays.sort(a);
        for(int i=a.length-1; i>=a.length-k; i--) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The smallest K elements in an array (Using sorting)
     *
     * TC: O(nlogn), average case
     * SC: O(1)
     */
    public static void kSmallestElementsSorting(int[] a, int k) {
        Arrays.sort(a);
        for (int j : a) {
            System.out.print(j + " ");
        }
    }

    /**
     * The largest K elements in an array (Bubble sort)
     *
     * TC: O(nk)
     * SC: O(1)
     */
    public static void kLargestElementsBubbleSort(int[] a, int k) {
        for(int i=a.length-1; i>=a.length-k; i--) {
            for(int j = 0; j<i; j++) {
                if(a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The smallest K elements in an array (Bubble sort)
     *
     * TC: O(nk)
     * SC: O(1)
     */
    public static void kSmallestElementsBubbleSort(int[] a, int k) {
        for(int i=a.length-1; i>=a.length-k; i--) {
            for(int j = 0; j<i; j++) {
                if(a[j] < a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The largest K elements in an array (Selection sort)
     *
     * TC: O(nk)
     * SC: O(1)
     */
    public static void kLargestElementsSelectionSort(int[] a, int k) {
        int maxIndex;
        for(int i=0; i<k; i++) {
            maxIndex = i;
            for(int j = i+1; j<a.length; j++) {
                if(a[j] > a[maxIndex]) {
                    maxIndex = j;
                }
            }
            int temp = a[maxIndex];
            a[maxIndex] = a[i];
            a[i] = temp;
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The smallest K elements in an array (Selection sort)
     *
     * TC: O(nk)
     * SC: O(1)
     */
    public static void kSmallestElementsSelectionSort(int[] a, int k) {
        int minIndex;
        for(int i=0; i<k; i++) {
            minIndex = i;
            for(int j = i+1; j<a.length; j++) {
                if(a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The largest K elements in an array (Priority Queue)
     *
     * TC: O(nlogk)
     * SC: O(n)
     */
    public static void kLargestElementsPriorityQueue(int[] a, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i=0; i<a.length; i++) {
            priorityQueue.add(a[i]);
            if(priorityQueue.size() > k) {
                priorityQueue.remove();
            }
        }
        while(!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.remove() + " ");
        }
    }

    /**
     * The smallest K elements in an array (Priority Queue)
     *
     * TC: O(nlogk)
     * SC: O(n)
     */
    public static void kSmallestElementsPriorityQueue(int[] a, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0; i<a.length; i++) {
            priorityQueue.add(a[i]);
            if(priorityQueue.size() > k) {
                priorityQueue.remove();
            }
        }
        while(!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.remove() + " ");
        }
    }

    /**
     * The largest K elements in an array (Binary search)
     *
     * TC: O(nlog(max-min))
     * SC: O(1)
     */
    public static void kLargestElementsBinarySearch(int[] a, int k) {
        int kthLargest = kthLargestBinarySearch(a, k);
        for(int i : a) {
            if(i >= kthLargest) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * The smallest K elements in an array (Binary search)
     *
     * TC: O(nlog(max-min))
     * SC: O(1)
     */
    public static void kSmallestElementsBinarySearch(int[] a, int k) {
        int kthSmallest = kthSmallestBinarySearch(a, k);
        for(int i : a) {
            if(i <= kthSmallest) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * The largest K elements in an array (Quick sort)
     *
     * TC: O(n^2), worst case   O(n), average case
     * SC: O(n)
     */
    public static void kLargestElementsQuickSort(int[] a, int k) {
        kthLargestQuickSort(a, 0, a.length-1, k);
        for(int i = a.length-1; i>=a.length-k; i--) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The smallest K elements in an array (Quick sort)
     *
     * TC: O(n^2), worst case   O(n), average case
     * SC: O(n)
     */
    public static void kSmallestElementsQuickSort(int[] a, int k) {
        kthSmallestQuickSort(a, 0, a.length-1, k);
        for(int i = a.length-1; i>=a.length-k; i--) {
            System.out.print(a[i] + " ");
        }
    }

    private static int partition(int[] a, int l, int r, boolean reverse) {
        int pIndex = l;
        for(int i=l; i<r; i++) {
            if(reverse) {
                if (a[i] >= a[r]) {
                    int temp = a[i];
                    a[i] = a[pIndex];
                    a[pIndex] = temp;
                    pIndex++;
                }
            } else {
                if (a[i] <= a[r]) {
                    int temp = a[i];
                    a[i] = a[pIndex];
                    a[pIndex] = temp;
                    pIndex++;
                }
            }
        }
        int temp = a[pIndex];
        a[pIndex] = a[r];
        a[r] = temp;
        return pIndex;
    }

    /**
     * Kth smallest elements in an array (Using sorting)
     *
     * TC: O(nlogn), average case
     * SC: O(1)
     */
    public static int kthSmallest(int[] a, int k) {
        Arrays.sort(a);
        return a[k-1];
    }

    /**
     * Kth largest elements in an array (Using sorting)
     *
     * TC: O(nlogn), average case
     * SC: O(1)
     */
    public static int kthLargest(int[] a, int k) {
        Arrays.sort(a);
        return a[a.length-k];
    }

    /**
     * Kth largest elements in an array (Binary Search)
     *
     * TC: O(nlog(max-min))
     * SC: O(1)
     */
    public static int kthLargestBinarySearch1(int[] a, int k) {
        int mid = kthLargestBinarySearch(a, k);
        int min = Integer.MAX_VALUE;
        for(int j: a) {
            if(j >= mid) {
                min = Math.min(min, j);
            }
        }
        return min;
    }

    public static int kthLargestBinarySearch(int[] a, int k) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for(int i : a) {
            low = Math.min(low, i);
            high = Math.max(high, i);
        }
        int mid = 0;
        while(low <= high) {
            mid = low + (high - low) / 2;
            int count = 0;
            for(int j: a) {
                if(j >= mid) {
                    count ++;
                }
            }
            if(count == k) {
                return mid;
            } else if(count > k) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return mid;
    }

    /**
     * Kth smallest elements in an array (Binary Search)
     *
     * TC: O(nlog(max-min))
     * SC: O(1)
     */
    public static int kthSmallestBinarySearch1(int[] a, int k) {
        int mid = kthSmallestBinarySearch(a, k);
        int max = Integer.MIN_VALUE;
        for(int j: a) {
            if(j <= mid) {
                max = Math.max(max, j);
            }
        }
        return max;
    }

    public static int kthSmallestBinarySearch(int[] a, int k) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for(int i : a) {
            low = Math.min(low, i);
            high = Math.max(high, i);
        }
        int mid = 0;
        while(low <= high) {
            mid = low + (high - low) / 2;
            int count = 0;
            for(int j: a) {
                if(j <= mid) {
                    count ++;
                }
            }
            if(count == k) {
                return mid;
            } else if(count < k) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return mid;
    }

    /**
     * Kth largest elements in an array (Priority Queue)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static int kthLargestElementPriorityQueue(int[] a, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int j : a) {
            priorityQueue.add(j);
            if (priorityQueue.size() > k) {
                priorityQueue.remove();
            }
        }
        return priorityQueue.remove();
    }

    /**
     * Kth smallest elements in an array (Priority Queue)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static int kthSmallestElementPriorityQueue(int[] a, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int j : a) {
            priorityQueue.add(j);
            if (priorityQueue.size() > k) {
                priorityQueue.remove();
            }
        }
        return priorityQueue.remove();
    }

    /**
     * Kth largest elements in an array (Quick sort)
     *
     * TC: O(n^2), worst case   O(n), average case
     * SC: O(n)
     */
    public static int kthLargestQuickSort(int[] a, int k) {
        return a[kthLargestQuickSort(a, 0, a.length-1, k)];
    }

    private static int kthLargestQuickSort(int[] a, int l, int r, int k) {
        int pivot = partition(a, l, r, false);
        if(pivot == r - k +1) {
            return pivot;
        } else if (pivot > r - k + 1) {
            return kthLargestQuickSort(a, l, pivot-1, k - (r - (pivot - 1)));
        } else {
            return kthLargestQuickSort(a, pivot+1, r, k);
        }
    }

    /**
     * Kth smallest elements in an array (Quick sort)
     *
     * TC: O(n^2), worst case   O(n), average case
     * SC: O(n)
     */
    public static int kthSmallestQuickSort(int[] a, int k) {
        return a[kthSmallestQuickSort(a, 0, a.length-1, k)];
    }

    private static int kthSmallestQuickSort(int[] a, int l, int r, int k) {
        int pivot = partition(a, l, r, true);
        if(pivot == r - k +1) {
            return pivot;
        } else if (pivot > r - k + 1) {
            return kthSmallestQuickSort(a, l, pivot-1, k - (r - (pivot - 1)));
        } else {
            return kthSmallestQuickSort(a, pivot+1, r, k);
        }
    }

    /**
     * Kth smallest elements in an array (Counting sort)
     *
     * TC: O(n + max)
     * SC: O(max)
     */
    public static int kthSmallestElementCountingSort(int[] a, int k) {
        int max = Integer.MIN_VALUE;
        for(int i : a) {
            max = Math.max(max, i);
        }
        int[] frequency = new int[max+1];
        for(int i : a) {
            frequency[i] ++ ;
        }
        int count = 0;
        for(int i=0; i<=max; i++) {
            if(frequency[i] != 0) {
                count += frequency[i];
                if(count == k) {
                    return i;
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Kth largest elements in an array (Counting sort)
     *
     * TC: O(n + max)
     * SC: O(max)
     */
    public static int kthLargestElementCountingSort(int[] a, int k) {
        int max = Integer.MIN_VALUE;
        for(int i : a) {
            max = Math.max(max, i);
        }
        int[] frequency = new int[max+1];
        for(int i : a) {
            frequency[i] ++ ;
        }
        int count = 0;
        for(int i=max; i>=0; i--) {
            if(frequency[i] != 0) {
                count += frequency[i];
                if(count == k) {
                    return i;
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Kth smallest elements in two sorted arrays
     *
     * TC: O(m+n)
     * SC: O(m+n)
     */
    public static int kthSmallestTwoSortedArray(int[] a, int[] b, int k) {
        int[] c = Manipulation.merge(a, b);
        return c[k-1];
    }

    /**
     * Kth smallest elements in two sorted arrays (Space optimized)
     *
     * TC: O(k)
     * SC: O1)
     */
    public static int kthSmallestTwoSortedArraysEfficient(int[] a, int[] b, int k) {
        int m = a.length;
        int n = b.length;
        int left = 0;
        int right = 0;
        int count = 0;
        while(left < m && right < n) {
            count++;
            if(a[left] <= a[right]) {
                if(count == k) {
                    return a[left];
                }
                left++;
            } else {
                if(count == k) {
                    return a[right];
                }
                right++;
            }
        }
        while(left < m) {
            count++;
            if(count == k) {
                return a[left];
            }
            left++;
        }
        while(right < n) {
            count++;
            if(count == k) {
                return a[right];
            }
            right++;
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Kth smallest elements in two sorted arrays (Binary Search)
     *
     * TC: O(log(m+n))
     * SC: O(1)
     */
    public static int kthSmallestTwoSortedArraysBinarySearch(int[] a, int[] b, int k) {
        int na = a.length;
        int nb = b.length;
        // if no of elements in 2nd array is less than k, we need at least k-nb element from 1st array to be at left side
        int low = Math.max(0, k - nb);
        // We can have at max these many elements from first array to be at left side
        int high = Math.min(na, k);
        // no of elements to be at left side to find the kth element
        int left = k;
        while (low <= high) {
            int midA = (low + high) / 2;
            int midB = left - midA;
            int lA = Integer.MIN_VALUE;
            int lB = Integer.MIN_VALUE;
            int rA = Integer.MAX_VALUE;
            int rB = Integer.MAX_VALUE;
            if (midA > 0) {
                lA = a[midA - 1];
            }
            if (midB > 0) {
                lB = b[midB - 1];
            }
            if (midA < na) {
                rA = a[midA];
            }
            if (midB < nb) {
                rB = b[midB];
            }
            if (lA <= rB && lB <= rA) {
                return Math.max(lA, lB);
            } else if (lA > rB) {
                high = midA - 1;
            } else {
                low = midA + 1;
            }
        }
        return Integer.MIN_VALUE;
    }
}
