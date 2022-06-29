package demo02;

/**
 * @ClassName TestThreadJosin
 * @Description 线程join方法
 * 1. join合并线程，待此线程执行完成之后，在执行其他线程，其他线程会造成阻塞
 * 2. 可以想象成插队
 * @Auther tanyi
 * @Date 2022/6/27
 * @Version 1.0
 **/
public class TestThreadJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程vip 来咯" + i);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        // 子线程
        TestThreadJoin testThreadJoin = new TestThreadJoin();
        Thread t1 = new Thread(testThreadJoin);
        t1.start();

        // 主线程
        for (int i = 0; i < 500; i++) {
            System.out.println("主线程 = " + i);

            if (i == 200) {
                // 子线程强制join，此时主线程会造成阻塞
                t1.join();
            }
        }
    }
}

