package com.hsb.java.blog;
        /*
         * Copyright ©2011-2017 hsb
         */

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter{
    public MyInstantiationAwareBeanPostProcessorAdapter() {
        super();
        System.out.println(" ====【Bean级别的方法】【构造器】调用InstantiationAwareBeanPostProcessorAdapter的实现类构造器实例化");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
        System.out.println(" ====【Bean级别的方法】InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法----------");
        return null;
    }

    // 接口方法、实例化Bean之后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(" ====【Bean级别的方法】InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法----------");
        return bean;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println(" ====【Bean级别的方法】InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
        return pvs;
    }
}
