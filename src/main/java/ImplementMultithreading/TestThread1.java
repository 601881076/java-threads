package ImplementMultithreading;

/**
 * @ClassName TestThread1
 * @Description 实现多线程方法1： 继承 thread
 * @Auther tanyi
 * @Date 2022/6/26
 * @Version 1.0
 **/
public class TestThread1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("我在看代码 --- " + i);
        }

    }

    public static void main(String[] args) {
        TestThread1 extendThread = new TestThread1();
        // 执行run()时，会先执行完run()的内容，在执行后面的内容
        // extendThread.run();

        // 执行 start()时，会对cpu发出创建新线程申请，此时在多核cpu的情况下新线程与主线程允许并行。
        extendThread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("我再学多线程 ---- " + i);
        }
    }
}
