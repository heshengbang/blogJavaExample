package com.hsb.java.blog;
        /*
         * Copyright ©2011-2017 hsb
         */

import com.hsb.java.blog.myReflect.MyClass;
import com.hsb.java.blog.myReflect.MyInvoke;
import com.hsb.java.blog.myReflect.MyTshirt;
import com.hsb.java.blog.myReflect.Param;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassDemo extends Param<MyClass, MyInvoke> {
    public static void main(String[] args) {
        ClassDemo classDemo = new ClassDemo();

        System.out.println("========================================");


        MyTshirt<String> one = new MyTshirt<>();
        one.wear("red");
        System.out.println(comparableClassFor(one));
        stdOut(one);

        System.out.println("========================================");

        MyTshirt second = new MyTshirt();
        second.wear(new MyClass());
        System.out.println(comparableClassFor(second));

        stdOut(second);

        System.out.println(compareComparables(MyTshirt.class, one, second));
    }

    private static void stdOut(MyTshirt one) {
        System.out.println(" instanceof Parameterized " + (one != null));
        if (one != null) {
            System.out.println(" getOwnerType " + one.getOwnerType());
            System.out.println(" getRawType " + one.getRawType());
            Type[] paras = one.getActualTypeArguments();
            if (paras != null) {
                for (Type type: paras) {
                    System.out.println(" getActualTypeArguments " + type);
                }
            }
        }
        System.out.println(" Class  " + one.getClass());
        Type[] interfaces = one.getClass().getGenericInterfaces();
        if (interfaces != null) {
            for (Type type: interfaces) {
                System.out.println(" ==== " + type);
                if (type instanceof ParameterizedType) {
                    System.out.println(" ==== ==== getRawType " + ((ParameterizedType)type).getRawType());
                    System.out.println(" ==== ==== getOwnerType " + ((ParameterizedType)type).getOwnerType());
                    Type[] types = ((ParameterizedType)type).getActualTypeArguments();
                    if (types != null) {
                        for (Type temp: types) {
                            System.out.println(" ==== ==== ==== " + temp.getTypeName());
                        }
                    }
                }
            }
        }
    }

    private static Class<?> comparableClassFor(MyTshirt x) {
        if (x != null) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                for (Type t1 : ts) {
                    if (((t = t1) instanceof ParameterizedType)) {
                        if ((p = (ParameterizedType) t).getRawType() == Comparable.class) {
                            if ((as = p.getActualTypeArguments()) != null) {
                                if (as.length == 1) {
                                    if (as[0] == c) {
                                        return c;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private static int compareComparables(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 : ((Comparable)k).compareTo(x));
    }
}
