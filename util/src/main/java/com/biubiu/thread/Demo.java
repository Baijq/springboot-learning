package com.biubiu.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Demo
 *
 * @author baijq
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        //来一个计数器，每个线程执行完后主线程才可以执行
        CountDownLatch downLatch = new CountDownLatch(3);
        new Thread(() -> {
            try {
                xiCai();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //洗菜完了计数器会减一
            downLatch.countDown();
        }).start();
        new Thread(() -> {
            try {
                shaoShui();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            downLatch.countDown();
        }).start();
        new Thread(() -> {
            try {
                zhengMi();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            downLatch.countDown();
        }).start();
        //等待所有工作完成后，走下一步
        downLatch.await();
        System.out.println("完成洗菜，烧水，蒸饭 花费时间：" + ((System.currentTimeMillis() - start) / 1000) + "s");
    }

    private static void xiCai() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("洗菜(3s)...");
    }

    private static void zhengMi() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("蒸米(10s)...");
    }

    private static void shaoShui() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("烧水(5s)...");
    }
}
