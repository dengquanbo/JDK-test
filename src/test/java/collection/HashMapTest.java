package collection;

import org.junit.Test;

import java.util.HashMap;

public class HashMapTest {

    HashMap<String, String> data = new HashMap<>();

    @Test
    public void test1() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (data.get("test") != null) {
                        break;
                    }
                    System.out.println("1");
                }
            }
        });

        t1.start();

        Thread.sleep(2000);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                data.put("test", "111");
                System.out.println("xxxx");
            }
        });

        t2.start();


        t1.join();
        t2.join();

    }

    @Test
    public void test2(){
        HashMap<String, String> data = new HashMap<>(1333);
        data.put("test", "111");
    }
}
