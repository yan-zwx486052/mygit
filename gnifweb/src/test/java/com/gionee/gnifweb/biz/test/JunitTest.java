package com.gionee.gnifweb.biz.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gionee.gnifweb.biz.model.Student;
import com.gionee.gnifweb.integration.dao.IStuDao;
import com.gionee.gnifweb.web.util.StringUtil;

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
//		Integer id = 1;
//
//		Student student = stuDao.getStuById(id);
//		System.err.println("---" + student.getName());
//		System.err.println("---" + student.getPhone());
//		System.err.println("---" + student.getId());

		String name = "小南";
		String phone = "15936632318";
		Student stu = new Student();
		stu.setName(name);
		stu.setPhone(phone);

		String name1 = "小绿";
		String phone1 = "15936632318";
		Student stu1 = new Student();
		stu1.setName(name1);
		stu1.setPhone(phone1);
//		int result = stuDao.add(stu1);

		List<Student> listStu = new ArrayList<Student>();

		if (StringUtil.isEmpty(name) || StringUtil.isEmpty(phone) || StringUtil.isEmpty(stu1.getName())
				|| StringUtil.isEmpty(stu1.getPhone()))
		{
			throw new RuntimeException("Invalid parameter. ");
		}

		listStu.add(stu);
		listStu.add(stu1);
		System.err.println(null == stu1);
		stuDao.addStus(listStu);

//		System.err.println("result---" + result);
	}
}
