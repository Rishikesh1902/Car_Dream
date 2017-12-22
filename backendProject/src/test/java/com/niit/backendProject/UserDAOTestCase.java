
package com.niit.backendProject;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backendProject.dao.UserDAO;
import com.niit.backendProject.model.User;

import junit.framework.TestCase;

@SuppressWarnings("unused")
public class UserDAOTestCase extends TestCase {
@Autowired
static AnnotationConfigApplicationContext context;
@Autowired
static UserDAO uDao;
@Autowired
static User user;
@BeforeClass
public static void intialize(){
	context=new AnnotationConfigApplicationContext();
	context.scan("com.niit.backendProject");
	context.refresh();
	uDao=(UserDAO)context.getBean("userDao");
	user=(User)context.getBean("user");
}
@Test
public void createUserTestCase(){
	/*user.setAddress("Faridabad");
user.setMobile((long) 813041);
user.setName("Aash");
*/


assertEquals("createUserTestCase", true);
}
}
