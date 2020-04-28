package com.enquero.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

public class ExtentTestReporter {
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReport;
    public static ExtentTest extentTest;


public void initializeModule(){
    htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")
            +"\\reporter\\src\\main\\java\\com\\enquero\\testReport.html");
    extentReport = new ExtentReports();
    extentReport.attachReporter(htmlReporter);
    htmlReporter.config().setDocumentTitle("Extent Report Demo");
    htmlReporter.config().setReportName("Test Report");
    htmlReporter.config().setTheme(Theme.STANDARD);
    htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
}

public void startTest(String testcaseName, String description){
    extentTest= extentReport.createTest(testcaseName,description);
}

    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            extentTest.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            extentTest.skip(result.getThrowable());
        }
    }

    public void endTest(){
        extentReport.flush();
    }


}
