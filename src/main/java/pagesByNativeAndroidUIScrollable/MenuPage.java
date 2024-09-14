package pagesByNativeAndroidUIScrollable;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.NativeAndroidActionBot;

import static utils.NativeAndroidActionBot.LocatorType.ACCESSIBILITY_ID;
import static utils.NativeAndroidActionBot.ScrollDirection.VERTICAL;

public class MenuPage {
    //Variables
    AppiumDriver driver;
    NativeAndroidActionBot action;

    //Locators
    By logoutLocator = AppiumBy.accessibilityId("test-LOGOUT");

    //Constructor
    public MenuPage(AppiumDriver driver) {
        this.driver = driver;
        action = new NativeAndroidActionBot(driver);
    }

    //Actions
    @Step("Log Out")
    public LoginPage logout()
    {
        action.tab(ACCESSIBILITY_ID,"test-LOGOUT",VERTICAL);
        return new LoginPage(driver);
    }

}
