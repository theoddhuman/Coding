package com.paul.subham.linkedlist.operation;

import java.util.Stack;

/**
 * Stock span problem
 */
public class StockSpanner {
    Stack<PricePair> stack;
    private int index;

    public StockSpanner() {
        stack = new Stack<>();
        index = -1;
    }

    public int next(int price) {
        while(!stack.isEmpty() && price >= stack.peek().price) {
            stack.pop();
        }
        int pge;
        if(stack.isEmpty()) {
            pge = -1;
        } else {
            pge = stack.peek().index;
        }
        stack.push(new PricePair(price, ++index));
        //System.out.println(stack);
        return index - pge;
    }
}

class PricePair {
    int price;
    int index;
    PricePair(int price, int index) {
        this.price = price;
        this.index = index;
    }
    @Override
    public String toString() {
        return price + " " + index;
    }
}
