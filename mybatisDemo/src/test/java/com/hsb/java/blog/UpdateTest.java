package com.hsb.java.blog;
        /*
         * Copyright ©2011-2017 hsb
         */

import com.hsb.java.blog.mybatis.entity.User;
import com.hsb.java.blog.mybatis.interfaces.IUserDao;
import com.hsb.java.blog.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UpdateTest {
    public static void main(String[] args) {
        updateSingleData();
    }

    private static void updateSingleData() {
        try (SqlSession session = MybatisUtil.getSession()) {
            if (session != null) {
                IUserDao userDao = session.getMapper(IUserDao.class);
                User user = new User();
                user.setId(11);
                user.setName("GoogleGoogle");
                user.setDept("Tech2");
                user.setWebsite("http://www.google.cn");
                user.setPhone("240");
                userDao.updateUser(user);
                session.commit();
                // 显示插入之后User信息
                QueryTest.queryByProperty(user);
                System.out.println("Test insert finished...");
            }
        }
    }
}
