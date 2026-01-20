package utils;

import org.testng.annotations.DataProvider;

public class DataStructureDataProvider {
	
	 @DataProvider(name = "TopicsForDataStructureIntroductionPage")
	    public static String[][] panelList() {
	        return new String[][]{
	                {"Time Complexity"},
	                };
	    }

}
