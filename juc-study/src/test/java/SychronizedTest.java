import org.junit.Test;

public class SychronizedTest {

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {

            Object object = new Object();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (object) {
                        try {
                            object.wait();
                            System.out.println("t1 execute");
                        } catch (InterruptedException e) {
                            System.out.println("t1中断 " + Thread.currentThread().isInterrupted());
                            e.printStackTrace();
                        }
                    }

                }
            });


            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (object) {
                        try {
                            object.wait();
                            System.out.println("t2 execute");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });

            t1.start();
            t2.start();

            Thread.sleep(2000);

            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (object) {
                        t1.interrupt();
                        object.notify();
                    }

                }
            });

            t3.start();


//        t1.join();
//        t2.join();
            t3.join();

            System.out.println("xxxx " + t1.isInterrupted());
            Thread.sleep(2000);
            System.out.println("-------------------------------------\n\n\n\n");
        }
    }
}
