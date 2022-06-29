package demo02;

/**
 * @ClassName StaticProxy
 * @Description 静态代理
 *
 * 静态代理模式总结：
 *  1. 真实对象和代理对象都要实现同一个接口
 *  2. 代理对象要代理真实角色(代码表现为 代理对象的全局变量 = 真实对象)
 *
 * 好处：
 *  1. 代理对象可以做很多真实对象做不了的事情
 *  2. 真实对象专注做自己的事情。
 *
 * @Auther tanyi
 * @Date 2022/6/26
 * @Version 1.0
 **/
public class StaticProxy {

    /**
     * 结婚
     * 1. 结婚对象是自己
     * 2. 结婚需要处理的一些过程可以由婚庆公司处理
     *
     * 流程：
     * 1. 定义一个结婚接口
     * 2. 定义一个结婚对象，实现结婚接口
     *  2.1 结婚真实对象只需要专注做自己的事情，当新郎
     * 3. 定义一个代理对象，实现结婚接口
     *  3.1 代理对象需要实现结婚真实对象做不了的事情，比如布置场地，摄像等
     * 4. 代理对象中实现接口方法时，需要调用结婚真实对象的实现方法
     */

    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new You("谭奕"));
        weddingCompany.happyMarry();
    }
}

/**
 * 定义一个结婚接口
 */
interface Marry {
    void happyMarry();
}

/**
 * 定义一个结婚对象，实现结婚接口
 */
class You implements Marry {

    private String name;

    public You(String name) {
        this.name = name;
    }

    /**
     * 结婚真实对象只需要专注做自己的事情，当新郎
     */
    @Override
    public void happyMarry() {
        System.out.println(name + "当新郎");
    }
}

/**
 * 定义一个代理对象，实现结婚接口
 */
class WeddingCompany implements Marry {

    /**
     * 真实对象
     */
    private Marry target;

    /**
     * 代理对象需要接受真实对象。
     * @param marry
     */
    public WeddingCompany(Marry marry) {
        this.target = marry;
    }
/**
     * 代理对象需要实现结婚真实对象做不了的事情，比如布置场地，摄像等
     */
    @Override
    public void happyMarry() {
        after();
        this.target.happyMarry();
        before();

    }

    /**
     * 帮助真实对象无法完成的事情
     */
    private void after() {
        System.out.println("结婚前，很开心");
    }

    /**
     * 帮助真实对象无法完成的事情
     */
    private void before() {
        System.out.println("结婚后，交尾款");
    }


}
