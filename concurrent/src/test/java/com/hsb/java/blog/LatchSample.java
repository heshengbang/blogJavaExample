package com.hsb.java.blog;/**
 * Created by heshengbang on 2018/6/29.
 */

import java.util.concurrent.CountDownLatch;

/**
 * Created by heshengbang on 2018/6/29.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com`
 */
public class LatchSample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new FirstBatchWorker(latch));
            thread.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new SecondBatchWorker(latch));
            thread.start();
        }
        // 接下来的所有操作都是为了将First和Second区分开
        while (latch.getCount() != 1) {
            Thread.sleep(100L);
        }
        System.out.println("Wait for first batch finish");
        latch.countDown();
    }
}

class FirstBatchWorker implements Runnable {
    private CountDownLatch latch;

    FirstBatchWorker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("First batch executed!");
        latch.countDown();
    }
}

class SecondBatchWorker implements Runnable {
    private CountDownLatch latch;

    SecondBatchWorker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Second batch executed!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
























