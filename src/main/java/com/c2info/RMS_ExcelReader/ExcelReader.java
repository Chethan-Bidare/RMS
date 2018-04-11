package com.c2info.RMS_ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	
	public String path ;
	XSSFWorkbook wb ;
	XSSFSheet sheet ;
	XSSFRow Row ;
	XSSFCell cell ;
	FileInputStream fis ;
	
	public ExcelReader(String path){
		
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
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public String[][] getdatafromSheet(String SheetName,String ExcelName){
		String DataSets[][]=null ;
		sheet = wb.getSheet(SheetName);
		int TotalRows = sheet.getLastRowNum()+1 ;
		int TotalCols = sheet.getRow(0).getLastCellNum();
		
		DataSets = new String [TotalRows -1][TotalCols];
		
		for(int i=1;i<TotalRows ;i++){
			Row = sheet.getRow(i);
			for(int j=0; j<TotalCols; j++){
				cell = Row.getCell(j);
				
				if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
					String cellText = String.valueOf(cell.getBooleanCellValue());
					 DataSets[i-1][j] = cellText ;
				}
				else if (cell.getCellType()==cell.CELL_TYPE_STRING){
					DataSets[i-1][j]=cell.getStringCellValue();
				}
				else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
					DataSets[i-1][j]= String.valueOf(cell.getNumericCellValue());
				}
			}
			
		}
		
		return DataSets;
		
		
	}
	
	
	
	
}
