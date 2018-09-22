package com.zhang.gnifweb.integration.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhang.gnifweb.biz.model.Student;
import com.zhang.gnifweb.integration.dao.IStuDao;
import com.zhang.gnifweb.integration.dao.mybatis.IStuDaoMybatis;
import com.zhang.gnifweb.web.util.StringUtil;

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
	private static final Logger LOG = LogManager.getLogger(StuDaoImpl.class);

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
			LOG.error("Invalid parameter. ");
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
			LOG.error("Invalid parameter. ");
			System.err.println("Invalid parameter. ");
			throw new IllegalArgumentException("Invalid parameter. ");
		}
		return stuDaoMybatis.getStuById(id);
	}

	@Override
	public List<Student> getPage(Map<String, Object> map)
	{
		return stuDaoMybatis.queryForPage(map);
	}

	@Override
	public Integer totalCount(Map<String, Object> map)
	{
		return stuDaoMybatis.getTotalCount(map);
	}

}
