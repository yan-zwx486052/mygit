package com.gionee.gnifweb.web.controller;

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

import com.gionee.gnifweb.biz.model.Student;
import com.gionee.gnifweb.biz.service.ExportExcel;
import com.gionee.gnifweb.biz.service.IStuService;
import com.gionee.gnifweb.biz.service.impl.ImportService;
import com.gionee.gnifweb.web.util.ResponseUtil;

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
	// 注入服务
	@Autowired
	private IStuService stuService;

	// 指定第二级 URL 地址，该级地址与第一级 URL 地址构成完整的业务响应地址。
	// 如当前响应地址为 /app/save.html，如果客户端发起一个请求为该地址
	// 则由 save 方法进行响应

	ExportExcel<T> excelService = new ExportExcel<>();

	@Autowired
	private ImportService importService;

	/*
	 * //添加操作
	 * 
	 * @RequestMapping(value="/stu_save.json", method =
	 * RequestMethod.POST)//因不再返回页面，故在此注明数据发送方式
	 *
	 * // 使用 ResponseBody指定返回的数据不由视图处理器进行渲染，而由消息转换器将数据作为消息返回给客户端
	 * 
	 * @ResponseBody public Map save11(@Validated Student stu){ //验证stu是否为空...接受数据
	 * System.out.println("进来了吗？+++++++++"+stu);
	 * 
	 */

	/**
	 * 
	 * @Title: save
	 * @Description: <添加操作>
	 * @param stu
	 * @return
	 */
	@RequestMapping(value = "/stu_save.html", method = RequestMethod.POST)
	// 因不再返回页面，故在此注明数据发送方式
	// 使用 ResponseBody 指定返回的数据不由视图处理器进行渲染，而由消息转换器将数据作为消息返回给客户端
	@ResponseBody
	public ModelAndView save(Student stu)
	{
		// 验证stu是否为空...接受数据
		System.out.println("modelandview进来了吗？=========" + stu);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("stus", stuService.save(stu));
		return new ModelAndView("success", data);// 返回一個ModelAndView对象
	}

	// 显示学生信息（不含查询条件）
	/*
	 * @RequestMapping(value = "/stu_list.json")
	 * 
	 * @ResponseBody public String listPage(Integer pageSize, Integer page,
	 * HttpServletResponse res) throws Exception {
	 * System.out.println("123123123123123"); //PageBean pageBean=new
	 * PageBean(page,5); //Map<String,Object> map=new HashMap<String,Object>(); //
	 * map.put("start", pageBean.getStart());//起始页 // map.put("size",
	 * pageBean.getPageSize());//当前页 List<Student>
	 * userList=stuService.queryForPage((page-1)*10,10);//获取显示列表数据 Integer
	 * total=stuService.getTotalCount();//获取总记录数 JSONObject result=new JSONObject();
	 * JSONArray jsonArray=JSONArray.fromObject(userList); result.put("rows",
	 * jsonArray); result.put("total", total); ResponseUtil.write(res, result);
	 * return null; }
	 */

	@RequestMapping(value = "/stu_list.json")
	public String goHome()
	{
		System.out.println("lolololololol");
		return "/stu/stu_list.json";
	}

//	@RequestMapping(value = "/stu_list.html")
//	@ResponseBody
//	public String listPage(Student stu, Integer page, Integer rows, HttpServletResponse res) throws Exception {
//		try {
//			PageBean<Object> pageBean = new PageBean<Object>(page, rows);
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("name", StringUtil.formatLike(stu.getName()));
//			map.put("phone", StringUtil.formatLike(stu.getPhone()));
//			map.put("start", pageBean.getStart());// 起始页
//			map.put("size", pageBean.getPageSize());// 当前页
//			List<Student> userList = stuService.queryForPage(map);// 获取显示列表数据
//			Integer total = stuService.getTotalCount(map);// 获取总记录数
//			JSONObject result = new JSONObject();
//			JSONArray jsonArray = JSONArray.fromObject(userList);
//			result.put("rows", jsonArray);
//			result.put("total", total);
//			res.sendRedirect(UrlBasedViewResolver.FORWARD_URL_PREFIX + "/WEB-INF/view/stu_list.html");
//			ResponseUtil.write(res, result);
//		} catch (java.lang.Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	// 显示学生信息（返回map）
	/*
	 * @RequestMapping(value = "/stu_list.json")
	 * 
	 * @ResponseBody public Map listPage() throws Exception {
	 * System.out.println("=================afasfas");
	 * userList=stuService.queryStus();//获取显示列表数据
	 * System.out.println("======--------"+userList); Map<String,Object> map=new
	 * HashMap<String,Object>(); map.put("rows", userList); return map; }
	 */

	/**
	 * 
	 * @Title: delete
	 * @Description: <删除操作 value = "/{ids}/stu_delete.json"传递id>
	 * @param ids
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{ids}/stu_delete.html", method = RequestMethod.POST)
	@ResponseBody
	// 这时的paramId可通过 @Pathvariable注解绑定它传过来的值到方法的参数上。
	public String delete(@PathVariable String ids, HttpServletResponse res) throws Exception
	{
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
			e.printStackTrace();
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
	@RequestMapping(value = "/{ids}/stu_update.html", method = RequestMethod.POST)
	public String update(@PathVariable String ids, Student stu, HttpServletResponse res) throws Exception
	{
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
			e.printStackTrace();
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
			map = (Map<String, Object>) student;
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
			System.out.println("success");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			System.out.println("error");
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				out.close();
			} catch (IOException e)
			{
				e.printStackTrace();
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
		System.out.println("进来了吗；；；；；？");
		try
		{
			List<Student> list = importService.getAllByExcel(file.getInputStream());
			System.out.println("list::" + list.size() + ",内容：" + list.get(0).getName());
			stuService.saveStus(list);
		} catch (IOException e)
		{
			System.out.println("有异常？");
			e.printStackTrace();
		}
		return null;
	}
}
