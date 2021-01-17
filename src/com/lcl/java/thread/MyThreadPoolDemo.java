package com.lcl.java.thread;

import java.util.concurrent.*;

/**
 * @author lcl
 * @date 2021/1/17 18:19
 * @Description
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        //jdk自带获取线程池的三种方式
        //固定线程数量
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一个线程池一个线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        //可缓存线程池,如果线程池长度超过处理需要,可灵活回收空闲线程,若无可回收,则创建新线程.
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        //最重要的自定义线程池
        ExecutorService threadPool4 = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                //默认抛出异常
                new ThreadPoolExecutor.AbortPolicy()
                //回退调用者
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //处理不来的不处理，丢弃最早的
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                //直接丢弃处理不了的。
               // new ThreadPoolExecutor.DiscardPolicy()
        );

        try {
            for (int i = 1; i <=10 ; i++) {
                threadPool4.execute(()->{ System.out.println(Thread.currentThread().getName() + "\t办理业务");});
            };
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        };
    }
}
