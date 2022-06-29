package ImplementMultithreading;

/**
 * @ClassName TestThread3
 * @Description 实现多线程方法3： 实现 Runnable接口
 * @Auther tanyi
 * @Date 2022/6/26
 * @Version 1.0
 **/
public class TestThread3 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("我在看代码 ---- " + i);
        }
    }

    public static void main(String[] args) {
        TestThread3 testThread3 = new TestThread3();

        new Thread(testThread3).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("我再学多线程 --- " + i);
        }
    }
}
