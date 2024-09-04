package testCasesByW3cTouchActions;

import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.CustomSoftAssert;

import java.io.IOException;
import java.net.MalformedURLException;

import static utils.AndroidDriverManager.*;
import static utils.CustomSoftAssert.softAssert;
import static utils.Screenshot.*;

public class BaseTest {

    public AppiumDriver driver;

    @BeforeMethod
    public void setUpAndOpenApp() throws MalformedURLException {
        //Open Browser
        driver = openApp();

        //Set the CustomSoftAssert Class with the driver
        CustomSoftAssert.softAssertDriver = driver;
    }

    @AfterMethod
    public void getScreenshots(ITestResult result) throws IOException, InterruptedException {

        //Take Screenshot after every successful test
        captureSuccess(driver,result);

        //Take Screenshot after every failed test
        captureFailure(driver, result);
    }

    @AfterMethod (dependsOnMethods = {"getScreenshots"})
    public void tearDownApp(){
        //Close App after every test
        closeApp(driver);
    }
}
