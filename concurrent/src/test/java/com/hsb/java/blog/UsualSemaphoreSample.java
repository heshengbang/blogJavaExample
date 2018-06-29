package com.hsb.java.blog;

import java.util.concurrent.Semaphore;

/**
 * Created by heshengbang on 2018/6/28.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class UsualSemaphoreSample {
    public static void main(String[] args) {
        System.out.println("Action...GO!");
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SemaphoreWorker(semaphore));
            thread.start();
        }
    }
}
class SemaphoreWorker implements Runnable {
    private String name;
    private Semaphore semaphore;
    SemaphoreWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        log("is waiting for a permit!");
        try {
            semaphore.acquire();
            log("acquired a permit!");
            log("executed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
    private void log(String msg) {
        if (name == null) {
            name = Thread.currentThread().getName();
        }
        System.out.println(name + " " + msg);
    }
}