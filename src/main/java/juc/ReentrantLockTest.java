package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private final transient ReentrantLock lock = new ReentrantLock();
    private final Condition available = lock.newCondition();


    public void test1() throws InterruptedException {
        lock.lock();
        try {
            available.await();
        } finally {
            lock.unlock();
            System.out.println("xxxxx");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        reentrantLockTest.test1();
    }
}
