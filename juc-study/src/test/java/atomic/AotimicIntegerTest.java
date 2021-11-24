package atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AotimicIntegerTest {
    AtomicInteger count = new AtomicInteger();

    /**
     * 多线程下 AtomicInteger 保证线程安全性
     */
    @Test
    public void test1() throws InterruptedException {
        while (true) {
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    count.incrementAndGet();
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    count.incrementAndGet();
                }
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(count);
            if (count.get() != 20000) {
                break;
            } else {
                count.set(0);
            }
        }
    }
}
