package com.hsb.java.blog.query;/*
 * Copyright ©2011-2016 hsb
 */

import com.hsb.java.blog.entity.Employee;
import com.hsb.java.blog.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class QueryObjectDemo2 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.getTransaction().begin();

        String sql = "select e from " + Employee.class.getName() + " e " + " where e.department.deptNo=:deptNo ";
        Query query = session.createQuery(sql);
        query.setParameter("deptNo", "D10");

        List<Employee> employees = query.getResultList();
        for (Employee emp : employees) {
            System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
        }
        session.getTransaction().commit();
        sessionFactory.close();
    }
}



















