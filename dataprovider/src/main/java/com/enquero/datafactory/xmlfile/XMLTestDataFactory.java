package com.enquero.datafactory.xmlfile;

import java.util.HashMap;

public class XMLTestDataFactory {

    private String testCaseId;
    private String testCaseName;
    private HashMap<Object, Object> inputParameters;
    private HashMap<Object, Object> validationParameters;


    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public HashMap<Object, Object> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(HashMap<Object, Object> inputParameters) {
        this.inputParameters = inputParameters;
    }

    public HashMap<Object, Object> getValidationParameters() {
        return validationParameters;
    }

    public void setValidationParameters(HashMap<Object, Object> validationParameters) {
        this.validationParameters = validationParameters;
    }


}
