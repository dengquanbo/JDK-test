import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDemo {
    public static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public void show() {
        SimpleDateFormat format = threadLocal.get();
        if (format == null) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            threadLocal.set(format);
        }
        System.out.println(format.format(new Date()));
    }

    public static void main(String[] args) {
        new Thread(() -> {
            ThreadLocalDemo demo = new ThreadLocalDemo();
            demo.show();
        }).start();

        new Thread(() -> {
            ThreadLocalDemo demo = new ThreadLocalDemo();
            demo.show();
        }).start();
    }
}
