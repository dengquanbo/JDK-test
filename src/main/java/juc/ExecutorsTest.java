package juc;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorsTest {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor executorService = buildThreadPoolTaskExecutor();
        executorService.execute(() -> sayHi("execute"));
        Future<?> submit = executorService.submit(() -> sayHi("submit"));
        try {
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void sayHi(String name) {
        String printStr = "【thread-name:" + Thread.currentThread().getName() + ",执行方式:" + name + "】";
        System.out.println(printStr);
        throw new RuntimeException(printStr + ",我异常啦!哈哈哈!");
    }

    private static ThreadPoolTaskExecutor buildThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executorService = new ThreadPoolTaskExecutor();
        executorService.setThreadNamePrefix("(公众号-why技术)-");
        executorService.setCorePoolSize(5);
        executorService.setMaxPoolSize(10);
        executorService.setQueueCapacity(1000);
        executorService.setKeepAliveSeconds(30);
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.initialize();
        return executorService;
    }
}