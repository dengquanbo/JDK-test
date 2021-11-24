package cn.dqb.juc;

public class TestSyncMemorySee1 {
    static int a = 0;
    static volatile boolean b = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            b = true;
            a = 1;
        }, "t1");

        Thread t2 = new Thread(() -> {
            if (a == 1) {
                System.out.println("xxxxx");
            }
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