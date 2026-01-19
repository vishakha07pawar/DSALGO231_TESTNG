package utils;

import org.apache.poi.ss.usermodel.*;

import java.util.HashMap;
import java.util.Map;


public class ExcelUtils {

    private static final DataFormatter formatter = new DataFormatter();

    public static Object[][] getDataBySheet(String sheetName) {
        Object[][] data = null;
        Workbook workbook;

        try {
            workbook = DataReader.getWorkBook();
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

            int nonEmptyDataRows = 0;

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                if (!isRowEmpty(sheet.getRow(i))) {
                    ++nonEmptyDataRows;
                }
            }

            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            data = new Object[nonEmptyDataRows - 1][colCount]; // skip header row

            for (int i = 1; i < nonEmptyDataRows; i++) {
                Row row = sheet.getRow(i);

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    String value = formatter.formatCellValue(sheet.getRow(i).getCell(j)).trim();
                    if ("empty".equalsIgnoreCase(value)) {
                        value = "";
                    }
                    data[i - 1][j] = value;
                }
            }
            workbook.close();
        } catch (Exception e) {
            LoggerFactory.getLogger().error("Error while reading data {} file - {}", "/testData/"+ "TestData.xlsx", e.getMessage());
        }

        return data;
    }

    public static Map<String, String> getDataByScenario(String sheetName, String scenarioName) {
        Workbook workbook = DataReader.getWorkBook();
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

    private static boolean isRowEmpty(Row row) {
        if (row == null)
            return true;

        if (row.getLastCellNum() <= 0)
            return true;

        //First cell has ScenarioName
        return row.getCell(0) == null;
    }

    private static Map<String, String> extractRowData(Row headerRow, Row dataRow) {
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
    
}