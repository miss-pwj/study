package com.lcl.java.lambda;


import java.util.concurrent.*;

class MyTask extends RecursiveTask<Integer> {

    public static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if ((end - begin) <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result +i;
            }
        }else{
            int middle = (end + begin) / 2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle+1, end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }
        return result;
    }
}
/**
 * @author lcl
 * @date 2021/1/18 11:27
 *
 * 分支合并框架
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);

        ForkJoinPool threadPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);

        System.out.println(forkJoinTask.get());
        threadPool.shutdown();
    }
    
}
