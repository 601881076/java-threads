package lock;

/**
 * @ClassName SolveTestDeadLock
 * @Description 解决场景TestDeadLock中的死锁问题
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 *
 *
 * 解决思路： 不再一个同步代码块中使用 "两个及以上对象的锁"
 **/
public class SolveTestDeadLock {

    public static void main(String[] args) {
        MakeUpSolve g1 = new MakeUpSolve(0,"欢欢坨");
        MakeUpSolve g2 = new MakeUpSolve(1,"白雪公主");
        g1.start();
        g2.start();
    }
}

class MakeUpSolve extends Thread{
    static LipStickSolve lipStickSolve = new LipStickSolve();
    static MirrorSolve mirrorSolve = new MirrorSolve();

    private int choice;
    private String name;

    MakeUpSolve(int choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void  makeup() throws InterruptedException {
        if (choice ==0) {
            synchronized (lipStickSolve) {
                System.out.println(this.name + "拿到了口红");
                Thread.sleep(1000);
            }

            // 不再一个同步代码块中使用两个以上对象的锁就不会造成死锁问题
            synchronized (mirrorSolve) {
                System.out.println(this.name + "拿到了镜子");
            }

        } else {
            synchronized (mirrorSolve) {
                System.out.println(this.name + "拿到了镜子");
                Thread.sleep(3000);
            }

            // 不再一个同步代码块中使用两个以上对象的锁就不会造成死锁问题
            synchronized (lipStickSolve) {
                System.out.println(this.name + "拿到了口红");
            }

        }
    }
}

class LipStickSolve {}

class MirrorSolve {}


