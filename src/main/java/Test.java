public class Test {

    public synchronized void method1() {
        int i = 0;
    }

    public void method2() {
        synchronized (Test.class) {

        }
    }

    public static synchronized void method3() {
        synchronized (Test.class) {

        }
    }
}
