package com.zhang.gnifweb.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.common.i18n.Exception;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.gnifweb.biz.model.Student;
import com.zhang.gnifweb.biz.service.ExportExcel;
import com.zhang.gnifweb.biz.service.IStuService;
import com.zhang.gnifweb.biz.service.impl.ImportService;
import com.zhang.gnifweb.web.util.PageBean;
import com.zhang.gnifweb.web.util.ResponseUtil;
import com.zhang.gnifweb.web.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: StuController
 * @Description: <展现层>
 * @author 乐
 * @date 2018年9月12日 下午11:15:05
 *
 */
@Controller
@RequestMapping("stu")
public class StuController
{
	private static final Logger LOG = LogManager.getLogger(StuController.class);

	@Autowired
	private IStuService stuService;

	@Autowired
	private ImportService importService;

	ExportExcel<T> excelService = new ExportExcel<T>();

	/**
	 * 
	 * @Title: save
	 * @Description: <添加操作>
	 * @param stu
	 * @return
	 */
	@RequestMapping(value = "/stu_save.json", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView save(Student stu)
	{
		if (null == stu)
		{
			LOG.error("Invalid parameter. ");
			throw new IllegalArgumentException("Invalid parameter. ");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("stus", stuService.save(stu));

		// 返回一個ModelAndView对象
		return new ModelAndView("success", data);
	}

	@RequestMapping(value = "/stu_list.json")
	@ResponseBody
	public String listPage(Student stu, Integer page, Integer rows, HttpServletResponse res) throws Exception
	{
		if (null == stu)
		{
			LOG.error("Invalid parameter. ");
			throw new IllegalArgumentException("Invalid parameter. ");
		}

		try
		{
			PageBean<Object> pageBean = new PageBean<Object>(page, rows);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", StringUtil.formatLike(stu.getName()));
			map.put("phone", StringUtil.formatLike(stu.getPhone()));
			map.put("start", pageBean.getStart());// 起始页
			map.put("size", pageBean.getPageSize());// 当前页

			List<Student> userList = stuService.queryForPage(map);// 获取显示列表数据
			Integer total = stuService.getTotalCount(map);// 获取总记录数

			JSONObject result = new JSONObject();
			JSONArray jsonArray = JSONArray.fromObject(userList);
			result.put("rows", jsonArray);
			result.put("total", total);
			// res.sendRedirect(UrlBasedViewResolver.FORWARD_URL_PREFIX +
			// "/view/stu_list.html");

			ResponseUtil.write(res, result);

		} catch (java.lang.Exception e)
		{
			LOG.error("Unknown error infomation is : {}", e);
		}
		return null;
	}

	// 显示学生信息（返回map）
//	@RequestMapping(value = "/stu_list.json")
//	@ResponseBody
//	public Map listPage() throws Exception
//	{
//		userList = stuService.queryStus();// 获取显示列表数据
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("rows", userList);
//		return map;
//	}

	/**
	 * 
	 * @Title: delete
	 * @Description: <删除操作 value = "/{ids}/stu_delete.json"传递id>
	 * @param ids
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{ids}/stu_delete.json", method = RequestMethod.POST)
	@ResponseBody
	// 这时的paramId可通过 @Pathvariable注解绑定它传过来的值到方法的参数上。
	public String delete(@PathVariable String ids, HttpServletResponse res) throws Exception
	{
		if (StringUtil.isEmpty(ids))
		{
			LOG.error("Invalid parameter. ");
			throw new IllegalArgumentException("Invalid parameter. ");
		}

		try
		{
			String[] idStr = ids.split(",");// 截取字符串
			JSONObject result = new JSONObject();
			for (String id : idStr)
			{
				stuService.delete(Integer.parseInt(id));
			}
			result.put("success", true);
			ResponseUtil.write(res, result);
		} catch (java.lang.Exception e)
		{
			LOG.error("Unknown error infomation is : {}", e);
		}
		return null;
	}

	/**
	 * 
	 * @Title: update
	 * @Description: <修改操作>
	 * @param ids
	 * @param stu
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{ids}/stu_update.json", method = RequestMethod.POST)
	public String update(@PathVariable String ids, Student stu, HttpServletResponse res) throws Exception
	{
		if (null == stu)
		{
			LOG.error("Invalid parameter. ");
			throw new IllegalArgumentException("Invalid parameter. ");
		}

		if (StringUtil.isEmpty(ids))
		{
			LOG.error("Invalid parameter. ");
			throw new IllegalArgumentException("Invalid parameter. ");
		}

		try
		{
			String[] idStr = ids.split(",");// 截取字符串
			JSONObject result = new JSONObject();
			for (String id : idStr)
			{
				stu.setId(Integer.parseInt(id));
			}
			stuService.change(stu);
			result.put("success", stuService.change(stu));
			ResponseUtil.write(res, result);

		} catch (java.lang.Exception e)
		{
			LOG.error("Unknown error infomation is : {}", e);
		}

		return null;
	}

	/**
	 * 
	 * @Title: exportSheets
	 * @Description: <导出学生信息>
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/stu_export.json", params = "method=sheetsExport")
	public void exportSheets(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		// Student student = new Student();
		List<Student> list = stuService.queryStus();
		Map<String, Object> map = new HashMap<>();
		for (Student student : list)
		{
			map.put(String.valueOf(student.getId()), student);
//			map = (Map<String, Object>) student;
		}
		String headers[] = new String[]
		{ "编号", "姓名", "电话" };
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = format.format(new Date().getTime()) + ".xls";
		res.setContentType("application/ms-excel;charset=UTF-8");
		OutputStream out = null;
		try
		{
			out = res.getOutputStream();
			res.setHeader("Content-Disposition",
					"attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
			excelService.exportExcel(headers, map, out);

			LOG.info("Export infomation success. ");
			System.out.println("success");
		} catch (UnsupportedEncodingException e)
		{
			LOG.error("Encoding exception infomation is : {}", e);
		} catch (IOException e)
		{
			LOG.error("IOException infomation is : {}", e);
		} finally
		{
			if (null != out)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					LOG.error("IOException infomation is : {}", e);
				}
			}
		}
	}

	/**
	 * 
	 * @Title: importExcels
	 * @Description: <导入学生信息>
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/stu_importExcel.json", method = RequestMethod.POST)
	public String importExcels(@RequestParam MultipartFile file) throws Exception
	{
		if (null == file)
		{
			LOG.error("Invalid parameter. ");
			throw new IllegalArgumentException("Invalid parameter. ");
		}
		try
		{
			List<Student> list = importService.getAllByExcel(file.getInputStream());
			System.out.println("list::" + list.size() + ",内容：" + list.get(0).getName());
			stuService.saveStus(list);
		} catch (IOException e)
		{
			LOG.error("IOException infomation is : {} ", e);
		}
		return null;
	}
}
