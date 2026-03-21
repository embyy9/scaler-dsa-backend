package com.scaler.dsa.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Stack Implementation + Common Problems
 * Day 51 — DSA: Stacks
 */
public class Stack {

    // Stack using Java's Deque (recommended)
    private final Deque<Integer> stack = new ArrayDeque<>();

    public void push(int val) { stack.push(val); }
    public int pop()          { return stack.pop(); }
    public int peek()         { return stack.peek(); }
    public boolean isEmpty()  { return stack.isEmpty(); }
    public int size()         { return stack.size(); }
}
