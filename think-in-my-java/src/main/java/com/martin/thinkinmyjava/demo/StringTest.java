package com.martin.thinkinmyjava.demo;

/**
 * @author Martin
 * @description
 * @date 2020/4/17
 */
public class StringTest {
    public static void main(String[] args) {
        StringBuilder abcde = new StringBuilder("abcde");
        StringBuilder edcba = abcde.reverse();
        System.out.println(edcba);
    }
}
