package com.hsb.java.blog.persist;/*
 * Copyright ©2011-2016 hsb
 */

import com.hsb.java.blog.entity.Department;
import com.hsb.java.blog.entity.Employee;
import com.hsb.java.blog.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/**
 * Created by tongheshang on 2017/11/5.
 * weibo.com/yunshixin
 * https://github.com/tongheshang
 * email: trulyheshengbang@gmail.com
 */
public class PersistDemo {
    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        Department department;
        Employee emp = null;
        try {
            session.getTransaction().begin();

            Long maxEmpId = DataUtils.getMaxEmpId(session);
            Long empId = maxEmpId + 1;

            // Get Persistent object.
            department = DataUtils.findDepartment(session, "D10");

            // Create transient object
            emp = new Employee();
            emp.setEmpId(empId);
            emp.setEmpNo("E" + empId);
            emp.setEmpName("Name " + empId);
            emp.setJob("Coder");
            emp.setSalary(1000f);
            emp.setManager(null);
            emp.setHideDate(new Date());
            emp.setDepartment(department);

            // Using persist(..)
            // Now 'emp' is managed by Hibernate.
            // it has Persistent status.
            // No action at this time with DB.
            session.persist(emp);

            /**
             * attention to this!
             * when we create table employee, we add a foreign key constraint named 'FK75C8D6AE6106A42', this constraint use employee_id for foreign key, it sucks.
             * we want add this emp to employee but employee_id doesn't exist yet, so we insert this emp to database failed and error log is:
             * com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`mydb`.`employee`, CONSTRAINT `FK75C8D6AE6106A42` FOREIGN KEY (`EMP_ID`) REFERENCES `employee` (`EMP_ID`))
             * to resolved this problem we must execute below command in sql command tool.
             * ALTER TABLE employee DROP FOREIGN KEY FK75C8D6AE6106A42;
             * after execute, now we can insert data to database.
             */

            // At this step the data is pushed to the DB.
            // Execute Insert statement.
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            factory.close();
        }

        // After the session is closed (commit, rollback, close)
        // Objects 'emp', 'dept' became the Detached objects.
        // It is no longer in the control of the session.
        System.out.println("Emp No: " + emp.getEmpNo());

    }

}
