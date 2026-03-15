package com.scaler.dsa.searching;

/**
 * Sorted Insert Position
 *
 * Given a sorted array A of size N and a target value B, find the index
 * (0-based) of the target value in the array.
 *   - If present, return its index.
 *   - If not found, return the index of the least element >= B.
 *   - If no element >= B exists, return N (the position where B would go).
 *
 * Approach: Standard lower-bound binary search.
 *   Track the insertion point as we narrow the search range.
 *
 * Constraints: 1 <= N <= 10^5, 1 <= A[i], B <= 10^5
 *
 * Time:  O(log N)
 * Space: O(1)
 */
public class SortedInsertPosition {

    public static int searchInsert(int[] A, int B) {
        int low = 0, high = A.length - 1, ans = A.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (A[mid] == B) return mid;
            if (A[mid] < B) {
                low = mid + 1;
            } else {
                ans = mid;
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5));  // 2 (found)
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));  // 1 (not found, least >= 2 is 3 at index 1)
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));  // 4 (no element >= 7, return N)
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0));  // 0 (insert at beginning)
        System.out.println(searchInsert(new int[]{1}, 1));            // 0
    }
}
