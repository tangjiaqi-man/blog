package com.hcjava.Test;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.support.SessionFlashMapManager;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.hcjava.dao.UserDao;
import com.hcjava.pojo.User;

public class TestCost {
//BasicDataSource
	@SuppressWarnings("resource")
	// SessionFlashMapManager
	// SqlSessionFactoryBean
	// MapperScannerConfigurer
	// InternalResourceViewResolver
	@Test
	public void testDao() {
		String[] cfg = { "conf/spring-mvc.xml", "conf/spring-mybatis.xml" };
		ApplicationContext ac = new ClassPathXmlApplicationContext(cfg);
		UserDao bean = ac.getBean("userDao", UserDao.class);
		User user = bean.findByName("shenfeiyu");
		System.out.println(user.getCn_user_password());
	}
}
