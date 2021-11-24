import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class CompletableFutureTest {

    /**
     * 使用ForkJoinPool.commonPool()作为它的线程池执行异步代码
     */
    @Test
    public void runAsync() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Hello");
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("CompletableFuture");

    }

    /**
     * 使用ForkJoinPool.commonPool()作为它的线程池执行异步代码，异步操作有返回值
     */
    @Test
    public void supplyAsync() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("CompletableFuture");
    }

    /**
     * 完成异步执行，并返回future的结果，只能调用一次，后续的重复调用会失效。
     */
    @Test
    public void complete() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        future.complete("World");

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 如果future已经执行完毕能够返回结果，此时再调用complete(T t)则会无效。
     */
    @Test
    public void complete1() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        future.complete("World");

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步执行不正常的结束
     */
    @Test
    public void completeExceptionally() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        future.completeExceptionally(new Exception());

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // ================================= 转换

    /**
     * 接受一个Function<? super T,? extends U>参数用来转换CompletableFuture
     * <p>
     * thenApply的功能相当于将CompletableFuture<T>转换成CompletableFuture<U>。
     */
    @Test
    public void thenApply() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        future = future.thenApply(new Function<String, String>() {

            @Override
            public String apply(String s) {

                return s + " World";
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {

                return s.toUpperCase();
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
