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
    public int sharedState;
    public void nonSafeAction() {
        while (sharedState < 100000) {
            int former;
            int latter;
            synchronized (this) {
                former = sharedState++;
                latter = sharedState;
            }
            if (former != latter - 1) {
                System.out.println("Observed data race, former is " +
                        former + ", " + "latter is " + latter);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample sample = new ThreadSafeSample();
        Thread threadA = new Thread(sample::nonSafeAction);
        Thread threadB = new Thread(sample::nonSafeAction);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
