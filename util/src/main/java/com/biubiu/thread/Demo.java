package com.biubiu.thread;

import java.util.concurrent.*;

/**
 * Demo
 *
 * @author baijq
 */
public class Demo {

    //不要使用如下方法创建线程池，因为这种方式会使用无界的任务队列，为避免OOM，我们应该使用ThreadPoolExecutor的构造方法手动指定队列的最大长度：
    //ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);

    //创建线程池 https://www.cnblogs.com/CarpenterLee/p/9558026.html
    //线程数量，可以自己指定
    int poolSize = Runtime.getRuntime().availableProcessors() * 2; // 2
    ExecutorService fixedThreadPool = new ThreadPoolExecutor(poolSize, // 线程池长期维持的线程数，即使线程处于Idle状态，也不会回收。
            poolSize, // 线程数的上限
            0, TimeUnit.SECONDS, // 超过corePoolSize的线程的idle时长，超过这个时间，多余的线程会被回收。
            new ArrayBlockingQueue<>(512), // 任务的排队队列 使用有界队列，避免OOM
            new ThreadPoolExecutor.DiscardPolicy()); // 拒绝策略

    public void dothing() {
        for (int i = 0; i < 50; i++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //do something
                }
            });
        }
        fixedThreadPool.shutdown();//关闭线程池

        //此处不可以删除或注释，需要线程执行结束后再执行别的内容,即只有线程结束后才会继续向下执行
        while (!fixedThreadPool.isTerminated()) {
            System.out.println("执行完毕");
        }
    }

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
