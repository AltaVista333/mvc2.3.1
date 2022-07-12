package web.config;

import java.util.Objects;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class PersistenceConfig {

    private final ApplicationContext applicationContext;

    public PersistenceConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private Environment enviroment() {
        return applicationContext.getEnvironment();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
            = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan("web.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(enviroment().getProperty(
            "db.driver")));
        dataSource.setUrl(enviroment().getProperty("db.url"));
        dataSource.setUsername(enviroment().getProperty("db.username"));
        dataSource.setPassword(enviroment().getProperty("db.password"));
        return dataSource;
    }

    @Bean
    Properties additionalProperties() {
        Properties props = new Properties();
        props.put("hibernate.show_sql",
            enviroment().getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto",
            enviroment().getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.format_sql",
            enviroment().getProperty("hibernate.format_sql"));
        return props;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
