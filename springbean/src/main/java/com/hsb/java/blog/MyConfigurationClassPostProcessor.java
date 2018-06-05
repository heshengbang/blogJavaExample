package com.hsb.java.blog;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

/*
         * Copyright ©2011-2017 hsb
         */
public class MyConfigurationClassPostProcessor extends ConfigurationClassPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        System.out.println("MyConfigurationClassPostProcessor - postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out.println("MyConfigurationClassPostProcessor - postProcessBeanFactory");
    }
}
