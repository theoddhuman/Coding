package com.paul.subham.tree.implementation.karray;

/**
 * @author subham.paul
 */
public class Test {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8,9};
        KArrayTree kArrayTree = new KArrayTree(3);
        kArrayTree.buildKArrayTree(a, a.length);
        kArrayTree.preOrder();
        kArrayTree.postOrder();
    }
}
