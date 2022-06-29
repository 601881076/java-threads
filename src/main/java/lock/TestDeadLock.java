package lock;

/**
 * @ClassName TestDeadLock
 * @Description 模拟线程死锁
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 * <p>
 * 场景：两个女生化妆，但只有一只口红和一面镜子，
 * 女生g1先拿口红，女生g2先拿镜子，双方都在等待对方先交出化妆品才进行物品交换。
 **/
public class TestDeadLock {

    public static void main(String[] args) {
        MakeUp g1 = new MakeUp(0, "欢欢坨");
        MakeUp g2 = new MakeUp(1, "白雪公主");

        g1.start();
        g2.start();
    }
}

/**
 * 化妆
 */
class MakeUp extends Thread {
    // 化妆的时候口红和镜子只有一份，使用static保证数量只有一份
    static LipStick lipStick = new LipStick();
    static Mirror mirror = new Mirror();

    // 选择口红还是镜子 0 -> 口红； 1->镜子
    private int choice;
    private String girlName;

    MakeUp(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void makeup() throws InterruptedException {
        if (choice == 0) {
            // 第一个人先拿口红
            synchronized (lipStick) {
                System.out.println(this.girlName + "拿到了口红");
                // 为了保证其他线程开启并拿到对应的锁，这里模拟网络延时
                Thread.sleep(1000);

                synchronized (mirror) {
                    System.out.println(this.girlName + "拿到了镜子");
                }
            }
        } else {
            // 第二个人先拿镜子
            synchronized (mirror) {
                System.out.println(this.girlName + "拿到了镜子");
                Thread.sleep(3000);

                synchronized (lipStick) {
                    System.out.println(this.girlName + "拿到了口红");
                }
            }
        }
    }
}

// 口红
class LipStick {
}

// 镜子
class Mirror {
}

