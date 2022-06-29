package syn;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;
import sun.tools.attach.HotSpotVirtualMachine;

/**
 * @ClassName UnsafeBuyTicket
 * @Description 模拟线程的不安全场景
 *
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 *
 * 买票场景
 * 没有对资源ticketNums 上锁，会造成买票人员买到同一张票
 **/
public class UnsafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket,"小明").start();
        new Thread(buyTicket,"老师").start();
        new Thread(buyTicket,"黄牛党").start();

    }
}


class BuyTicket implements Runnable {
    // 票数量
    private int ticketNums = 10;
    // 外部停止方式
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void buy() throws InterruptedException {
        if (ticketNums <= 0) {
            System.out.println("票已经售卖完毕");
            flag = false;
            return;
        }

        Thread.sleep(100);

        System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNums-- + "票");

    }
}