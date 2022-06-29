package demo02;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import java.time.chrono.MinguoEra;

/**
 * @ClassName TestThreadDeamon
 * @Description 守护线程
 *
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 *
 * 案例：上帝守护着你
 * 上帝是守护线程
 * 我是用户线程。
 *
 * 用户线程结束，守护线程也会结束
 **/
public class TestThreadDaemon {


    public static void main(String[] args) {

        /**
         * 本案例中，根据代码情况查看可以得知上帝这个守护线程应该是不会停止的。但真实情况是mine用户线程结束，守护线程也跟着结束
         */
        God god = new God();
        Mine mine = new Mine();

        // 启动守护线程
        Thread t1 = new Thread(god);
        // 设置t1为守护线程， true: 守护线程; false：用户线程，默认为false
        t1.setDaemon(true);
        t1.start();

        // 启动用户线程
        Thread t2 = new Thread(mine);
        t2.start();
    }


}

class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("上帝守护着你");
        }
    }
}


class Mine implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("开心每一天");
        }

        System.out.println("------> goodBy world");
    }
}
