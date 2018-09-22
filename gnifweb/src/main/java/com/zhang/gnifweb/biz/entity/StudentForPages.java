package com.zhang.gnifweb.biz.entity;

import java.util.List;

import com.zhang.gnifweb.biz.model.Student;
import com.zhang.gnifweb.web.util.PageBean;

/**
 * 
 * @ClassName: StudentForPages
 * @Description: <一句話功能簡述>
 * @author 乐
 * @date 2018年9月12日 下午11:59:35
 *
 */
public class StudentForPages
{
	private Student stu;
	private PageBean<?> pageBean;
	private List<Student> userList;
	private int pageSize;

	public Student getStu()
	{
		return stu;
	}

	public void setStu(Student stu)
	{
		this.stu = stu;
	}

	public PageBean<?> getPageBean()
	{
		return pageBean;
	}

	public void setPageBean(PageBean<?> pageBean)
	{
		this.pageBean = pageBean;
	}

	public List<Student> getUserList()
	{
		return userList;
	}

	public void setUserList(List<Student> userList)
	{
		this.userList = userList;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

}
