package com.biubiu.base.pattern.singleton;

/**
 * 饿汉模式
 */
public class EhanSingleton {

    /**
     * 在静态初始化器中创建单例实例，这段代码保证了线程安全
     **/
    private static EhanSingleton instance = new EhanSingleton();

    /**
     * Singleton类只有一个构造方法并且是被private修饰的，所以用户无法通过new方法创建该对象实例
     **/
    private EhanSingleton() {
    }

    /**
     * 外部可以获取实例的方法
     **/
    public static EhanSingleton getInstance() {
        return instance;
    }
}
