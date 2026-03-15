package com.scaler.dsa.queues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Queue Using Two Stacks
 *
 * Implement a FIFO queue using only two LIFO stacks.
 *
 * Approach (lazy transfer):
 *   - Push always goes to stackIn.
 *   - Pop/peek: if stackOut is empty, pour all of stackIn into stackOut
 *     (reversing the order), then pop/peek from stackOut.
 *
 * Time:  Amortised O(1) per operation — each element is moved at most twice
 * Space: O(n)
 */
public class QueueUsingStacks {

    private final Deque<Integer> stackIn  = new ArrayDeque<>();
    private final Deque<Integer> stackOut = new ArrayDeque<>();

    public void enqueue(int val) {
        stackIn.push(val);
    }

    public int dequeue() {
        transferIfNeeded();
        return stackOut.pop();
    }

    public int front() {
        transferIfNeeded();
        return stackOut.peek();
    }

    public boolean isEmpty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    private void transferIfNeeded() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
    }

    public static void main(String[] args) {
        QueueUsingStacks q = new QueueUsingStacks();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println("front: " + q.front());     // 1
        System.out.println("dequeue: " + q.dequeue());  // 1
        System.out.println("dequeue: " + q.dequeue());  // 2
        q.enqueue(4);
        System.out.println("dequeue: " + q.dequeue());  // 3
        System.out.println("dequeue: " + q.dequeue());  // 4
        System.out.println("isEmpty: " + q.isEmpty());  // true
    }
}
