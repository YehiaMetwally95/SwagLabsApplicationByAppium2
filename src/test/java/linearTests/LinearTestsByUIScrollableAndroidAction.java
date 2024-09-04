package linearTests;

import io.appium.java_client.AppiumBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import testCasesByNativeAndroidUIScrollable.BaseTest;
import utils.NativeAndroidActionBot;
import utils.W3CTouchActions;

import java.io.IOException;

import static utils.AlertsActions.getTextInAlert;
import static utils.W3CTouchActions.Direction.*;

public class LinearTestsByUIScrollableAndroidAction extends BaseTest {

    @Test
    public void swipe() throws IOException, ParseException {
        NativeAndroidActionBot action = new NativeAndroidActionBot(driver);

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By tabsLocator = AppiumBy.accessibilityId("Tabs");
        By scrollableLocator = AppiumBy.accessibilityId("5. Scrollable");
        By swipedElementLocator = By.xpath("//android.widget.HorizontalScrollView");
        By TAB3Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 3\")");
        By TAB25Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 25\")");
        By textField = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/tabcontent']//android.widget.TextView");

        action.tab(viewsLocator)
                .tab(tabsLocator)
                .tab(scrollableLocator)
                .tab(TAB25Locator)
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

    @Test
    public void alert() throws IOException, ParseException {
        By app = AppiumBy.accessibilityId("App");
        By alertDialogs = AppiumBy.accessibilityId("Alert Dialogs");
        By ok_CanceldialogAlert = AppiumBy.accessibilityId("OK Cancel dialog with Holo Light theme");
        By listDialog = AppiumBy.accessibilityId("List dialog");
        By commandOne = AppiumBy.androidUIAutomator("new UiSelector().text(\"Command one\")");
        By singleChoiceList =AppiumBy.accessibilityId("Single choice list");
        By streetviewChoice = AppiumBy.androidUIAutomator("new UiSelector().text(\"Street view\")");

        W3CTouchActions action = new W3CTouchActions(driver);
        action.tab(app)
                .tab(alertDialogs)
                .tab(ok_CanceldialogAlert);
        System.out.println(getTextInAlert(driver));
        //acceptAlert(driver);

        action.tab(singleChoiceList)
                .tab(streetviewChoice);
        //dismissAlert(driver);

        action.tab(listDialog)
                .tab(commandOne);
    }
}
