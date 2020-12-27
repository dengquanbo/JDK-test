package refercence;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {

    @Test
    public void testPR1() {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        Object object = new Object();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);
        System.out.println(phantomReference.get());
    }


    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(referenceQueue.poll());
        System.out.println(phantomReference.get());

        o1 = null;
        System.gc();
        Thread.sleep(3000);

        System.out.println(o1);
        System.out.println(referenceQueue.poll().get()); //引用队列中
        System.out.println(phantomReference.get());
    }
}
