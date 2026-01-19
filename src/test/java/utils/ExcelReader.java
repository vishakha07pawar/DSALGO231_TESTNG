package utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelReader {
	
	public String[][] getExcelData(String sheetName) throws Exception {
		File excelFile = new File("./src/test/resources/testData/TestData.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int noOfRows = sheet.getPhysicalNumberOfRows();
		int noOfColumns = sheet.getRow(0).getLastCellNum();

		String[][] data = new String[noOfRows - 1][noOfColumns];
		for (int i = 0; i < noOfRows - 1; i++) {
			for (int j = 0; j < noOfColumns; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i + 1).getCell(j));
			}
		}
		workbook.close();
		fis.close();
		return data;

	}
	@DataProvider(name = "registerWithValidData")
	public String[][] getRegisterValidData() throws Exception{
	
		return getExcelData("Register_valid");
		
	}
	@DataProvider(name = "VerifyRegisterationWithInvalid")
	public String[][] getRegisterInValidData() throws Exception{
		
		return getExcelData("Register_invalid");
		
	}
}

