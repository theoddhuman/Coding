package com.paul.subham.tree.operations;

import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Construct binary tree from preorder and inorder traversal
 * 2. Construct binary tree from preorder and inorder traversal (Using hashing)
 * 4. Construct binary tree from postorder and inorder traversal
 * 5. Construct binary tree from postorder and inorder traversal (Hashing)
 * 6. Construct binary tree from levelorder and inorder traversal
 * 7. Construct binary tree from levelorder and inorder traversal (Hashing)
 * 8. Construct binary tree from linked list
 * 9. Construct complete binary tree from levelorder traversal
 * 10. Construct of binary tree from postorder and preorder traversal
 * 11. Construct of binary tree from postorder and preorder traversal (Using hashing)
 * 12. Construct of binary tree from preorder and preorder traversal of its mirror tree (Using hashing)
 * 13. Construct ancestor matrix of a binary tree
 * 14. Construct ancestor matrix of a binary tree (Transitive Closure)
 * 15. Construct binary tree from preorder traversal and leaf node information
 * 16. Construct a binary tree from inorder traversal where child nodes are less than parent node.
 * 17. Construct binary tree from parent array
 * 18. Flatten a binary tree (Recursion)
 * 19. Flatten a binary tree (Using stack)
 * 20. Flatten a binary tree (Morris)
 * 21. Construct binary search tree from preorder
 * 22. Recover binary search tree
 */
public class Construction {
    public static void main(String[] args) {
//         int[] pre = {1,2,4,5,3,6,7};
//         int[] preM = {1,3,7,6,2,5,4};
//        LinkedList linkedList = new LinkedList();
//        linkedList.insertAtEnd(1);
//        linkedList.insertAtEnd(2);
//        linkedList.insertAtEnd(3);
//        linkedList.insertAtEnd(4);
//        linkedList.insertAtEnd(5);
//        int level[] = {1,2,3,4,5,6,7};
//        BinaryTree binaryTree = constructBinaryTreePrePreM(pre, preM);
//        boolean[][] m = ancestorMatrixTransitiveClosure(binaryTree);
//        System.out.println();
//        int[] pre = {10,30,20,5,15};
//        char[] preC = {'N', 'N', 'L', 'L','L'};
//        BinaryTree binaryTree = constructTree(pre, preC);
//        binaryTree.levelOrder();
        int[] parent = {6,4,4,2,-1,1,1,2};
        int[] pre = {1,2,3,4,5,6,7};
        int[] in = {4,2,5,1,6,3,7};
    }

