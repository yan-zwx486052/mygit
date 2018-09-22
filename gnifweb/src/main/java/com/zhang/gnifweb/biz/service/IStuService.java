package com.zhang.gnifweb.biz.service;

import java.util.List;
import java.util.Map;

import com.zhang.gnifweb.biz.model.Student;

/**
 * 
 * @ClassName: IStuService
 * @Description: <服务接口>
 * @author 乐
 * @date 2018年9月12日 下午11:57:59
 *
 */
public interface IStuService
{
	int save(Student stu);

	void saveStus(List<Student> list);

	void delete(Integer id);

	int change(Student stu);

	List<Student> queryStus();

	Student getStu(Integer id);

	List<Student> queryForPage(Map<String, Object> map);

	Integer getTotalCount(Map<String, Object> map);
}
