package com.web3.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.web3.notification.utility.HibernateFactory;

@SpringBootApplication
public class MainClass {
  
  public static void main(final String[] args) {
    HibernateFactory.buildSessionFactory();
    SpringApplication.run(MainClass.class, args);
  }

}
