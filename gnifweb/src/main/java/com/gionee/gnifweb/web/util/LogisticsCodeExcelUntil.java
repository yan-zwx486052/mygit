package com.gionee.gnifweb.web.util;

import com.gionee.gnifweb.biz.model.Student;
import org.apache.poi.hssf.usermodel.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 乐 on 2017/3/30.
 */
public class LogisticsCodeExcelUntil {
    public static HSSFWorkbook getLogisticsCode(List<Student> listCode){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("物流码信息");

        //创建表头
        HSSFRow row = sheet.createRow(0);// 创建第一行
        ExcelTital depoTital = new ExcelTital();
        HSSFCell cell = null;
        for (int i = 0; i < depoTital.getTITAL_List().size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(new HSSFRichTextString(depoTital.getTITAL_List().get(i)));
        }
        for (int i = 0; i < listCode.size(); i++) {
            Student code = listCode.get(i);
            row = sheet.createRow(i + 1);// 创建第i+1行

            cell = row.createCell(0);// 序号
            cell.setCellValue(code.getId());

            cell = row.createCell(1);// 姓名
            cell.setCellValue(code.getName());

            cell = row.createCell(2);// 联系电话
            cell.setCellValue(code.getPhone());

        }
        return workbook;
    }

    public static String getNowDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new java.util.Date());
    }
}
/**
 * @author echosoft2
 * 表头信息
 */
class ExcelTital{
    private final static     String id ="序号";
    private final static     String name ="姓名";
    private final static    String phone="联系电话";
    private ArrayList<String> TITAL_List = new ArrayList<String>();

    public ExcelTital() {
        TITAL_List.add(id);
        TITAL_List.add(name);
        TITAL_List.add(phone);
    }
    public ArrayList<String> getTITAL_List() {
        return TITAL_List;
    }
}
