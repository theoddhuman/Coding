package com.paul.subham.tree.implementation.karray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author subham.paul
 */
public class KNode {
    public int data;
    public List<KNode> children;
    public KNode(int data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}
