package com.hsb.java.blog;
        /*
         * Copyright ©2011-2017 hsb
         */

import com.hsb.java.blog.mybatis.interfaces.IUserDao;
import com.hsb.java.blog.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class DeleteTest {
    public static void main(String[] args) {
        deleteSingleData();
    }

    private static void deleteSingleData() {
        try (SqlSession session = MybatisUtil.getSession()) {
            if (session != null) {
                IUserDao userDao = session.getMapper(IUserDao.class);
                userDao.deleteUser(11);
                session.commit();
                // 显示插入之后User信息
                QueryTest.queryListData();
                System.out.println("Test insert finished...");
            }
        }
    }
}
