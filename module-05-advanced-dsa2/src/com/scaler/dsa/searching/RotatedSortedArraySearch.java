package com.scaler.dsa.searching;

/**
 * Search in Rotated Sorted Array
 * Given a sorted array rotated at an unknown pivot, search for a target value.
 *
 * Approach: Modified binary search — at each step, determine which half is sorted,
 * then check if the target falls in that sorted range.
 * Time: O(log N) | Space: O(1)
 */
public class RotatedSortedArraySearch {

    // Time: O(log N)
    // Space: O(1)
    public static int search(final int[] A, int B) {
        int n = A.length;
        int high = n - 1;
        int low = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (A[mid] == B) {
                return mid;
            }

            if (A[low] <= A[mid]) {
                // Left half is sorted
                if (A[low] <= B && B <= A[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // Right half is sorted
                if (A[mid] <= B && B <= A[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // Test 1: Target at index 0
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2, 3}, 4)); // 0

        // Test 2: Target in second half
        System.out.println(search(new int[]{9, 10, 3, 5, 6, 8}, 5)); // 3

        // Test 3: Target not found
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2, 3}, 9)); // -1

        // Test 4: Single element found
        System.out.println(search(new int[]{1}, 1)); // 0

        // Test 5: Target at rotation point
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2, 3}, 0)); // 4
    }
}
