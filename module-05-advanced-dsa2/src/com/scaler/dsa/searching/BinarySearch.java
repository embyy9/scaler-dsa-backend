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

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13};
        System.out.println(search(arr, 7));  // 3
        System.out.println(search(arr, 6));  // -1
    }
}
