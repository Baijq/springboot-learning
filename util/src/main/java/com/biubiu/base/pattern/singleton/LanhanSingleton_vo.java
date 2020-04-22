package com.biubiu.base.pattern.singleton;

/**
 * 懒汉模式：静态内部类，懒加载
 */
public class LanhanSingleton_vo {

    private static class SingletonHolder {
        private static final LanhanSingleton_vo INSTANCE = new LanhanSingleton_vo();
    }

    public LanhanSingleton_vo() {
    }

    /**只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance（只有第一次使用这个单例的实例的时候才加载，同时不会有线程安全问题）。**/
    public static final LanhanSingleton_vo getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
