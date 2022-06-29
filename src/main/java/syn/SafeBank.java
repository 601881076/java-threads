package syn;

/**
 * @ClassName SafeBank
 * @Description 安全的取钱流程
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 **/
public class SafeBank {

    public static void main(String[] args) {
        Account1 account1 = new Account1("旅游基金", 100);

        Bank bank = new Bank(account1, 50, "my");
        Bank bank1 = new Bank(account1, 30, "wife");
        Bank bank2 = new Bank(account1, 20, "monther");

        bank.setPriority(10);
        bank1.setPriority(Thread.NORM_PRIORITY);
        bank2.setPriority(Thread.MIN_PRIORITY);

        bank.start();
        bank1.start();
        bank2.start();

    }
}

class Bank extends Thread {
    // 账户
    private Account1 account1;
    // 取多少钱
    private int drawingMoney;
    // 手上有多少钱
    private int nowMoney;

    public Bank(Account1 account1, int drawingMoney, String name) {
        super(name);
        this.account1 = account1;
        this.drawingMoney = drawingMoney;
    }

    // 为什么不能将锁写在run方法上。因为synchronized写在方法上默认是给当前类对象上锁，即Bank对象。
    // 但是实际操作中，多线程修改的资源是账户（account对象），所以在这里给run()方法上锁是没有用的
    // 不生效：public synchronized void run() {
    @Override
    public void run() {

        // 因为修改的对象是account1，所以要使用synchronized代码块对account1上锁
        synchronized (account1) {
            // 判断余额
            if (account1.money - drawingMoney < 0) {
                System.out.println("余额不足");
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account1.money -= drawingMoney;
            nowMoney += drawingMoney;

            System.out.println(Thread.currentThread().getName() + "取走了" + drawingMoney + "元，还剩" + account1.money + ",现在手上还有" + nowMoney);
        }


    }
}

class Account1 {
    String name;
    // 余额
    int money;

    public Account1(String name, int money) {
        this.name = name;
        this.money = money;
    }
}
