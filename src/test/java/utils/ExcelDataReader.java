package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelDataReader {
    private static final DataFormatter formatter = new DataFormatter();
    private static Workbook workbook = null;
    private static String validUserName;
    private static String validPassword;

    private ExcelDataReader() { }

    public static void ReadTestData(String filePath) {
        try {
            InputStream fileResourceInputStream = ExcelDataReader.class.getResourceAsStream(filePath);
            workbook = new XSSFWorkbook(fileResourceInputStream);
            fileResourceInputStream.close();
        } catch (Exception e) {
            LoggerFactory.getLogger().error("Error while reading data {} file - {}", filePath, e.getMessage());
        }
    }

    public static void getValidCredentials() {
        String sheetName = "login_valid";
        Object[][] testData = getDataBySheet(sheetName);

        setValidUserName((String) testData[0][0]);
        setValidPassword((String) testData[0][1]);
    }

    private static void setValidUserName(String userName) {
        validUserName = userName;
    }

    public static String getValidUserName() {
        return validUserName;
    }

    private static void setValidPassword(String password) {
        validPassword = password;
    }

    public static String getValidPassword() {
        return validPassword;
    }

    public static Object[][] getDataBySheet(String sheetName) {
        Object[][] data = null;

        try {
            Sheet sheet = workbook.getSheet(sheetName);
            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[noOfRows - 1][noOfCols]; // skip header row

            for (int i = 1; i < noOfRows; i++) {
                Row row = sheet.getRow(i);

                for (int j = 0; j < noOfCols; j++) {
                    String value = formatter.formatCellValue(sheet.getRow(i).getCell(j)).trim();
                    data[i - 1][j] = value;
                }
            }

            workbook.close();
        } catch (Exception e) {
            LoggerFactory.getLogger().error("Error while reading data from sheet {} - {}", sheetName, e.getMessage());
        }

        return data;
    }
    public ExcelDataReader(String filePath) {
        try {
            InputStream fileResourceInputStream = getClass().getResourceAsStream(filePath);
            workbook = WorkbookFactory.create(fileResourceInputStream);
        } catch (Exception e) {
            LoggerFactory.getLogger().error("Error while reading data {} file - {}", "/testData/"+ "TestData.xlsx", e.getMessage());
        }
    }

    public static Workbook getWorkBook() {
        return workbook;
    }
    
    public static String getCellData(String sheetName, int rowNumber, int cellNumber) {
		Sheet sheet = workbook.getSheet(sheetName);

		if (sheet == null) {
			throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in the Excel file.");
		}

		Row row = sheet.getRow(rowNumber);
		if (row == null) {
			throw new IllegalArgumentException("Row " + rowNumber + " does not exist in the sheet.");
		}

		Cell cell = row.getCell(cellNumber);
		if (cell == null) {
			throw new IllegalArgumentException(
					"Cell at row " + rowNumber + ", column " + cellNumber + " does not exist.");
		}

		return cell.getStringCellValue();
	}

	public static void close() throws IOException {
		workbook.close();
	}
	
	// Method to get the total number of rows in a sheet
    public static int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist.");
        }
        return sheet.getLastRowNum(); // Returns the zero-based index of the last row
    }
    
    // Method to get the total number of columns in the first row of the sheet
    public static int getColumnCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet " + sheetName + " does not exist.");
        }
        
        // Get the first row (usually contains headers)
        Row firstRow = sheet.getRow(0); // The first row is at index 0
        if (firstRow == null) {
            return 0; // No columns if there is no row
        }
        
        // Returns the number of cells in the first row
        return firstRow.getPhysicalNumberOfCells();
    }
    public static Object[][] loadDataFromExcelForDataProvider(String sheetName) {
	    try {
	    	ExcelDataReader reader = new ExcelDataReader("/testData/" + "TestData.xlsx");
	        int rowCount = 0;
			try {
				rowCount = ExcelDataReader.getRowCount(sheetName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        int colCount = ExcelDataReader.getColumnCount(sheetName);

	        Object[][] data = new Object[rowCount][1]; // Each row is a Map

	        for (int i = 1; i <= rowCount; i++) { // Assuming row 0 is the header
	            Map<String, String> rowData = new HashMap<>();
	            for (int j = 0; j < colCount; j++) {
	                String columnName = ExcelDataReader.getCellData(sheetName, 0, j); // Column header
	                String cellValue = ExcelDataReader.getCellData(sheetName, i, j); // Cell value
	                rowData.put(columnName, cellValue);
	            }
	            data[i - 1][0] = rowData; // Each row in the Object[][] array contains a Map
	        }
	        ExcelDataReader.close();
	        return data;
	    } catch (IOException e) {
	        LoggerFactory.getLogger().error("Failed to read data from Excel");
	        throw new RuntimeException("Failed due to IO error", e);
	    }
	}
    
}
