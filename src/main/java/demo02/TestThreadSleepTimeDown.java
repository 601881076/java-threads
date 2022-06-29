package demo02;

import javax.swing.*;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TestThreadSleepTimeDown
 * @Description 使用sleep模拟倒计时
 * @Auther tanyi
 * @Date 2022/6/26
 * @Version 1.0
 **/
public class TestThreadSleepTimeDown {

    private static int nums = 10;

    public static void main(String[] args) throws InterruptedException {
        // tenDown();

        // 打印系统时间
        printTime();

    }

    /**
     * 打印系统时间
     */
    public static void printTime() throws InterruptedException {
        Date date = new Date(System.currentTimeMillis());

        while (true) {
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
            Thread.sleep(1000);

            // 重新获取时间
            date = new Date(System.currentTimeMillis());

        }
    }


    /**
     * 倒计时
     *
     * @throws InterruptedException
     */
    public static void tenDown() throws InterruptedException {
        while (true) {
            if (nums <= 0) {
                break;
            }

            Thread.sleep(1000);
            System.out.println(nums--);

        }
    }

}
