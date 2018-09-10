package com.gionee.gnifweb.biz.service;

import com.gionee.gnifweb.biz.model.Student;

import java.util.List;
import java.util.Map;

/**
 * 服务接口
 * @author zhang
 *
 */
public interface IStuService {
	int save(Student stu);

    void saveStus(List<Student> list);

    void delete(Integer id);
    
    int change(Student stu);

    List<Student> queryStus();

    Student getStu(Integer id);
    
    List<Student> queryForPage(Map<String, Object> map);

    Integer getTotalCount(Map<String, Object> map);
}
