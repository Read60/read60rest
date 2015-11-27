package com.read60.rest.hibernate;

import org.hibernate.SessionFactory;  
import org.hibernate.cfg.Configuration;  
import org.hibernate.service.ServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
  
public class HibernateUtil {  
      
    private static final SessionFactory sessionFactory;  
    private static final ServiceRegistry serviceRegistry;  
      
    static {  
        Configuration conf = new Configuration();  
        conf.addAnnotatedClass(com.read60.rest.entity.Student.class);
        conf.addAnnotatedClass(com.read60.rest.entity.Address.class);
        conf.addAnnotatedClass(com.read60.rest.entity.Parent.class);
        conf.addAnnotatedClass(com.read60.rest.entity.Teacher.class);
        conf.addAnnotatedClass(com.read60.rest.entity.School.class);
        conf.addAnnotatedClass(com.read60.rest.entity.ReadLog.class);
        conf.addAnnotatedClass(com.read60.rest.entity.Library.class);
        conf.addAnnotatedClass(com.read60.rest.entity.Book.class);
        conf.addAnnotatedClass(com.read60.rest.entity.Credentials.class);
        conf.configure();  
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();  
        try {  
            sessionFactory = conf.buildSessionFactory(serviceRegistry);  
        } catch (Exception e) {  
            System.err.println("Initial SessionFactory creation failed." + e);  
            throw new ExceptionInInitializerError(e);  
        }         
    }  
      
    public static SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
}  
