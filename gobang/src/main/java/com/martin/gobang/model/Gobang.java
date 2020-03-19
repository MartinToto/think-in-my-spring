package com.martin.gobang.model;

import java.io.Serializable;
import java.util.Stack;

/**
 * @author Martin
 * @description
 * @date 2020/3/15
 */
public class Gobang implements Serializable {

    //棋盘大小
    private int[][] core;
    private int x;
    private int y;
    //栈
    Stack<Chess> stack;

    public Gobang(int x,int y) {
        stack = new Stack<>();
        this.x=x;
        this.y=y;
    }

    public int[][] getCore() {
        return core;
    }

    public void setCore(int[][] core) {
        this.core = core;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Stack<Chess> getStack() {
        return stack;
    }

    public void setStack(Stack<Chess> stack) {
        this.stack = stack;
    }
}