    /**
     * Construct binary tree from preorder and inorder traversal
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeFromPreIn(int[] pre, int[] in) {
        if(pre.length == 0 || in.length==0) {
            return null;
        }
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = buildBT(pre, in, 0, in.length-1);
        return binaryTree;
    }

    private static int pIndex = 0;

    private static Node buildBT(int[] pre, int[] in, int inStart, int inEnd) {
        if(inStart > inEnd) {
            return null;
        }
        Node node = new Node(pre[pIndex++]);
        if(inStart == inEnd) {
            return node;
        }
        int index = search(in, inStart, inEnd, node.data);
        node.left = buildBT(pre, in, inStart, index-1);
        node.right = buildBT(pre, in, index+1, inEnd);
        return node;
    }

    private static int search(int[] a, int left, int right, int data) {
        for(int i=left; i<=right; i++) {
            if(a[i] == data) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Construct binary tree from preorder and inorder traversal (using Hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreePreInHashing(int[] pre, int[] in) {
        if(pre.length == 0 || in.length==0) {
            return null;
        }
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i=0; i<in.length; i++) {
            inMap.put(in[i], i);
        }
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = buildBT(pre, in, 0, in.length-1, inMap);
        return binaryTree;
    }

    private static Node buildBT(int[] pre, int[] in, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(inStart > inEnd) {
            return null;
        }
        Node node = new Node(pre[pIndex++]);
        if(inStart == inEnd) {
            return node;
        }
        int index = inMap.get(node.data);
        node.left = buildBT(pre, in, inStart, index-1, inMap);
        node.right = buildBT(pre, in, index+1, inEnd, inMap);
        return node;
    }

    /**
     * Construct binary tree from postorder and inorder traversal
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreePostIn(int[] in, int[] post) {
        BinaryTree binaryTree = new BinaryTree();
        pIndex = post.length-1;
        binaryTree.root = buildBTPost(post, in, 0, in.length-1);
        return binaryTree;
    }

    private static Node buildBTPost(int[] post, int[] in, int inStart, int inEnd) {
        if(inStart > inEnd) {
            return null;
        }
        Node node = new Node(post[pIndex--]);
        if(inStart == inEnd) {
            return node;
        }
        int index = search(in, inStart, inEnd, node.data);
        node.right = buildBTPost(post, in, index+1, inEnd);
        node.left = buildBTPost(post, in, inStart, index-1);
        return node;
    }

    /**
     * Construct binary tree from postorder and inorder traversal (Hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreePostInHashing(int[] in, int[] post) {
        BinaryTree binaryTree = new BinaryTree();
        pIndex = post.length-1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<in.length; i++) {
            map.put(in[i], i);
        }
        binaryTree.root = buildBTPost(post, in, 0, in.length-1, map);
        return binaryTree;
    }

    private static Node buildBTPost(int[] post, int[] in, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(inStart > inEnd) {
            return null;
        }
        Node node = new Node(post[pIndex--]);
        if(inStart == inEnd) {
            return node;
        }
        int index = inMap.get(node.data);
        node.right = buildBTPost(post, in, index+1, inEnd, inMap);
        node.left = buildBTPost(post, in, inStart, index-1, inMap);
        return node;
    }

    /**
     * Construct binary tree from levelorder and inorder traversal
     *
     * TC: O(n^3)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeLevelIn(int[] level, int[] in) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = buildBinaryTree(level, in, 0, in.length-1);
        return binaryTree;
    }

    private static Node buildBinaryTree(int[] level, int[] in, int inStart, int inEnd) {
        if(inStart > inEnd) {
            return null;
        }
        if(inStart == inEnd) {
            return new Node(in[inStart]);
        }
        Node node = null;
        int index = 0;
        for(int i=0; i<level.length; i++) {
            boolean found = false;
            for(int j=inStart; j<=inEnd; j++) {
                if(level[i] == in[j]) {
                    node = new Node(in[j]);
                    index = j;
                    found = true;
                    break;
                }
            }
            if(found) {
                break;
            }
        }
        node.left = buildBinaryTree(level, in, inStart, index-1);
        node.right = buildBinaryTree(level, in, index+1, inEnd);
        return node;
    }

    /**
     * Construct binary tree from levelorder and inorder traversal (Hashing)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeLevelInHashing(int[] level, int[] in) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = buildBinaryTreeHashing(level, in, 0, in.length-1);
        return binaryTree;
    }

    private static Node buildBinaryTreeHashing(int[] level, int[] in, int inStart, int inEnd) {
        if(inStart > inEnd) {
            return null;
        }
        if(inStart == inEnd) {
            return new Node(in[inStart]);
        }
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i=inStart; i<=inEnd; i++) {
            inMap.put(in[i], i);
        }
        Node node = null;
        int index = 0;
        for(int i=0; i<level.length; i++) {
            if(inMap.containsKey(level[i])) {
                node = new Node(level[i]);
                index = inMap.get(level[i]);
                break;
            }
        }
        node.left = buildBinaryTree(level, in, inStart, index-1);
        node.right = buildBinaryTree(level, in, index+1, inEnd);
        return node;
    }

    /**
     * Construct binary tree from linked list
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeFromLinkedList(LinkedList linkedList) {
        if(linkedList.head == null) {
            return null;
        }
        Queue<Node> queue = new java.util.LinkedList<>();
        Node node = new Node(linkedList.head.data);
        queue.add(node);
        com.paul.subham.linkedlist.implementation.single.Node current = linkedList.head.next;
        while(current != null) {
            Node parent = queue.remove();
            Node left = new Node(current.data);
            parent.left = left;
            current = current.next;
            queue.add(left);
            if(current !=null) {
                Node right = new Node(current.data);
                parent.right = right;
                queue.add(right);
                current = current.next;
            }
        }
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = node;
        return binaryTree;
    }

    /**
     * Construct complete binary tree from levelorder traversal
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeFromLevelOrder(int[] level) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = constructBinaryTreeFromLevelOrder(level, 0);
        return binaryTree;
    }

    private static Node constructBinaryTreeFromLevelOrder(int[] level, int i) {
        if(i >= level.length) {
            return null;
        }
        Node node = new Node(level[i]);
        node.left = constructBinaryTreeFromLevelOrder(level, 2*i+1);
        node.right = constructBinaryTreeFromLevelOrder(level, 2*i+2);
        return node;
    }

    /**
     * Construct of binary tree from postorder and preorder traversal
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeFromPostPre(int[] post, int[] pre) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = constructBinaryTreeFromPostPre(post, pre, 0, post.length-1);
        return binaryTree;
    }

    private static Node constructBinaryTreeFromPostPre(int[] post, int[] pre, int postStart, int postEnd) {
        if(pIndex == pre.length || postStart > postEnd) {
            return null;
        }
        Node node = new Node(pre[pIndex++]);
        if(postStart == postEnd || pIndex >= pre.length) {
            return node;
        }
        int postIndex;
        for(postIndex = postStart; postIndex <=postEnd; postIndex++) {
            if(pre[pIndex] == post[postIndex]) {
                break;
            }
        }
        if(postIndex <= postEnd) {
            node.left = constructBinaryTreeFromPostPre(post, pre, postStart, postIndex);
            node.right = constructBinaryTreeFromPostPre(post, pre, postIndex+1, postEnd-1);
        }
        return node;
    }

    /**
     * Construct of binary tree from postorder and preorder traversal (Using hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeFromPostPreHashing(int[] post, int[] pre) {
        BinaryTree binaryTree = new BinaryTree();
        Map<Integer, Integer> postMap = new HashMap<>();
        for(int i=0; i<post.length; i++) {
            postMap.put(post[i], i);
        }
        binaryTree.root = constructBinaryTreeFromPostPre(pre, 0, post.length-1, postMap);
        return binaryTree;
    }

    private static Node constructBinaryTreeFromPostPre(int[] pre, int postStart, int postEnd, Map<Integer, Integer> postMap) {
        if (pIndex == pre.length || postStart > postEnd) {
            return null;
        }
        Node node = new Node(pre[pIndex++]);
        if (postStart == postEnd || pIndex >= pre.length) {
            return node;
        }
        int postIndex = postMap.get(pre[pIndex]);
        node.left = constructBinaryTreeFromPostPre(pre, postStart, postIndex, postMap);
        node.right = constructBinaryTreeFromPostPre(pre, postIndex + 1, postEnd - 1, postMap);
        return node;
    }

    /**
     * Construct of binary tree from preorder and preorder traversal of its mirror tree (Using hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreePrePreM(int[] pre, int[] preM) {
        BinaryTree binaryTree = new BinaryTree();
        Map<Integer, Integer> preMMap = new HashMap<>();
        for(int i=0; i<preM.length; i++) {
            preMMap.put(preM[i], i);
        }
        binaryTree.root = constructBinaryTreePrePreM(pre, 0, preM.length-1, preMMap);
        return binaryTree;
    }

    private static Node constructBinaryTreePrePreM(int[] pre, int pStart, int pEnd, Map<Integer, Integer> preMMap) {
        if(pIndex==pre.length || pStart > pEnd) {
            return null;
        }
        Node node = new Node(pre[pIndex++]);
        if(pStart == pEnd)  {
            return node;
        }
        int index = preMMap.get(pre[pIndex]);
        node.left = constructBinaryTreePrePreM(pre, index, pEnd, preMMap);
        node.right = constructBinaryTreePrePreM(pre, pStart+1, index-1, preMMap);
        return node;
    }

    /**
     * Construct ancestor matrix of a binary tree
     *
     * a[i][j] = true, if j can be reached from i node.
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static boolean[][] ancestorMatrix(BinaryTree binaryTree) {
        boolean[][] matrix = new boolean[10][10];
        ancestorMatrix(binaryTree.root, matrix, 10);
        return matrix;
    }

    private static void ancestorMatrix(Node node, boolean[][] matrix, int n) {
        if(node == null) {
            return;
        }
        ancestorMatrix(node.left, matrix, n);
        ancestorMatrix(node.right, matrix, n);
        if(node.left != null) {
            matrix[node.data][node.left.data] = true;
            for(int i=0; i<n; i++) {
                if(matrix[node.left.data][i]) {
                    matrix[node.data][i] = true;
                }
            }
        }
        if(node.right != null) {
            matrix[node.data][node.right.data] = true;
            for(int i=0; i<n; i++) {
                if(matrix[node.right.data][i]) {
                    matrix[node.data][i] = true;
                }
            }
        }
    }

    /**
     * Construct ancestor matrix of a binary tree (Transitive Closure)
     *
     * a[i][j] = true, if j can be reached from i node.
     *
     * TC: O(n^3)
     * SC: O(n)
     */
    public static boolean[][] ancestorMatrixTransitiveClosure(BinaryTree binaryTree) {
        boolean[][] m = new boolean[10][10];
        ancestorUtil(binaryTree.root, -1, m);
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                for(int k=0; k<10; k++) {
                    if(m[i][k] && m[k][j]) {
                        m[i][j] = true;
                    }
                }
            }
        }
        return m;
    }

    private static void ancestorUtil(Node node, int index, boolean[][] m) {
        if(node == null) {
            return;
        }
        if(index != -1) {
            m[index][node.data] = true;
        }
        ancestorUtil(node.left, node.data, m);
        ancestorUtil(node.right, node.data, m);
    }

    /**
     * Construct binary tree from preorder traversal and leaf node information

     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree constructTree(int[] pre, char[] preC) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = constructTreeUtil(pre,preC,pre.length);
        return binaryTree;
    }

    private static Node constructTreeUtil(int[] pre, char[] preC, int n) {
        if(pIndex == n) {
            return null;
        }
        Node node = new Node(pre[pIndex++]);
        if(preC[pIndex-1] == 'N') {
            node.left = constructTreeUtil(pre, preC, n);
            node.right = constructTreeUtil(pre, preC, n);
        }
        return node;
    }

    /**
     * Construct a binary tree from inorder traversal where child nodes are less than parent node.

     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree buildBinaryTree(int[] in) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = buildTree(in, 0, in.length-1);
        return binaryTree;
    }

    private static Node buildTree(int[] in, int start, int end) {
        if(start > end) {
            return null;
        }
        int maxIndex = max(in, start, end);
        Node node =  new Node(in[maxIndex]);
        if(start == end) {
            return node;
        }
        node.left = buildTree(in, start, maxIndex-1);
        node.right = buildTree(in, maxIndex+1, end);
        return node;
    }

    private static int max(int[] a, int start, int end) {
        int max = a[start];
        int maxIndex= start;
        for(int i=start+1; i<=end; i++) {
            if(a[i] > max) {
                max = a[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Construct binary tree from parent array
     *
     * Given array that represents a tree in such a way that array indexes are values in tree nodes and array values give
     * the parent node of that particular index. the value of the root index will be always -1.
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree buildTreeFromParentArray(int[] parent) {
        BinaryTree binaryTree = new BinaryTree();
        Node[] created = new Node[parent.length];
        for(int i=0; i<parent.length; i++) {
            if(created[i] == null) {
                createNode(parent, i, created, binaryTree);
            }
        }
        return binaryTree;
    }

    public static void createNode(int[] parent, int i, Node[] created, BinaryTree binaryTree) {
        if(created[i] != null) {
            return;
        }
        created[i] = new Node(i);
        if(parent[i] == -1) {
            binaryTree.root = created[i];
            return;
        }
        if(created[parent[i]] == null) {
            createNode(parent, parent[i], created, binaryTree);
        }
        Node p = created[parent[i]];
        if(p.left == null) {
            p.left = created[i];
        } else {
            p.right = created[i];
        }
    }

    /**
     * Flatten a binary tree (Recursion)
     *
     * TC: O(n)
     * SC: O(n)
     */
    private static Node prev = null;
    public static void flatten(Node root) {
        if(root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    /**
     * Flatten a binary tree (Using stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void flattenStack(Node root) {
        if(root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node current = stack.pop();
            if(current.right != null) {
                stack.push(current.right);
            }
            if(current.left != null) {
                stack.push(current.left);
            }
            if(!stack.isEmpty()) {
                current.right = stack.peek();
            }
            current.left = null;
        }
    }

    /**
     * Flatten a binary tree (Morris)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void flattenMorris(Node root) {
        if(root == null) {
            return;
        }
        Node current = root;
        while(current != null) {
            if(current.left != null) {
                Node pred = current.left;
                while(pred.right != null) {
                    pred = pred.right;
                }
                pred.right = current.right;
                current.right = current.left;
                current.left = null;
            }
            current = current.right;
        }
    }

    /**
     * Construct binary search tree from preorder
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static Node bstFromPreorder(int[] preorder) {
        pIndex = 0;
        return bstFromPreorder(preorder, Integer.MAX_VALUE);
    }

    private static Node bstFromPreorder(int[] pre, int bound) {
        if(pIndex == pre.length || pre[pIndex] > bound) {
            return null;
        }
        Node node = new Node(pre[pIndex++]);
        node.left = bstFromPreorder(pre, node.data);
        node.right = bstFromPreorder(pre, bound);
        return node;
    }

    /**
     * Recover binary search tree
     *
     * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake.
     * Recover the tree without changing its structure.
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void recoverTree(Node root) {
        inorder(root);
        if(first != null && second != null) {
            int x = first.data;
            first.data = second.data;
            second.data = x;
        }
    }

    static Node first;
    static Node second;

    private static void inorder(Node node) {
        if(node == null) {
            return;
        }
        inorder(node.left);
        if(prev != null && node.data < prev.data) {
            if(first == null) {
                first = prev;
            }
            second = node;
        }
        prev = node;
        inorder(node.right);
    }

}
