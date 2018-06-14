package com.hsb.java.blog.persist;/*
 * Copyright ©2011-2016 hsb
 */

import com.hsb.java.blog.entity.Department;
import com.hsb.java.blog.entity.Employee;
import com.hsb.java.blog.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ClearDemo {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = factory.getCurrentSession();
        Employee emp;
        Department dept;
        try {
            session.getTransaction().begin();

            // It is an object has Persistent status.
            emp = DataUtils.findEmployee(session, "E7499");
            dept = DataUtils.findDepartment(session, "D10");


            // clear() evicts all the objects in the session.
            session.clear();


            // Now 'emp' & 'dept' has Detached status
            // ==> false
            System.out.println("- emp Persistent? " + session.contains(emp));
            System.out.println("- dept Persistent? " + session.contains(dept));

            // All change on the 'emp' will not update
            // if not reattach 'emp' to session
            emp.setEmpNo("NEW");

            dept = DataUtils.findDepartment(session, "D20");
            System.out.println("Dept Name = " + dept.getDeptName());

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }  finally {
            session.close();
            factory.close();
        }
    }
}
