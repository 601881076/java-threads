package demo02;

/**
 * @ClassName TestThreadStop
 * @Description 如何让线程安全的停止，
 * 本类使用标志位的形式让线程安全停止。
 * @Auther tanyi
 * @Date 2022/6/26
 * @Version 1.0
 **/
public class TestThreadStop implements Runnable {
    // 声明标志位
    private boolean flag = true;

    /**
     * 1. 建议线程正常停止 --> 利用次数，不建议死循环
     * 2. 建议使用标志位 --> 设置一个标志位
     * 3. 不要使用stop或者destroy等过时或者JDK不建议使用的方法
     */

    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName() + "线程循环");
        }
    }

    // 修改标志位
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TestThreadStop testThreadStop = new TestThreadStop();
        new Thread(testThreadStop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main = " + i);
            if (i == 900) {
                // 修改标志位 = false，使线程安全停止。
                testThreadStop.stop();
                System.out.println("线程该停止了。");
            }
        }
    }


}


