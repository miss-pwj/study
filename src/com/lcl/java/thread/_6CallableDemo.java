package com.lcl.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        System.out.println("***********come in Callable");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}

/**
 * @author lcl
 * @date 2021/1/17 10:32
 * @Description Callable 第三种方式获取多线程
 *  get方法一般放在最后一行
 */
public class _6CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
    FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();//只调用一次，有缓存



        //new Thread(Runnable,name)

        System.out.println(Thread.currentThread().getName()+"计算完成");
        System.out.println(futureTask.get());
    }
}
