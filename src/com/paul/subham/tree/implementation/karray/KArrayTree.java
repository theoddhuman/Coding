package com.paul.subham.tree.implementation.karray;

/**
 * @author subham.paul
 *
 *
 * 1. Build k-array tree from preorder
 * 2. Preorder traversal
 * 3. Postorder traversal
 */
public class KArrayTree {
    public KNode root;
    private static int index;
    private int k;

    public KArrayTree(int k) {
        this.k = k;
    }

    /**
     * Build k-array tree from preorder
     *
     * TC: O(n)
     * SC: O(n)
     *
     * No of nodes, n = k^0 + k^1 + k^2 + ...... + k^h = (k^(h+1) - 1) / (k - 1)
     * If we consider height of root level as 1,
     * n = k^0 + k^1 + k^2 + ...... + k^(h-1) = (k^h - 1) / (k - 1)
     * => n * (k-1) = k^h - 1
     * => n * (k-1) + 1 = k^h
     * => h = log(n * (k-1) + 1) / log k
     */
    public void buildKArrayTree(int[] pre, int n) {
        int height = (int) Math.ceil(Math.log(n*(k-1)+1)/Math.log(k));
        root = buildKArrayTree(pre, n, height);
    }

    private KNode buildKArrayTree(int[] pre, int n, int height) {
        if(n <= 0) {
            return null;
        }
        KNode node = new KNode(pre[index++]);
        for(int i=0; i<k; i++) {
            if(index < n && height > 1) {
                node.children.add(buildKArrayTree(pre, n, height-1));
            }
        }
        return node;
    }

    /**
     * Preorder traversal
     *
     * TC: O(n)
     * SC: O(n)
     */
    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(KNode node) {
        if(node == null) {
            return;
        }
        System.out.print(node.data + " ");
        for(KNode kNode : node.children) {
            preOrder(kNode);
        }
    }

    /**
     * Postorder traversal
     *
     * TC: O(n)
     * SC: O(n)
     */
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(KNode node) {
        if(node == null) {
            return;
        }
        for(KNode kNode : node.children) {
            postOrder(kNode);
        }
        System.out.print(node.data + " ");
    }
}
