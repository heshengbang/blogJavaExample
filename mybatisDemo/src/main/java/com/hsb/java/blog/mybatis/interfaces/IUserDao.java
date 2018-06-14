package com.hsb.java.blog.mybatis.interfaces;
        /*
         * Copyright ©2011-2017 hsb
         */

import com.hsb.java.blog.mybatis.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserDao {
    @Select("select * from user where id= #{id}")
    User getUserByID(int id);

    @Select("select * from user")
    List<User> getUserList();

    @Insert("insert into user(name,dept,website,phone) values(#{name},#{dept},#{website},#{phone})")
    void insertUser(User user);

    @Update("update user set name=#{name}, dept=#{dept}, website=#{website}, phone=#{phone} where id=#{id}")
    void updateUser(User user);

    @Delete("delete from user where id = #{id}")
    void deleteUser(int userId);

    @Select("select * from user where id= #{id}")
    User getUser(int id);

    @Select("select * from user where id=#{id} or name=#{name} or dept=#{dept} or website=#{website} or phone=#{phone}")
    List<User> getUserByProperties(User user);
}
