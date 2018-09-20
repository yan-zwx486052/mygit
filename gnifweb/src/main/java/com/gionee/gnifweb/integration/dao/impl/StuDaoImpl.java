package com.gionee.gnifweb.integration.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gionee.gnifweb.biz.model.Student;
import com.gionee.gnifweb.integration.dao.IStuDao;
import com.gionee.gnifweb.integration.dao.mybatis.IStuDaoMybatis;
import com.gionee.gnifweb.web.util.StringUtil;

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
		return stuDaoMybatis.add(stu);
	}

	@Override
	public void addStus(List<Student> list)
	{
		stuDaoMybatis.saveStus(list);
	}

	@Override
	public int del(Integer id)
	{
		if (StringUtil.isEmpty(String.valueOf(id)))
		{
			System.err.println("Invalid parameter. ");
			throw new IllegalArgumentException("Invalid parameter. ");
		}
		return stuDaoMybatis.delete(id);
	}

	@Override
	public int update(Student stu)
	{
		return stuDaoMybatis.update(stu);
	}

	@Override
	public List<Student> getStus()
	{
		return stuDaoMybatis.queryStus();
	}

	@Override
	public Student getStuById(Integer id)
	{
		if (StringUtil.isEmpty(String.valueOf(id)))
		{
			System.err.println("Invalid parameter. ");
			throw new IllegalArgumentException("Invalid parameter. ");
		}
		return stuDaoMybatis.getStuById(id);
	}

	@Override
	public List<Student> getPage(Map<String, Object> map)
	{
		return null;
	}

	@Override
	public Integer totalCount(Map<String, Object> map)
	{
		return null;
	}

}
