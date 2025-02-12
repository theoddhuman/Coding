package com.paul.subham.tree.operations;

import com.paul.subham.linkedlist.implementation.doubly.DLNode;
import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author subham.paul
 *
 * 1. Sum of a level in a binary tree (Recursive)
 * 2. Sum of a level in a binary tree (Iterative)
 * 3. Maximum level sum of a binary tree
 * 4. Maximum level sum of a binary tree (Using level order)
 * 5. Maximum level sum of a binary tree (Using preorder)
 * 6. Sum of all nodes in a binary tree (Recursive)
 * 7. Sum of all nodes in a binary tree (Iterative)
 * 8. Sum of all nodes in a binary tree having child node value x (Recursive)
 * 9. Sum of all nodes in a binary tree having child node value x (Iterative)
 * 10. Vertical sum of a binary tree (Using Tree Map)
 * 11. Vertical sum of a binary tree (Using Doubly Linked List)
 * 12. Average of levels in a binary tree
 * 13. Sum of left leaves of a binary tree (Recursive)
 * 14. Sum of left leaves of a binary tree (Iterative)
 * 15. Sum of right leaves of a binary tree (Recursive)
 * 16. Sum of right leaves of a binary tree (Iterative)
 * 17. Sum of all nodes of the given perfect binary tree
 * 18. Sum of all nodes of the given perfect binary tree (Efficient)
 * 19. Diagonal sum of a binary tree
 * 20. Diagonal sum of a binary tree (diagonal relation in matrices)
 * 21. Children Sum in a Binary Tree
 * 22. Two sum in binary search tree (inorder)
 * 23. Two sum in binary search tree (Using iterator)
 *
 */
