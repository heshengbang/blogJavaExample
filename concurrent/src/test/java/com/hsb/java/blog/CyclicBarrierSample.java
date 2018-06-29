package com.hsb.java.blog;/**
 * Created by heshengbang on 2018/6/29.
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by heshengbang on 2018/6/29.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class CyclicBarrierSample {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("Action....Go again!"));
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new CyclicWorker(barrier));
            thread.start();
        }
    }

    private static class CyclicWorker implements Runnable {
        private CyclicBarrier barrier;

        CyclicWorker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Executed!");
                    barrier.await();
                }
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}





















