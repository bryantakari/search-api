package com.apps.geonames.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class AppsConfig {
    @Bean("extProp")
    public Properties load(){
        try{
            File defaultProperties = new File("assets/config.properties");
            InputStream inputStream;
            if (defaultProperties.exists()){
                inputStream = new FileInputStream(defaultProperties);
            }else{
                //for production location;
                inputStream = new FileInputStream("/apps/config.properties");
            }
            Properties prop = new Properties();
            prop.load(inputStream);
            return prop;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Failed to fetch external properties");
            return null;
        }
    }

    @Bean
    public DataSource dataSource(Properties extProp){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(extProp.getProperty("JDBC_URL"));
        dataSource.setUsername(extProp.getProperty("JDBC_USERNAME"));
        dataSource.setPassword(extProp.getProperty("JDBC_PASSWORD"));
        dataSource.setMaximumPoolSize(15);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.apps.geonames.repository");
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");

        em.setJpaProperties(prop);
        return em;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory
    ){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
