package ImplementMultithreading;

import sun.nio.cs.ext.MacHebrew;

/**
 * @ClassName TestThread4
 * @Description 多个线程操作同一资源。
 * 发现问题： 多个线程操作同一资源(ticketNums)的情况下，线程不安全，数据紊乱。
 * @Auther tanyi
 * @Date 2022/6/26
 * @Version 1.0
 **/
public class TestThread4 implements Runnable {

    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }

            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNums-- + "张票");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("线程延迟错误" + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        TestThread4 testThread4 = new TestThread4();

        new Thread(testThread4,"小明").start();
        new Thread(testThread4,"老师").start();
        new Thread(testThread4,"黄牛党").start();

    }
}
