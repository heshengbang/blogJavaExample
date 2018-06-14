package com.hsb.java.blog;

import com.hsb.java.blog.mybatis.entity.User;
import com.hsb.java.blog.mybatis.interfaces.IUserDao;
import com.hsb.java.blog.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Unit test for simple App.
 */

public class QueryTest {
    public static void main(String[] args) {
        querySingleData();
        System.out.println("=======================================================================");
        queryListData();
        System.out.println("=======================================================================");
        testQueryByProperty();
    }

    public static void queryListData() {
        try (SqlSession session = MybatisUtil.getSession()) {
            if (session != null) {
                IUserDao userDao = session.getMapper(IUserDao.class);
                List<User> list = userDao.getUserList();
                for (User user: list) {
                    System.out.println("名字：" + user.getName());
                    System.out.println("所属部门：" + user.getDept());
                    System.out.println("主页：" + user.getWebsite());
                }
            }
        }
    }

    public static void querySingleData() {
        try (SqlSession session = MybatisUtil.getSession()) {
            if (session != null) {
                IUserDao userDao = session.getMapper(IUserDao.class);
                User user = userDao.getUserByID(1);
                System.out.println("名字：" + user.getName());
                System.out.println("所属部门：" + user.getDept());
                System.out.println("主页：" + user.getWebsite());
            }
        }
    }

    public static void testQueryByProperty() {
        User user = new User();
        user.setName("GOOGLE");
        queryByProperty(user);
    }

    public static void queryByProperty(User user) {
        try (SqlSession session = MybatisUtil.getSession()) {
            if (session != null) {
                IUserDao userDao = session.getMapper(IUserDao.class);
                List<User> results = userDao.getUserByProperties(user);
                if (results != null) {
                    for (User it: results) {
                        System.out.println("名字：" + it.getName());
                        System.out.println("所属部门：" + it.getDept());
                        System.out.println("主页：" + it.getWebsite());
                    }
                }
            }
        }
    }
}
