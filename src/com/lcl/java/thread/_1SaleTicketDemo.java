package com.lcl.java.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int number = 30;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+number--+"张"+"\t 还剩下："+number);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * @author lcl
 * @date 2021/1/16 19:48
 * @Description
 */
public class _1SaleTicketDemo {
    public static void main(String[] args){
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
                
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();

            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();

            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();

            }
        }, "D").start();
    }

}
