package refercence;

import sun.misc.Cleaner;

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
            Cleaner.create(obj, new DoSomethingThread("thread_" + index++, obj));
        }
    }

    static class DoSomethingThread implements Runnable {
        private String name;

        private DemoObject obj;

        public DoSomethingThread(String name, DemoObject obj) {
            this.name = name;
            this.obj = obj;
        }

        // do something before gc
        @Override
        public void run() {
            System.out.println(name + " running DoSomething ..." + obj.getName());
        }
    }

    @Data
    @AllArgsConstructor
    static class DemoObject {
        private String name;
    }
}