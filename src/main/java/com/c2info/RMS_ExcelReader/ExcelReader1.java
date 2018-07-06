package com.c2info.RMS_ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;

import com.c2info.RMS_TestBase.TestBase;

public class ExcelReader1 extends TestBase{

	public String path ;
	XSSFWorkbook wb ;
	XSSFSheet sheet ;
	XSSFCell cell ;
	XSSFRow row ;
	FileInputStream fis ;
	
	public ExcelReader1(String path){
		
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
	
	
	@SuppressWarnings("deprecation")
	public String[][] getDataFromSheet(String excelName,String sheetName){
		
		sheet = wb.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum()+1 ;
		int totalColumns = sheet.getRow(0).getLastCellNum() ;
		String[][] dataSets = null ;
		
		dataSets = new String [totalRows-1][totalColumns];
		
		for(int i=0 ; i<totalRows; i++){
			row = sheet.getRow(i);
			for(int j=0; j<totalColumns; j++){
				cell = row.getCell(j);
				
				if(cell.getCellType()==Cell.CELL_TYPE_STRING){
					dataSets[i-1][j]= cell.getStringCellValue();
				}
				else if(cell.getCellType()== Cell.CELL_TYPE_NUMERIC){
					String cellText = String.valueOf(cell.getNumericCellValue());
					dataSets[i-1][j]= cellText;
				}
				else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
					dataSets[i-1][j]= String.valueOf(cell.getBooleanCellValue());
				}
			}
		}
		return dataSets ;
	}
	
	@AfterTest
	public String[][] writeDataIntoSheet(String excelName,String sheetName){
		sheet = wb.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum();
		int totalColumns = sheet.getRow(0).getLastCellNum();
		return null ;
		
		
	}
}
