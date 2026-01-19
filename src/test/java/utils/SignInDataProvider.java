
package utils;

import static utils.Constants.TEST_DATA_FILE_NAME1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class SignInDataProvider {
	private static final String VALID_LOGIN_SHEET = "validLogin";
    private static final String INVALID_LOGIN_SHEET = "invalidLogin";
    
	public static Object[][] loadDataFromExcelForDataProvider(String sheetName) {
	    try {
	    	DataReader reader = new DataReader("/testData/" + TEST_DATA_FILE_NAME1);
	        int rowCount = 0;
			try {
				rowCount = DataReader.getRowCount(sheetName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        int colCount = DataReader.getColumnCount(sheetName);

	        Object[][] data = new Object[rowCount][1]; // Each row is a Map

	        for (int i = 1; i <= rowCount; i++) { // Assuming row 0 is the header
	            Map<String, String> rowData = new HashMap<>();
	            for (int j = 0; j < colCount; j++) {
	                String columnName = DataReader.getCellData(sheetName, 0, j); // Column header
	                String cellValue = DataReader.getCellData(sheetName, i, j); // Cell value
	                rowData.put(columnName, cellValue);
	            }
	            data[i - 1][0] = rowData; // Each row in the Object[][] array contains a Map
	        }
	        DataReader.close();
	        return data;
	    } catch (IOException e) {
	        LoggerFactory.getLogger().error("Failed to read data from Excel");
	        throw new RuntimeException("Failed due to IO error", e);
	    }
	}
	 @DataProvider(name = "validLoginDataProvider")
	    public static Object[][] validLoginDataProvider() throws IOException {
	        Object[][] data = loadDataFromExcelForDataProvider(VALID_LOGIN_SHEET);
	        return data;
	    }
	    
	 @DataProvider(name = "invalidLoginDataProvider")
	    public static Object[][] invalidLoginDataProvider() throws IOException {
	        Object[][] data = loadDataFromExcelForDataProvider(INVALID_LOGIN_SHEET);
	        return data;       
	    }
	
}