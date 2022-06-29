package pcmodel;

/**
 * @ClassName TestSignalLamp
 * @Description 解决生产者和消费者模式方法二 -- 信号灯模式
 * <p>
 * 使用标志位控制生产者与消费者之间的线程调度。 像红绿灯一样，红灯行，绿灯停
 * @Auther tanyi
 * @Date 2022/6/29
 * @Version 1.0
 * <p>
 * 1. 演员表演节目
 * 2. 观众观看节目
 * 3. 演员表演节目的时候观众等待
 * 4. 观众观看节目的时候演员等待
 *
 * 展示出来的结果是
 * 演员表演xxxx
 * 观众观看了xxxx
 *
 * 因为flag的控制原因且flag只有true和false，所以两者是交替运行的。
 **/
public class TestSignalLamp {

    public static void main(String[] args) {
        Tv tv = new Tv();

        new Performer(tv).start();
        new Watcher(tv).start();
    }

}

// 演员 -> 生产者
class Performer extends Thread {
    // 电视台，共享资源
    Tv tv;

    Performer(Tv tv) {
        this.tv = tv;
    }

    /**
     * 演员只负责表演
     */
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                if (i % 2 == 0) {
                    this.tv.voice("表演了快乐大本营");
                } else {
                    this.tv.voice("天天向上");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


// 观众 -> 消费者
class Watcher extends Thread {
    // 电视台，共享资源
    Tv tv;

    Watcher(Tv tv) {
        this.tv = tv;
    }

    /**
     * 观众负责观看节目
     */
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                this.tv.watch();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 节目
class Program {
    // 节目名称
    String name;

    Program(String name) {
        this.name = name;
    }
}

// 线程之间的通信
class Tv {

    /**
     * 核心逻辑
     * * 3. 演员表演节目的时候观众等待 flag = T
     * * 4. 观众观看节目的时候演员等待 flag = F
     */
    String programName;
    // 标志位
    boolean flag = true;

    /**
     * 演员表演节目
     */
    public synchronized void voice(String programName) throws InterruptedException {
        if (!flag) {
            // 观众观看节目的时候演员等待
            this.wait();
        }

        // 否则演员表演节目
        this.programName = programName;
        System.out.println("演员表演了节目" + programName);
        // 标志位取反 ******
        this.flag = !this.flag;

        // 表演完毕，通知观众观看节目
        this.notifyAll();
    }

    /**
     * 观众观看节目
     */
    public synchronized void watch() throws InterruptedException {
        if (flag) {
            // 演员表演节目的时候观众等待
            this.wait();
        }

        // 否则观众观看节目
        System.out.println("观众观看了节目" + programName);

        // 标志位取反 ******
        this.flag = !this.flag;

        // 观看完毕，通知演员继续表演节目 -- 唤醒线程
        this.notifyAll();
    }

}




















