package pagesByNativeAndroidUIScrollable;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.NativeAndroidActionBot;
import static utils.NativeAndroidActionBot.LocatorType.*;
import static utils.NativeAndroidActionBot.ScrollDirection.*;
import java.io.IOException;

public class LoginPage{

    //Variables
    AppiumDriver driver;
    NativeAndroidActionBot action;

    //Locators
    By usernameTextBox = AppiumBy.accessibilityId("test-Username");
    By passwordTextBox = AppiumBy.accessibilityId("test-Password");
    By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    By errorMassage = AppiumBy.xpath("//*[@content-desc='test-Error message']//android.widget.TextView");

    //Constructor
    public LoginPage(AppiumDriver driver)
    {
        this.driver = driver;
        action = new NativeAndroidActionBot(driver);
    }

    //Actions
    @Step("Enter Username")
    public LoginPage setUsername(String username) throws IOException {
        action.type(ACCESSIBILITY_ID,"test-Username",VERTICAL,username);
        return this;
    }

    @Step("Enter Password")
    public LoginPage setPassword(String password) throws IOException {
        action.type(ACCESSIBILITY_ID,"test-Password",VERTICAL,password);
        return this;
    }

    @Step("Click on Submit Button")
    public LoginPage clickLoginButtonFailure() throws IOException {

        action.tab(ACCESSIBILITY_ID,"test-LOGIN",VERTICAL);
        return this;
    }

    @Step("Click on Submit Button")
    public ProductsPage clickLoginButtonSuccess() throws IOException {

        action.tab(ACCESSIBILITY_ID,"test-LOGIN",VERTICAL);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Assert Error Massage For Invalid Credentials")
    public LoginPage assertErrorMassageForInvalidCredentials(String massage) throws IOException, ParseException {

        Assert.assertEquals(action.readChildText(ACCESSIBILITY_ID,"test-Error message",VERTICAL,"TextView"),massage);
        return this;
    }

    @Step("Assert Error Massage For Locked User")
    public LoginPage assertErrorMassageForLockedUser(String massage) throws IOException, ParseException {

        Assert.assertEquals(action.readChildText(ACCESSIBILITY_ID,"test-Error message",VERTICAL,"TextView"),massage);
        return this;
    }

    @Step("Verify The LogOut is Succeeded")
    public LoginPage verifyLogOutIsSucceeded()
    {
        return this;
    }

}
