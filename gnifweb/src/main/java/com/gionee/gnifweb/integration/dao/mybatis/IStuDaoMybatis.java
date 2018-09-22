package com.gionee.gnifweb.integration.dao.mybatis;

import java.util.List;
import java.util.Map;

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

	int delete(@Param("id") Integer id);

	int update(Student stu);

	List<Student> queryStus();

	List<Student> queryForPage(Map<String, Object> map);

	Integer getTotalCount(Map<String, Object> map);
}
