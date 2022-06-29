package lock;

import javax.swing.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TestReenTrantLock
 * @Description jdk1.5新增了一个 lock类，用来显示的标注代码加锁与解锁
 * 此处使用lock的其中一个子类
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 **/
public class TestReenTrantLock {
    public static void main(String[] args) {
        BuyTicket2 buyTicket2 = new BuyTicket2();

        new Thread(buyTicket2).start();
        new Thread(buyTicket2).start();
        new Thread(buyTicket2).start();
    }
}

class BuyTicket2 implements Runnable {

    // 定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            // 加锁
            lock.lock();
            try {
                if (ticketNums > 0) {
                    System.out.println(ticketNums--);
                } else {
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } finally {
                // 解锁
                lock.unlock();
            }



        }
    }
}
