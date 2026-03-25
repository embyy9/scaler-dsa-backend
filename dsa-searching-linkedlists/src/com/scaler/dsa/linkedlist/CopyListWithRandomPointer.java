package com.scaler.dsa.linkedlist;

import java.util.HashMap;

/**
 * Copy List with Random Pointer
 *
 * Given a linked list where each node has a next pointer and a random pointer
 * (which can point to any node or null), create a deep copy of the list.
 *
 * Approach: HashMap to map original nodes -> cloned nodes
 *   Pass 1: Create all cloned nodes, store mapping
 *   Pass 2: Wire up next and random pointers using the map
 *
 * Why HashMap? The random pointer can point to ANY node — possibly one we
 * haven't created yet. The map lets us find "given this original node,
 * what's its corresponding clone?" in O(1).
 */
public class CopyListWithRandomPointer {

    // Node definition for this problem (has random pointer)
    static class RandomNode {
        int val;
        RandomNode next;
        RandomNode random;

        RandomNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // ---------------------------------------------------------------
    // Approach 1: HashMap — O(n) time, O(n) space
    // ---------------------------------------------------------------
    // Two passes:
    //   1. Clone every node, store original -> clone in map
    //   2. Set next and random pointers on each clone using the map
    static RandomNode copyListWithHashMap(RandomNode head) {
        if (head == null) return null;

        HashMap<RandomNode, RandomNode> map = new HashMap<>();

        // Pass 1: Create clone nodes (value only)
        RandomNode curr = head;
        while (curr != null) {
            map.put(curr, new RandomNode(curr.val));
            curr = curr.next;
        }

        // Pass 2: Wire up next and random pointers
        curr = head;
        while (curr != null) {
            RandomNode clone = map.get(curr);
            clone.next = map.get(curr.next);       // null if curr.next is null
            clone.random = map.get(curr.random);   // null if curr.random is null
            curr = curr.next;
        }

        return map.get(head);
    }

    // ---------------------------------------------------------------
    // Approach 2: Interleaving — O(n) time, O(1) space
    // ---------------------------------------------------------------
    // Three passes (no extra data structure!):
    //   1. Interleave: insert clone right after each original
    //      1 -> 1' -> 2 -> 2' -> 3 -> 3'
    //   2. Set random pointers on clones
    //      clone.random = original.random.next (which is the clone of random target)
    //   3. Separate the two lists back apart
    static RandomNode copyListInterleave(RandomNode head) {
        if (head == null) return null;

        // Pass 1: Interleave — insert clone after each original
        //   Before: A -> B -> C
        //   After:  A -> A' -> B -> B' -> C -> C'
        RandomNode curr = head;
        while (curr != null) {
            RandomNode clone = new RandomNode(curr.val);
            clone.next = curr.next;
            curr.next = clone;
            curr = clone.next;  // move to next original
        }

        // Pass 2: Set random pointers on clones
        //   If original.random points to X, then clone.random = X.next (which is X's clone)
        curr = head;
        while (curr != null) {
            RandomNode clone = curr.next;
            if (curr.random != null) {
                clone.random = curr.random.next;  // .next is the clone of random target
            }
            curr = clone.next;  // jump to next original
        }

        // Pass 3: Separate the two interleaved lists
        RandomNode newHead = head.next;
        curr = head;
        while (curr != null) {
            RandomNode clone = curr.next;
            curr.next = clone.next;                          // restore original's next
            clone.next = (clone.next != null) ? clone.next.next : null;  // set clone's next
            curr = curr.next;                                // advance to next original
        }

        return newHead;
    }

    // ---- Helpers for testing ----

    static RandomNode buildList(int[] values, int[] randomIndices) {
        if (values.length == 0) return null;

        // Create all nodes
        RandomNode[] nodes = new RandomNode[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new RandomNode(values[i]);
        }
        // Wire next pointers
        for (int i = 0; i < values.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        // Wire random pointers
        for (int i = 0; i < values.length; i++) {
            int idx = randomIndices[i];
            if (idx < values.length) {
                nodes[i].random = nodes[idx];
            }
            // else random stays null
        }
        return nodes[0];
    }

    static void printList(RandomNode head) {
        // Print values with next
        RandomNode curr = head;
        System.out.print("List:   ");
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println();

        // Print random pointers
        curr = head;
        System.out.print("Random: ");
        while (curr != null) {
            System.out.print(curr.val + "->" + (curr.random != null ? curr.random.val : "null"));
            if (curr.next != null) System.out.print(",  ");
            curr = curr.next;
        }
        System.out.println();
    }

    static boolean verifyCopy(RandomNode original, RandomNode copy) {
        RandomNode o = original, c = copy;
        while (o != null && c != null) {
            if (o.val != c.val) return false;
            if (o == c) return false;  // must be different objects!
            if (o.random == null && c.random != null) return false;
            if (o.random != null && c.random == null) return false;
            if (o.random != null && o.random.val != c.random.val) return false;
            if (c.random != null && c.random == o.random) return false;  // clone must not point to original
            o = o.next;
            c = c.next;
        }
        return o == null && c == null;
    }

    public static void main(String[] args) {
        // Example: 1 -> 2 -> 3
        // Random:  1->3, 2->1, 3->1
        int[] values = {1, 2, 3};
        int[] randomIdx = {2, 0, 0};  // indices into the node array

        RandomNode original = buildList(values, randomIdx);

        System.out.println("=== Original ===");
        printList(original);

        // Test HashMap approach
        RandomNode copy1 = copyListWithHashMap(original);
        System.out.println("\n=== HashMap Copy ===");
        printList(copy1);
        System.out.println("Valid deep copy? " + verifyCopy(original, copy1));

        // Test Interleave approach
        RandomNode original2 = buildList(values, randomIdx);  // fresh list (interleave modifies it temporarily)
        RandomNode copy2 = copyListInterleave(original2);
        System.out.println("\n=== Interleave Copy ===");
        printList(copy2);
        System.out.println("Valid deep copy? " + verifyCopy(original2, copy2));

        // Edge case: empty list
        System.out.println("\n=== Empty List ===");
        System.out.println("HashMap:     " + (copyListWithHashMap(null) == null ? "null ✓" : "FAIL"));
        System.out.println("Interleave:  " + (copyListInterleave(null) == null ? "null ✓" : "FAIL"));

        // Edge case: single node with random pointing to itself
        RandomNode single = new RandomNode(42);
        single.random = single;
        RandomNode singleCopy = copyListWithHashMap(single);
        System.out.println("\n=== Single Node (random -> self) ===");
        System.out.println("Value: " + singleCopy.val + ", random -> " + singleCopy.random.val);
        System.out.println("Is deep copy? " + (singleCopy != single && singleCopy.random != single));
    }
}
