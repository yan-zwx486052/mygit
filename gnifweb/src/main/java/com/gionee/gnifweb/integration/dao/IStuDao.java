package com.gionee.gnifweb.integration.dao;

import com.gionee.gnifweb.biz.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 接口
 * @author zhang
 *
 */
@Service("stuDao")
public interface IStuDao {
	int add(Student stu);

	void addStus(List<Student> list);
	
	int del(Integer id);
	
	int update(Student stu);//在更新操作时，返回值应该是 int 型数据类型。该值表示当前方法执行后影响的数据库行数。
	
	List<Student> getStus();
	
	Student getStuById(Integer id);

	List<Student> getPage(Map<String,Object> map);

	Integer totalCount(Map<String,Object> map);
}
