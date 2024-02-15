package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Print all root to leaf paths of a binary tree
 * 2. Print ancestors of a node in binary tree (Using Recursion)
 * 3. Print ancestors of a node in binary tree (Iterative)
 * 4. Check if one node has path from another node (recursive)
 * 5. Print path from root to a node in a binary tree (recursive)
 * 6. Print path from root to a node in a binary tree (iterative - using stack)
 * 7. Lowest common ancestor of a binary tree ( by storing path from root )
 * 8. Lowest common ancestor of a binary tree ( single traversal )
 * 9. Lowest common ancestor of a binary tree ( single traversal with presence check)
 * 10. Lowest common ancestor of a binary tree (Using hashing)
 * 11. Distance between two nodes of a binary tree (Using the lowest common ancestor)
 * 12. Distance between two nodes of a binary tree (Using the lowest common ancestor - efficient)
 * 13. Distance between two nodes of a binary tree (Single traversal)
 */
public class Path {
    /**
     *          1
     *        /   \
     *      2      3
     *    /   \
     *  4      5
     *   \   /   \
     *   11 6     8
     *        \
     *         7
     */
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(1);
        bt.root.left = new Node(2);
        bt.root.right = new Node(3);
        bt.root.left.left = new Node(4);
        bt.root.left.left.right = new Node(11);
        bt.root.left.right = new Node(5);
        bt.root.left.right.left = new Node(6);
        bt.root.left.right.right = new Node(8);
        bt.root.left.right.left.right = new Node(7);
        bt.levelOrder();
        System.out.println();
        System.out.println(distanceSingleTraversal(bt, 11,1));
    }


    /**
     * Print all root to leaf paths of a binary tree
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void printPathRecursive(BinaryTree binaryTree) {
        printPathRecursiveUtil(binaryTree.root, new int[100], 0);
    }

    private static void printPathRecursiveUtil(Node node, int[] path, int pathLen) {
        if(node == null) {
            return;
        }
        path[pathLen] = node.data;
        pathLen++;
        if(node.left == null && node.right == null) {
            for(int i=0; i<pathLen; i++) {
                System.out.print(path[i] + " ");
            }
            System.out.println();
        } else {
            printPathRecursiveUtil(node.left, path, pathLen);
            printPathRecursiveUtil(node.right, path, pathLen);
        }
    }

    /**
     * Print ancestors of a node in binary tree (Using Recursion)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void printAncestorsByRecursion(BinaryTree binaryTree, int child) {
        printAncestorsUtil(binaryTree.root, child);
    }

    private static boolean printAncestorsUtil(Node node, int child) {
        if(node == null) {
            return false;
        }
        if(node.data == child) {
            return true;
        }
        if(printAncestorsUtil(node.left, child) || printAncestorsUtil(node.right, child)) {
            System.out.print(node.data + " ");
            return true;
        }
        return false;
    }

    /**
     * Print ancestors of a node in binary tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void printAncestorsIterative(BinaryTree binaryTree, int child) {
        Stack<Node> stack = new Stack<>();
        Node current = binaryTree.root;
        while(!stack.empty() || current != null) {
            while(current != null && current.data != child) {
                stack.push(current);
                current = current.left;
            }
            if(current != null) {
                break;
            }
            if(!stack.isEmpty() && stack.peek().right == null) {
                current = stack.pop();
                while(!stack.isEmpty() && stack.peek().right == current) {
                    current = stack.pop();
                }
            }
            current = !stack.isEmpty() ? stack.peek().right : null;
        }
        if(!stack.isEmpty()) {
            while(!stack.isEmpty()) {
                System.out.print(stack.pop().data + " ");
            }
        }
    }

    /**
     * Check if one node has path from another node (recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean hasPath(Node node, List<Integer> path, int target) {
        if(node == null) {
            return false;
        }
        path.add(node.data);
        if(node.data == target) {
            return true;
        }
        if(hasPath(node.left, path, target) || hasPath(node.right, path, target)) {
            return true;
        }
        path.remove(path.size()-1);
        return false;
    }

    /**
     * Print path from root to a node in a binary tree (recursive)
     *
     * TC: O(n)
     * SC: O(n)
     *
     */
    public static void printPathFromRoot(BinaryTree binaryTree, int target) {
        List<Integer> list = new ArrayList<>();
        if(hasPath(binaryTree.root, list, target)) {
            System.out.println(list);
        } else {
            System.out.println("No Path");
        }
    }

    /**
     * Print path from root to a node in a binary tree (iterative - using stack)
     *
     * TC: O(n)
     * SC: O(n)
     *
     */
    public static void printPathFromRootIterative(BinaryTree binaryTree, int target) {
        List<Integer> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = binaryTree.root;
        Node prev = null;
        while(!stack.isEmpty() || current != null) {
            while(current != null) {
                stack.push(current);
                list.add(current.data);
                current = current.left;
            }
            current = stack.peek();
            if(current.right != null && current.right != prev) {
                current = current.right;
            } else {
                if(current.data == target) {
                    System.out.println(list);
                    return;
                } else {
                    stack.pop();
                    list.remove(list.size() -1);
                    prev = current;
                    current = null;
                }
            }
        }
        System.out.println("No Path");
    }

    /**
     * Lowest common ancestor of a binary tree ( by storing path from root )
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int lowestCommonAncestorByPath(BinaryTree bt, int a, int b) {
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();
        if(!hasPath(bt.root, path1, a) || !hasPath(bt.root, path2, b)) {
            return -1;
        }
        int i;
        for(i=0; i<path1.size() && i<path2.size(); i++) {
            if(path1.get(i) != path2.get(i)) {
                break;
            }
        }
        return path1.get(i-1);
    }

    /**
     * Lowest common ancestor of a binary tree ( single traversal )
     *
     * This method assumes that keys are present in Binary Tree. If one key is present and the other is absent,
     * then it returns the present key as LCA (Ideally should have returned NULL)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int lowestCommonAncestorSingleTraversal(BinaryTree bt, int a, int b) {
        return lcaUtil(bt.root, a, b).data;
    }

    private static Node lcaUtil(Node node, int a, int b) {
        if(node == null) {
            return null;
        }
        if(node.data == a || node.data == b) {
            return node;
        }
        Node leftLca = lcaUtil(node.left, a, b);
        Node rightLca = lcaUtil(node.right, a, b);
        if(leftLca != null && rightLca != null) {
            return node;
        }
        return leftLca != null ? leftLca : rightLca;
    }

    /**
     * Lowest common ancestor of a binary tree ( single traversal with presence check)
     *
     * This method traverse the complete binary tree to check nodes are present or not.
     *
     * TC: O(n)
     * SC: O(n)
     */
    private static boolean v1 = false;
    private static boolean v2 = false;
    public static int lowestCommonAncestorSingleTraversalCorrect(BinaryTree bt, int a, int b) {
        Node lca = lcaCorrectUtil(bt.root, a, b);
        if(v1 && v2) {
            return lca.data;
        }
        return -1;
    }

    private static Node lcaCorrectUtil(Node node, int a, int b) {
        if(node == null) {
            return null;
        }
        Node temp = null;
        if(node.data == a) {
            v1 = true;
            temp = node;
        }
        if(node.data == b) {
            v2 = true;
            temp = node;
        }
        Node leftLca = lcaCorrectUtil(node.left, a, b);
        Node rightLca = lcaCorrectUtil(node.right, a, b);
        if(temp != null) {
            return temp;
        }
        if(leftLca != null && rightLca != null) {
            return node;
        }
        return leftLca != null ? leftLca : rightLca;
    }

    /**
     * Lowest common ancestor of a binary tree (Using hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int lowestCommonAncestorUsingHashing(BinaryTree binaryTree, int a, int b) {
        Map<Node, Node> parentMap = buildParentMap(binaryTree);
        Queue<Node> queue = new LinkedList<>();
        Node p = null;
        Node q = null;
        queue.add(binaryTree.root);
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            if(current.data == a) {
                p = current;
            }
            if(current.data == b) {
                q = current;
            }
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
        Set<Node> ancestors = new HashSet<>();
        while(p != null) {
            ancestors.add(p);
            p = parentMap.get(p);
        }
        while(q != null) {
            if(ancestors.contains(q)) {
                return q.data;
            }
            q = parentMap.get(q);
        }
        return -1;
    }

    private static Map<Node, Node> buildParentMap(BinaryTree binaryTree) {
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            if(current.left != null) {
                map.put(current.left, current);
                queue.add(current.left);
            }
            if(current.right != null) {
                map.put(current.right, current);
                queue.add(current.right);
            }
        }
        return map;
    }

    /**
     * Distance between two nodes of a binary tree (Using the lowest common ancestor)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int distanceUsingLca(BinaryTree binaryTree, int a, int b) {
        int distanceA = Structure.levelOfNodeRecursive(binaryTree, a)-1;
        int distanceB = Structure.levelOfNodeRecursive(binaryTree, b)-1;
        int lca = lowestCommonAncestorSingleTraversalCorrect(binaryTree, a, b);
        int distanceLca = Structure.levelOfNodeRecursive(binaryTree, lca)-1;
        return  distanceA + distanceB - 2*distanceLca;
    }

    /**
     * Distance between two nodes of a binary tree (Using the lowest common ancestor - efficient)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int distanceUsingLcaEfficient(BinaryTree binaryTree, int a, int b) {
        Node lca = lcaCorrectUtil(binaryTree.root, a, b);
        int distanceA = Structure.levelOfNodeRecursiveUtil(lca, a, 0);
        int distanceB = Structure.levelOfNodeRecursiveUtil(lca, b, 0);
        return distanceA + distanceB;
    }

    /**
     * Distance between two nodes of a binary tree (Single traversal)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int distanceSingleTraversal(BinaryTree binaryTree, int a, int b) {
        ans = 0;
        distanceUtil(binaryTree.root, a , b);
        return ans;
    }

    private static int ans;

    private static int distanceUtil(Node node, int a, int b) {
        if(node == null) {
            return 0;
        }
        int left = distanceUtil(node.left, a, b);
        int right = distanceUtil(node.right, a, b);
        if(node.data == a || node.data == b) {
            if (left != 0 || right != 0) {
                ans = Math.max(left, right);
                return 0;
            } else {
                return 1;
            }
        }
        if( left != 0 && right != 0) {
            ans = left + right;
            return 0;
        }
        if(left != 0 || right != 0) {
            return Math.max(left, right) + 1;
        }
        return 0;
    }

}
