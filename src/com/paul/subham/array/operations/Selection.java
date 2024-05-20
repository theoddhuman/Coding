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
 */

public class Selection {
    public static void main(String[] args) {
        int[] a = {8,9,2,1,7,11,4,14};
        kSmallestElementsQuickSort(a, 4);
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
     * TC: O(nlogn)
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
     * TC: O(nlogn)
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

    private static int kthLargestBinarySearch(int[] a, int k) {
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

    private static int kthSmallestBinarySearch(int[] a, int k) {
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

    private static int kthLargestQuickSort(int[] a, int l, int r, int k) {
        int pivot = partition(a, l, r, k, false);
        if(pivot == r - k +1) {
            return pivot;
        } else if (pivot > r - k + 1) {
            return kthLargestQuickSort(a, l, pivot-1, k - (r - (pivot - 1)));
        } else {
            return kthLargestQuickSort(a, pivot+1, r, k);
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

    private static int kthSmallestQuickSort(int[] a, int l, int r, int k) {
        int pivot = partition(a, l, r, k, true);
        if(pivot == r - k +1) {
            return pivot;
        } else if (pivot > r - k + 1) {
            return kthSmallestQuickSort(a, l, pivot-1, k - (r - (pivot - 1)));
        } else {
            return kthSmallestQuickSort(a, pivot+1, r, k);
        }
    }

    private static int partition(int[] a, int l, int r, int k, boolean reverse) {
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
}
