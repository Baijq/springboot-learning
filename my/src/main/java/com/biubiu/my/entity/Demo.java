package com.biubiu.my.entity;

/**
 * TODO
 *
 * @author wbbaijq
 */
public class Demo {

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                Thread.currentThread().interrupt();
                System.out.println("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            System.out.println(2);
        }).start();



    }
}
