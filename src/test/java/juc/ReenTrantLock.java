package juc;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLock {

    @Test
    public void test1() {
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
    }


    @Test
    public void test2() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 中断");
                LockSupport.park(this);
                System.out.println("t1 唤醒");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t1.interrupt();
            }
        });

        t1.start();
        t2.start();


        t1.join();
        t2.join();


    }

    @Test
    public void test3() {
        Thread t1 = null;
        try {
            t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    //while (!Thread.interrupted()) {
                    //    //doWork();
                    //    System.out.println(Thread.currentThread().getName() + "我做完一件事了，准备做下一件，如果没有其他线程中断我的话");
                    //}

                    try {
                        Thread.sleep(70000);
                        System.out.println("xxxxxx");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }, "hh");


            t1.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Thread finalT = t1;
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finalT.interrupt();
            }
        });

        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        //Thread t1 = new Thread(() -> {
        //    try {
        //        Thread.sleep(120000);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //},"dqb-thread1");
        //
        //t1.start();

        //Thread t2 = new Thread(() -> LockSupport.park(new Object()), "dqb-thread2");
        //
        //t2.start();

        Object object = new Object();
        Thread t3 = new Thread(() -> {
            synchronized (object) {
                try {
                    System.out.println("sleep");
                    object.wait(5000);
                    System.out.println("alive");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"dqb-thread3");

        t3.start();

        Thread.sleep(2000);
        synchronized (object){
            int i = 0;
            while (true){
                i++;
            }
        }

    }

}
