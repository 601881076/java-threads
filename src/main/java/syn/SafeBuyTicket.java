package syn;

/**
 * @ClassName SafeBuyTicket
 * @Description 安全的购票流程
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 **/
public class SafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket1 buyTicket1 = new BuyTicket1();

        new Thread(buyTicket1, "小明").start();
        new Thread(buyTicket1, "老师").start();
        new Thread(buyTicket1, "黄牛党").start();
    }

}

class BuyTicket1 implements Runnable {

    private int ticketNums = 10;
    private boolean flag = true;


    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 购票方法
     */
    public synchronized void buy() throws InterruptedException {
        if (ticketNums <= 0) {
            System.out.println("票已售卖完毕");
            flag = false;
            return;
        }

        Thread.sleep(100);

        System.out.println(Thread.currentThread().getName()+"买到了第" + ticketNums-- +"张票");
    }
}
