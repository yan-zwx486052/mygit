package com.gionee.gnifweb.biz.entity;

import java.util.List;

import com.gionee.gnifweb.biz.model.Student;
import com.gionee.gnifweb.web.util.PageBean;

/**
 * 
 * @author 乐
 *
 */
public class StudentForPages {
	private Student stu;
	private PageBean<?> pageBean;
	private List<Student> userList;
	private int pageSize;
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	public PageBean<?> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<?> pageBean) {
		this.pageBean = pageBean;
	}
	public List<Student> getUserList() {
		return userList;
	}
	public void setUserList(List<Student> userList) {
		this.userList = userList;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
