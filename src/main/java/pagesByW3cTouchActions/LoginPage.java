package pagesByW3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.W3CTouchActions;

import java.io.IOException;

import static utils.ActionBot.*;

public class LoginPage{

    //Variables
    AppiumDriver driver;
    W3CTouchActions action;

    //Locators
    By usernameTextBox = AppiumBy.accessibilityId("test-Username");
    By passwordTextBox = AppiumBy.accessibilityId("test-Password");
    By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    By errorMassage = AppiumBy.xpath("//*[@content-desc='test-Error message']//android.widget.TextView");

    //Constructor
    public LoginPage(AppiumDriver driver)
    {
        this.driver = driver;
        action = new W3CTouchActions(driver);
    }

    //Actions
    @Step("Enter Username")
    public LoginPage setUsername(String username) throws IOException {
        type(driver,usernameTextBox,username);
        return this;
    }

    @Step("Enter Password")
    public LoginPage setPassword(String password) throws IOException {
        type(driver,passwordTextBox,password);
        return this;
    }

    @Step("Click on Submit Button")
    public LoginPage clickLoginButtonFailure() throws IOException {

        action.tab(loginButton);
        return this;
    }

    @Step("Click on Submit Button")
    public ProductsPage clickLoginButtonSuccess() throws IOException {

        action.tab(loginButton);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Assert Error Massage For Invalid Credentials")
    public LoginPage assertErrorMassageForInvalidCredentials(String massage) throws IOException, ParseException {

        Assert.assertEquals(readText(driver,errorMassage),massage);
        return this;
    }

    @Step("Assert Error Massage For Locked User")
    public LoginPage assertErrorMassageForLockedUser(String massage) throws IOException, ParseException {

        Assert.assertEquals(readText(driver,errorMassage),massage);
        return this;
    }

    @Step("Verify The LogOut is Succeeded")
    public LoginPage verifyLogOutIsSucceeded()
    {

        return this;
    }

}
