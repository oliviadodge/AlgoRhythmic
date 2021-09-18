package com.olivia.sep2021;

import java.util.Stack;

class MinStack {

    Stack<Node> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty() || val < stack.peek().minUpTilNow) {
            stack.push(new Node(val, val));
        } else {
            stack.push(new Node(stack.peek().minUpTilNow, val));
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().minUpTilNow;
    }

    static class Node {
        int minUpTilNow;
        int val;

        Node(int minUpTilNow, int val) {
            this.minUpTilNow = minUpTilNow;
            this.val = val;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
