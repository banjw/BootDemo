package com.learn.demo.csv;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.*;

public class Csv2Xls {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\Ban\\运营商&广电等资料");
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                String filePath = file1.getAbsolutePath();
                if(filePath.endsWith(".csv")){
//        String filePath = "C:\\Users\\Administrator\\Desktop\\Ban\\运营商&广电等资料\\DIANXIN.csv";
//        String filePath = "C:\\Users\\Administrator\\Desktop\\Ban\\运营商&广电等资料\\GUANGDIAN.csv";
//        String filePath = "C:\\Users\\Administrator\\Desktop\\Ban\\运营商&广电等资料\\LIANTONG.csv";
//        String filePath = "C:\\Users\\Administrator\\Desktop\\Ban\\运营商&广电等资料\\YW_SHH_LDY.csv";
//                    String filePath = "C:\\Users\\Administrator\\Desktop\\Ban\\运营商&广电等资料\\YW_SHH_SHOUJITONGXUNLU.csv";
                    InputStream is = null;
                    long starTime = System.currentTimeMillis();
                    try {
                        HSSFWorkbook workbook = new HSSFWorkbook();
                        Sheet sheet = workbook.createSheet();
//            XSSFWorkbook xssWorkbook = new XSSFWorkbook();
                        // 创建工作表
//            XSSFSheet xssfSheet = xssWorkbook.createSheet();
                        is = new FileInputStream(filePath);
                        InputStreamReader isr = new InputStreamReader(is,"unicode");
                        BufferedReader br = new BufferedReader(isr);
                        String line = null;
                        Row row = null;
//            XSSFRow row = null;
                        //字段名
                        String[] titles = null;
                        int i = 1;
                        int rowNum = 0;
                        int blankNum = 0;
                        while (blankNum < 100000){
                            line = br.readLine();
                            if(!StringUtils.isEmpty(line)){
                                String[] rows = line.replaceAll("\"","").split(",");
//                Row row = sheet.createRow(i);
                                row = sheet.createRow(rowNum);
                                if(rowNum == 0){
                                    if(i == 1){
                                        titles = rows;
                                    }
                                    for (int col = 0; col < titles.length; col++)
                                    {
                                        // 向工作表中写入title
                                        row.createCell(col).setCellValue(titles[col]);
                                    }
                                }else {
                                    for (int col = 0; col < rows.length; col++)
                                    {
                                        // 向工作表中添加数据
                                        row.createCell(col).setCellValue(rows[col]);
                                    }
                                }
                                rowNum++;
                                if((i>= 50000) && (i % 50000 == 0)){//50000条数据写一次文件
                                    String newFilePath = filePath.replace("运营商&广电等资料", "out").replace(".csv", String.format("_%d.xls", i/50000));
                                    File xlsFile = new File(newFilePath);
                                    FileOutputStream xlsStream = new FileOutputStream(xlsFile);
                                    workbook.write(xlsStream);
                                    xlsStream.flush();
                                    xlsStream.close();
                                    //重新新建一个文件
                                    workbook = new HSSFWorkbook();
                                    sheet = workbook.createSheet();
                                    rowNum = 0;
                                    System.out.println(String.format("成功写入50000条数据，生成第%d个文件", i/50000));
                                    System.out.println(String.format("其中空行数有%d条", blankNum));
                                }
                                i++;
                            }else {
                                blankNum++;
                            }
                        }
                        if(rowNum > 0){
                            //最后一次不满50000条数据写文件
                            String newFilePath = filePath.replace("运营商&广电等资料", "out").replace(".csv", String.format("_%d.xls", i/50000 +1));
                            File xlsFile = new File(newFilePath);
                            FileOutputStream xlsStream = new FileOutputStream(xlsFile);
                            workbook.write(xlsStream);
                            xlsStream.flush();
                            xlsStream.close();
                            System.out.println(String.format("成功写入%d条数据，生成第%d个文件",i%50000, i/50000 +1));
//            xssWorkbook.write(xlsStream);
                        }
                        System.out.println(String.format("耗时%d ms", System.currentTimeMillis() - starTime));
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        try{
                            if (null != is){
                                is.close();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
