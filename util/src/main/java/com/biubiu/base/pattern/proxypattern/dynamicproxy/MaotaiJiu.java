package com.biubiu.base.pattern.proxypattern.dynamicproxy;

/**
 * 目标类
 */
public class MaotaiJiu implements SellWine {
    @Override
    public void maiJiu() {
        System.out.println("我卖茅台酒");
    }
}
