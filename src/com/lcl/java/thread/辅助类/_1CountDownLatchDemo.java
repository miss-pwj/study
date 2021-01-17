package com.lcl.java.thread.辅助类;

import java.util.concurrent.CountDownLatch;

/**
 * @author lcl
 * @date 2021/1/17 11:37
 * @Description
 */
public class _1CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开了教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t班长关门了");
    }
}
