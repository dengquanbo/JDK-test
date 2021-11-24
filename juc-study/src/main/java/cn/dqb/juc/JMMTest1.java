package cn.dqb.juc;

public class JMMTest1 {
    static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!flag) {
            }
            System.out.println("开始处理数据");
        }).start();
        Thread.sleep(2000);
        new Thread(() -> {
            prepareDate();
            System.out.println("准备数据完毕");
        }).start();
    }

    public static void prepareDate() {
        flag = true;
    }
}
