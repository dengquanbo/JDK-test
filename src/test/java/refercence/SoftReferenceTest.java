package refercence;

import org.junit.Test;

import java.lang.ref.SoftReference;

public class SoftReferenceTest {

    public static void main(String[] args) {
        softRefMemoryEnough();
        System.out.println("------内存不够用的情况------");
        softRefMemoryNotEnough();
    }

    private static void softRefMemoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> s1 = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(s1.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(s1.get());
    }

    /**
     * JVM配置`-Xms5m -Xmx5m` ，然后故意new一个一个大对象，使内存不足产生 OOM，看软引用回收情况
     */
    private static void softRefMemoryNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> s1 = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(s1.get());

        o1 = null;

        byte[] bytes = new byte[3 * 1024 * 1024];
        byte[] bytes1 = new byte[3 * 1024 * 1024];
        System.gc();


        System.out.println(o1);
        System.out.println(s1.get());
    }


    /**
     * JVM配置`-Xms5m -Xmx5m`
     */
    @Test
    public void test1() {
        byte[] o1 = new byte[3 * 1024 * 1024];
        SoftReference<Object> s1 = new SoftReference<Object>(o1);

        System.out.println(o1);
        System.out.println(s1.get());
        o1 = null;
        System.out.println(o1);
        System.out.println(s1.get());

        System.out.println("--------------------");

        byte[] o2 = new byte[3 * 1024 * 1024];
        SoftReference<Object> s2 = new SoftReference<Object>(o2);

        System.out.println(o2);
        System.out.println(s2.get());

        System.out.println("s1是否被回收了" + s1.get());
    }
}