package com.scaler.dsa.searching;

/**
 * Median of Two Sorted Arrays (LeetCode #4, Hard)
 * Given two sorted arrays, find the floor of the median of the combined sorted array.
 *
 * Approach: Binary search on partition of the smaller array.
 * Time: O(log(min(M, N))) | Space: O(1)
 */
public class MedianOfTwoSortedArrays {

    // Time: O(log(min(M, N)))
    // Space: O(1)
    public static int solve(int[] A, int[] B) {
        // Ensure A is the smaller array
        if (A.length > B.length) {
            return solve(B, A);
        }

        int m = A.length;
        int n = B.length;
        int half = (m + n + 1) / 2;

        int low = 0, high = m;

        while (low <= high) {
            int i = (low + high) / 2;
            int j = half - i;

            int maxLeftA  = (i == 0) ? Integer.MIN_VALUE : A[i - 1];
            int minRightA = (i == m) ? Integer.MAX_VALUE : A[i];
            int maxLeftB  = (j == 0) ? Integer.MIN_VALUE : B[j - 1];
            int minRightB = (j == n) ? Integer.MAX_VALUE : B[j];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeftA, maxLeftB);
                } else {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2;
                }
            } else if (maxLeftA > minRightB) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // Test 1: Odd total — median = 4
        System.out.println(solve(new int[]{1, 3, 8}, new int[]{2, 4, 5, 9}));

        // Test 2: Even total — floor((2+3)/2) = 2
        System.out.println(solve(new int[]{1, 2}, new int[]{3, 4}));

        // Test 3: One empty array — median = 2
        System.out.println(solve(new int[]{}, new int[]{1, 2, 3}));

        // Test 4: Single elements — floor((1+2)/2) = 1
        System.out.println(solve(new int[]{1}, new int[]{2}));
    }
}
