package com.hsb.java.blog;
        /*
         * Copyright ©2011-2017 hsb
         */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.weaving.AspectJWeavingEnabler;

public class MyAspectJWeavingEnabler extends AspectJWeavingEnabler {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyAspectJWeavingEnabler - postProcessBeanFactory");
    }
}
