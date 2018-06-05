package com.hsb.java.blog;
        /*
         * Copyright ©2011-2017 hsb
         */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyCustomAutowireConfigurer extends CustomAutowireConfigurer {

    @Override
    @SuppressWarnings("unchecked")
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyCustomAutowireConfigurer - postProcessBeanFactory");
    }
}
