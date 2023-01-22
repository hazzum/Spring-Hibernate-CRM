package com.hazzum.CRMDemo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.hazzum.CRMDemo.entity.Customer;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.hazzum.CRMDemo"})
@PropertySource("classpath:persistence.properties")
public class Config {
	
	@Autowired
	private Environment env;

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
		
		// create connection pool
		ComboPooledDataSource myDataSource
									= new ComboPooledDataSource();
				
		// set the jdbc driver class
		
		try {
			myDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		
		// set database connection props
		
		myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		myDataSource.setUser(env.getProperty("jdbc.user"));
		myDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		
		myDataSource.setInitialPoolSize(
				getIntProperty("connection.pool.initialPoolSize"));

		myDataSource.setMinPoolSize(
				getIntProperty("connection.pool.minPoolSize"));

		myDataSource.setMaxPoolSize(
				getIntProperty("connection.pool.maxPoolSize"));

		myDataSource.setMaxIdleTime(
				getIntProperty("connection.pool.maxIdleTime"));
		return myDataSource;
	}
	
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		factoryBean.setHibernateProperties(properties);
		factoryBean.setAnnotatedClasses(Customer.class);
		return factoryBean;

	}
	
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
	
	// need a helper method 
		// read environment property and convert to int
		
		private int getIntProperty(String propName) {
			
			String propVal = env.getProperty(propName);
			
			// now convert to int
			int intPropVal = Integer.parseInt(propVal);
			
			return intPropVal;
		}
	
	
}
