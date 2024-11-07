package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilitiy {
	
	public String[] getExcelData() throws IOException {
		XSSFRow row;
		String destination = System.getProperty("user.dir")+"\\src\\test\\resources\\HackatonData.xlsx";
		FileInputStream file = new FileInputStream(destination);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Input");
		int totalRowCount = sheet.getPhysicalNumberOfRows();
		int totalColCount = sheet.getRow(0).getPhysicalNumberOfCells();
		String[] data = new String[totalRowCount-1];
		for(int i=0;i<totalRowCount-1;i++) {
			row = sheet.getRow(i+1);
			for(int j=0;j<totalColCount;j++) {
				XSSFCell cell= row.getCell(j);
				switch (cell.getCellType()) {
				case STRING:
					data[i] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i] = String.valueOf(cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i] = String.valueOf(cell.getBooleanCellValue());
					break;
				default: data[i]="";
					break;
				}
			}
		}
		return data;
	}
	
	public static void setExcelUtility(List<String> filteredHospital,List<String> Cities) {
		try {
			FileOutputStream file = new FileOutputStream("C:\\Users\\2361446\\Downloads\\HackathonProject\\HackathonProject\\test data\\HackathonProjectOutput.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet1 = workbook.createSheet("OutPut 1");
				XSSFRow Row = sheet1.createRow(0);
				Row.createCell(0).setCellValue("SL.NO");
				Row.createCell(1).setCellValue("Hospitals with all facilities");
				for(int i=1;i<=filteredHospital.size();i++) {
					XSSFRow row = sheet1.createRow(i);
					row.createCell(0).setCellValue(i);
					row.createCell(1).setCellValue(filteredHospital.get(i-1));
				}
				XSSFSheet sheet2 = workbook.createSheet("OutPut 2");
				XSSFRow Row0 = sheet2.createRow(0);
				Row0.createCell(0).setCellValue("SL.NO");
				Row0.createCell(1).setCellValue("All the Cities");
				for(int i=1;i<=Cities.size();i++) {
					XSSFRow row = sheet2.createRow(i);
					row.createCell(0).setCellValue(i);
					String value = Cities.get(i-1);
					row.createCell(1).setCellValue(value);
				}
				workbook.write(file);
				workbook.close();
				file.close();
					
					 
			} catch (IOException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
}
