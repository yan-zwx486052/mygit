package com.gionee.gnifweb.integration.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gionee.gnifweb.biz.model.Student;
import com.gionee.gnifweb.integration.dao.IStuDao;
import com.gionee.gnifweb.integration.dao.mybatis.IStuDaoMybatis;

/**
 * 
 * @ClassName: StuDaoImpl
 * @Description: <一句話功能簡述>
 * @author 乐
 * @date 2018年9月17日 下午6:54:13
 *
 */
@Repository("stuDao")
public class StuDaoImpl implements IStuDao
{
	@Autowired
	private IStuDaoMybatis stuDaoMybatis;

	@Override
	public int add(Student stu)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addStus(List<Student> list)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int del(Integer id)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Student stu)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> getStus()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStuById(Integer id)
	{
		// TODO Auto-generated method stub
		return stuDaoMybatis.getStuById(id);
	}

	@Override
	public List<Student> getPage(Map<String, Object> map)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer totalCount(Map<String, Object> map)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
