package com.scaler.dsa.linkedlist;

/**
 * Linked List Introduction — creation, traversal, insertion, deletion
 */
public class LinkedListIntro {

    // Insert at head
    public static Node insertAtHead(Node head, int val) {
        Node node = new Node(val);
        node.next = head;
        return node;
    }

    // Insert at tail
    public static Node insertAtTail(Node head, int val) {
        Node node = new Node(val);
        if (head == null) return node;
        Node curr = head;
        while (curr.next != null) curr = curr.next;
        curr.next = node;
        return head;
    }

    // Print list
    public static void print(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? " -> " : "\n"));
            curr = curr.next;
        }
    }

    // Length of list
    public static int length(Node head) {
        int len = 0;
        while (head != null) { len++; head = head.next; }
        return len;
    }

    public static void main(String[] args) {
        Node head = null;
        head = insertAtTail(head, 1);
        head = insertAtTail(head, 2);
        head = insertAtTail(head, 3);
        head = insertAtHead(head, 0);
        print(head);           // 0 -> 1 -> 2 -> 3
        System.out.println(length(head)); // 4
    }
}
