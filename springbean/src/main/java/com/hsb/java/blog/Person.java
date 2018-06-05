package com.hsb.java.blog;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Hello world!
 */
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean, ApplicationContextAware {

    private String name;
    private String address;
    private int phone;

    private BeanFactory beanFactory;
    private String beanName;

    public Person() {
        System.out.println(" ====【Bean级别的方法】【构造器】调用Person的构造器实例化");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(" ====【Bean级别的方法】【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(" ====【Bean级别的方法】【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = name;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(" ====【Bean级别的方法】【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" ====【Bean级别的方法】【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    public void myInit() {
        System.out.println(" ====【Bean级别的方法】【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    public void myDestroy() {
        System.out.println("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【注入属性】注入属性name");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        System.out.println("【注入属性】注入属性address");
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        System.out.println("【注入属性】注入属性phone");
        this.phone = phone;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(" ====【Bean级别的方法】【ApplicationContext接口】调用ApplicationContextAware.setApplicationContext()");
    }
}
