package com.hsb.java.blog;
        /*
         * Copyright ©2011-2017 hsb
         */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor{
    public MyBeanPostProcessor() {
        super();
        System.out.println(" ====【Bean级别的方法】【构造器】调用BeanPostProcessor实现类构造器实例化");
    }

    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
        System.out.println(" ====【Bean级别的方法】BeanPostProcessor接口方法postProcessBeforeInitialization在初始化对属性进行更改！+++++++++++++++++++++++++");
        return arg0;
    }

    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1)
            throws BeansException {
        System.out.println(" ====【Bean级别的方法】BeanPostProcessor接口方法postProcessAfterInitialization在初始化对属性进行更改！+++++++++++++++++++++++++");
        return arg0;
    }
}
