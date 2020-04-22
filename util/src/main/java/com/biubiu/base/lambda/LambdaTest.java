package com.biubiu.base.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * LambdaTest
 *
 * @author biubiu
 */
public class LambdaTest {
    public static void main(String[] args) {
        Random random = new Random();

        List<Hero> heroes = new ArrayList<Hero>();
        for (int i = 0; i < 10; i++) {
            heroes.add(new Hero("hero" + i, random.nextInt(1000), random.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heroes);
        System.out.println("筛选出 hp > 100 && damage < 50 的英雄");
        System.out.println(filter(heroes));
    }

    private static List<Hero> filter(List<Hero> heroes) {
        List<Hero> filterHeros = new ArrayList<Hero>();
        for (Hero hero : heroes) {
            if (hero.getHp() > 100 && hero.getDamage() < 50) {
                filterHeros.add(hero);
            }
        }
        return filterHeros;
    }
}
