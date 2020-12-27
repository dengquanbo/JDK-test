package juc;

public class TestSyncMemorySee {
    static boolean r = false;
    static Object o = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                while (!r) {
                }
            }
            System.out.println(Thread.currentThread().getName() + "end");
        }, "t1");

        Thread t2 = new Thread(() -> {
            r = true;
            System.out.println(Thread.currentThread().getName() + "end");
        }, "t2");

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

    }
}