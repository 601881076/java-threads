package demo02;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import javax.management.StandardEmitterMBean;

/**
 * @ClassName TestThreadState
 * @Description 观测线程的状态
 * @Auther tanyi
 * @Date 2022/6/27
 * @Version 1.0
 *
 * 1. 线程体
 * 2. 创建线程
 * 3. 获取线程新生状态 new
 * 4. 线程运行，此时线程状态应该是run
 * 5. 当线程停止时，线程状态应该是TERMINATED
 * 6. 线程阻塞时，状态 = BLOCKED、Waiting、timed_waiting
 **/
public class TestThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.State state = t1.getState();
        // 观测线程启动，线程状态应该 = 新生态new
        System.out.println(state);

        t1.start();
        state = t1.getState();
        // 观测线程启动后，线程状态应该 = runnable
        System.out.println(state);

        while (state != Thread.State.TERMINATED) {
            Thread.sleep(100);
            // 当线程状态没有死亡时，一直获取线程状态 这里大部分状态 都是TIMED_WAITING，真实情况应该还存在blocked、waiting等状态
            state = t1.getState();
            System.out.println(state);
        }

    }
}
