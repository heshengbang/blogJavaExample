package com.hsb.java.blog.mybatis.util;
        /*
         * Copyright ©2011-2017 hsb
         */

import com.hsb.java.blog.mybatis.interfaces.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("Configure.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.getConfiguration().addMapper(IUserDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static SqlSession getSession() {
        if (sqlSessionFactory != null) {
            return sqlSessionFactory.openSession();
        }
        return null;
    }
}