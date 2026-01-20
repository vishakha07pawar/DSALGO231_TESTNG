package utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestDataProviders {

    // Home Page Data Providers
    @DataProvider(name = "panelNamesDP")
    public static String[][] panelList() {
        return new String[][]{
                {"Data Structures-Introduction"},
                {"Array"},
                {"Linked List"},
                {"Stack"},
                {"Queue"},
                {"Tree"},
                {"Graph"}};
    }

    @DataProvider(name = "dropdownNamesDP")
    public static String[][] ddlList() {
        return new String[][]{
                {"Arrays"},
                {"Linked List"},
                {"Stack"},
                {"Queue"},
                {"Tree"},
                {"Graph"}
        };
    }

    @DataProvider(name = "dropdownNavigateDP")
    public static String[][] ddNavigate() {
        return new String[][]{
                {"Arrays", "array"},
                {"Linked List", "linked-list"},
                {"Stack", "stack"},
                {"Queue", "queue"},
                {"Tree", "tree"},
                {"Graph", "graph"}
        };
    }

    @DataProvider(name = "panelNavigateDP")
    public static String[][] panelNavigate() {
        return new String[][]{
                {"Data Structures-Introduction", "data-structures-introduction"},
                {"Array", "array"},
                {"Linked List", "linked-list"},
                {"Stack", "stack"},
                {"Queue", "queue"},
                {"Tree", "tree"},
                {"Graph", "graph"}
        };
    }
    @DataProvider(name = "arrayTopics")
    public Object[][] arrayTopicsData() {
        return new Object[][] {
                { "Arrays in Python", "Arrays in Python", "arrays-in-python" },
                { "Arrays Using List", "Arrays Using List", "arrays-using-list" },
                { "Basic Operations in Lists", "Basic Operations in Lists", "basic-operations-in-lists" }
        };
    }
    @DataProvider(name = "arrayTopicsurlcheck")
    public Object[][] arrayTopics() {
        return new Object[][]{
                {"arrays-in-python"},
                {"arrays-using-list"},
                {"basic-operations-in-lists"}
        };
    }

    @DataProvider(name = "practiceQuestions")
    public Object[][] practiceQuestionsData() {
        return new Object[][] {
                { "Search the array" },
                { "Max Consecutive Ones" },
                { "Find Numbers with Even Number of Digits" },
                { "Squares of a Sorted Array" }
        };
    }
    @DataProvider(name = "arrayHeaders")
    public Object[][] arrayHeaders() {
        return new Object[][]{
                {"Arrays in Python"},
                {"Arrays Using List"},
                {"Basic Operations in Lists"}
        };
    }

    // Data driven tests
    @DataProvider(name = "TryEditorData")
    public static Object[][] getData(Method method) {
        return ExcelDataReader.getDataBySheet("TryEditorPage_Data");
    }
    @DataProvider(name = "validLoginDataProvider")
	public static Object[][] validLoginDataProvider() {
		return ExcelDataReader.getDataBySheet("login_valid");
	}
	@DataProvider(name = "invalidLoginDataProvider")
	public static Object[][] invalidLoginDataProvider() {
		return ExcelDataReader.getDataBySheet("login_invalid");
    }
	@DataProvider(name = "registerWithValidData")
	public Object[][] getRegisterValidData() {
      return ExcelDataReader.getDataBySheet("Register_valid");
    }
	@DataProvider(name = "VerifyRegisterationWithInvalid")
	public Object[][] getRegisterInValidData(){
		return ExcelDataReader.getDataBySheet("Register_invalid");
		
	}
}