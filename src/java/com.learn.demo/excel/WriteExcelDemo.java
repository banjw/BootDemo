package com.learn.demo.excel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class WriteExcelDemo {
    public static void main(String[] args){
        try {
            // 创建工作薄 xlsx
            XSSFWorkbook xssWorkbook = new XSSFWorkbook();
            // 创建工作表
            XSSFSheet sheet = xssWorkbook.createSheet("sheet1");

            for (int row = 0; row < 10; row++)
            {
                XSSFRow rows = sheet.createRow(row);
                for (int col = 0; col < 10; col++)
                {
                    // 向工作表中添加数据
                    rows.createCell(col).setCellValue("data" + row + col);
                }
            }
            File xlsFile = new File("D:/workspace/JAVA/BootDemo/file/excel/export.xlsx");
            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
            xssWorkbook.write(xlsStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
