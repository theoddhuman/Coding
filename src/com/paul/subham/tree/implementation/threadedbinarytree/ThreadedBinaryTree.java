package com.paul.subham.tree.implementation.threadedbinary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * 1. insert data
 * 2. delete data
 * 3. Insert right of a node
 * 4. Insert left of a node
 * 5. Inorder traversal
 * 6. Inorder successor of a node
 * 7. Reverse inorder traversal
 * 8. Inorder predecessor of a node
 * 9. Preorder traversal
 * 10. Preorder successor of a node
 */
public class ThreadedBinaryTree {
    public TBTNode root;

    public ThreadedBinaryTree() {
        this.root = new TBTNode(Integer.MAX_VALUE);
        this.root.left = this.root.right = this.root;
    }

    /**
     * insert data
     *
     * TC: O(n)
     * SC: O(n)
     */
    public void insert (int data) {
        TBTNode newNode = new TBTNode(data);
        if(root.left == root && root.right == root) {
            newNode.left = root.left;
            root.left = newNode;
            newNode.leftBit = root.leftBit;
            root.leftBit = true;
            newNode.right = root;
            return;
        }
        Queue<TBTNode> queue = new LinkedList<>();
        queue.add(root.left);
        while(!queue.isEmpty()) {
            TBTNode temp = queue.remove();
            if(temp.leftBit) {
                queue.add(temp.left);
            } else {
                newNode.left = temp.left;
                temp.left = newNode;
                newNode.leftBit = false;
                temp.leftBit = true;
                newNode.right = temp;
                return;
            }
            if(temp.rightBit) {
                queue.add(temp.right);
            } else {
                newNode.right = temp.right;
                temp.right = newNode;
                newNode.rightBit = false;
                temp.rightBit = true;
                newNode.left = temp;
                return;
            }
        }
    }

    /**
     * delete data
     *
     * TC: O(n)
     * SC: O(n)
     */
    public void delete(int data) {
        if(root.left == root && root.right == root) {
            return;
        }
        TBTNode current = root.left;
        TBTNode keyNode = null;
        if(!current.leftBit && !current.rightBit) {
            if(current.data == data) {
                root.leftBit = false;
                root.left = current.left;
                return;
            } else {
                return;
            }
        }
        Queue<TBTNode> queue = new LinkedList<>();
        queue.add(current);
        while(!queue.isEmpty()) {
            current = queue.remove();
            if(current.data == data) {
                keyNode = current;
            }
            if(current.leftBit) {
                queue.add(current.left);
            }
            if(current.rightBit) {
                queue.add(current.right);
            }
        }
        if(keyNode != null) {
            int x = current.data;
            deleteDeepest(current);
            keyNode.data = x;
        }
    }

    private void deleteDeepest(TBTNode node) {
        Queue<TBTNode> queue = new LinkedList<>();
        queue.add(root.left);
        TBTNode current = null;
        while(!queue.isEmpty()) {
            current = queue.remove();
            if(current == node) {
                root.leftBit = current.leftBit;
                root.left = current.left;
                return;
            }
            if(current.rightBit) {
                if(current.right == node) {
                    current.rightBit = current.right.rightBit;
                    current.right = current.right.right;
                    return;
                } else {
                    queue.add(current.right);
                }
            }
            if(current.leftBit) {
                if(current.left == node) {
                    current.leftBit = current.left.leftBit;
                    current.left = current.left.left;
                } else {
                    queue.add(current.left);
                }
            }
        }
    }

    /**
     * Insert right of a node
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void insertRight(TBTNode node, int data) {
        if(node == null) {
            return;
        }
        TBTNode newNode = new TBTNode(data);
        newNode.rightBit = node.rightBit;
        newNode.right = node.right;
        node.right = newNode;
        node.rightBit = true;
        newNode.left = node;
        TBTNode temp;
        if(newNode.rightBit) {
            temp = newNode.right;
            while(temp.leftBit) {
                temp = temp.left;
            }
            temp.left = newNode;
        }
     }

    /**
     * Insert left of a node
     *
     * TC: O(n)
     * SC: O(1)
     */
     public void insertLeft(TBTNode node, int data) {
        if(node == null) {
            return;
        }
        TBTNode newNode = new TBTNode(data);
        newNode.leftBit = node.leftBit;
        newNode.left = node.left;
        node.left = newNode;
        node.leftBit = true;
        newNode.right = node;
        TBTNode temp;
        if(newNode.leftBit) {
            temp = newNode.left;
            while(temp.rightBit) {
                temp = temp.right;
            }
            temp.right = newNode;
        }
     }

    /**
     * Inorder traversal
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void inorder() {
        TBTNode node = root.left;
        while(node.leftBit) {
            node = node.left;
        }
        while(node != root) {
            System.out.print(node.data + " ");
            node = inorderSuccessor(node);
        }
    }

    /**
     * Inorder successor of a node
     *
     * TC: O(n)
     * SC: O(1)
     */
    public TBTNode inorderSuccessor(TBTNode node) {
        if(!node.rightBit) {
            return node.right;
        }
        node = node.right;
        while(node.leftBit) {
            node = node.left;
        }
        return node;
    }

    /**
     * Reverse inorder traversal
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void reverseInorder() {
        TBTNode node = root.left;
        while(node.rightBit) {
            node = node.right;
        }
        while(node != root) {
            System.out.print(node.data + " ");
            node = inorderPredecessor(node);
        }
    }

    /**
     * Inorder predecessor of a node
     *
     * TC: O(n)
     * SC: O(1)
     */
    public TBTNode inorderPredecessor(TBTNode node) {
        if(!node.leftBit) {
            return node.left;
        }
        node = node.left;
        while(node.rightBit) {
            node = node.right;
        }
        return node;
    }

    /**
     * Preorder traversal
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void preorder() {
        TBTNode node = root.left;
        while(node != root) {
            System.out.print(node.data + " ");
            node = preorderSuccessor(node);
        }
    }

    /**
     * Preorder successor of a node
     *
     * TC: O(n)
     * SC: O(1)
     */
    public TBTNode preorderSuccessor(TBTNode node) {
        if(node.leftBit) {
            return node.left;
        }
        while(!node.rightBit) {
            node = node.right;
        }
        return node.right;
    }
}
