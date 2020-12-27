package refercence;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

import sun.misc.Cleaner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CleanerTest {
    public static void main(String[] args) throws Exception {
        int index = 0;
        while (true) {
            Thread.sleep(1000);
            // 提醒 GC 去进行垃圾收集了
            System.gc();

            // 该对象不断重新指向其他地方，那么原先指针指向的对象的就属于需要回收的数据
            DemoObject obj = new DemoObject("demo01");
            /*
                 增加 obj 的虚引用,定义清理的接口 DoSomethingThread
                 第一个参数：需要监控的堆内存对象
                 第二个参数：程序释放资源前的回调。
             */
            Cleaner cleaner = Cleaner.create(obj, new DoSomethingThread("thread_" + index++));

            obj = null;

            System.out.println(cleaner.get());
        }
    }

    static class DoSomethingThread implements Runnable {
        private String name;

        private DemoObject obj;

        public DoSomethingThread(String name) {
            this.name = name;
        }
        public DoSomethingThread(String name, DemoObject obj) {
            this.name = name;
            this.obj = obj;
        }

        // do something before gc
        @Override
        public void run() {
            System.out.println("111");
            System.out.println(name + " running DoSomething ..." + obj.getName());
        }
    }

    @Data
    static class DemoObject {
        private String name;
        private List<Object> list = new ArrayList<>(20);

        public DemoObject(String name) {
            this.name = name;
        }
    }

    @Test
    public void test33() throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.exit(-1);
            }
        }).start();

        Thread.sleep(4000);
        System.out.println("1111");
    }


}