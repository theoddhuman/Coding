package com.paul.subham.stack.operation;

import java.util.Stack;

/**
 * 1. Checking balancing of brackets
 * 2. Postfix evaluation
 * 3. Infix evaluation
 * 4. Infix to Postfix conversion
 * 5. Postfix to Infix conversion
 * 6. Infix to Prefix conversion
 * 7. Prefix to Infix conversion
 * 8. Prefix to Postfix conversion
 * 9. Postfix to Prefix conversion
 * 10. Maximum depth of nested parenthesis in a string (using stack)
 * 11. Maximum depth of nested parenthesis in a string (space optimized)
 */
public class Expression {
    public static void main(String[] args) {
        System.out.println(maxDepthEfficient("( a(b) (c) (d(e(f)g)h) I (j(k)l)m)"));
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

    /**
     * Infix to Postfix conversion
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String inFixToPostFix(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (isOperator(c)){
                while(!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while(!stack.isEmpty()) {
            if(stack.peek() == '(') {
                return "Invalid expression";
            } else {
                result.append(stack.pop());
            }
        }
        return String.valueOf(result);
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '/'|| c == '*';
    }

    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    /**
     * Postfix to Infix conversion
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String postfixToInfix(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isOperator(ch)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push("(" + op2 + ch + op1 + ")");
            } else {
                stack.push(String.valueOf(ch));
            }
        }
        return stack.pop();
    }

    /**
     * Infix to Prefix conversion
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String inFixToPreFix(String s) {
        String result = "";
        Stack<Character> stack = new Stack<>();
        for(int i=s.length()-1; i>=0; i--) {
            char c = s.charAt(i);
            if(c == ')') {
                stack.push(c);
            } else if (c == '(') {
                while(!stack.isEmpty() && stack.peek() != ')') {
                    result = stack.pop() + result;
                }
                stack.pop();
            } else if (Character.isLetterOrDigit(c)) {
                result = c + result;
            } else if (isOperator(c)){
                while(!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result = stack.pop() + result;
                }
                stack.push(c);
            }
        }
        while(!stack.isEmpty()) {
            if(stack.peek() == ')') {
                return "Invalid expression";
            } else {
                result = stack.pop() + result;
            }
        }
        return result;
    }

    /**
     * Prefix to Infix conversion
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String prefixToInfix(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = s.length()-1; i>=0; i--) {
            char ch = s.charAt(i);
            if (isOperator(ch)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push("(" + op1 + ch + op2 + ")");
            } else {
                stack.push(String.valueOf(ch));
            }
        }
        return stack.pop();
    }

    /**
     * Prefix to Postfix conversion
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String prefixToPostfix(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = s.length()-1; i>=0; i--) {
            char ch = s.charAt(i);
            if (isOperator(ch)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push( op1 + op2 + ch);
            } else {
                stack.push(String.valueOf(ch));
            }
        }
        return stack.pop();
    }

    /**
     * Postfix to Prefix conversion
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String postfixToPrefix(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (isOperator(ch)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push( ch + op2 + op1);
            } else {
                stack.push(String.valueOf(ch));
            }
        }
        return stack.pop();
    }

    /**
     * Maximum depth of nested parenthesis in a string (using stack)
     * ((x(y)))
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxDepth(String s) {
        Stack<Character> stack = new Stack<>();
        int maxDepth = -1;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                if(stack.isEmpty()) {
                    return -1;
                }
                maxDepth = Math.max(maxDepth, stack.size());
                stack.pop();
            }
        }
        if(!stack.isEmpty()) {
            return -1;
        }
        return maxDepth;
    }

    /**
     * Maximum depth of nested parenthesis in a string (space optimized)
     * ((x(y)))
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxDepthEfficient(String s) {
        int count = 0;
        int max = -1;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                count++;
                max = Math.max(max, count);
            } else if (s.charAt(i) == ')') {
                if(count > 0) {
                    count--;
                } else {
                    return -1;
                }
            }
        }
        if(count > 0) {
            return -1;
        }
        return max;
    }
}
