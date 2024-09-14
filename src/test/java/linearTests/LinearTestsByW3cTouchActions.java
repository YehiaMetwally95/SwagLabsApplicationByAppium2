package linearTests;

import org.testng.asserts.SoftAssert;
import testCasesByW3cTouchActions.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.CustomSoftAssert;
import utils.W3CTouchActions;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.NativeAndroidActionBot;

import java.io.IOException;

import static utils.W3CTouchActions.Direction.*;
import static utils.AlertsActions.*;
import static utils.NativeAndroidActionBot.LocatorType.*;
import static utils.NativeAndroidActionBot.ScrollDirection.*;


@Listeners(utils.TestNGListners.class)
public class LinearTestsByW3cTouchActions extends BaseTest {

    @Test
    public void swipeIntoElementHorizontally() throws IOException, ParseException {
        W3CTouchActions action = new W3CTouchActions(driver);

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By tabsLocator = AppiumBy.accessibilityId("Tabs");
        By scrollableLocator = AppiumBy.accessibilityId("5. Scrollable");
        By swipedElementLocator = By.xpath("//android.widget.TabWidget[@resource-id=\"android:id/tabs\"]");
        By TAB3Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 3\")");
        By TAB25Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 25\")");
        By textField = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/tabcontent']//android.widget.TextView");

        action.tab(viewsLocator)
                .swipeIntoScreen(tabsLocator, DOWN)
                .tab(tabsLocator)
                .tab(scrollableLocator)
                .swipeIntoElement(swipedElementLocator, LEFT, TAB25Locator)
                .tab(TAB25Locator)
                .swipeIntoElement(swipedElementLocator, RIGHT, TAB3Locator)
                .tab(TAB3Locator);

        Assert.assertTrue(action.readText(textField).contains("Tab 3"));
    }

    @Test
    public void dropDown() throws IOException, ParseException {
        By app = AppiumBy.accessibilityId("App");
        By menu = AppiumBy.accessibilityId("Menu");
        By inflate = AppiumBy.accessibilityId("Inflate from XML");
        By spinner = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"io.appium.android.apis:id/spinner\")");
        By groupsOption = AppiumBy.androidUIAutomator("new UiSelector().text(\"Groups\")");

        W3CTouchActions action = new W3CTouchActions(driver);
        action.tab(app)
                .tab(menu)
                .tab(inflate)
                .tab(spinner)
                .tab(groupsOption);
    }

}
