package com.hsb.java.blog;/**
 * Created by heshengbang on 2018/6/10.
 */

/**
 * Created by heshengbang on 2018/6/10.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class ThreadSafeSample {
    private int sharedState;
    private boolean isSafe = false;

    private ThreadSafeSample(boolean isSafe) {
        this.isSafe = isSafe;
    }

    private ThreadSafeSample() {}

    private void safeAction() {
        while (sharedState < 100000) {
            if (isSafe) {
                synchronized (this) {
                    method();
                }
            } else {
                method();
            }
        }
    }

    private void method() {
        int former = sharedState++;
        int latter = sharedState;
        if (former != latter - 1) {
            System.out.println("Observed data race, former is " +
                    former + ", " + "latter is " + latter);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample sample = new ThreadSafeSample(true);
        Thread threadA = new Thread(sample::safeAction);
        Thread threadB = new Thread(sample::safeAction);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
