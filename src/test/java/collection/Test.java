package collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    static Map<Integer, Integer> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("f(" + 14 + ") =" + fibonacci(14));
    }

    static int fibonacci(int i) {
        if (i == 0)
            return i;
        if (i == 1)
            return 1;
        return cache.computeIfAbsent(i, (key) -> {
            System.out.println("Slow calculation of " + key);
            return fibonacci(i - 2) + fibonacci(i - 1);
        });
    }
}