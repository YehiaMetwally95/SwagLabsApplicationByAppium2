package pagesByW3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.elementActions.W3CFingerActions;

public class MenuPage {
    //Variables
    AppiumDriver driver;
    W3CFingerActions action;

    //Locators
    By logoutLocator = AppiumBy.accessibilityId("test-LOGOUT");

    //Constructor
    public MenuPage(AppiumDriver driver) {
        this.driver = driver;
        action = new W3CFingerActions(driver);
    }

    //Actions
    @Step("Log Out")
    public LoginPage logout()
    {
        action.tap(logoutLocator);
        return new LoginPage(driver);
    }

}
