package com.web3.notification.utility;

import javax.annotation.Priority;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.web3.notification.model.CategoryModel;

public class HibernateFactory {

  
  public enum Priority {
    HIGH, MEDIUM, LOW;
  }
  private static SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    if (null == sessionFactory) {
      buildSessionFactory();
    }
    return sessionFactory;
  }


  public static void buildSessionFactory() {
    Configuration configuration = new Configuration();

    String jdbcUrl = "jdbc:mysql://" + System.getenv("Host") + "/" + System.getenv("DB_Name");

    configuration.setProperty("hibernate.connection.url", jdbcUrl);
    configuration.setProperty("hibernate.connection.username", System.getenv("User name"));
    configuration.setProperty("hibernate.connection.password", System.getenv("Password"));
    configuration.configure("hibernate.xml");
    configuration.addAnnotatedClass(CategoryModel.class);
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        .applySettings(configuration.getProperties()).build();
    try {
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (HibernateException e) {
      System.err.println("Initial SessionFactory creation failed." + e);
      throw new ExceptionInInitializerError(e);
    }
  }
 
}
