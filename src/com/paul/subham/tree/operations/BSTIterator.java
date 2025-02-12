package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.Node;

import java.util.Stack; /**
 * Binary search tree inorder iterator
 *
 * TC: O(1)
 * SC: O(h)
 */
public class BSTIterator {
    private Stack<Node> nextStack = new Stack<>();
    private Stack<Node> beforeStack = new Stack<>();

    public BSTIterator(Node root) {
        pushNext(root);
    }

    public int next() {
        Node res = nextStack.pop();
        pushNext(res.right);
        return res.data;
    }

    public int before() {
        Node res = beforeStack.pop();
        pushBefore(res.left);
        return res.data;
    }

    public boolean hasNext() {
        return !nextStack.isEmpty();
    }

    public boolean hasBefore() {
        return !beforeStack.isEmpty();
    }

    private void pushNext(Node node) {
        while(node != null) {
            nextStack.push(node);
            node = node.left;
        }
    }

    private void pushBefore(Node node) {
        while(node != null) {
            beforeStack.push(node);
            node = node.left;
        }
    }
}
