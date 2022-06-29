package demo02;

/**
 * @ClassName TestThreadPriority
 * @Description 线程的优先级
 * @Auther tanyi
 * @Date 2022/6/27
 * @Version 1.0
 **/
public class TestThreadPriority {

    public static void main(String[] args) {
        System.out.println("主线程"+Thread.currentThread().getName() + "优先级" + Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();
        Thread t1 = new Thread(myPriority);
        Thread t2 = new Thread(myPriority);
        Thread t3 = new Thread(myPriority);
        Thread t4 = new Thread(myPriority);
        Thread t5 = new Thread(myPriority);
        Thread t6 = new Thread(myPriority);

        // 线程优先级1
        t1.setPriority(1);
        t1.start();

        // 优先级最高 = 10
        t2.setPriority(Thread.MAX_PRIORITY);
        t2.start();

        // 优先级正常 = 5
        t3.setPriority(Thread.NORM_PRIORITY);
        t3.start();

        t4.setPriority(8);
        t4.start();

        t5.setPriority(7);
        t5.start();

        t6.setPriority(3);
        t6.start();

    }
}

class MyPriority implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "优先级" + Thread.currentThread().getPriority());
    }
}
