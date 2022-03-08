package edu.happydev.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "edu.happydev" })
@EnableJpaRepositories(basePackages = { "edu.happydev.repository" })
@PropertySource(value = { "classpath:application.properties" })
public class JpaConfig {

    @Value("${database.driver}")
    private String dbDriver;

    @Value("${database.url}")
    private String dbUrl;

    @Value("${database.username}")
    private String dbUsername;

    @Value("${database.password}")
    private String dbPwd;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] { "edu.happydev.model", "edu.happydev.entity" });
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(additionalProperties());
        return entityManagerFactoryBean;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "none");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.default_schema", "facebook");
        return hibernateProperties;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPwd);
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}