package com.scaler.dsa.searching;

/**
 * Square Root of Integer
 *
 * Given an integer A, compute and return floor(sqrt(A)).
 * Do not use the standard library sqrt function.
 *
 * Approach: Binary search on the answer space [1, A/2].
 *   - If mid*mid <= A, mid is a candidate — search higher.
 *   - If mid*mid > A, mid is too large — search lower.
 *   - Cast to long to avoid overflow since A can be up to 10^9.
 *
 * Constraints: 0 <= A <= 10^9
 *
 * Time:  O(log A)
 * Space: O(1)
 */
public class SquareRoot {

    public static int sqrt(int A) {
        if (A < 2) return A;

        int low = 1, high = A / 2, ans = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((long) mid * mid <= A) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(sqrt(11));         // 3  (3.316… → floor = 3)
        System.out.println(sqrt(9));          // 3  (perfect square)
        System.out.println(sqrt(0));          // 0
        System.out.println(sqrt(1));          // 1
        System.out.println(sqrt(2));          // 1
        System.out.println(sqrt(100));        // 10
        System.out.println(sqrt(1000000000)); // 31622 (edge: max constraint)
    }
}
