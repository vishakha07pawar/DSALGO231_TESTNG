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

    // Data driven tests
    @DataProvider(name = "TryEditorData")
    public static Object[][] getData(Method method) {
            return ExcelDataReader.getDataBySheet("TryEditorPage_Data");
    }

}
