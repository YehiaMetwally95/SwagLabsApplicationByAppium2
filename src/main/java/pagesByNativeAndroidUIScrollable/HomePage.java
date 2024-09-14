package pagesByNativeAndroidUIScrollable;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.NativeAndroidActionBot;
import static utils.NativeAndroidActionBot.LocatorType.*;
import static utils.NativeAndroidActionBot.ScrollDirection.*;

public class HomePage {
    //Variables
    AppiumDriver driver;
    NativeAndroidActionBot action;

    //Locators
    By menuIcon = AppiumBy.accessibilityId("test-Menu");
    By cartIcon = AppiumBy.accessibilityId("test-Cart");
    By footer = AppiumBy.androidUIAutomator("new UiSelector().text(\"© 2024 Sauce Labs. All Rights Reserved.\")");

    //Constructor
    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        action = new NativeAndroidActionBot(driver);
    }

    //Actions
    @Step("Open Menu From Header")
    public MenuPage openMenuFromHeader()
    {
        action.tab(ACCESSIBILITY_ID,"test-Menu",VERTICAL);
        return new MenuPage(driver);
    }

    public CartPage openCartPageFromHeader()
    {
        action.tab(ACCESSIBILITY_ID,"test-Cart",VERTICAL);
        return new CartPage(driver);
    }

    @Step("Open Twitter From Footer")
    public void openTwitterPageFromFooter()
    {

    }

    @Step("Open Facebook From Footer")
    public void openFacebookPageFromFooter()
    {

    }

    @Step("Open GooglePlus From Footer")
    public void openGooglePlusPageFromFooter()
    {

    }

    @Step("Open Linkedin From Footer")
    public void openLinkedinPageFromFooter()
    {

    }
}
