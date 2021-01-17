---
typora-root-url: D:\java\lclworks\study\resources
---

# JUC高并发（JUC）之BlockingQueue 阻塞队列

这次讲解的是**BlockingQueue**：
**BlockingQueue（阻塞队列）是什么？**
当队列为空时，从队列中获取元素将**阻塞**。
当队列为满时，从队列中添加元素将**阻塞**。
因为是队列，所以我们理应想到**先进先出**。
接下来我们来演示简单的使用一下：

## add（抛出异常）：

```bash
/**
 * @author Cocowwy
 * @create 2020-05-05-14:53
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
    BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.add("x"));
    }
}

1234567891011121314
```

**结果如下：**

```bash
true
true
true
Exception in thread "main" java.lang.IllegalStateException: Queue full
	at java.util.AbstractQueue.add(AbstractQueue.java:98)
	at java.util.concurrent.ArrayBlockingQueue.add(ArrayBlockingQueue.java:312)
	at juc.BlockingQueueDemo.main(BlockingQueueDemo.java:16)

12345678
```

我们可以看到如果添加的元素大于3之后，即当队列满了之后会抛异常：`java.lang.IllegalStateException: Queue full`

## remove（抛出异常）：

```bash
public class BlockingQueueDemo {
    public static void main(String[] args) {
    BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("x"));
        System.out.println(blockingQueue.remove()); //返回值是E（即对象） 因为是队列 所以是a
        System.out.println(blockingQueue.remove()); 
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }
}
12345678910111213
```

这里需要注意的是remove的返回类型是E（对象）
**结果如下：**

```bash
true
true
true
a
b
c
Exception in thread "main" java.util.NoSuchElementException
	at java.util.AbstractQueue.remove(AbstractQueue.java:117)
	at juc.BlockingQueueDemo.main(BlockingQueueDemo.java:20)

Process finished with exit code 1

123456789101112
```

我们可以看到如果添加的元素大于3之后，即当队列空了之后再删会抛异常：`java.util.NoSuchElementException`

## element（抛出异常）：

```bash
public class BlockingQueueDemo {
    public static void main(String[] args) {
    BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.element());
    }
}
123456789
```

**结果如下：**

```bash
true
true
true
a
1234
```

检查队首元素是什么

## offer（特殊值）：

```bash
public class BlockingQueueDemo {
    public static void main(String[] args) {
    BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));
    }
}
123456789
```

结果如下：

```bash
true
true
true
false
1234
```

插入，返回成功

## pull（特殊值）：

```bash
public class BlockingQueueDemo {
    public static void main(String[] args) {
    BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }
}
12345678910111213
```

**结果如下：**

```bash
true
true
true
a
b
c
null
1234567
```

## peek（特殊值）：

```bash
public class BlockingQueueDemo {
    public static void main(String[] args) {
    BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println(blockingQueue.peek());
    }
}
12345678910
```

**结果如下：**

```
true
true
true
a

12345
```

## put（阻塞）：

```bash
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
    BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
    blockingQueue.put("a");
    blockingQueue.put("b");
    blockingQueue.put("c");
    blockingQueue.put("d");
    }
}

12345678910
```

**结果如下：**

```bash
1
```

就是啥都没有一片空白，因为此时一直阻塞，等待，与接下来的**take**进行对比

## take（阻塞）：

```bash
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println(blockingQueue.take());

        blockingQueue.put("e");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }
}

123456789101112131415
```

**结果如下：**

```bash
a
b
c
e

12345
```

与上面的put对比一下，这里abc添加进去后如果想加e是加不进去的，会阻塞，而take进行取出后就能加入e了

## offer（超时）：

```bash
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d", 3L, TimeUnit.SECONDS));
    }
}
123456789
```

结果如下：

```bash
true
true
true
false  //等待了3s
1234
```

让其等待3s，返回值为boolean

## poll（超时）：

```bash
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
    }
}
123456789101112
```

**结果如下：**

```bash
true
true
true
a
b
c
null //等待了3s
1234567
```

综合上述情况我们可以得到此图：
![在这里插入图片描述](/image/阻塞队列.png)



|          |                                                              |
| :------- | :----------------------------------------------------------- |
| 抛出异常 | 当阻塞队列满时,再往队列里面add插入元素会抛IllegalStateException: Queue full当阻塞队列空时,再往队列Remove元素时候回抛出NoSuchElementException |
| 特殊值   | 插入方法,成功返回true 失败返回false<br/>移除方法,成功返回元素,队列里面没有就返回null |
| 一直阻塞 | 当阻塞队列满时,生产者继续往队列里面put元素,队列会一直阻塞直到put数据or响应中断退出<br/>当阻塞队列空时,消费者试图从队列take元素,队列会一直阻塞消费者线程直到队列可用. |
| 超时退出 | 当阻塞队列满时,队列会阻塞生产者线程一定时间,超过后限时后生产者线程就会退出 |

