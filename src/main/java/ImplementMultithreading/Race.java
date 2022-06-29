package ImplementMultithreading;

/**
 * @ClassName Race
 * @Description 模拟实现龟兔赛跑
 * @Auther tanyi
 * @Date 2022/6/26
 * @Version 1.0
 **/
public class Race implements Runnable {

    private static String winner;

    @Override
    public void run() {

        for (int i = 0; i <= 100; i++) {

            if (Thread.currentThread().getName().equals("兔子") && i % 10 == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (getWinner(i)) {
                // 如果在这里对胜利者进行打印消息的的话，会造成重复操作。
                // System.out.println("胜利者是 " + winner);
                break;
            }

            System.out.println(Thread.currentThread().getName() + "--> 跑了" + i + "步");
        }
    }

    /**
     * 获取胜利者
     *
     * @param i
     * @return
     */
    public boolean getWinner(int i) {
        if (winner != null) {
            // 已经存在胜利者
            return true;

        } else {

            if (i >= 100) {
                winner = Thread.currentThread().getName();
                // 只能在这里对胜利者进行操作。
                System.out.println("胜利者是 " + winner);
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();

        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }


}
