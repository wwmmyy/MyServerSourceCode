//package com.dist.service.impl;
//
//import static org.junit.Assert.fail;
//
//import org.junit.Test;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.dist.service.UserService;
//
//public class UserServiceImplTest {
//	
//	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans-client.xml","applicationContext.xml" });
//	UserService client = (UserService) context.getBean("userServiceClinet");
//
//	@Test
//	public void testFindUser() {
//		String response = client.sayHi("Joe");
//		System.out.println("Response: " + response);
//		System.out.println(client.findUser("from User").size());
//	}
//
//	@Test
//	public void testSaveUser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSayHi() {
//		String response = client.sayHi("Joe");
//		System.out.println("Response: " + response);
//		System.out.println(client.findUser("from User").size());
//	}
//
//	@Test
//	public void testSetUserDao() {
//		fail("Not yet implemented");
//	}
//
//}
