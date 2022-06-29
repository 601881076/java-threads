package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TestThreadPool
 * @Description 线程池的创建
 * @Auther tanyi
 * @Date 2022/6/29
 * @Version 1.0
 *
 * 1. 创建线程池
 * 2. 使用线程池启动线程
 * 3. 关闭线程池
 **/
public class TestThreadPool {

    public static void main(String[] args) {
        // 创建一个线程池大小 = 10的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 使用线程池启动线程
        // 此处使用的是方式是execute，需要放入一个实现了Runnable接口的对象
        executorService.execute(new MyThreadPoll());
        executorService.execute(new MyThreadPoll());
        executorService.execute(new MyThreadPoll());
        executorService.execute(new MyThreadPoll());

        // 线程池关闭
        executorService.shutdown();
    }

}

class MyThreadPoll implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

