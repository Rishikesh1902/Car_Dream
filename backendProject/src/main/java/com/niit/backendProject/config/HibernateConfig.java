package com.niit.backendProject.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.niit.backendProject.model.Author;
import com.niit.backendProject.model.BillingAddress;
import com.niit.backendProject.model.Cart;
import com.niit.backendProject.model.Category;
import com.niit.backendProject.model.Customer;
import com.niit.backendProject.model.CustomerOrder;
import com.niit.backendProject.model.Product;

import com.niit.backendProject.model.ShippingAddress;
import com.niit.backendProject.model.Supplier;
import com.niit.backendProject.model.User;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages ="com.niit.backendProject")
public class HibernateConfig {
	@Bean("dataSource")
	public DataSource geth2DataSource()
	{
	DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
		dataSource.setUsername("h2");
		dataSource.setPassword("h2");
		return dataSource;
		
	}
	@Autowired
	@Bean("sessionFactory")
	public  SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionFactory=new LocalSessionFactoryBuilder(dataSource);
		
		sessionFactory.addAnnotatedClass(User.class);
		sessionFactory.addAnnotatedClass(Supplier.class);
		
		sessionFactory.addAnnotatedClass(Product.class);
		sessionFactory.addAnnotatedClass(Category.class);
		sessionFactory.addAnnotatedClass(Author.class);
		sessionFactory.addAnnotatedClass(BillingAddress.class);
		
		sessionFactory.addAnnotatedClass(ShippingAddress.class);
		sessionFactory.addAnnotatedClass(Customer.class);
		sessionFactory.addAnnotatedClass(CustomerOrder.class);

		sessionFactory.addAnnotatedClass(Cart.class);
		sessionFactory.addProperties(getHibernateProperties());
		
		return sessionFactory.buildSessionFactory();
		
	}
	public Properties getHibernateProperties()
	{

		Properties properties=new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto","update");
		return properties;
	}
	@Autowired
	@Bean
	public HibernateTransactionManager geTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}


		}
