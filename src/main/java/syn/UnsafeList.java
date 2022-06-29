package syn;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UnsafeList
 * @Description list 线程不安全
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 **/
public class UnsafeList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
               list.add(Thread.currentThread().getName());
            }).start();
        }

        // 理想情况是size = 10000， 真实情况是 < 10000的
        // 因为多线程的情况下，可能存在两个或多个线程对一个list.index进行赋值。
        System.out.println("list size = " + list.size());
    }
}
