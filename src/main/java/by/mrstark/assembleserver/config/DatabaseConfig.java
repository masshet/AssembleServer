package by.mrstark.assembleserver.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mrstark on 13.3.16.
 */

@Configuration
@EnableJpaRepositories("by.mrstark.assembleserver.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("by.mrstark.assembleserver")
public class DatabaseConfig {

    @Resource
    private Environment enviroment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan(enviroment.getRequiredProperty("db.entity.package"));
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(enviroment.getRequiredProperty("db.url"));
        dataSource.setDriverClassName(enviroment.getRequiredProperty("db.driver"));
        dataSource.setUsername(enviroment.getRequiredProperty("db.username"));
        dataSource.setPassword(enviroment.getRequiredProperty("db.password"));

        dataSource.setInitialSize(Integer.parseInt(enviroment.getRequiredProperty("db.initialSize")));
        dataSource.setMinIdle(Integer.parseInt(enviroment.getRequiredProperty("db.minIdle")));
        dataSource.setMaxIdle(Integer.parseInt(enviroment.getRequiredProperty("db.maxIdle")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(enviroment.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(enviroment.getRequiredProperty("db.minEvictableIdleTimeMillis")));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(enviroment.getRequiredProperty("db.testOnBorrow")));
        dataSource.setValidationQuery(enviroment.getRequiredProperty("db.validationQuery"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return jpaTransactionManager;
    }

    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
        try {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
