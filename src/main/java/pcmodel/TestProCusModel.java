package pcmodel;

import com.sun.org.apache.xml.internal.utils.CharKey;

import javax.print.attribute.standard.PrinterURI;

/**
 * @ClassName TestProCusModel
 * @Description 生产者消费者模式
 * @Auther tanyi
 * @Date 2022/6/29
 * @Version 1.0
 *
 * 线程之间的交互
 * 1. 生产者只负责生产
 * 2. 消费者只负责消费
 * 3. 缓冲区容器是两者之间的交互地区
 * 4. 生产者生产完产品，将产品放入缓冲区容器，并且通知消费者来拿产品。
 * 5. 消费者去缓冲区拿产品，并且通知生产者继续生产。
 **/
public class TestProCusModel {

    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();

        Product product = new Product(synContainer);
        Custom custom = new Custom(synContainer);

        product.start();
        custom.start();

    }
}

/**
 * 缓冲区容器
 */
class SynContainer {
    // 容器大小
    Chicken[] chickens = new Chicken[10];
    // 容器计数器
    int count = 0;

    SynContainer() {
        System.out.println(chickens.length);
    }

    /**
     * 生产者将产品放入缓冲区容器
     * @param chicken
     */
    public synchronized void push(Chicken chicken) throws InterruptedException {
        if (count == chickens.length) {
            // 如果容器满了，生产者停止生产（线程等待）
            this.wait();
        }

        // 如果没有容器没有满，需要将产品丢入容器
        chickens[count] = chicken;
        System.out.println("生产了" + count + "只鸡");
        count++;


        // 通知消费者来拿产品（线程唤醒）
        this.notifyAll();
    }

    /**
     * 消费者消费产品
     * @return
     */
    public synchronized Chicken pop() throws InterruptedException {
        // 如果容器没有产品，通知生产者生产产品，消费者等待（线程wait）
        if (count == 0) {
            this.wait();
        }

        // 如果容器存在产品，消费者获取产品，
        count--;
        System.out.println("消费了" + count + "只鸡");
        Chicken chicken = chickens[count];


        // 并通知生产者生产产品
        this.notifyAll();

        return chicken;
    }
}

// 产品
class Chicken {
    // 产品编号
    int id;

    Chicken(int id) {
        this.id = id;
    }
}

/**
 * 生产者
 */
class Product extends Thread {

    private SynContainer synContainer;

    Product(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    // 生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Chicken chicken = new Chicken(i);

            try {
                // 生产者生产产品
                synContainer.push(chicken);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

/**
 * 消费者
 */
class Custom extends Thread {
    private SynContainer synContainer;

    Custom(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Chicken chicken = synContainer.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}




