package com.scaler.dsa.searching;

/**
 * Classic Binary Search
 * Time: O(log n) | Space: O(1)
 */
public class BinarySearch {

    public static int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // Sorted Insert Position (lower bound)
    // Time: O(log n) | Space: O(1)
    public static int sortedInsertPosition(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13};
        System.out.println(search(arr, 7));  // 3
        System.out.println(search(arr, 6));  // -1

        int[] a1 = {1, 3, 5, 6};
        System.out.println(sortedInsertPosition(a1, 5));  // 2
        int[] a2 = {1, 4, 9};
        System.out.println(sortedInsertPosition(a2, 3));  // 1
        int[] a3 = {1, 3, 5};
        System.out.println(sortedInsertPosition(a3, 7));  // 3 (N)
    }
}
