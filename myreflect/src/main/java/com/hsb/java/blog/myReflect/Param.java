package com.hsb.java.blog.myReflect;
        /*
         * Copyright ©2011-2017 hsb
         */

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Param<T1, T2> {

    class A {}
    class B extends A {}

    private Class<T1> entityClass;
    public Param (){
        Type type = getClass().getGenericSuperclass();
        System.out.println("getClass() == " + getClass());
        System.out.println("type = " + type);
        System.out.println(type.getTypeName() + "'s rawtype is " + ((ParameterizedType)type).getRawType());
        Type trueType = ((ParameterizedType)type).getActualTypeArguments()[0];
        System.out.println("trueType1 = " + trueType);
        trueType = ((ParameterizedType)type).getActualTypeArguments()[1];
        System.out.println("trueType2 = " + trueType);
        this.entityClass = (Class<T1>)trueType;
        System.out.println("entityClass = " + entityClass);

        B t = new B();
        type = t.getClass().getGenericSuperclass();

        System.out.println("B is A's super class :" + ((ParameterizedType)type).getActualTypeArguments().length);
    }

}
