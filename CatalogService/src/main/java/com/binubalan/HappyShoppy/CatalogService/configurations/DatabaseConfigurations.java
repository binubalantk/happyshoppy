package com.binubalan.HappyShoppy.CatalogService.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class DatabaseConfigurations {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.port}")
    private String port;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver}")
    private String driver;

    @Value("${spring.jpa.generate-ddl}")
    private String generateDDL;

    @Value("${spring.hibernate.dialect}")
    private String dialect;

    @Value("${spring.hibernate.show_sql}")
    private String showSQL;

    @Value("${spring.hibernate.hbm2ddl.auto}")
    private String hbmDDLAuto;

    @Value("${spring.hibernate.entitymanager.packagesToScan}")
    private String packagesToScan;

    public String getUrl() {
        return url;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    public String getGenerateDDL() {
        return generateDDL;
    }

    public String getDialect() {
        return dialect;
    }

    public String getShowSQL() {
        return showSQL;
    }

    public String getHbmDDLAuto() {
        return hbmDDLAuto;
    }

    public String getPackagesToScan() {
        return packagesToScan;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(getDriver());
        driverManagerDataSource.setUrl(getUrl());
        driverManagerDataSource.setUsername(getUsername());
        driverManagerDataSource.setPassword(getPassword());
        return driverManagerDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(getPackagesToScan());

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", getDialect());
        hibernateProperties.put("hibernate.show_sql", getShowSQL());
        hibernateProperties.put("hibernate.hbm2ddl.auto", getHbmDDLAuto());

        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.afterPropertiesSet();
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws IOException {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
