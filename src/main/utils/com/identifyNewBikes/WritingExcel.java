package com.identifyNewBikes;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class WritingExcel {
	
		public static XSSFWorkbook workbook;
		
		/***********************************************************************************
		 * Following method is used to write 'Upcoming Honda Bikes' in the 'Excel Sheet'
		 ***********************************************************************************/
		
		public static void writeExcel(ArrayList<String> arr) {
			//XSSFWorkbook
			workbook = new XSSFWorkbook();
			//XSSFSheet
			XSSFSheet sheet = workbook.createSheet("Upcoming Honda Bikes");
			//Writing the data
			Row row1 = sheet.createRow(0);
			Cell cell1 = row1.createCell(0);
			cell1.setCellValue("Upcoming Honda Bikes");
			int count = 0;
			for(int i = 1;i<=arr.size();i++) {
				
				Row row = sheet.createRow(i);
				Cell cell = row.createCell(0);
				cell.setCellValue(arr.get(count++));
			}
			try {
				FileOutputStream file = new FileOutputStream("src\\test\\resources\\ExcelFiles\\Bikes.xlsx");
				workbook.write(file);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

	}



