import java.util.concurrent.CountDownLatch;

public class ReentrantLockForIncrease {
    static int cnt = 0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int n = 10000;
                while (n > 0) {
                    cnt++;
                    n--;
                }
                countDownLatch.countDown();
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        Thread t4 = new Thread(r);
        Thread t5 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        countDownLatch.await();
        System.out.println(cnt);
    }

}