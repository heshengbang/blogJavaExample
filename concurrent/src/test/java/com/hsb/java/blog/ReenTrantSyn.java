package com.hsb.java.blog;/**
 * Created by heshengbang on 2018/6/10.
 */

/**
 * Created by heshengbang on 2018/6/10.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class ReenTrantSyn {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            App.multiply(3);
            System.out.println("thread1 : " + App.getCount());
        });
        Thread thread2 = new Thread(() -> {
            App.multiply(3);
            System.out.println("thread2 : " + App.getCount());
        });
        Thread thread3 = new Thread(() -> {
            App.multiply(3);
            System.out.println("thread3 : " + App.getCount());
        });
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
        System.out.println(App.getCount());
    }
}
