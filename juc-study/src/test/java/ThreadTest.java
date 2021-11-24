import org.junit.Test;

import java.lang.management.ManagementFactory;

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

    public static void main(String[] args) {
        System.out.println(pid());
        runnable();     // 1
        //     blocked();      // 2
        //     waiting();      // 3
        //     timedWaiting(); // 4
    }

    public static String pid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        return name.split("@")[0];
    }

    public static void runnable() {
        long i = 0;
        while (true) {
            i++;
        }
    }

}
