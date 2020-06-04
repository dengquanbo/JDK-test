package juc;

public class JMMTest2 {
    volatile int  count = 0;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            JMMTest2 jmmTest2 = new JMMTest2();
            Thread t1 = new Thread(() -> {
                run(jmmTest2);
            });
            Thread t2 = new Thread(() -> {
                run(jmmTest2);
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(jmmTest2.count);
            if (jmmTest2.count==1){
                System.out.println("xxxx");
            }

        }
    }

    public static void run(JMMTest2 jmmTest2) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jmmTest2.count++;
    }

}
