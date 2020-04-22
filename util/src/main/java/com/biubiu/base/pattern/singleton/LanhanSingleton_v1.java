package com.biubiu.base.pattern.singleton;

/**
 * 懒汉模式
 */
public class LanhanSingleton_v1 {

    private static LanhanSingleton_v1 instance;

    private LanhanSingleton_v1() {
    }

    /**没有加入synchronized关键字的版本是线程不安全的**/
    public static LanhanSingleton_v1 getInstance() {
        //判断当前单例是否已经存在，若存在则返回，不存在则再建立单例
        if (null == instance) {
            instance = new LanhanSingleton_v1();
        }
        return instance;
    }

}
