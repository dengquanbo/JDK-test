package juc;

import org.junit.Test;

public class ThreadTest {
    Object object = new Object();

    @Test
    public void test1() {
        Runnable target;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (t1) {

            try {
                t1.wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
