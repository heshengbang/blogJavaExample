package com.hsb.java.blog;/**
 * Created by heshengbang on 2018/6/10.
 */

/**
 * Created by heshengbang on 2018/6/10.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class BoundBufferTest {
    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(() -> {
                try {
                    boundedBuffer.put(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setName("thread - " + i);
            thread.start();
        }

        for (int i = 50; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    boundedBuffer.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setName("thread - " + i);
            thread.start();
        }
    }
}
