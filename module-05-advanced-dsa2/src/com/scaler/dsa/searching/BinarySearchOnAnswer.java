package com.scaler.dsa.searching;

/**
 * Binary Search on Answer Pattern
 * Used when: answer lies in a range and we can check feasibility
 * Examples: sqrt, min days to ship, koko eating bananas
 */
public class BinarySearchOnAnswer {

    // Example: Square root of a number (floor)
    public static int sqrt(int n) {
        if (n < 2) return n;
        int low = 1, high = n / 2, ans = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((long) mid * mid <= n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Example: Minimum days to make m bouquets
    public static int minDays(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length) return -1;
        int low = 1, high = 0;
        for (int d : bloomDay) high = Math.max(high, d);
        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canMake(bloomDay, m, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private static boolean canMake(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0, consecutive = 0;
        for (int d : bloomDay) {
            if (d <= day) {
                consecutive++;
                if (consecutive == k) { bouquets++; consecutive = 0; }
            } else {
                consecutive = 0;
            }
        }
        return bouquets >= m;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(16));  // 4
        System.out.println(sqrt(8));   // 2
        System.out.println(minDays(new int[]{1, 10, 3, 10, 2}, 3, 1, 3)); // Compiler note: remove last arg
    }
}
