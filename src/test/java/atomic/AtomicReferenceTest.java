package atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

//    public static AtomicReference<User> atomicUserRef = new AtomicReference<User>();

    public StackNoCas stackNoCas = new StackNoCas();
    public StackUseCas stackUseCas = new StackUseCas();

    /**
     * 单线程下线，是线程安全的
     */
    @Test
    public void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                stackNoCas.push(String.valueOf(i));
            }
        });


        t1.start();

        t1.join();

        System.out.println(stackNoCas.size);

    }

    /**
     * 多线程下线，是线程不安全的，数据会丢失
     */
    @Test
    public void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                stackNoCas.push(String.valueOf(i));
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 100; i < 200; i++) {
                stackNoCas.push(String.valueOf(i));
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(stackNoCas.size);

    }

    /**
     * 多线程下线，是线程安全的
     */
    @Test
    public void test3() throws InterruptedException {
        while (true) {
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    stackUseCas.push(String.valueOf(i));
                }
            });
            Thread t2 = new Thread(() -> {
                for (int i = 100; i < 200; i++) {
                    stackUseCas.push(String.valueOf(i));
                }
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(stackUseCas.size);
            if (stackUseCas.size.get() != 200) {
                System.out.println("有线程安全问题");
                break;
            } else {
                stackUseCas.clear();
            }
        }
    }


    static class StackNoCas {
        private Node head;

        private int size;

        public void push(String value) {
            Node node = new Node(value);
            node.next = head;
            head = node;
            size++;
        }

        public String pop() {
            Node next = head.next;

            String value = head.val;

            head = next;

            size--;

            return value;
        }
    }

    static class StackUseCas {
        private AtomicReference<Node> head = new AtomicReference<>();

        private AtomicInteger size = new AtomicInteger();

        public void push(String value) {
            Node oldNode;
            Node node = new Node(value);
            do {
                oldNode = head.get();
                node.next = oldNode;
            } while (!head.compareAndSet(oldNode, node));
            size.incrementAndGet();
        }

        public String pop() {
            Node newNode;
            Node oldNode;
            do {
                oldNode = head.get();
                if (oldNode == null) {
                    return null;
                }
                newNode = oldNode.next;
            } while (!head.compareAndSet(oldNode, newNode));
            size.decrementAndGet();
            return oldNode.val;
        }

        public void clear() {
            if (head.get() == null) {
                return;
            }
            Node oldNode = head.get();
            head.compareAndSet(oldNode, null);
            size.set(0);
        }
    }

    static class Node {
        public String val;
        public Node next;

        public Node(String val) {
            this.val = val;
        }
    }
}