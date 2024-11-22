package pagesByNativeAndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.elementActions.NativeAndroidActions;

public class MenuPage {
    //Variables
    AppiumDriver driver;
    NativeAndroidActions action;

    //Locators
    By logoutLocator = AppiumBy.accessibilityId("test-LOGOUT");

    //Constructor
    public MenuPage(AppiumDriver driver) {
        this.driver = driver;
        action = new NativeAndroidActions(driver);
    }

    //Actions
    @Step("Log Out")
    public LoginPage logout()
    {
        action.tap(logoutLocator);
        return new LoginPage(driver);
    }

}