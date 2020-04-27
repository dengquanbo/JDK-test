import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {

    /**
     * 这里不会发生 {@link java.util.ConcurrentModificationException}，原因第一次移除后 size = 1，cursor = 1,
     * <p>
     * 则在下一次 hasNext 的时候 cursor = size 则不会调用 next 方法，直接就结束了
     */
    @Test
    public void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (Integer l : list) {
            if (l == 1) {
                list.remove(l);
            }
        }
    }


    /**
     * 这里会抛出 {@link java.util.ConcurrentModificationException}
     * <p>
     * 初始时，添加了两个元素，则 modCount = 2，然后编译后则 exceptedModCount = 2
     * <p>
     * 第一次遍历后，size = 2 ,cursor = 1, 继续循环
     * <p>
     * 第二次遍历，移除元素后，size = 1, cursor = 2，modCount = 3, exceptedModCount = 2 继续循环
     * <p>
     * 第三次遍历，此时，由于 hasNext 方法 size != cursor 则继续循环，此时 expectedModCount 检测到 modCount != exceptedModCount，抛出异常
     */
    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (Integer l : list) {
            if (l == 2) {
                list.remove(l);
            }
        }
    }

    /**
     * 和 {@link test1 } 一样，只会执行一次方法体，第二次 hasNext 方法会退出
     */
    @Test
    public void test3() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (Integer l : list) {
            list.remove(l);
        }
    }

}
