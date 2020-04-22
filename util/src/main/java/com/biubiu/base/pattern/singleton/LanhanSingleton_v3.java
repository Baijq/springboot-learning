package com.biubiu.base.pattern.singleton;

/**
 * 懒汉模式：解决程序中每次使用getInstance() 都要经过synchronized加锁这一层，这难免会增加getInstance()的方法的时间消费，而且还可能会发生阻塞
 * 很明显，这种方式相比于使用synchronized关键字的方法，可以大大减少getInstance() 的时间消费。
 */
public class LanhanSingleton_v3 {

    /**
     * 使用volatile修饰的变量会强制将修改的值立即写入主存，主存中值的更新会使缓存中的值失效(非volatile变量不具备这样的特性，非volatile变量的值会被缓存，线程A更新了这个值，线程B读取这个变量的值时可能读到的并不是是线程A更新后的值)。volatile会禁止指令重排
     **/
    private static volatile LanhanSingleton_v3 instance;

    private LanhanSingleton_v3() {
    }

    public static LanhanSingleton_v3 getInstance() {
        //检查实例，如果不存在，就进入同步代码块
        if (null == instance) {
            //只有第一次才彻底执行这里的代码
            synchronized (LanhanSingleton_v3.class) {
                //进入同步代码块后，再检查一次，如果仍是null，才创建实例
                if (null == instance) {
                    instance = new LanhanSingleton_v3();
                }
            }
        }
        return instance;
    }
}
