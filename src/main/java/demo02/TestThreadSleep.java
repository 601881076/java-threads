package demo02;

/**
 * @ClassName TestThreadSleep
 * @Description 使用线程sleep模拟延时与倒计时
 * @Auther tanyi
 * @Date 2022/6/26
 * @Version 1.0
 *
 * 模拟网络延时作用：使代码更加符合真实情况，放大问题的发生性
 **/
public class TestThreadSleep implements Runnable{

    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            // 如果此处不模拟网络延时，有可能会发生老师/小明/黄牛一个人把票全部强完的情况

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNums-- + "张票");
        }
    }

    public static void main(String[] args) {
        TestThreadSleep TestThreadSleep = new TestThreadSleep();

        new Thread(TestThreadSleep,"小明").start();
        new Thread(TestThreadSleep,"老师").start();
        new Thread(TestThreadSleep,"黄牛").start();
    }
}
