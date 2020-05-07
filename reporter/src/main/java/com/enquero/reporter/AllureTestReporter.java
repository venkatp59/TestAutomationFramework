package com.enquero.reporter;

/*import io.qameta.allure.Attachment;*/
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureTestReporter {

    /**
     * screenShot method is invoked whenever the Testcase is Failed.
     * @param name
     * @param driver
     * @return
     */
//@Attachment
public byte[] saveScreenshot(String name, WebDriver driver){
    return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
}







}
