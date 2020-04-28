package com.enquero.TestNGListener;

import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Test SUite Finished: "+suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Case Started: "+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Case Success: "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Case Failure: "+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Context Start: "+context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Context Finish: "+context.getName());
    }

    @Override
    public void onStart(ISuite suite) {
    System.out.println("Test SUite Started: "+suite.getName());
    }
}
