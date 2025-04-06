package pagesByW3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;
import yehiaEngine.elementActions.W3CFingerActions;

public class LoginPage{

    //Variables
    AppiumDriver driver;
    W3CFingerActions action;

    //Locators
    By usernameTextBox = AppiumBy.accessibilityId("test-Username");
    By passwordTextBox = AppiumBy.accessibilityId("test-Password");
    By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    By errorMassage = AppiumBy.xpath("//*[@content-desc='test-Error message']//android.widget.TextView");

    //Constructor
    public LoginPage(AppiumDriver driver)
    {
        this.driver = driver;
        action = new W3CFingerActions(driver);
    }

    //Actions
    @Step("Login with Valid User")
    public ProductsPage loginWithValidUser(String username, String password) {
        setUsername(username)
                .setPassword(password)
                .clickLoginButtonSuccess();
        return new ProductsPage(driver);
    }

    @Step("Login with Invalid User")
    public LoginPage loginWithInvalidUser(String username, String password) {
        setUsername(username)
                .setPassword(password)
                .clickLoginButtonFailure();
        return this;
    }

    //Private Methods
    @Step("Enter Username")
    private LoginPage setUsername(String username) {
        action.type(usernameTextBox,username);
        return this;
    }

    @Step("Enter Password")
    private LoginPage setPassword(String password) {
        action.type(passwordTextBox,password);
        return this;
    }

    @Step("Click on Submit Button")
    private LoginPage clickLoginButtonFailure() {

        action.tap(loginButton);
        return this;
    }

    @Step("Click on Submit Button")
    private ProductsPage clickLoginButtonSuccess() {

        action.tap(loginButton);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Assert Error Massage For Invalid Credentials")
    public LoginPage assertErrorMassageForInvalidCredentials(String massage) {

        CustomAssert.assertEquals(action.readText(errorMassage),massage);
        return this;
    }

    @Step("Assert Error Massage For Locked User")
    public LoginPage assertErrorMassageForLockedUser(String massage)  {

        CustomAssert.assertEquals(action.readText(errorMassage),massage);
        return this;
    }

    @Step("Verify The LogOut is Succeeded")
    public LoginPage verifyLogOutIsSucceeded()
    {
        CustomSoftAssert.assertTrue(action.isElementDisplayed(loginButton));
        return this;
    }
}
