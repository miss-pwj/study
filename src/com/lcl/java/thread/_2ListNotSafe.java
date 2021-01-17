package com.lcl.java.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;



/**
 * @author lcl
 * @date 2021/1/15 18:29
 * @Description 1 故障原因
 * java.util.ConcurrentModificationException
 * <p>
 * 2 导致原因
 * 多线程争抢同一个资源类，且没有加锁
 * 3 解决方法
 * 3.1  new Vector<>()
 * 3.2  Collections.synchronizedList(new ArrayList<>())
 * 3.3  new CopyOnWriteArrayList<>()   //写时复制
 * 4 优化建议
 */
public class _2ListNotSafe {

    public static void main(String[] args) {

        mapNotSafe();
    }

    public static void mapNotSafe() {
        Map<String, String> map = new ConcurrentHashMap<>();//new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();//new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();//new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
