package com.scaler.dsa.queues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Queue Implementations
 * Day 53 — DSA: Queues: Implementation & Problems
 */
public class Queue {

    // Standard Queue using Deque
    private final Deque<Integer> queue = new ArrayDeque<>();

    public void enqueue(int val) { queue.addLast(val); }
    public int dequeue()         { return queue.removeFirst(); }
    public int front()           { return queue.peekFirst(); }
    public boolean isEmpty()     { return queue.isEmpty(); }
    public int size()            { return queue.size(); }
}
