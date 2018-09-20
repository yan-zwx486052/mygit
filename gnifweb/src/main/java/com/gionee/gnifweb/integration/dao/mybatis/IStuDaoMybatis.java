package com.gionee.gnifweb.integration.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gionee.gnifweb.biz.model.Student;

/**
 * 
 * @ClassName: IStuDaoMybatis
 * @Description: <一句話功能簡述>
 * @author 乐
 * @date 2018年9月17日 下午6:54:00
 *
 */
public interface IStuDaoMybatis
{
	Student getStuById(@Param("id") Integer id);

	int add(Student stu);

	void saveStus(List<Student> list);

	int delete(Integer id);

	int update(Student stu);

	List<Student> queryStus();
}
