package com.excel.manager.util;

import com.excel.manager.pojo.TbJob;
import com.excel.util.EntityReflectionUtils;
import com.excel.util.JobChineseEnglishFieldTranslator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {

    public static List<TbJob> readExcel(InputStream inputStream) throws IOException {
        List<TbJob> departmentList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        HashMap<Integer, String> titleMap = new HashMap<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            TbJob department = new TbJob();
            Iterator<Cell> cellIterator = row.cellIterator();
            if (row.getRowNum() == 0) {
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    // 用map将列名和序号对应
                    titleMap.put(cell.getColumnIndex(), JobChineseEnglishFieldTranslator.translate(cell.getStringCellValue()));
                }
                continue; // 跳过第一行
            }
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                // 根据序号找到列名并通过反射插入到对象
                EntityReflectionUtils.setFieldValue(department, titleMap.get(cell.getColumnIndex()), cell.getStringCellValue());
            }
//            System.out.println(department);
            departmentList.add(department);
        }
        workbook.close();
        inputStream.close();
        return departmentList;
    }
}