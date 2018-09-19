package com.gionee.gnifweb.biz.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gionee.gnifweb.biz.model.Student;
import com.gionee.gnifweb.integration.dao.IStuDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath*:/biz-context.xml", "classpath*:/mybatis-config.xml", "classpath*:/web-context.xml" })
public class JunitTest
{
	@Autowired
	private IStuDao stuDao;

	@Test
	public void getStuById()
	{
		Integer id = 1;

		Student student = stuDao.getStuById(id);
		System.err.println("---" + student.getName());
		System.err.println("---" + student.getPhone());
		System.err.println("---" + student.getId());
	}

//	@Test
//	public void test()
//	{
//		Integer id = 1;
//		Student student = getStuById(id);
//	}
}
