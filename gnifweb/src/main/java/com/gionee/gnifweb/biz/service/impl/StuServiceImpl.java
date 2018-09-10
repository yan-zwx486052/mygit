package com.gionee.gnifweb.biz.service.impl;

import com.gionee.gnifweb.biz.model.Student;
import com.gionee.gnifweb.biz.service.IStuService;
import com.gionee.gnifweb.integration.dao.IStuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

//import com.gionee.gnif.GnifException;

/**
 * 应用层（服务接口）实现类
 * @author zhang
 *
 */
@Service
public class StuServiceImpl implements IStuService{
	//注入dao层
	@Autowired
	private IStuDao stuDao;

	//添加操作
	@Override
	@Transactional//标注事务完成提交动作
	public int save(Student stu) {
//		if(stuDao.update(stu)!=1){
//			throw new GnifException("更新用户信息失败，该数据已经由其他操作员更新！");
//		}
		return stuDao.add(stu);
	}

	//批量添加操作
	@Override
	public void saveStus(List<Student> list) {
		stuDao.addStus(list);
	}

	//删除
	@Override
	public void delete(Integer id) {
		stuDao.del(id);
	}

	//修改
	@Override
	public int change(Student stu) {
		return stuDao.update(stu);
	}

	//多行查询
	@Override
	public List<Student> queryStus() {
		List<Student> stus=stuDao.getStus();
		return stus;
	}

	//单行查询
	@Override
	public Student getStu(Integer id) {
		return stuDao.getStuById(id);
	}

	//分页
	@Override
	public List<Student> queryForPage(Map<String, Object> map) {
		List<Student> userList = stuDao.getPage(map);
		return userList;
	}

	//总记录数
	@Override
	public Integer getTotalCount(Map<String,Object> map) {
		return stuDao.totalCount(map);
	}
}
