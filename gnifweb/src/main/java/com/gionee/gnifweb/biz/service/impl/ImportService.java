package com.gionee.gnifweb.biz.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.gionee.gnifweb.biz.model.Student;

/**
 * Created by 乐 on 2017/4/6.
 */
@Service
public class ImportService {
    public List<Student> getAllByExcel(InputStream fis){
       List<Student> list = new ArrayList<Student>();
        Student stu = null;
        try{
            HSSFWorkbook hwb = new HSSFWorkbook(fis);
            HSSFSheet sheet = hwb.getSheetAt(0);
            HSSFRow row = null;
            //DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i<hwb.getNumberOfSheets(); i++){
                sheet = hwb.getSheetAt(i);
                for (int j = 1;j<sheet.getPhysicalNumberOfRows();j++){
                    row = sheet.getRow(j);
                    stu = new Student();
                    stu.setId(0);
                    stu.setName(row.getCell(1).toString());
                    stu.setPhone(row.getCell(2).toString());
                }
            }
            list.add(stu);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
