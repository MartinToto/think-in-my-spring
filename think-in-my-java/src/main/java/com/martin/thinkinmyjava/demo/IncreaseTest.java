package com.martin.thinkinmyjava.demo;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Martin
 * @description
 * @date 2020/4/16
 */
public class IncreaseTest {
    private static final AtomicLong counter = new AtomicLong();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            increase();
        }

    }
    public static void increase() {
        counter.incrementAndGet();
    }
}
