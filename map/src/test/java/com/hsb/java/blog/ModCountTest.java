package com.hsb.java.blog;/**
 * Created by heshengbang on 2018/6/5.
 */

import java.util.HashMap;

/**
 * Created by heshengbang on 2018/6/5.
 * https://github.com/heshengbang
 * www.heshengbang.men
 * email: trulyheshengbang@gmail.com
 */
public class ModCountTest {
    public static void main(String[] args) {
        HashMap<Integer, String> one = new HashMap<>(1);
        // debug System.out.println("modCount=" + one.modCount + "   size=" + one.size + "   threshold="+one.threshold)
        one.put(1, "1");
        one.put(2, "2");
        one.put(3, "3");
        one.put(4, "4");
        one.put(5, "5");
        one.remove(1);
        System.out.println(one.toString());

    }
}