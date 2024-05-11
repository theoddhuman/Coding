package com.paul.subham.tree.implementation.generic;

/**
 * @author subham.paul
 */
public class Test {
    public static void main(String[] args) {
        GenericTree genericTree = new GenericTree();
        genericTree.root = new GNode(1);
        genericTree.addSibling(genericTree.root, 2);
        genericTree.addChild(genericTree.root, 4);
        genericTree.addChild(genericTree.root, 5);
        genericTree.levelOrderTraversal();
    }
}
