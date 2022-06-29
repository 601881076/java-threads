package syn;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SafeLIst
 * @Description 安全的list多线程
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 **/
public class SafeLIst {

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();

        }

        // 如果这里不做线程休眠的话，下面的打印代码可能会在线程循环完前执行
        Thread.sleep(3000);

        System.out.println(list.size());
    }
}
