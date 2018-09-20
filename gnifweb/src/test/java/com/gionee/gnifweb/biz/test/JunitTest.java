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

	/**
	 * 
	 * @Title: getStuById
	 * @Description: <通过ID获取学生信息>
	 */
	@Test
	public void getStuById()
	{
		Integer id = 1;

		Student student = stuDao.getStuById(id);
		System.err.println("---" + student.getName());
		System.err.println("---" + student.getPhone());
		System.err.println("---" + student.getId());
	}

	@Test
	public void getStuList()
	{
		List<Student> list = stuDao.getStus();

		for (Student student : list)
		{
			System.err.println("---" + student.getId() + "---" + student.getName() + "---" + student.getPhone());
		}
	}

	/**
	 * 
	 * @Title: saveStudent
	 * @Description: <添加单个学生信息>
	 */
	@Test
	public void saveStudent()
	{
		String name = "小南";
		String phone = "15936632318";
		Student stu = new Student();
		stu.setName(name);
		stu.setPhone(phone);
		int result = stuDao.add(stu);

		System.err.println("result---" + result);
	}

	/**
	 * 
	 * @Title: saveStuList
	 * @Description: <批量添加学生信息>
	 */
	@Test
	public void saveStuList()
	{
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

		List<Student> listStu = new ArrayList<Student>();

		if (StringUtil.isEmpty(name) || StringUtil.isEmpty(phone) || StringUtil.isEmpty(stu1.getName())
				|| StringUtil.isEmpty(stu1.getPhone()))
		{
			throw new IllegalArgumentException("Invalid parameter. ");
		}

		listStu.add(stu);
		listStu.add(stu1);
		System.err.println(null == stu1);
		stuDao.addStus(listStu);

		System.err.println("Add student information success. ");
	}

	/**
	 * 
	 * @Title: deleteStu
	 * @Description: <删除学生信息>
	 */
	@Test
	public void deleteStu()
	{
		Integer id = 1;
		int result = stuDao.del(id);
		System.err.println("The result of delete student process is : " + result);
	}

	/**
	 * 
	 * @Title: updateStu
	 * @Description: <更新学生信息>
	 */
	@Test
	public void updateStu()
	{
		Student student = new Student();
		String name = "大黄蜂";
		String phone = "15933345645";
		Integer id = 2;
		student.setId(id);
		student.setName(name);
		student.setPhone(phone);
		int result = stuDao.update(student);

		System.err.println("The result of update student process is : " + result);
	}
}
