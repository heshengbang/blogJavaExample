package com.hsb.java.blog;

/**
 * Hello world!
 */
public class App {
    private static int count = 1;

    public synchronized static void plus(int num) {
        count += num;
    }

    public synchronized static void minus(int num) {
        count -= num;
    }

    public synchronized static void multiply(int num) {
        int temp = count;
        while (num > 1) {
            // synchronized 重入
            plus(temp);
            num--;
        }
    }

    public synchronized static void divide(int num) {
        count = count / num;
    }

    public static int getCount() {
        return count;
    }
}
