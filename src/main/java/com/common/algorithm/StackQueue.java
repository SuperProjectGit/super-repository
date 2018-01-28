package com.common.algorithm;

import java.util.Stack;

/**
 * 用两个栈实现一个队列
 *
 * @author subo
 * @create 2018-01-28 16:38
 **/
public class StackQueue {

    private Stack<Integer> stack1 = new Stack<>();

    private Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        StackQueue stackQueue = new StackQueue();
        stackQueue.push(1);
        stackQueue.push(2);
        stackQueue.push(3);
        System.out.println(stackQueue.pop());
    }

}
