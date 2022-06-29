package ImplementMultithreading;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @ClassName TestThread2
 * @Description 使用多线程下载图片
 * @Auther tanyi
 * @Date 2022/6/26
 * @Version 1.0
 **/
public class TestThread2 implements Runnable {

    private String url;
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.fileDownLoader(url, name);

        System.out.println("图片 " + name + "下载完成" + ",线程名称 =" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://uploadfile.huiyi8.com/2014/0210/20140210095802824.jpg.270.jpg","1.jpg");
        TestThread2 t2 = new TestThread2("https://xiaotua.com/images/2021/06/07/202106070525433161571.jpg","2.jpg");
        TestThread2 t3 = new TestThread2("https://pic2.zhimg.com/v2-3be05963f5f3753a8cb75b6692154d4a_1440w.jpg?source=172ae18b","3.jpg");

        // 以下执行代码为继承Thread类时执行。执行程序可以看到，图片下载顺序并不是按照理想t1,t2,t3下载，而是不规则的乱序。
        // t1.start();
        // t2.start();
        // t3.start();

        // 通过实现Runnable接口创建多线程
        new Thread(t1,"线程1").start();
        new Thread(t2,"线程2").start();
        new Thread(t3,"线程3").start();
    }
}

/**
 * 图片下载器
 */
class WebDownloader {

    public void fileDownLoader(String url, String name) {
        try {

            // 通过url复制文件到本地。
            FileUtils.copyURLToFile(new URL(url), new File(name));

        } catch (IOException e) {
            System.out.println("下载器异常，" + e.getMessage());
        }
    }
}
