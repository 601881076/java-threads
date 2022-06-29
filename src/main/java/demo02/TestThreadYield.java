package demo02;

/**
 * @ClassName TestThreadYield
 * @Description 线程礼让
 * @Auther tanyi
 * @Date 2022/6/27
 * @Version 1.0
 **/
public class TestThreadYield {


    public static void main(String[] args) {
        myYield myYield = new myYield();
        new Thread(myYield,"A").start();
        new Thread(myYield,"B").start();
    }

}


class myYield implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        // 线程礼让。让出cpu资源，当前线程回到就绪状态与其他线程一起等待被cpu分配。所以让出的线程有可能重新拿到cpu资源，也有可能礼让成功其他线程
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程执行完毕");


    }
}
