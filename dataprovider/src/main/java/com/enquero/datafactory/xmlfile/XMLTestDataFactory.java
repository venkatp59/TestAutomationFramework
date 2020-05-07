package com.enquero.datafactory.xmlfile;

import org.json.simple.JSONObject;

public class XMLTestDataFactory {

    private String testCaseId;
    private String testCaseName;
    private JSONObject inputParameters;
    private JSONObject validationParameters;


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

    public JSONObject getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(JSONObject inputParameters) {
        this.inputParameters = inputParameters;
    }

    public JSONObject getValidationParameters() {
        return validationParameters;
    }

    public void setValidationParameters(JSONObject validationParameters) {
        this.validationParameters = validationParameters;
    }


}
