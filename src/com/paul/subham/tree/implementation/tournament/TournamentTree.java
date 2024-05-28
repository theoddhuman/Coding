package com.paul.subham.tree.implementation.tournament;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author subham.paul
 */
public class TournamentTree {
    public TNode root;

    /**
     * Construct tournament tree from an array
     *
     * TC: O(n)
     */
    public void insertArray(int[] a) {
        List<TNode> list = new LinkedList<>();
        TNode current = null;
        for(int i=0; i<a.length; i+=2) {
            TNode tNode1 = new TNode(i);
            if(i+1 < a.length) {
                TNode tNode2 = new TNode(i+1);
                current = a[i] > a[i+1] ? new TNode(i) : new TNode(i+1);
                current.left = tNode1;
                current.right = tNode2;
                list.add(current);
            } else {
                list.add(tNode1);
            }
        }
        int lSize = list.size();
        while(list.size() > 1) {
            for(int i=0; i<lSize; i+=2) {
                TNode tNode1 = list.remove(0);
                if(i+1 < lSize) {
                    TNode tNode2 = list.remove(0);
                    current = a[tNode1.index] > a[tNode2.index] ? new TNode(tNode1.index) : new TNode(tNode2.index);
                    current.left = tNode1;
                    current.right = tNode2;
                    list.add(current);
                } else {
                    list.add(tNode1);
                }
            }
            lSize = list.size();
        }
        root = current;
    }

    public void levelOrder(int[] a) {
        Queue<TNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TNode current = queue.remove();
            System.out.print(a[current.index] + " ");
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
    }
}
