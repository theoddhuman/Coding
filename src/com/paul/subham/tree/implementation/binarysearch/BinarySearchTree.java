package com.paul.subham.tree.implementation.binarysearch;

/**
 * 1. insert an element
 * 2. searching an element recursive
 * 3. searching an element iterative
 * 4. minimum recursive
 * 5. minimum iterative
 * 6. maximum recursive
 * 7. maximum iterative
 * 8. delete an element
 */
public class BinarySearchTree {
    BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    //insert an element, TC: O(n), SC: O(n)
    public void insert(int data) {
        root = insertUtil(root, data);
    }

    private BSTNode insertUtil(BSTNode BSTNode, int data) {
        if(BSTNode == null) {
            BSTNode = new BSTNode(data);
            return BSTNode;
        }

        if(data < BSTNode.data) {
            BSTNode.left = insertUtil(BSTNode.left, data);
        } else if(data > BSTNode.data) {
            BSTNode.right = insertUtil(BSTNode.right, data);
        }
        return BSTNode;
    }

    //searching an element recursive, TC: O(n), SC: O(n)
    public boolean searchRecursive(int data) {
        if(searchRecursiveUtil(root, data) != null) {
            return true;
        }
        return false;
    }

    private BSTNode searchRecursiveUtil(BSTNode BSTNode, int data) {
        if(BSTNode == null || BSTNode.data == data) {
            return BSTNode;
        }
        if(data < BSTNode.data) {
            return searchRecursiveUtil(BSTNode.left, data);
        }
        return searchRecursiveUtil(BSTNode.right, data);
    }

    //searching an element iterative, TC: O(n), SC: O(n)
    public boolean searchIterative(int data) {
        BSTNode current = root;
        while(current != null) {
            if(current.data == data) {
                return true;
            }
            if(data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    //minimum recursive, TC: O(n), SC: O(n)
    public int minRecursive(){
        BSTNode min = minRecursiveUtil(root);
        if(min == null) {
            return Integer.MIN_VALUE;
        }
        return min.data;
    }

    private BSTNode minRecursiveUtil(BSTNode BSTNode) {
        if(BSTNode == null || BSTNode.left == null) {
            return BSTNode;
        }
        return minRecursiveUtil(BSTNode.left);
    }

    //minimum iterative, TC: O(n), SC: O(n)
    int minIterative() {
        if(root == null) {
            return Integer.MIN_VALUE;
        }
        BSTNode current = root;
        while(current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    //maximum recursive, TC: O(n), SC: O(n)
    public int maxRecursive(){
        BSTNode max = maxRecursiveUtil(root);
        if(max == null) {
            return Integer.MAX_VALUE;
        }
        return max.data;
    }

    BSTNode maxRecursiveUtil(BSTNode BSTNode) {
        if(BSTNode == null || BSTNode.right == null) {
            return BSTNode;
        }
        return maxRecursiveUtil(BSTNode.right);
    }

    //maximum iterative, TC: O(n), SC: O(n)
    int maxIterative() {
        if(root == null) {
            return Integer.MAX_VALUE;
        }
        BSTNode current = root;
        while(current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    //delete an element, TC: O(n), SC: O(n)
    public void delete(int data) {
        root = deleteUtil(root, data);
    }

    BSTNode deleteUtil(BSTNode node, int data) {
        if(node == null) {
            return node;
        }
        if(data < node.data) {
            node.left = deleteUtil(node.left, data);
        } else if(data > node.data) {
            node.right = deleteUtil(node.right, data);
        } else {
            if(node.left == null) {
                return node.right;
            }
            if(node.right == null) {
                return node.left;
            }
            node.data = minRecursiveUtil(node.right).data;
            node.right = deleteUtil(node.right, node.data);
        }
        return node;
    }

    //inorder traversal
    public void inOrder() {
        inOrderUtil(root);
    }

    private void inOrderUtil(BSTNode node) {
        if(node != null) {
            inOrderUtil(node.left);
            System.out.print(node.data + " ");
            inOrderUtil(node.right);
        }
    }
}

