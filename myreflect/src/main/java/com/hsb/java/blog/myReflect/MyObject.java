package com.hsb.java.blog.myReflect;
        /*
         * Copyright ©2011-2017 hsb
         */

public class MyObject implements Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("I'm Coming  -  message from MyObject");
    }
}
