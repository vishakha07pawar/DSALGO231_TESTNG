package utils;


import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

public class DataReader {

    private static Workbook workbook = null ;


    public DataReader(String filePath) {
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
}


