package syn;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName TestJUC
 * @Description Juc并发编程
 * juc： java.utils.concurrent 并发包
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 *
 * 使用juc中的 CopyOnWriteArrayList
 **/
public class TestJUC {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        // 如果这里不做线程休眠的话，下面的打印代码可能会在线程循环完前执行
        Thread.sleep(3000);

        System.out.println(list.size());
    }
}
