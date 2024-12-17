package pagesByNativeAndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.elementActions.NativeAndroidActions;

public class WebViewPage extends HomePage{
    //Variables

    //Constructor
    public WebViewPage(AppiumDriver driver) {
        super(driver);
    }

    //Locators
    By textBoxLocator = AppiumBy.accessibilityId("test-enter a https url here...");
    By goToSiteButtonLocator = AppiumBy.accessibilityId("test-GO TO SITE");

    //Actions
    @Step("NavigateToWebViewURL")
    public void NavigateToWebViewURL(String url)
    {
        action
                .type(textBoxLocator,url)
                .tap(goToSiteButtonLocator);
    }
}
