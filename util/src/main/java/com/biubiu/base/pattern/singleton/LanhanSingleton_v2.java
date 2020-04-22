package com.biubiu.base.pattern.singleton;

/**
 * 懒汉模式(解决线程安全)
 */
public class LanhanSingleton_v2 {

    private static LanhanSingleton_v2 instance;

    private LanhanSingleton_v2() {
    }

    public static synchronized LanhanSingleton_v2 getInstance() {
        if (null == instance) {
            instance = new LanhanSingleton_v2();
        }
        return instance;
    }

}
