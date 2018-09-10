package com.gionee.gnifweb.biz.model;

//import com.gionee.gnif.model.base.BusinessObject;

/**
 * 实体类(模型层)
 * @author zhang
 *
 */
public class Student{
	private Integer id;
	private String name;
	private String phone;

	public Student(Student student) {
		setId(student.getId());
		setName(student.getName());
		setPhone(student.getPhone());
	}

	public Student(){}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
