package com.paul.subham.searching.binarysearch;

/**
 * 1. binary search iterative
 * 2. binary search recursive
 * 3. Ceil(Upper bound) of a number in a sorted array (Recursive)
 * 4. Ceil(Upper bound) of a number in a sorted array (Iterative)
 * 3. Floor(Lower bound) of a number in a sorted array (Recursive)
 * 4. Floor(Lower bound) of a number in a sorted array (Iterative)
 * 5. Search insert position (Recursive)
 * 6. Search insert position (Iterative - upper bound)
 * 7. Search insert position (Iterative - lower bound)
 * 7. First occurrence of an element in an array (Binary Search - Recursive)
 * 8. First occurrence of an element in an array (Binary Search - Iterative)
 * 9. Last occurrence of an element in an array (Binary Search - Recursive)
 * 10. Last occurrence of an element in an array (Binary Search - Iterative)
 * 11. Count no of occurrences of an element in an array (Linear search)
 * 12. Count no of occurrences of an element in an array (Binary Search)
 * 13. Count no of occurrences of an element in an array (Binary Search - improved)
 * 14. Searching an element in sorted and rotated array (Using pivot)
 * 15. Searching an element in sorted and rotated array (Binary Search - Recursive)
 * 16. Searching an element in sorted and rotated array (Binary Search - Iterative)
 * 17. Maximum in sorted and rotated array (Binary Search)
 * 18. Minimum in sorted and rotated array (Binary Search)
 * 19. Minimum in sorted and rotated array (Binary Search - iterative)
 * 19. Searching an element in sorted and rotated array - duplicate elements (Binary Search - Iterative)
 * 20. Rotation count in sorted and rotated array (Binary Search - Recursive)
 * 21. Rotation count in sorted and rotated array (Binary Search - Iterative)
 * 22. Single element in sorted array
 * 23. Single element in sorted array (Binary Search)
 * 24. Local maxima of an array (Iterative)
 * 25. Local maxima of an array (Recursive)
 * 25. Local minima of an array (Binary search - recursive)
 * 26. Local minima of an array (Binary search - iterative)
 */
public class OneD {
    public static void main(String[] args) {
        int[] a = {1,2,4,5,6};
        System.out.println(lowerBoundIterative(a,5, 12));
    }

