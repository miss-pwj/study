package com.lcl.java.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    private int number = 1;  //A:1 B:2 C:3

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5() throws InterruptedException {

        lock.lock();
        try {

            //判断
            while (number != 1) {
                c1.await();
            }
            //操作
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+(i+1));
            }

            //通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
    public void print10() throws InterruptedException {


        lock.lock();
        try {
        while (number != 2) {
            c2.await();
        }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+(i+1));
            }

            number=3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
    public void print15() throws InterruptedException {


        lock.lock();
        try {
            while (number != 3) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+(i+1));
            }


            number=1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}

/**
 * @author lcl
 * @date 2021/1/17 9:28
 * @Description 精确通知顺序访问
 * 备注：多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求：
 * A打印5次 B打印10次 C打印15次，进行10轮
 */
public class _5ConditionDemo {

    public static void main(String[] args) throws Exception{
        ShareData shareData = new ShareData();
        new Thread(() -> {
            try {
                shareData.print5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                shareData.print10();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                shareData.print15();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}
