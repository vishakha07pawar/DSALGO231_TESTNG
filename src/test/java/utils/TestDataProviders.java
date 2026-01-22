package utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestDataProviders {

    // Data Providers
    @DataProvider(name = "panelNamesDP")
    public static String[][] panelList() {
        return new String[][]{{"Data Structures-Introduction"}, {"Array"}, {"Linked List"}, {"Stack"},
                {"Queue"}, {"Tree"}, {"Graph"}};
    }

    @DataProvider(name = "dropdownNamesDP")
    public static String[][] ddlList() {
        return new String[][]{{"Arrays"}, {"Linked List"}, {"Stack"}, {"Queue"}, {"Tree"}, {"Graph"}};
    }

    @DataProvider(name = "dropdownNavigateDP")
    public static String[][] ddNavigate() {
        return new String[][]{{"Arrays", "array"}, {"Linked List", "linked-list"}, {"Stack", "stack"},
                {"Queue", "queue"}, {"Tree", "tree"}, {"Graph", "graph"}};
    }

    @DataProvider(name = "panelNavigateDP")
    public static String[][] panelNavigate() {
        return new String[][]{{"Data Structures-Introduction", "data-structures-introduction"},
                {"Array", "array"}, {"Linked List", "linked-list"}, {"Stack", "stack"}, {"Queue", "queue"},
                {"Tree", "tree"}, {"Graph", "graph"}};
    }

    @DataProvider(name = "arrayTopics")
    public Object[][] arrayTopicsData() {
        return new Object[][]{{"Arrays in Python", "Arrays in Python", "arrays-in-python"},
                {"Arrays Using List", "Arrays Using List", "arrays-using-list"},
                {"Basic Operations in Lists", "Basic Operations in Lists", "basic-operations-in-lists"}};
    }

    @DataProvider(name = "arrayTopicsurlcheck")
    public Object[][] arrayTopics() {
        return new Object[][]{{"arrays-in-python"}, {"arrays-using-list"}, {"basic-operations-in-lists"}};
    }

    @DataProvider(name = "practiceQuestions")
    public Object[][] practiceQuestionsData() {
        return new Object[][]{{"Search the array"}, {"Max Consecutive Ones"},
                {"Find Numbers with Even Number of Digits"}, {"Squares of a Sorted Array"}};
    }

    @DataProvider(name = "arrayHeaders")
    public Object[][] arrayHeaders() {
        return new Object[][]{{"Arrays in Python"}, {"Arrays Using List"}, {"Basic Operations in Lists"}};
    }

    @DataProvider(name = "stackTopics")
    public Object[][] stackTopicsData() {
        return new Object[][]{{"Operations in Stack", "Operations in Stack", "operations-in-stack"},
                {"Implementation", "Implementation", "implementation"},
                {"Applications", "Applications", "stack-applications"}};
    }

    @DataProvider(name = "stackHeaders")
    public Object[][] stackHeaders() {
        return new Object[][]{{"Operations in Stack"}, {"Implementation"}, {"Applications"}};
    }

    @DataProvider(name = "queuePageLinks")
    String[][] queuePageLink() {
        return new String[][]{{"Implementation of Queue in Python"}, {"Implementation using collections.deque"},
                {"Implementation using array"}, {"Queue Operations"}};
    }

    @DataProvider(name = "queuePageTopicsNavigationLinks")
    String[][] queuePageDirectedLinks() {
        return new String[][]{{"Implementation of Queue in Python", "implementation-lists"},
                {"Implementation using collections.deque", "implementation-collections"},
                {"Implementation using array", "Implementation-array"}, {"Queue Operations", "QueueOp"}};
    }

    @DataProvider(name = "treePageLinks")
    String[][] treePageLinks() {
        return new String[][]{{"Overview of Trees"}, {"Terminologies"}, {"Types of Trees"},
                {"Tree Traversals"}, {"Traversals-Illustration"}, {"Binary Trees"}, {"Types of Binary Trees"},
                {"Implementation in Python"}, {"Binary Tree Traversals"}, {"Implementation of Binary Trees"},
                {"Applications of Binary trees"}, {"Binary Search Trees"}, {"Implementation Of BST"}};
    }

    @DataProvider(name = "treePageTopicsNavigationLinks")
    String[][] treePageRedirectionLinks() {
        return new String[][]{{"Overview of Trees", "overview-of-trees"}, {"Terminologies", "terminologies"},
                {"Types of Trees", "types-of-trees"}, {"Tree Traversals", "tree-traversals"},
                {"Traversals-Illustration", "traversals-illustration"}, {"Binary Trees", "binary-trees"},
                {"Types of Binary Trees", "types-of-binary-trees"},
                {"Implementation in Python", "implementation-in-python"},
                {"Binary Tree Traversals", "binary-tree-traversals"},
                {"Implementation of Binary Trees", "implementation-of-binary-trees"},
                {"Applications of Binary trees", "applications-of-binary-trees"},
                {"Binary Search Trees", "binary-search-trees"},
                {"Implementation Of BST", "implementation-of-bst"}};
    }

    @DataProvider(name = "TopicsForDataStructureIntroductionPage")
    public static String[][] dsTopicList() {
        return new String[][]{{"Time Complexity"},};
    }

    @DataProvider(name = "TopicsForLinkedListPage")
    public static String[][] linkedListTopic() {
        return new String[][]{{"Introduction"}, {"Creating Linked LIst"}, {"Types of Linked List"},
                {"Implement Linked List in Python"}, {"Traversal"}, {"Insertion"}, {"Deletion"}};
    }

    @DataProvider(name = "UrlForLinkedListPageLinks")
    public static String[][] linkedListTopicUrl() {
        return new String[][]{{"Introduction", "introduction"}, {"Creating Linked LIst", "creating-linked-list"},
                {"Types of Linked List", "types-of-linked-list"},
                {"Implement Linked List in Python", "implement-linked-list-in-python"}, {"Traversal", "traversal"},
                {"Insertion", "insertion-in-linked-list"}, {"Deletion", "deletion-in-linked-list"}};

    }

    @DataProvider(name = "TopicsForGraphPage")
    public static String[][] graphTopics() {
        return new String[][]{{"Graph"}, {"Graph Representations"}

        };

    }

    @DataProvider(name = "UrlForGraphPage")
    public static String[][] graphTopicsUrl() {
        return new String[][]{{"Graph", "graph"}, {"Graph Representations", "graph-representations"}

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
    public Object[][] getRegisterInValidData() {
        return ExcelDataReader.getDataBySheet("Register_invalid");
    }

    @DataProvider(name = "validInvalidPythonCode")
    public static Object[][] validInvalidPythonCodeRunDataProvider() {
        return ExcelDataReader.getDataBySheet("TryEditorPage_Data");
    }
}