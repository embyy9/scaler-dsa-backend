package com.scaler.dsa.queues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Queue Problems
 * - Sliding window maximum
 * - Implement queue using two stacks
 */
public class QueueProblems {

    // Sliding window maximum using deque
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; i++) {
            // Remove elements outside window
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) dq.pollFirst();
            // Remove smaller elements
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
            dq.addLast(i);
            if (i >= k - 1) result[i - k + 1] = nums[dq.peekFirst()];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for (int x : result) System.out.print(x + " "); // 3 3 5 5 6 7
    }
}
