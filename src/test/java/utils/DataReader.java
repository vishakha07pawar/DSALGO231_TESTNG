package utils;


import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DataReader {

    private static Workbook workbook;
    private DataFormatter formatter = new DataFormatter();

    public DataReader(String filename) {
        try {
            InputStream fileResourceInputStream = getClass().getResourceAsStream(filename);
            workbook = WorkbookFactory.create(fileResourceInputStream);
        } catch (Exception e) {
            LoggerFactory.getLogger().error("Error while reading data {} file - {}", filename, e.getMessage());
        }
    }

	public Map<String, String> getDataByScenarioName(String sheetName, String scenarioName) {
		Sheet sheet = workbook.getSheet(sheetName);
        Row headerRow = sheet.getRow(0);
        int scenarioColumnIndex = -1;

		for (Cell cell : headerRow) {
			if ("ScenarioName".equalsIgnoreCase(formatter.formatCellValue(cell))) {
				scenarioColumnIndex = cell.getColumnIndex();
				break;
			}
		}
		if (scenarioColumnIndex == -1) {
			throw new IllegalStateException("ScenarioName column not found");
		}

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null)
				continue;
			String scenarioCellValue = formatter.formatCellValue(row.getCell(scenarioColumnIndex)).trim();
			if (scenarioCellValue.equalsIgnoreCase(scenarioName)) {
				return extractRowData(headerRow, row);
			}
		}
		throw new IllegalArgumentException("Scenario not found in Excel: " + scenarioName);
	}

    private Map<String, String> extractRowData(Row headerRow, Row dataRow) {
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            String header = formatter.formatCellValue(headerRow.getCell(i)).trim();
            String value = formatter.formatCellValue(dataRow.getCell(i)).trim();
            if ("empty".equalsIgnoreCase(value)) {
                value = "";
            }
            data.put(header, value);
        }
        return data;
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
		try {
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