public class Sum {
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
        System.out.println(diagonalSumMatrix(bt));
//        bt.levelOrder();
//        System.out.println();
//        System.out.println(rightLeavesSumIterative(bt));
        //System.out.println(sumPerfectBinaryTreeEfficient(3));
        //System.out.println(sumWithSpecificChildRecursive(bt, 6));

    }

    /**
     * Sum of a level in a binary tree (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int levelSumRecursive(BinaryTree binaryTree, int level) {
        return levelSumUtil(binaryTree.root, level);
    }

    private static int levelSumUtil(Node node, int level) {
        if (node == null) {
            return 0;
        }
        if (level == 1) {
            return node.data;
        } else if (level > 1) {
            return levelSumUtil(node.left, level - 1) + levelSumUtil(node.right, level - 1);
        }
        return 0;
    }

    /**
     * Sum of a level in a binary tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int levelSumIterative(BinaryTree binaryTree, int level) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int currentLevel = 1;
        int sum = binaryTree.root.data;
        while(true) {
            int nodeCount = queue.size();
            if(currentLevel == level) {
                return sum;
            }
            sum = 0;
            while(--nodeCount >= 0) {
                Node current = queue.remove();
                if(current.left != null) {
                    sum += current.left.data;
                    queue.add(current.left);
                }
                if(current.right != null) {
                    sum += current.right.data;
                    queue.add(current.right);
                }
            }
            currentLevel++;
        }
    }

    /**
     * Maximum level sum of a binary tree
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int maxLevelSum(BinaryTree binaryTree) {
        int max = 0;
        int height = Structure.heightIterative(binaryTree);
        int width;
        for(int i=1; i<=height; i++) {
            width = levelSumIterative(binaryTree, i);
            max = Math.max(max, width);
        }
        return max;
    }

    /**
     * Maximum level sum of a binary tree (Using level order)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxLevelSumLevelOrder(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int maxSum = binaryTree.root.data;
        int sum;
        while(true) {
            int nodeCount = queue.size();
            if(nodeCount == 0) {
                return maxSum;
            }
            sum = 0;
            while(--nodeCount >= 0) {
                Node current = queue.remove();
                sum += current.data;
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
            }
            maxSum = Math.max(sum, maxSum);
        }
    }

    /**
     * Maximum level sum of a binary tree (Using preorder)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxLevelSumPreOrder(BinaryTree binaryTree) {
        int max = 0;
        int height = Structure.heightIterative(binaryTree);
        int[] sum = new int[height + 1];
        maxLevelSumUtil(binaryTree.root, sum, 1);
        for(int i = 0; i< sum.length; i++) {
            max = Math.max(sum[i], max);
        }
        return max;
    }

    private static void maxLevelSumUtil(Node node, int[] sum, int level) {
        if(node == null) {
            return;
        }
        sum[level] += node.data;
        maxLevelSumUtil(node.left, sum, level+1);
        maxLevelSumUtil(node.right, sum, level+1);
    }

    /**
     * Sum of all nodes in a binary tree (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int sumRecursive(BinaryTree binaryTree) {
        return sumUtil(binaryTree.root);
    }

    private static int sumUtil(Node node) {
        if(node == null) {
            return 0;
        }
        return node.data + sumUtil(node.left) + sumUtil(node.right);
    }

    /**
     * Sum of all nodes in a binary tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int sumIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int sum = 0;
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            sum += current.data;
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
        return sum;
    }

    /**
     * Sum of all nodes in a binary tree having child node value x (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int sumWithSpecificChildRecursive(BinaryTree binaryTree, int x) {
        return sumWithSpecificChildRecursiveUtil(binaryTree.root, x);
    }

    private static int sumWithSpecificChildRecursiveUtil(Node node, int x) {
        if(node == null) {
            return 0;
        }
        int sum = 0;
        if((node.left != null && node.left.data == x) || (node.right != null && node.right.data == x)) {
            sum = node.data;
        }
        return sum + sumWithSpecificChildRecursiveUtil(node.left, x) + sumWithSpecificChildRecursiveUtil(node.right, x);
    }

    /**
     * Sum of all nodes in a binary tree having child node value x (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int sumWithSpecificChildIterative(BinaryTree binaryTree, int x) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int sum = 0;
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            if((current.left != null && current.left.data == x) || (current.right != null && current.right.data == x)) {
                sum = current.data;
            }
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
        return sum;
    }

    /**
     * Vertical sum of a binary tree (Using Tree Map)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static void printVerticalSums(BinaryTree binaryTree) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        verticalSumUtil(binaryTree.root, 0, map);
        map.values().forEach(sum -> System.out.print(sum + " "));
    }

    private static void verticalSumUtil(Node node, int vIndex, TreeMap<Integer, Integer> map) {
        if(node == null) {
            return;
        }
        verticalSumUtil(node.left, vIndex-1, map);
        int prevSum = map.get(vIndex) == null ? 0 : map.get(vIndex);
        map.put(vIndex, prevSum + node.data);
        verticalSumUtil(node.right, vIndex + 1, map);
    }

    /**
     * Vertical sum of a binary tree (Using Doubly Linked List)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void printVerticalSumsDLL(BinaryTree binaryTree) {
        DLNode dlNode = new DLNode(0);
        verticalSumUtil(binaryTree.root, dlNode);
        while(dlNode.pre != null) {
            dlNode = dlNode.pre;
        }
        while(dlNode != null) {
            System.out.print(dlNode.data + " ");
            dlNode = dlNode.next;
        }
    }

    private static void verticalSumUtil(Node node, DLNode dlNode) {
        dlNode.data = dlNode.data + node.data;
        if(node.left != null) {
            if(dlNode.pre == null) {
                dlNode.pre = new DLNode(0);
                dlNode.pre.next = dlNode;
            }
            verticalSumUtil(node.left, dlNode.pre);
        }
        if(node.right != null) {
            if(dlNode.next == null) {
                dlNode.next = new DLNode(0);
                dlNode.pre.next = dlNode;
            }
            verticalSumUtil(node.right, dlNode.next);
        }
    }

    /**
     * Average of levels in a binary tree
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void averageOfLevels(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        while(!queue.isEmpty()) {
            int sum = 0;
            int count = 0;
            Queue<Node> tempQueue = new LinkedList<>();
            while(!queue.isEmpty()) {
                Node current = queue.remove();
                sum += current.data;
                count++;
                if(current.left != null) {
                    tempQueue.add(current.left);
                }
                if(current.right != null) {
                    tempQueue.add(current.right);
                }
            }
            queue = tempQueue;
            System.out.println(sum * 1.0/count + " ");
        }
    }

    /**
     * Sum of left leaves of a binary tree (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int leftLeavesSum(BinaryTree binaryTree) {
        return leftLeavesSum(binaryTree.root);
    }

    private static int leftLeavesSum(Node node) {
        int res = 0;
        if(node == null) {
            return res;
        }
        if(isLeaf(node.left)) {
            res += node.left.data;
        } else {
            res += leftLeavesSum(node.left);
        }
        res += leftLeavesSum(node.right);
        return res;
    }

    private static boolean isLeaf(Node node) {
        if(node == null) {
            return false;
        }
        return node.left == null && node.right == null;
    }

    /**
     * Sum of left leaves of a binary tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int leftLeavesSumIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int sum = 0;
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            if(isLeaf(current.left)) {
                sum += current.left.data;
            }
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
        return sum;
    }

    /**
     * Sum of right leaves of a binary tree (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int rightLeavesSum(BinaryTree binaryTree) {
        return rightLeavesSum(binaryTree.root);
    }

    private static int rightLeavesSum(Node node) {
        int res = 0;
        if(node == null) {
            return res;
        }
        if(isLeaf(node.right)) {
            res += node.right.data;
        } else {
            res += rightLeavesSum(node.right);
        }
        res += rightLeavesSum(node.left);
        return res;
    }

    /**
     * Sum of right leaves of a binary tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int rightLeavesSumIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int sum = 0;
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            if(isLeaf(current.right)) {
                sum += current.right.data;
            }
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
        return sum;
    }

    /**
     * Sum of all nodes of the given perfect binary tree
     *
     * The leaf nodes in the perfect binary tree are numbered starting from 1 to n, total no of leaves.
     * Parent node is the sum of two child nodes.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int sumPerfectBinaryTree(int level) {
        int leafNodeCount = (int) Math.pow(2,level-1);
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<level;i++) {
            list.add(new ArrayList<>());
        }
        for(int i=1;i<=leafNodeCount; i++) {
            list.get(level-1).add(i);
        }
        for(int i=level-2; i>=0; i--) {
            int k = 0;
            while (k<list.get(i+1).size()) {
                list.get(i).add(list.get(i+1).get(k) +list.get(i+1).get(k+1));
                k += 2;
            }
        }
        int sum = 0;
        for(int i=0;i<level;i++) {
            for(int j=0;j<list.get(i).size();j++) {
                sum+=list.get(i).get(j);
            }
        }
        return sum;
    }

    /**
     * Sum of all nodes of the given perfect binary tree (Efficient)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int sumPerfectBinaryTreeEfficient(int level) {
        int n = (int) Math.pow(2, level-1);
        int sumLevel = n*(n+1)/2;
        return sumLevel*level;
    }

    /**
     * Diagonal sum of a binary tree
     *
     * TC: O(n)
     * SC: O(h+d), h is height and d is no of diagonals
     */
    public static List<Integer> diagonalSum(BinaryTree binaryTree) {
        Map<Integer, Integer> sumMap = new TreeMap<>();
        Map<Node, Integer> verticalDistanceMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        verticalDistanceMap.put(binaryTree.root, 0);
        queue.add(binaryTree.root);
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            int verticalDistance = verticalDistanceMap.get(current);
            while(current != null) {
                int prevSum = sumMap.getOrDefault(verticalDistance, 0);
                sumMap.put(verticalDistance, prevSum + current.data);
                if(current.left != null) {
                    queue.add(current.left);
                    verticalDistanceMap.put(current.left, verticalDistance+1);
                }
                current = current.right;
            }
        }
        return new ArrayList<>(sumMap.values());
    }


    /**
     * Diagonal sum of a binary tree (diagonal relation in matrices)
     *
     * The difference of the respective row and column indices of each element on diagonal is same
     * Similarly, every diagonal has its own unique difference of rows and column,
     * and with the help of this, we can identify each element, that to which diagonal it belongs.
     *
     * TC: O(n)
     * SC: O(h+d), h is height and d is no of diagonals
     */
    public static List<Integer> diagonalSumMatrix(BinaryTree binaryTree) {
        Map<Integer, Integer> grid = new HashMap<>();
        constructGrid(binaryTree.root, 0, 0, grid);
        return new ArrayList<>(grid.values());
    }

    private static void constructGrid(Node node, int level, int index, Map<Integer, Integer> grid) {
        if(node == null) {
            return;
        }
        if(grid.containsKey(level-index)) {
            grid.put(level-index, grid.get(level-index) + node.data);
        } else {
            grid.put(level-index, node.data);
        }
        constructGrid(node.left, level+1, index-1, grid);
        constructGrid(node.right, level+1, index+1, grid);
    }

    /**
     * Children Sum in a Binary Tree
     *
     * Given a binary tree having n nodes. Check whether all of its nodes have a value equal to the sum of their child nodes
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isSumProperty(Node node) {
        if(node == null) {
            return true;
        }
        if(node.left == null && node.right == null) {
            return true;
        }
        int sum = 0;
        if(node.left != null) {
            sum += node.left.data;
            if(!isSumProperty(node.left)) {
                return false;
            }
        }
        if(node.right != null) {
            sum += node.right.data;
            if(!isSumProperty(node.right)){
                return false;
            }
        }
        return node.data == sum;

    }

    /**
     * Two sum in binary search tree (inorder)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean findTarget(Node node, int k) {
        List<Integer> in = new ArrayList<>();
        inorder(node, in);
        int low = 0;
        int high = in.size()-1;
        while(low < high) {
            int sum = in.get(low) + in.get(high);
            if(sum == k) {
                return true;
            } else if(sum > k) {
                high--;
            } else {
                low++;
            }
        }
        return false;
    }

    private static void inorder(Node node, List<Integer> in) {
        if(node == null) {
            return;
        }
        inorder(node.left, in);
        in.add(node.data);
        inorder(node.right, in);
    }

    /**
     * Two sum in binary search tree (Using iterator)
     *
     * TC: O(n)
     * SC: O(h)
     */
    public static boolean findTargetItr(Node node, int k) {
        BSTIterator itr = new BSTIterator(node);
        int next = itr.next();
        int before = itr.before();
        while(next != before) {
            int sum = next + before;
            if(sum == k) {
                return true;
            } else if (sum > k) {
                before = itr.before();
            } else {
                next = itr.next();
            }
        }
        return false;
    }
}
