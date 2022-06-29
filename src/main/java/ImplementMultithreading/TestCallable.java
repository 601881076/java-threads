package ImplementMultithreading;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @ClassName TestThread2
 * @Description 使用多线程下载图片
 * @Auther tanyi
 * @Date 2022/6/26
 * @Version 1.0
 **/
public class TestCallable implements Callable<Boolean> {

    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.fileDownLoader(url, name);

        System.out.println("图片 " + name + "下载完成" + ",线程名称 =" + Thread.currentThread().getName());
        return true;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://uploadfile.huiyi8.com/2014/0210/20140210095802824.jpg.270.jpg", "1.jpg");
        TestCallable t2 = new TestCallable("https://xiaotua.com/images/2021/06/07/202106070525433161571.jpg", "2.jpg");
        TestCallable t3 = new TestCallable("https://pic2.zhimg.com/v2-3be05963f5f3753a8cb75b6692154d4a_1440w.jpg?source=172ae18b", "3.jpg");

        // 以下执行代码为继承Thread类时执行。执行程序可以看到，图片下载顺序并不是按照理想t1,t2,t3下载，而是不规则的乱序。
        // t1.start();
        // t2.start();
        // t3.start();

        // 通过实现Runnable接口创建多线程
        // new Thread(t1,"线程1").start();
        // new Thread(t2,"线程2").start();
        // new Thread(t3,"线程3").start();

        // 通过实现callable接口创建多线程
        // 创建执行服务: 如果线程池只有一个线程，则线程会按照顺序执行。
        ExecutorService ser = Executors.newFixedThreadPool(3);

        // 5. 提交执行:
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        // 6. 获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        // 关闭服务:
        ser.shutdownNow();
    }


}

