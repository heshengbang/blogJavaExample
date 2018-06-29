package com.hsb.java.blog;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by heshengbang on 2018/6/17.
 */

public class DoubleUnLock {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.tryLock();
        try {
            System.out.println("Do somgthing");
        } finally {
            lock.unlock();
        }
    }
}
