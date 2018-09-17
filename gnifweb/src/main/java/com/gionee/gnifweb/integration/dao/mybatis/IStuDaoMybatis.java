package com.gionee.gnifweb.integration.dao.mybatis;

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
}
