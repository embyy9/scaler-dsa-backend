package com.scaler.dsa.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Classic Stack Problems
 * - Valid parentheses
 * - Next greater element
 * - Min stack
 */
public class StackProblems {

    // Valid parentheses
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }

    // Next greater element
    public static int[] nextGreater(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // stores indices
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) stack.pop();
            result[i] = stack.isEmpty() ? -1 : arr[stack.peek()];
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));  // true
        System.out.println(isValid("([)]"));    // false
        int[] nge = nextGreater(new int[]{4, 5, 2, 10, 8});
        for (int x : nge) System.out.print(x + " "); // 5 10 10 -1 -1
    }
}
