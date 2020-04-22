package com.biubiu.base.pattern.proxypattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

        MaotaiJiu maotaiJiu = new MaotaiJiu();
        Wuliangye wuliangye = new Wuliangye();
        Furongwang furongwang = new Furongwang();

        InvocationHandler jingxiao1 = new GuitaiA(maotaiJiu);
        InvocationHandler jingxiao2 = new GuitaiA(wuliangye);
        InvocationHandler jingxiao3  = new GuitaiA(furongwang);

        SellWine dynamicProxy1 = (SellWine) Proxy.newProxyInstance(MaotaiJiu.class.getClassLoader(), MaotaiJiu.class.getInterfaces(), jingxiao1);
        SellWine dynamicProxy2 = (SellWine) Proxy.newProxyInstance(Wuliangye.class.getClassLoader(), Wuliangye.class.getInterfaces(), jingxiao2);
        SellCigarette dynamicProxy3 = (SellCigarette) Proxy.newProxyInstance(Furongwang.class.getClassLoader(), Furongwang.class.getInterfaces(), jingxiao3);

        dynamicProxy1.maiJiu();
        dynamicProxy2.maiJiu();
        dynamicProxy3.sell();
    }

}
