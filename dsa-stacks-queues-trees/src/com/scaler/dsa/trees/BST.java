package com.scaler.dsa.trees;

/**
 * Binary Search Tree — insert, search, delete, validate
 * Day 55 — DSA: Trees 2: BST
 */
public class BST {

    // Insert into BST
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left  = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);
        return root;
    }

    // Search in BST
    public static boolean search(TreeNode root, int val) {
        if (root == null) return false;
        if (val == root.val) return true;
        return val < root.val ? search(root.left, val) : search(root.right, val);
    }

    // Find minimum value node
    public static TreeNode findMin(TreeNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    // Delete from BST
    public static TreeNode delete(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val)      root.left  = delete(root.left, val);
        else if (val > root.val) root.right = delete(root.right, val);
        else {
            if (root.left == null)  return root.right;
            if (root.right == null) return root.left;
            TreeNode min = findMin(root.right);
            root.val = min.val;
            root.right = delete(root.right, min.val);
        }
        return root;
    }

    // Validate BST
    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        TreeNode root = null;
        for (int val : new int[]{5, 3, 7, 1, 4, 6, 8}) {
            root = insert(root, val);
        }
        System.out.println(search(root, 4));     // true
        System.out.println(search(root, 9));     // false
        System.out.println(isValidBST(root));    // true
        System.out.print("Inorder (sorted): ");
        TreeTraversal.inorder(root);             // 1 3 4 5 6 7 8
    }
}
