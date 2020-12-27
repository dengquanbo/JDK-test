package juc;

public class VolatileDetail {

    volatile static int a = 1;

    static int b = 0;

    static int c = 0;

    static final Object object = new Object();

    public static void main(String[] args) {
        test();
        test1();
        test2();
    }

    public static void test() {
        a++;
    }

    public static synchronized void test1() {
        b++;
    }

    public static void test2() {
        synchronized (object) {
            c++;
        }
    }
}