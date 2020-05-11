package com.enquero.TestNGListerner;

import com.aventstack.extentreports.Status;
import com.enquero.driverfactory.web.WebDriverFactory;
import com.enquero.reporter.ExtentTestReporter;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {


    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Test Suite Finished: "+suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Running Test Method: "+result.getMethod().getMethodName());
        ExtentTestReporter.startTest(result.getMethod().getMethodName());
        ExtentTestReporter.getTest().log(Status.INFO,"Execution of "+result.getMethod().getMethodName()+" STARTED ");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Executed  "+result.getMethod().getMethodName()+" Succesfully .....");
        ExtentTestReporter.getTest().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String path = null;
        System.out.println("Executed  "+result.getMethod().getMethodName()+" failed .....");
        String testMethodName = result.getName().trim();
//        try {
//             path=ExtentTestReporter.getScreenshot(WebDriverFactory.getDriverinstance(),testMethodName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ExtentTestReporter.getTest().info(("An exception occurred while taking screenshot " + e.getCause()));
//        }
//        String tag= "<a href="+"\""+path+"\""+"target='_blank'"+">"+"Click here"+"</a";
//        System.out.print("tag name: "+tag);
//        ExtentTestReporter.getTest().log(Status.FAIL,"Test Case failed and screenshot attached: "+tag);
        final Throwable error= result.getThrowable();
        final String message= ExtentTestReporter.getCustomStackTrace(error);
        String tag1= "<a href=\"javascript:window.alert('"+message+"');\">Click here</a>";
        ExtentTestReporter.getTest().log(Status.FAIL,"Logs generated for failed step: "+tag1);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestReporter.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("***** Test "+context.getName()+" started *******");

    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("***** Test "+context.getName()+" Ended *******");
        ExtentTestReporter.endTest();
    }

    @Override
    public void onStart(ISuite suite) {
        System.out.println("***** Test Suite "+suite.getName()+" started *******");
    }
}
