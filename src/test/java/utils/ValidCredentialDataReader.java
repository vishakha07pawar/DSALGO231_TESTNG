package utils;

import java.util.Map;

public class ValidCredentialDataReader {

    private static String validUserName;
    private static String validPassword;

    public static void getValidCredentialsFromExcelData() {

        String sheetName = "login_valid";
        String scenarioName = "valid username and valid password";

        Map<String, String> testData = ExcelUtils.getDataByScenario(sheetName, scenarioName);

        setValidUserName(testData.get("Username"));
        setValidPassword(testData.get("Password"));
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
}
