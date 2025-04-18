package com.paul.subham.tree.implementation.binary;

import com.paul.subham.tree.operations.Structure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 1. insert an element
 * 2. delete an element
 * 3. inorder traversal recursive
 * 4. preorder traversal recursive
 * 5. postorder traversal recursive
 * 6. preorder traversal iterative
 * 7. inorder traversal iterative
 * 8. Inorder traversal (Morris)
 * 9. Preorder traversal (Morris)
 * 10. postorder traversal iterative
 * 11. level order traversal
 * 12. level order traversal (recursive)
 * 13. find maximum recursive
 * 14. find maximum iterative
 * 15. find minimum recursive
 * 16. find minimum iterative
 * 17. search an element recursive
 * 18. search an element iterative
 * 19. size of tree recursive
 * 20. size of tree iterative
 * 21. Reverse level order traversal (recursive)
 * 22. Reverse level order traversal (Using stack and queue)
 * 23. Reverse level order traversal (Hashing)
 * 24. Reverse inorder traversal (Recursive)
 * 25. Zigzag traversal (Recursive)
 * 26. Zigzag traversal
 *
 */
public class BinaryTree {
    public Node root;

    public BinaryTree(){
        root = null;
    }

    //insert an element, TC: O(n), SC: O(9)
    public void insert(int data) {
        Node newNode = new Node(data);
        if(root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node current;
        while(!queue.isEmpty()) {
            current = queue.remove();
            if(current.left != null) {
                queue.add(current.left);
            } else {
                current.left = newNode;
                return;
            }
            if(current.right != null) {
                queue.add(current.right);
            } else {
                current.right = newNode;
                return;
            }
        }
    }

    //insert an element with parent pointer, TC: O(n), SC: O(9)
    public void insertWithParentPointer(int data) {
        Node newNode = new Node(data);
        if(root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node current;
        while(!queue.isEmpty()) {
            current = queue.remove();
            if(current.left != null) {
                queue.add(current.left);
            } else {
                current.left = newNode;
                newNode.parent = current;
                return;
            }
            if(current.right != null) {
                queue.add(current.right);
            } else {
                current.right = newNode;
                newNode.parent = current;
                return;
            }
        }
    }

    //delete an element, TC: O(n), SC: O(9)
    void delete(int data) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            if(root.data == data) {
                root = null;
                return;
            } else {
                return;
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node current = null;
        Node keyNode = null;
        while(!queue.isEmpty()) {
            current = queue.remove();
            if(current.data == data) {
                keyNode = current;
            }
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }

        if(keyNode != null) {
            int x = current.data;
            deleteDeepest(current);
            keyNode.data = x;
        }
    }

    private void deleteDeepest(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node current;
        while(!queue.isEmpty()) {
            current = queue.remove();
            if(current == node) {
                current = null;
                return;
            }
            if(current.left != null) {
                if(current.left == node) {
                    current.left = null;
                    return;
                } else {
                    queue.add(current.left);
                }
            }
            if(current.right != null) {
                if(current.right == node) {
                    current.right = null;
                    return;
                } else {
                    queue.add(current.right);
                }
            }
        }
    }

    //inorder traversal recursive, TC: O(n), SC: O(n)
    public void inOrderRecursive(Node node) {
        if(node != null) {
            inOrderRecursive(node.left);
            System.out.print(node.data + " ");
            inOrderRecursive(node.right);
        }
    }

    //preorder traversal recursive, TC: O(n), SC: O(n)
    public void preOrderRecursive(Node node) {
        if(node != null) {
            System.out.print(node.data + " ");
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    //postorder traversal recursive, TC: O(n), SC: O(n)
    void postOrderRecursive(Node node) {
        if(node != null) {
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            System.out.print(node.data + " ");
        }
    }

    //preorder traversal iterative, TC: O(n), SC: O(n)
    void preOrderIterative() {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while(true) {
            while(current != null) {
                System.out.print(current.data + " ");
                stack.push(current);
                current = current.left;
            }
            if(stack.isEmpty()) {
                break;
            }
            current = stack.pop().right;
        }
    }

    //inorder traversal iterative, TC: O(n), SC: O(n)
    void inOrderIterative() {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while(true) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }
            if(stack.isEmpty()) {
                break;
            }
            current = stack.pop();
            System.out.print(current.data + " ");
            current = current.right;
        }
    }

    /**
     * Inorder traversal (Morris)
     *
     * TC: O(n)
     * SC: O(1)
     */
    void inOrderMorris() {
        Node current = root;
        while(current != null) {
            if(current.left == null) {
                System.out.print(current.data + " ");
                current = current.right;
            } else {
                Node pred = current.left;
                while(pred.right != null && pred.right != current ) {
                    pred = pred.right;
                }
                if(pred.right == null) {
                    pred.right = current;
                    current = current.left;
                } else {
                    pred.right = null;
                    System.out.print(current.data +" ");
                    current = current.right;
                }
            }
        }
    }

    /**
     * Preorder traversal (Morris)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void preOrderMorris() {
        Node current = root;
        while(current != null) {
            if(current.left == null) {
                System.out.print(current.data + " ");
                current = current.right;
            } else {
                Node pred = current.left;
                while(pred.right != null && pred.right != current) {
                    pred = pred.right;
                }
                if(pred.right == null) {
                    System.out.print(current.data + " ");
                    pred.right = current;
                    current = current.left;
                } else {
                    pred.right = null;
                    current = current.right;
                }
            }
        }
    }



    //postorder traversal iterative, TC: O(n), SC: O(n)
    public void postOrderIterative() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node current = null;
        Node prev = null;
        while(!stack.isEmpty()) {
            current = stack.peek();
            if(prev == null || prev.left == current || prev.right == current) {
                if(current.left != null) {
                    stack.push(current.left);
                } else if(current.right != null) {
                    stack.push(current.right);
                }
            } else if(current.left == prev) {
                if(current.right != null) {
                    stack.push(current.right);
                }
            } else {
                System.out.print(current.data + " ");
                stack.pop();
            }
            prev = current;
        }
    }

    /**
     * level order traversal
     * TC: O(n)
     * SC: O(n)
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node current = null;
        while(!queue.isEmpty()) {
            current = queue.remove();
            System.out.print(current.data + " ");
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
    }

    /**
     * level order traversal (recursive)
     * TC: O(n^2)
     * SC: O(n)
     */
    public void levelOrderRecursive() {
        int height = Structure.heightIterative(this);
        for(int i=1; i<=height; i++) {
            printLevel(root, i);
        }
    }

    private void printLevel(Node node, int level) {
        if(node == null) {
            return;
        }
        if(level == 1) {
            System.out.print(node.data + " ");
        }
        printLevel(node.left, level-1);
        printLevel(node.right, level-1);
    }

    //find maximum recursive, TC: O(n), SC: O(n)
    public int maxRecursive(Node node) {
        int max = Integer.MIN_VALUE;
        if(node != null) {
            int leftMax = maxRecursive(node.left);
            int rightMax = maxRecursive(node.right);
            max = Math.max(node.data, Math.max(leftMax, rightMax));
        }
        return max;
    }

    //find maximum iterative, TC: O(n), SC: O(n)
    int maxIterative() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node current;
        int max = Integer.MIN_VALUE;
        while(!queue.isEmpty()) {
            current = queue.remove();
            max = Math.max(current.data, max);
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
        return max;
    }

    //find minimum recursive, TC: O(n), SC: O(n)
    public int minRecursive(Node node) {
        int min = Integer.MAX_VALUE;
        if(node != null) {
            int leftMin = minRecursive(node.left);
            int rightMin = minRecursive(node.right);
            min = Math.min(node.data, Math.min(leftMin, rightMin));
        }
        return min;
    }

    //find minimum iterative, TC: O(n), SC: O(n)
    int minIterative() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node current;
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            current = queue.remove();
            min = Math.min(current.data, min);
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
        return min;
    }

    //search an element recursive, TC:O(n), SC:O(n)
    boolean searchRecursive(Node node, int data) {
        if(node == null) {
            return false;
        }
        if(node.data == data) {
            return true;
        }
        if(searchRecursive(node.left, data)) {
            return true;
        }
        return searchRecursive(node.right, data);
    }

    //search an element iterative, TC:O(n), SC:O(n)
    boolean searchIterative(int data) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node current;
        while(!queue.isEmpty()) {
            current = queue.remove();
            if(current.data == data) {
                return true;
            }
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
        return false;
    }

    //size of tree recursive
    int sizeRecursive(Node node) {
        if(node == null) {
            return 0;
        }
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }

    //size of tree iterative
    int sizeIterative() {
        if(root == null) {
            return 0;
        }
        int count = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node current;
        while(!queue.isEmpty()) {
            current = queue.remove();
            count++;
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
        return count;
    }

    /**
     * Reverse level order traversal (recursive)
     * TC: O(n^2)
     * SC: O(n)
     */
    public void reverseLevelOrderRecursive() {
        int height = Structure.heightIterative(this);
        for(int i=height; i>=1; i--) {
            printLevel(root, i);
        }
    }

    /**
     * Reverse level order traversal (Using stack and queue)
     * TC: O(n)
     * SC: O(n)
     */
    public void reverseLevelOrderTraversalUsingStackAndQueue() {
        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node node = queue.remove();
            stack.push(node);
            if(node.right != null ){
                queue.add(node.right);
            }
            if(node.left != null ) {
                queue.add(node.left);
            }
        }
        while(!stack.isEmpty()) {
            System.out.print(stack.pop().data + " ");
        }
    }

    /**
     * Reverse level order traversal (Hashing)
     * TC: O(n)
     * SC: O(n)
     */
    public void reverseLevelOrderTraversalUsingHashing() {
        Map<Integer, List<Node>> map = new LinkedHashMap<>();
        addNodeToMap(root, 1, map);
        for(int level = map.size(); level >=1; level--) {
            for(Node node : map.get(level)) {
                System.out.print(node.data + " ");
            }
        }
    }

    private void addNodeToMap(Node node, int level, Map<Integer, List<Node>> map) {
        if(node == null) {
            return;
        }
        if(!map.containsKey(level)) {
            map.put(level, new ArrayList<>());
        }
        map.get(level).add(node);
        addNodeToMap(node.left, level+1, map);
        addNodeToMap(node.right, level+1, map);
    }

    public void reverseInorder() {
        reverseInorderUtil(root);
    }

    /**
     * Reverse inorder traversal (Recursive)
     * TC: O(n)
     * SC: O(n)
     */
    private void reverseInorderUtil(Node node) {
        if(node != null) {
            reverseInorderUtil(node.right);
            System.out.print(node.data + " ");
            reverseInorderUtil(node.left);
        }
    }

    /**
     * Zigzag traversal (Recursive)
     * TC: O(n^2)
     * SC: O(n)
     */
    public void zigzagTraversalRecursive() {
        int height = Structure.heightRecursive(this);
        boolean ltr = false;
        for(int i=1; i<=height; i++) {
            printGivenLevel(root, i, ltr);
            ltr = !ltr;
        }
    }

    private void printGivenLevel(Node node, int level, boolean ltr) {
        if(node == null) {
            return;
        }
        if(level == 1) {
            System.out.print(node.data + " ");
        } else if(level > 1) {
            if(ltr) {
                printGivenLevel(node.left, level-1, ltr);
                printGivenLevel(node.right, level-1, ltr);
            } else {
                printGivenLevel(node.right, level-1, ltr);
                printGivenLevel(node.left, level-1, ltr);
            }
        }
    }

    /**
     * Zigzag traversal
     * TC: O(n)
     * SC: O(n)
     */
    public void zigzagTraversal() {
        if(root == null) {
            return;
        }
        Stack<Node> currentLevel = new Stack<>();
        Stack<Node> nextLevel = new Stack<>();
        currentLevel.push(root);
        boolean leftToRight = true;
        while(!currentLevel.isEmpty()) {
            Node node = currentLevel.pop();
            System.out.print(node.data + " ");
            if (leftToRight) {
                if(node.left != null) {
                    nextLevel.push(node.left);
                }
                if(node.right != null) {
                    nextLevel.push(node.right);
                }
            } else {
                if(node.right != null) {
                    nextLevel.push(node.right);
                }
                if(node.left != null) {
                    nextLevel.push(node.left);
                }
            }
            if(currentLevel.isEmpty()) {
                leftToRight = !leftToRight;
                Stack<Node> temp = nextLevel;
                nextLevel = currentLevel;
                currentLevel = temp;
            }
        }
    }
}

