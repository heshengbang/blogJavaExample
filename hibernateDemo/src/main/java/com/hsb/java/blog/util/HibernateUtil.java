package com.hsb.java.blog.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil() {

    }

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public static Session getNewSession() {
        return sessionFactory.openSession();
    }

    public static void add(Object entity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getNewSession();
            transaction = session.getTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void update(Object entity) {
        Session session = null;
        Transaction transaction= null;

        try {
            session = HibernateUtil.getNewSession();
            transaction = session.getTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void delete(Object entity) {
        Session session = null;
        Transaction transaction= null;

        try {
            session = HibernateUtil.getNewSession();
            transaction = session.getTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Object get(Class clazz, Serializable id) {
        Session session = null;
        try {
            session = HibernateUtil.getNewSession();
            return session.get(clazz, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
