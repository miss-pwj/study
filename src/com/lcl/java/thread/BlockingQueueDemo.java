package com.lcl.java.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author lcl
 * @date 2021/1/17 16:27
 * @Description
 * 阻塞队列 必要时阻塞/不得不阻塞
 *
 *
    抛出异常(add/remove/element) :
        当阻塞队列满时,再往队列里面add插入元素会抛IllegalStateException: Queue full
        当阻塞队列空时,再往队列Remove元素时候回抛出NoSuchElementException
    特殊值(offer/poll/peek):
        插入方法,成功返回true 失败返回false
        移除方法,成功返回元素,队列里面没有就返回null
    一直阻塞	(put/take/不可用) :
        当阻塞队列满时,生产者继续往队列里面put元素,队列会一直阻塞直到put数据or响应中断退出
        当阻塞队列空时,消费者试图从队列take元素,队列会一直阻塞消费者线程直到队列可用.
    超时退出(offer/poll/不可用):
        当阻塞队列满时,队列会阻塞生产者线程一定时间,超过后限时后生产者线程就会退出

 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

       /* System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        */

       /* System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

        /*blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();*/

        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));

        blockingQueue.poll(2, TimeUnit.SECONDS);
        blockingQueue.poll(2, TimeUnit.SECONDS);
        blockingQueue.poll(2, TimeUnit.SECONDS);
*/

    }
}
