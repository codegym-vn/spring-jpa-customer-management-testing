package com.codegym.cms.repository.impl;

import com.codegym.cms.repository.CustomerRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
public class CustomerRepositoryTestConfig {

    @Bean
    public CustomerRepository customerRepository(){
        return new CustomerRepositoryImpl();
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return Mockito.mock(EntityManager.class);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
        return entityManagerFactory;
    }
}