    /**
     * binary search iterative
     * TC: O(n)
     * SC: O(1)
     */
    public static int search(int[] a, int n, int data) {
        int low = 0;
        int high = n-1;
        int mid;
        while(high >= low) {
            mid = low + (high-low)/2;
            if(a[mid] == data) {
                return mid;
            } else if(a[mid] > data) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return -1;
    }

    /**
     * binary search recursive
     * TC: O(log n)
     * SC: O(1)
     */
    public static int searchRecursive(int a[], int low, int high, int data) {
        if(low < 0 || high >= a.length) {
            return -1;
        }
        int mid = low + (high - low)/data;
        if(a[mid] == data) {
            return mid;
        } else if(a[mid] > data) {
            return searchRecursive(a, low, mid-1, data);
        } else {
            return searchRecursive(a, mid+1, high, data);
        }
    }

    /**
     * Ceil(Upper bound) of a number in a sorted array (Recursive)
     *
     * find the smallest index i such that a[i] >= x
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int upperBound(int[] a, int n, int x) {
        return upperBoundRec(a, n, x, 0, n-1, n);
    }

    public static int upperBoundRec(int[] a, int n, int x, int low, int high, int ans) {
        if(low > high) {
            return ans;
        }
        int mid = (low+high)/2;
        if(a[mid] >= x) {
            return upperBoundRec(a, n, x, low, mid-1, mid);
        }
        return upperBoundRec(a, n, x, mid+1, high, ans);
    }

    /**
     * Ceil(Upper bound) of a number in a sorted array (Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int upperBoundIterative(int[] a, int n, int x) {
        int low = 0;
        int high = n-1;
        int ans = n;
        while(low <= high) {
            int mid = (low+high)/2;
            if(a[mid]>=x) {
                ans = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return ans;
    }

    /**
     * Floor(Lower bound) of a number in a sorted array (Recursive)
     *
     * find the smallest index i such that a[i] <= x
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int lowerBound(int[] a, int n, int x) {
        return lowerBoundRec(a, n, x, 0, n-1, -1);
    }

    public static int lowerBoundRec(int[] a, int n, int x, int low, int high, int ans) {
        if(low > high) {
            return ans;
        }
        int mid = (low+high)/2;
        if(a[mid] <= x) {
            return lowerBoundRec(a, n, x, mid+1, high, mid);
        }
        return lowerBoundRec(a, n, x, low, mid-1, ans);
    }

    /**
     * Floor(Lower bound) of a number in a sorted array (Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int lowerBoundIterative(int[] a, int n, int x) {
        int low = 0;
        int high = n-1;
        int ans = -1;
        while(low <= high) {
            int mid = (low+high)/2;
            if(a[mid]<=x) {
                ans = mid;
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return ans;
    }

    /**
     * Search insert position (Recursive)
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int searchInsertRec(int[] a, int target) {
        return searchInsertRec(a, target, 0, a.length-1, a.length);
    }

    public static int searchInsertRec(int[] a, int target, int low, int high, int ans) {
        if(low>high) {
            return ans;
        }
        int mid = (low+high)/2;
        if(a[mid] >= target) {
            return searchInsertRec(a, target, low, mid-1, mid);
        }
        return searchInsertRec(a,target,mid+1,high,ans);
    }

    /**
     * Search insert position (Iterative - lower bound)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int searchInsertX(int[] a, int target) {
        int low = 0;
        int high = a.length-1;
        int ans = 0;
        while(low <= high) {
            int mid = (low+high)/2;
            if(a[mid] >= target) {
                high = mid-1;
            } else {
                ans = mid+1;
                low = mid+1;
            }
        }
        return ans;
    }

    /**
     * Search insert position (Iterative - upper bound)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int searchInsert(int[] a, int target) {
        int low = 0;
        int high = a.length-1;
        int ans = a.length;
        while(low <= high) {
            int mid = (low+high)/2;
            if(a[mid] >= target) {
                ans = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return ans;
    }

    /**
     * First occurrence of an element in an array (Binary Search - Recursive)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int firstOccurrenceRecursive(int[] a, int low, int high, int data) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if ((mid == low && a[mid] == data) || (a[mid] == data && a[mid - 1] < data)) {
            return mid;
        } else if (a[mid] >= data) {
            return firstOccurrenceRecursive(a, low, mid - 1, data);
        } else {
            return firstOccurrenceRecursive(a, mid + 1, high, data);
        }
    }

    /**
     * First occurrence of an element in an array (Binary Search - Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int firstOccurrenceIterative(int[] a, int data) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((mid == low && a[mid] == data) || (a[mid] == data && a[mid - 1] < data)) {
                return mid;
            } else if (a[mid] >= data) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Last occurrence of an element in an array (Binary Search - Recursive)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int lastOccurrenceRecursive(int[] a, int low, int high, int data) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if ((mid == high && a[mid] == data) || (a[mid] == data && a[mid + 1] > data)) {
            return mid;
        } else if (a[mid] <= data) {
            return lastOccurrenceRecursive(a, mid+1, high, data);
        } else {
            return lastOccurrenceRecursive(a, low, mid-1, data);
        }
    }

    /**
     * Last occurrence of an element in an array (Binary Search - Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int lastOccurrenceIterative(int[] a, int data) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((mid == high && a[mid] == data) || (a[mid] == data && a[mid + 1] > data)) {
                return mid;
            } else if (a[mid] <= data) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return -1;
    }

    /**
     * Count no of occurrences of an element in an array (Linear search)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int countOccurrences(int[] a, int data) {
        int count = 0;
        for (int i : a) {
            if (i == data) {
                count++;
            }
        }
        return count;
    }

    /**
     * Count no of occurrences of an element in an array (Binary Search)
     * <p>
     * TC: O(logn + k), where k is no of occurrences
     * SC: O(1)
     */
    public static int countOccurrencesBinarySearch(int[] a, int data) {
        int index = search(a, a.length, data);
        int count = 1;
        int left = index - 1;
        while (left >= 0 && a[left] == data) {
            count++;
            left--;
        }
        int right = index + 1;
        while (index < a.length && a[right] == data) {
            count++;
            right++;
        }
        return count;
    }

    /**
     * Count no of occurrences of an element in an array (Binary Search - improved)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int countOccurrencesBinarySearchImproved(int[] a, int data) {
        int firstIndex = firstOccurrenceIterative(a, data);
        if (firstIndex == -1) {
            return 0;
        }
        int lastIndex = lastOccurrenceIterative(a, data);
        return lastIndex - firstIndex + 1;
    }

    /**
     * Searching an element in sorted and rotated array (Using pivot)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int searchSortedAndRotatedPivot(int[] a, int data) {
        int pivot = findPivot(a, 0, a.length - 1);
        if (pivot == -1) {
            return OneD.searchRecursive(a, 0, a.length - 1, data);
        }
        if (a[pivot] == data) {
            return pivot;
        }
        if (a[0] <= data) {
            return OneD.searchRecursive(a, 0, pivot - 1, data);
        } else {
            return OneD.searchRecursive(a, pivot + 1, a.length - 1, data);
        }
    }

    /**
     * Searching an element in sorted and rotated array (Binary Search - Recursive)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int searchSortedAndRotatedBinaryRecursive(int[] a, int data, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (a[mid] == data) {
            return mid;
        }
        if (a[low] <= a[mid]) {
            if (a[low] <= data && data < a[mid]) {
                return searchSortedAndRotatedBinaryRecursive(a, data, low, mid - 1);
            } else {
                return searchSortedAndRotatedBinaryRecursive(a, data, mid + 1, high);
            }
        } else {
            if (a[mid] < data && data <= a[high]) {
                return searchSortedAndRotatedBinaryRecursive(a, data, mid + 1, high);
            } else {
                return searchSortedAndRotatedBinaryRecursive(a, data, low, mid - 1);
            }
        }
    }

    /**
     * Searching an element in sorted and rotated array (Binary Search - Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int searchSortedAndRotatedBinaryIterative(int[] a, int data, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == data) {
                return mid;
            }
            if (a[low] <= a[mid]) {
                if (a[low] <= data && data < a[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (a[mid] < data && data <= a[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * Maximum in sorted and rotated array (Binary Search)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int findPivot(int[] a, int low, int high) {
        if (low > high) {
            return -1;
        }
        if (low == high) {
            return low;
        }
        int mid = low + (high - low) / 2;
        if (mid < high && a[mid] > a[mid + 1]) {
            return mid;
        }
        if (mid > low && a[mid] < a[mid - 1]) {
            return mid - 1;
        }
        if (a[low] >= a[mid]) {
            return findPivot(a, low, mid - 1);
        } else {
            return findPivot(a, mid + 1, high);
        }
    }

    /**
     * Minimum in sorted and rotated array (Binary Search)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    private int findMin(int[] a, int low, int high) {
        if(low > high) {
            return a[low];
        }
        if(low == high) {
            return a[low];
        }
        int mid = (low + high)/2;
        if(mid < high && a[mid] > a[mid+1]) {
            return a[mid+1];
        }
        if(low < mid && a[mid] < a[mid-1]) {
            return a[mid];
        }
        if(a[high] <= a[mid]) {
            return findMin(a, mid+1, high);
        }
        return findMin(a, low, mid-1);
    }

    /**
     * Minimum in sorted and rotated array (Binary Search - iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int findMin(int[] a) {
        int low = 0;
        int high = a.length-1;
        while(low < high) {
            int mid = (low+high)/2;
            if(mid < high && a[mid] > a[mid+1]) {
                return a[mid+1];
            }
            if(mid > low && a[mid] < a[mid-1]) {
                return a[mid];
            }
            if(a[mid] >= a[high]) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return a[low];
    }

    /**
     * Searching an element in sorted and rotated array - duplicate elements (Binary Search - Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static boolean searchSortRotateDuplicate(int[] a, int data) {
        int low = 0;
        int high = a.length-1;
        while(low<=high) {
            System.out.println(low + " " + high);
            int mid = (low+high)/2;
            if(a[mid] == data) {
                return true;
            }
            if(a[low] == a[mid] && a[mid] == a[high]) {
                low++;
                high--;
            } else if(a[low] <= a[mid]) {
                if(a[low] <= data && data<a[mid]) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            } else {
                if(a[mid] < data && data <=a[high]) {
                    low = mid+1;
                } else {
                    high = mid-1;
                }
            }
        }
        return false;
    }

    /**
     * Rotation count in sorted and rotated array (Binary Search - Recursive)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    private static int findKRotation(int[] a, int low, int high) {
        if(low >= high) {
            return low;
        }
        int mid = (low+high)/2;
        if(mid > low && a[mid] < a[mid-1]) {
            return mid;
        }
        if(mid < high && a[mid] > a[mid+1]) {
            return mid+1;
        }
        if(a[mid] >= a[high]) {
            return findKRotation(a, mid+1, high);
        }
        return findKRotation(a, low, mid-1);
    }

    /**
     * Rotation count in sorted and rotated array (Binary Search - Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    private static int findKRotationIterative(int[] a, int low, int high) {
        while(low < high) {
            int mid = (low+high)/2;
            if(mid > low && a[mid] < a[mid-1]) {
                return mid;
            }
            if(mid < high && a[mid] > a[mid+1]) {
                return mid+1;
            }
            if(a[mid] >= a[high]) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return low;
    }

    /**
     * Single element in sorted array
     *
     * all other appears exactly twice
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int singleNonDuplicate(int[] a) {
        int n = a.length;
        if(n == 1) {
            return a[0];
        }
        for(int i=0;i<n;i++) {
            if(i==0 && a[i] != a[i+1]) {
                return a[i];
            }
            if(i==n-1 && a[i] != a[i-1]) {
                return a[i];
            }
            if(a[i] != a[i+1] && a[i] != a[i-1]){
                return a[i];
            }
        }
        return -1;
    }

    /**
     * Single element in sorted array (Binary Search)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int singleNonDuplicateBS(int[] a) {
        int n = a.length;
        if(n == 1) {
            return a[0];
        }
        if(a[0] != a[1]) {
            return a[0];
        }
        if(a[n-1] != a[n-2]) {
            return a[n-1];
        }
        int low = 1;
        int high = n-2;
        while(low <= high) {
            int mid = (low+high)/2;
            if(a[mid] != a[mid-1] && a[mid] != a[mid+1]) {
                return a[mid];
            }
            if((mid%2 == 1 && a[mid] == a[mid-1]) || (mid%2==0 && a[mid] == a[mid+1])) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return -1;
    }

    /**
     * Local maxima of an array (Iterative)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public int localMaximaIterative(int[] a) {
        int n = a.length;
        if(n==1) {
            return 0;
        }
        if(a[0] > a[1]) {
            return 0;
        }
        if(a[n-1] > a[n-2]) {
            return n-1;
        }
        int low = 1;
        int high = n-2;
        while(low <= high) {
            int mid = (low+high)/2;
            if(a[mid-1] < a[mid] && a[mid] > a[mid+1]) {
                return mid;
            }
            if(a[mid] < a[mid-1]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return -1;
    }

    /**
     * Local maxima of an array (Recursive)
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public int localMaximaRecursive(int[] a) {
        return localMaxima(a, 0, a.length-1, a.length);
    }

    private static int localMaxima(int[] a, int low, int high, int n) {
        if(low > high) {
            return -1;
        }
        int mid = (low+high)/2;
        if((mid==0 || a[mid] > a[mid-1])&&(mid==n-1 || a[mid] > a[mid+1])){
            return mid;
        }
        if(mid > 0 && a[mid-1] > a[mid]) {
            return localMaxima(a, low, mid-1, n);
        }
        return localMaxima(a, mid+1, high,n);
    }

    /**
     * Local minima of an array (Binary search - recursive)
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int localMinima(int[] a) {
        return localMinimaUtil(a, 0, a.length-1, a.length);
    }

    private static int localMinimaUtil(int[] a, int low, int high, int n) {
        if(low > high) {
            return Integer.MIN_VALUE;
        }
        int mid = (low + high)/2;
        if((mid == 0 || a[mid-1] > a[mid]) && (mid == n-1 || a[mid] < a[mid+1])) {
            return a[mid];
        }
        if(mid > 0 && a[mid-1] < a[mid]) {
            return localMinimaUtil(a, low, mid-1, n);
        }
        return localMinimaUtil(a, mid+1, high, n);
    }

    /**
     * Local minima of an array (Binary search - iterative)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int localMinimaIterative(int[] a) {
        int low = 0;
        int high = a.length-1;
        while(low <= high) {
            int mid = (low + high)/2;
            if((mid == 0 || a[mid-1] > a[mid]) && (mid == a.length-1 || a[mid] < a[mid+1])) {
                return a[mid];
            }
            if(mid > 0 && a[mid-1] < a[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return Integer.MIN_VALUE;
    }


}
