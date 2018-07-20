package com.c2info.RMS_ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.c2info.RMS_TestBase.TestBase;

public class ExcelWriter extends TestBase{

	String path ;
	XSSFWorkbook wb ;
	XSSFSheet sheet ;
	XSSFRow row ;
	XSSFCell cell ;
	FileInputStream fis ;
	
	
	public ExcelWriter(String path){
		this.path = path ;
		
		try {
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void writeDataIntoExcel(String sheetName,String excelName,String testName,String testResult) throws IOException{
		
		sheet = wb.getSheet(sheetName);
		
		int totalRows = sheet.getLastRowNum()+1 ;
		FileOutputStream fos = new FileOutputStream(path);
		for(int i=1;i<totalRows; i++){
			row = sheet.getRow(i);
			cell = row.getCell(1);
			if(cell.getStringCellValue().equalsIgnoreCase(testName)){
				sheet.getRow(i).createCell(2).setCellValue(testResult);
				wb.write(fos);
				wb.close();
			}
		}
	}
	
}
