package com.paul.subham.stack.operation;

import java.util.Stack;

/**
 * 1.Checking balancing of brackets
 * 2.Postfix evaluation
 * 3.Infix evaluation
 */
public class Expression {
    public static void main(String[] args) {
        System.out.println(infixEvaluation("((2+8)/5)+4"));
    }


    /**
     * Checking balancing of brackets
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isValidSymbolPattern(String s) {
        Stack<Character> stack = new Stack<>();
        if(s==null || s.length()==0) return true;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == ')') {
                if(!stack.empty() && stack.peek()=='(') {
                    stack.pop();
                } else {
                    return false;
                }
            } else if(s.charAt(i) == ']') {
                if(!stack.empty() && stack.peek()=='[') {
                    stack.pop();
                } else {
                    return false;
                }
            } else if(s.charAt(i) == '}') {
                if(!stack.empty() && stack.peek()=='{') {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        if(stack.empty()) return true;
        return false;
    }

    /**
     * Postfix evaluation
     * TC: O(n)
     * SC: O(N)
     */
    public static int postFixEvaluation(String exp) {
        Stack<Integer> stack = new Stack<>();
        String str[] = exp.split(" ");
        for(int i = 0; i <str.length; i++) {
            String s = str[i];
            if(s.equals("+")) {
                int op2 = stack.pop();
                int op1 = stack.pop();
                stack.push(op1 + op2);
            } else if (s.equals("-")) {
                int op2 = stack.pop();
                int op1 = stack.pop();
                stack.push(op1 - op2);
            } else if (s.equals("*")) {
                int op2 = stack.pop();
                int op1 = stack.pop();
                stack.push(op1 * op2);
            } else if (s.equals("/")) {
                int op2 = stack.pop();
                int op1 = stack.pop();
                stack.push(op1 / op2);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    /**
     * Infix evaluation
     * TC: O(n)
     * SC: O(n)
     */
    public static int infixEvaluation(String s) {
        Stack<Integer> valueStack = new Stack<>();
        Stack<Character> opsStack = new Stack<>();
        for(int i=0; i<s.length();) {
            char c = s.charAt(i);
            if(c == ' ') {
                i++;
                continue;
            }
            if(c >= '0' && c <= '9') {
                StringBuffer stringBuffer = new StringBuffer();
                while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    stringBuffer.append(s.charAt(i));
                    i++;
                }
                valueStack.push(Integer.parseInt(stringBuffer.toString()));
                continue;
            } else if (c == '(') {
                opsStack.push(c);
            } else if (c == ')') {
                while(opsStack.peek() != '(') {
                    valueStack.push(applyOperator(opsStack.pop(), valueStack.pop(), valueStack.pop()));
                }
                opsStack.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while(!opsStack.empty() && hasPrecedence(opsStack.peek(), c)) {
                    valueStack.push(applyOperator(opsStack.pop(), valueStack.pop(), valueStack.pop()));
                }
                opsStack.push(c);
            }
            i++;
        }
        while(!opsStack.empty()) {
            valueStack.push(applyOperator(opsStack.pop(), valueStack.pop(), valueStack.pop()));
        }
        return valueStack.pop();
    }

    static int applyOperator(char operator, int value2, int value1) {
        switch(operator) {
            case '+':
                return value1 + value2;
            case '-':
                return value1 - value2;
            case '*':
                return value1 * value2;
            case '/':
                return value1 / value2;
        }
        return 0;
    }

    static boolean hasPrecedence(char op1, char op2) {
        if(op1 == '(' || op1 == ')') {
            return false;
        }
        if((op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-')) {
            return false;
        }
        return true;
    }
}
