package com.lcl.java.thread;

class Phone{
    public static synchronized void sendEmail() throws Exception{

//        TimeUnit.SECONDS.sleep(4);
        System.out.println("*******sendEmail");
    }

    public synchronized void sendSms() throws Exception{
        System.out.println("********sendSms");
    }
    public  void sayHello() {
        System.out.println("********hello");
    }

}

/**
 * @author lcl
 * @date 2021/1/16 15:43
 * @Description
*      一个对象里面如果有多个synchronized方法，某一个时刻内，只要有一个线程去调用其中的一个synchronize方法了，其他线程都只能等待，换句话说，
*      某一个时刻内，只能有一个线程去访问这些synchronized方法。锁的是当前对象this，被锁定之后，其他的线程都不能进入到当前独享的
 *     其他的synchronized方法
 *
 *     加个普通方法，发现和同步锁无关
 *
 *     换成两个对象以后，发现不是同一把锁了，情况立刻变化。
 *
 *     synchronized实现同步的基础：Java的每一个对象都可以作为锁，具体表现为一下三种形式：
 *      对于普通同步方法，锁是当前实力对象，锁的是当前对象this，
 *      对于同步方法块，锁是synchronized括号里配置的对象
 *      对于静态同步方法啊，锁的是当前类的Class对象。
 *
 *     当一个线程试图访问同步代码块时，他首先必须得到锁，退出或抛出异常必须释放锁。
 *     也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放后才能获取锁，
 *     可是别的实例对象的非静态同步方法因为跟改实例对象的非静态同步方法用的是不同的锁，所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们之间的锁
 *
 *      所有的静态同步方法用的也是同一把锁——类对象本身，这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有意态条件的。
 *      但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，而不管是同一个实例对象的静态同步方法之间，还是不同的实阅对象的静态同步方法之间，只要它们同一个类的实例对象！
 *
 * 1 标准访问，请问先发邮件还是先发短信              邮件
 * 2 暂停4秒在邮件方法，请问先发邮件还是先发短信      邮件
 * 3 新增普通sayHello方法，请问先邮件还是先hello       hello
 * 4 两部手机，请问先发邮件还是先发短信              短信
 * 5 两个静态同步方法，同一部手机，请问先打印邮件还是短信     邮件
 * 6 两个静态同步方法，两部手机，请问先打印邮件还是短信     邮件
 * 7 一个静态同步方法，一个普通同步方法，同一部手机，请问先打印邮件还是短信     短信
 * 8 一个静态同步方法，一个普通同步方法，两部手机，请问先打印邮件还是短信     短信
 */
public class _3Lock_8 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);
        new Thread(() -> {
            try {
                phone2.sendSms();
//                phone.sayHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
