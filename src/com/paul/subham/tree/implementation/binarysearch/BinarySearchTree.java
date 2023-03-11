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
    Node root;

    public Node getRoot() {
        return this.root;
    }

    //insert an element, TC: O(n), SC: O(n)
    public void insert(int data) {
        root = insertUtil(root, data);
    }

    private Node insertUtil(Node node, int data) {
        if(node == null) {
            node = new Node(data);
            return node;
        }

        if(data < node.data) {
            node.left = insertUtil(node.left, data);
        } else if(data > node.data) {
            node.right = insertUtil(node.right, data);
        }
        return node;
    }

    //searching an element recursive, TC: O(n), SC: O(n)
    public boolean searchRecursive(int data) {
        if(searchRecursiveUtil(root, data) != null) {
            return true;
        }
        return false;
    }

    private Node searchRecursiveUtil(Node node, int data) {
        if(node == null || node.data == data) {
            return node;
        }
        if(data < node.data) {
            return searchRecursiveUtil(node.left, data);
        }
        return searchRecursiveUtil(node.right, data);
    }

    //searching an element iterative, TC: O(n), SC: O(n)
    public boolean searchIterative(int data) {
        Node current = root;
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
        Node min = minRecursiveUtil(root);
        if(min == null) {
            return Integer.MIN_VALUE;
        }
        return min.data;
    }

    private Node minRecursiveUtil(Node node) {
        if(node == null || node.left == null) {
            return node;
        }
        return minRecursiveUtil(node.left);
    }

    //minimum iterative, TC: O(n), SC: O(n)
    int minIterative() {
        if(root == null) {
            return Integer.MIN_VALUE;
        }
        Node current = root;
        while(current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    //maximum recursive, TC: O(n), SC: O(n)
    int maxRecursive(){
        Node max = maxRecursiveUtil(root);
        if(max == null) {
            return Integer.MAX_VALUE;
        }
        return max.data;
    }

    Node maxRecursiveUtil(Node node) {
        if(node == null || node.right == null) {
            return node;
        }
        return maxRecursiveUtil(node.right);
    }

    //maximum iterative, TC: O(n), SC: O(n)
    int maxIterative() {
        if(root == null) {
            return Integer.MAX_VALUE;
        }
        Node current = root;
        while(current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    //delete an element, TC: O(n), SC: O(n)
    void delete(int data) {
        root = deleteUtil(root, data);
    }

    Node deleteUtil(Node node, int data) {
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

    private void inOrderUtil(Node node) {
        if(node != null) {
            inOrderUtil(node.left);
            System.out.print(node.data + " ");
            inOrderUtil(node.right);
        }
    }
}

