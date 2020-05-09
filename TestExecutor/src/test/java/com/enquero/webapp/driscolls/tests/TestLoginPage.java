package com.enquero.webapp.driscolls.tests;

import com.aventstack.extentreports.Status;
import com.enquero.Testlogs.GenerateLogs;
import com.enquero.datafactory.DataFactory.TestDataFactory;
import com.enquero.datafactory.xlsfile.ReadXlsFile;
import com.enquero.driverfactory.web.WebDriverFactory;
import com.enquero.reporter.ExtentTestReporter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class TestLoginPage {

    Logger logger= Logger.getLogger(TestLoginPage.class.getName());
    WebDriver driver;
    String path= System.getProperty("user.dir")+System.getProperty("file.separator")+"src\\main\\resources\\testData.xlsx";

    @DataProvider(name="getTestData", parallel=false)
    public Iterator<Object[]> getTestData(Method m) throws IOException {
        ReadXlsFile rd = new ReadXlsFile();
        return rd.getTestData(path,"testcase",m.getName());
    }

    @Parameters({ "browser", "webRunMode" })
    @BeforeClass
    public void setup(@Optional("chrome") String browser, @Optional ("local") String webRunMode) throws IOException {
        WebDriverFactory driverFactory = new WebDriverFactory();
        driver = driverFactory.getDriver(browser, webRunMode);
        GenerateLogs.loadLogPropertyFile();
        logger.info(Thread.currentThread().getName()+" ************* Execution Started *************");
    }

    /*@Epic("first test case")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case description: verify login page")
    @Story("Story name:to check title of the page")*/
    @Test(dataProvider="getTestData", description="Verifying login page title")
    public void testLogin(TestDataFactory dataFactory)
    {
        System.out.println("data path is "+path);
        logger.info(Thread.currentThread().getName()+" Testlogin Test case started");
        ExtentTestReporter.getTest().log(Status.INFO,"Inside Test Method...");
        System.out.println(dataFactory.getInputParameters());
        logger.warn(Thread.currentThread().getName()+" Input Parameters not validated");
        String username=dataFactory.getInputParameters().get("username").toString();
        System.out.println(dataFactory.getValidationParameters());
        Assert.assertEquals(username,"SwatiChetty");
        logger.info(Thread.currentThread().getName()+" Username validated successfully");
        driver.get("https://www.moneycontrol.com/");
        ExtentTestReporter.getTest().log(Status.INFO,"launched money control url...");
        ExtentTestReporter.getTest().log(Status.PASS,"Validated url successfully");
        logger.info(Thread.currentThread().getName()+" testlogin Testcase ended");
    }

  /*  @Link
    @Epic("Second test case")
    @Severity(SeverityLevel.MINOR)
    @Description("Test Case description: verify facebook page")*/
   /* @Story("Story name:to check fb of the page")*/
    @Test(dataProvider="getTestData")
    public void testLoginFacebook(TestDataFactory dataFactory) {
        logger.info(Thread.currentThread().getName() +" facebook tc started");
        driver.get("https://www.selenium.dev/");
        System.out.println(dataFactory.getInputParameters());
        System.out.println(dataFactory.getValidationParameters());
        logger.info(Thread.currentThread().getName() +" Validation successfull for second tc");
        ExtentTestReporter.getTest().log(Status.INFO,"launched facebook url...");
        ExtentTestReporter.getTest().log(Status.PASS,"Validated page title successfully");
        logger.error(Thread.currentThread().getName()+" validation failed");
        Assert.assertTrue(false);
        logger.info(Thread.currentThread().getName() +" Validation successfull for second tc");
    }

    @AfterClass
    public  void tearDown(){
        logger.info(Thread.currentThread().getName()+" ************* Execution Ended *************");
        driver.quit();
    }

}
