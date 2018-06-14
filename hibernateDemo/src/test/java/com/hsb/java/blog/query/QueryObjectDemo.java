package com.hsb.java.blog.query;

import com.hsb.java.blog.entity.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Iterator;

public class QueryObjectDemo {
    public static void main(String[] args) {
        // 新建configuration实例对象，调用configure()方法对其进行装配
        Configuration configuration = new Configuration().configure();
        // 根据configuration实例获取SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // 从SessionFactory中获取一个session实例
        Session session = sessionFactory.getCurrentSession();
        // 在session中开始一次事务，只有对数据库的更新(add/update/delete)操作才需要事务
        // 事务是对数据库最基本的操作单元，四大特性是ACID
        session.beginTransaction();
        String sql = "from " + Department.class.getName();
        // 在session中创建一次查询
        Query query = session.createQuery(sql);
        // 设置查询结果的偏移量offset
        query = query.setFirstResult(0);
        // 设置查询结果的数量limit
        query = query.setMaxResults(10);
        // 执行查询
        Iterator<Department> employees = query.iterate();
        while (employees.hasNext()) {
            System.out.println(employees.next().getDeptName());
        }
        // 同样只有更新操作才需要事务
        session.getTransaction().commit();
        // 关闭Session，一级缓存失效
        session.close();
        // 关闭SessionFactory，二级缓存和查询缓存失效
        sessionFactory.close();
    }
}
