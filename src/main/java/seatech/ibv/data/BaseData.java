package seatech.ibv.data;

import com.univocity.parsers.annotations.Parsed;

public class BaseData {
    @Parsed(field = "Test Case ID", defaultNullRead = "")
    private String testCaseId;


    public String getGetTestCaseDescription() {
        return getTestCaseDescription;
    }

    public void setGetTestCaseDescription(String getTestCaseDescription) {
        this.getTestCaseDescription = getTestCaseDescription;
    }
//    }

    @Parsed(field = "Test Case Description", defaultNullRead = "")
    private String getTestCaseDescription;

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    @Parsed(field = "Condition", defaultNullRead = "")
    private String condition;

    public String getCondition() {
        return condition;
    }

}
