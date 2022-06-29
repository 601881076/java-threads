package syn;

import java.util.UnknownFormatConversionException;

/**
 * @ClassName UnsafeBank
 * @Description 模拟线程不安全
 * <p>
 * 模拟场景：银行取钱
 * 同时取钱会造成都取成功。
 * @Auther tanyi
 * @Date 2022/6/28
 * @Version 1.0
 **/
public class UnsafeBank {

    public static void main(String[] args) {
        // 创建账户对象(资源)
        Account account = new Account("共同存折",100);

        // 创建线程
        Drawing my = new Drawing(account,50,"我");
        Drawing wife = new Drawing(account,100,"妻子");

        my.start();
        wife.start();
    }

}

/**
 * 账户对象
 */
class Account {
    // 卡名
    String name;
    // 余额
    int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }
}

/**
 * 银行：模拟取款
 */
class Drawing extends Thread {
    // 账户
    Account account;
    // 取了多少钱
    int drawingMoney;
    // 手上还有多少钱
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        // 当账户余额不够时，不允许取钱
        if (account.money - drawingMoney < 0) {
            System.out.println("账户余额不足");
            return;
        }

        // 放大问题的发生性
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        account.money -= drawingMoney;

        nowMoney += drawingMoney;

        System.out.println(this.getName() + "取了" + drawingMoney + ",还剩" + account.money + ",手上有" + nowMoney);
    }
}


