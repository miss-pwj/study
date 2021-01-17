package com.lcl.java.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition{
    private int number = 0;

    //老版加锁 synchronized
   /* public synchronized void increment() throws Exception{
        while (number!=0){
            this.wait();
        }
        number++;
        System.out.println(number);

        this.notifyAll();
    }


    public synchronized void decrement() throws Exception{
        while (number == 0) {
            this.wait();
        }

        number--;
        System.out.println(number);

        this.notifyAll();
    }*/

    //JUC Lock方式加锁
    Lock lock = new ReentrantLock();//可重入非公平递归锁
    Condition condition = lock.newCondition();
    public  void increment() throws Exception{
        lock.lock();
        try {
            while (number!=0){
                condition.await();
            }
            number++;
            System.out.println(number);

            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public  void decrement() throws Exception{
        lock.lock();
        try {
            while (number==0){
                condition.await();
            }
            number--;
            System.out.println(number);

            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }



}


/**
 * @author lcl
 * @date 2021/1/16 19:05
 * @Description
 * 题目：现在两个线程，可以操作初始值为零的一个变量，实现一个线程对该变量加1，一个线程对该线程减1，
 * 实现交替，进行10轮，变量初始化为0.
 *
 * 1    高内聚低耦合前提下，线程操作资源类
 * 2    判断/干活/通知
 * 3    防止虚假唤醒
 *
 * 知识总结 = 多线程编程套路+while判断+新版写法
 */
public class _4ProdCustomer {

    public static void main(String[] args) throws Exception{
        Aircondition aircondition = new Aircondition();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "B").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "D").start();
    }
}
