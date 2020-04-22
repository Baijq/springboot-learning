package com.biubiu.base.pattern.proxypattern.dynamicproxy;

public class Furongwang implements SellCigarette {

    @Override
    public void sell() {
        System.out.println("售卖的是正宗的芙蓉王，可以扫描条形码查证。");
    }
}
