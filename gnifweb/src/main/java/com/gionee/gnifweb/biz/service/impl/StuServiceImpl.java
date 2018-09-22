package com.gionee.gnifweb.biz.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gionee.gnifweb.biz.model.Student;
import com.gionee.gnifweb.biz.service.IStuService;
import com.gionee.gnifweb.integration.dao.IStuDao;

//import com.gionee.gnif.GnifException;

/**
 * 
 * @ClassName: StuServiceImpl
 * @Description: <应用层（服务接口）实现类>
 * @author 乐
 * @date 2018年9月12日 下午11:57:33
 *
 */
@Service
public class StuServiceImpl implements IStuService
{
	private static final Logger LOG = LogManager.getLogger(StuServiceImpl.class);

	// 注入dao层
	@Autowired
	private IStuDao stuDao;

	// 添加操作
	@Override
	@Transactional // 标注事务完成提交动作
	public int save(Student stu)
	{
		if (stuDao.add(stu) != 1)
		{
			LOG.error("更新用户信息失败，该数据已经由其他操作员更新！");
			throw new RuntimeException("更新用户信息失败，该数据已经由其他操作员更新！");
		}
		return stuDao.add(stu);
	}

	// 批量添加操作
	@Override
	public void saveStus(List<Student> list)
	{
		stuDao.addStus(list);
	}

	// 删除
	@Override
	public void delete(Integer id)
	{
		int result = stuDao.del(id);
		if (result != 1)
		{
			LOG.error("删除用户信息失败，该数据已经由其他操作员更新！");
			throw new RuntimeException("删除用户信息失败，该数据已经由其他操作员更新！");
		}
	}

	// 修改
	@Override
	public int change(Student stu)
	{
		return stuDao.update(stu);
	}

	// 多行查询
	@Override
	public List<Student> queryStus()
	{
		List<Student> stus = stuDao.getStus();
		return stus;
	}

	// 单行查询
	@Override
	public Student getStu(Integer id)
	{
		return stuDao.getStuById(id);
	}

	// 分页
	@Override
	public List<Student> queryForPage(Map<String, Object> map)
	{
		if (null == map || map.isEmpty())
		{
			LOG.error("Invalied parameter. ");
			throw new IllegalArgumentException("Invalied parameter. ");
		}
		return stuDao.getPage(map);
	}

	// 总记录数
	@Override
	public Integer getTotalCount(Map<String, Object> map)
	{
		if (null == map || map.isEmpty())
		{
			LOG.error("Invalied parameter. ");
			throw new IllegalArgumentException("Invalied parameter. ");
		}
		return stuDao.totalCount(map);
	}
}
