package com.learn.demo.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReadExcelDemo {
    public static void main(String[] args) {
        Workbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String, String>> listMap = null;
        String cellData = null;
        String filePath = "E:\\fiberhome\\java\\workspace\\Boot\\BootDemo\\file\\excel\\import.xlsx";
//        String columns[] = {"号码","经度","纬度","所属区县","详细地址（仅供参考）"};
        workbook = readExcel(filePath);
        if(workbook != null){
            //用来存放表中数据
            listMap = new ArrayList<Map<String,String>>();
            //获取第一个sheet
            sheet = workbook.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                String[] colnums = new String[colnum];
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        colnums[j] = cellData;
                    }
                    String join = String.join(",", colnums).replaceAll("\t","");
                    System.out.println(join);
                }else{
                    break;
                }
                listMap.add(map);
            }
        }
        //遍历解析出来的list
//        for (Map<String,String> map : listMap) {
//            for (Map.Entry<String,String> entry : map.entrySet()) {
//                System.out.print(entry.getKey()+":"+entry.getValue()+",");
//            }
//            System.out.println();
//        }

    }
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook workbook = null;
        if(filePath==null){
            return null;
        }
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(filePath.endsWith(".xls")){
                return workbook = new HSSFWorkbook(is);
            }else if(filePath.endsWith(".xlsx")){
                return workbook = new XSSFWorkbook(is);
            }else{
                return workbook = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }
}
