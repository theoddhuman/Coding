package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Serialization and deserialization of a tree
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root== null) {
            return "";
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        String s = "";
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            if(current == null) {
                s += "#,";
            } else {
                s = s+current.data+",";
                queue.add(current.left);
                queue.add(current.right);
            }
        }
        return s;
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.length() == 0) {
            return null;
        }
        String[] nodes = data.split(",");
        Node root = new Node(Integer.parseInt(nodes[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 0;
        while(i < nodes.length && !queue.isEmpty()) {
            Node current = queue.remove();
            if(!"#".equals(nodes[i+1])) {
                current.left = new Node(Integer.parseInt(nodes[i+1]));
                queue.add(current.left);
            }
            if(!"#".equals(nodes[i+2])) {
                current.right = new Node(Integer.parseInt(nodes[i+2]));
                queue.add(current.right);
            }
            i += 2;
        }
        return root;
    }
}
