package cn.dqb.juc;


public class Service {
    private boolean isContinueRun = true;

    Object object = new Object();
    public void runMethod() {
        while (isContinueRun) {
            synchronized (object) {
            }
            //new String();
            //Object object = new Object();
        }
        System.out.println("stop");
    }

    public void stopMethod() {
        isContinueRun = false;
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.start();

        Thread.sleep(1000);
        ThreadB b = new ThreadB(service);
        b.start();
    }
}

class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.runMethod();
    }
}

class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.stopMethod();
    }
}