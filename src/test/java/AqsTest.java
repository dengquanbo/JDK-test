import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class AqsTest {
    @Test
    public void testCountDownLatch() throws InterruptedException {
        int threadCount = 10;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch workLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startLatch.await();
                        doSomething();
                        workLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "thread-" + i).start();
        }
        Thread.sleep(3000);
        startLatch.countDown();
        workLatch.await();
    }

    private void doSomething() {
        System.out.println("Thread = " + Thread.currentThread().getName() + ",开始执行");

    }

    static int count = 0;
    public static void main(String[] args) throws InterruptedException {

        while (true) {
            count = 0;
            int threadCount = 5;
            CountDownLatch countDownLatch = new CountDownLatch(threadCount);
            ReentrantLock lock = new ReentrantLock();
            for (int i = 0; i < threadCount; i++) {
                new Thread(() -> {
                    int n = 1000;
                    lock.lock();
                    while (n > 0) {
                        count += 1;
                        n--;
                    }
                    lock.unlock();
                    countDownLatch.countDown();
                }).start();
            }
            countDownLatch.await();
            System.out.println(count);
            if (count != 5000) {
                System.out.println("+++++++++++++++++++++++++++++");
                Thread.sleep(3000);
            }
        }
    }
}
