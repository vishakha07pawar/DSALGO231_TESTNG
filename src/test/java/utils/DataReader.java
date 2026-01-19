package utils;


import org.apache.poi.ss.usermodel.*;

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
}


