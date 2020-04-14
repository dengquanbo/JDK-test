import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {

    @Test
    public void testConcurrentHashMap() {
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>(16);
        for (int i = 0; i < 17; i++) {
            map.put(i, i);
        }
    }

    @Test
    public void testConcurrentHashMap1() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(16);
        map.computeIfAbsent(
                "AaAa",
                key -> map.computeIfAbsent("BBBB", key2 -> 42)
        );
        map.clear();
    }

    @Test
    public void testInitSize() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(16);
    }

    @Test
    public void testConcurrentHashMap2() {
        int i = 1 << 30;
        System.out.println(i);
        System.out.println(Math.pow(2, 30));
        System.out.println(Math.pow(2, 31) - 1);
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void test1122() {
        int MAXIMUM_CAPACITY = 1 << 30;
        System.out.println(MAXIMUM_CAPACITY >> 1);

        int i = 4;
        int cap = tableSizeFor(i);
        System.out.println(cap);
    }

    @Test
    public void test22222() {
        System.out.println(tableSizeFor(16));
        int i = Integer.numberOfLeadingZeros(16) | (1 << (16 - 1));
        System.out.println(i);
        System.out.println(i << 16);
    }

    private static final int tableSizeFor(int c) {
        int MAXIMUM_CAPACITY = 1 << 30;
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
