package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

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
}