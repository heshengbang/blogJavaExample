package com.hsb.java.blog;
        /*
         * Copyright ©2011-2017 hsb
         */

import com.hsb.java.blog.mybatis.entity.User;
import com.hsb.java.blog.mybatis.interfaces.IUserDao;
import com.hsb.java.blog.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class InsertTest {
    public static void main(String[] args) {
        insertSingleData();
    }

    private static void insertSingleData() {
        try (SqlSession session = MybatisUtil.getSession()) {
            if (session != null) {
                IUserDao userDao = session.getMapper(IUserDao.class);
                User user = new User();
                user.setName("Google");
                user.setDept("Tech");
                user.setWebsite("http://www.google.com");
                user.setPhone("120");
                userDao.insertUser(user);
                session.commit();
                // 显示插入之后User信息
                QueryTest.queryByProperty(user);
                System.out.println("Test insert finished...");
            }
        }
    }
}
