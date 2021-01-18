package com.lcl.java;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author lcl
 * @date 2021/1/18 11:42
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{System.out.println(
                Thread.currentThread().getName()+"\t 没有返回，更新数据成功");});
        completableFuture.get();

        //异步回调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t 没有返回，completableFuture2");
            return 1024;});

        System.out.println(completableFuture2.whenComplete((t, u) -> {
            System.out.println("****t:" + t);
//            int age = 10 / 0;
            System.out.println("****u:" + u);
        }).exceptionally((f) -> {
            System.out.println("******exception:" + f.getMessage());
            return 4444;
        }).get());

    }
}
