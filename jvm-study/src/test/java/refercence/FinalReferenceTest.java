package refercence;

import com.google.common.collect.Lists;

import org.junit.Test;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class FinalReferenceTest {
    public static void main(String[] args) throws Exception {
        int index = 0;
        while (true) {
            Thread.sleep(1000);
            // 提醒 GC 去进行垃圾收集了
            System.gc();

            // 该对象不断重新指向其他地方，那么原先指针指向的对象的就属于需要回收的数据
            DemoObject obj = new DemoObject("demo" + index++);
        }
    }

    @Data
    @AllArgsConstructor
    @ToString
    static class DemoObject {
        private String name;
        private static final List<Object> objectList = Lists.newArrayList();

        @Override
        protected void finalize() throws Throwable {
            System.out.println(name + " running DoSomething ...");
            objectList.add(this);
            System.out.println(objectList.size());
            System.out.println(objectList);
        }
    }

    @Test
    public void test1() {
        System.runFinalization();

        Runtime.getRuntime().runFinalization();
    }
}
