package linearTests;

import testCasesByW3cTouchActions.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import utils.W3CTouchActions;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.NativeAndroidActionBot;

import java.io.IOException;

import static utils.ActionBot.readText;
import static utils.W3CTouchActions.Direction.*;
import static utils.AlertsActions.*;
import static utils.NativeAndroidActionBot.LocatorType.*;
import static utils.NativeAndroidActionBot.ScrollDirection.*;


@Listeners(utils.TestNGListners.class)
public class LinearTestsByW3cTouchActions extends BaseTest {

    @Test
    public void swipe() throws IOException, ParseException {
        W3CTouchActions action = new W3CTouchActions(driver);

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By tabsLocator = AppiumBy.accessibilityId("Tabs");
        By scrollableLocator = AppiumBy.accessibilityId("5. Scrollable");
        By swipedElementLocator = By.xpath("//android.widget.HorizontalScrollView");
        By TAB1Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 1\")");
        By TAB25Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 25\")");
        By textField = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/tabcontent']//android.widget.TextView");

        action.tab(viewsLocator)
                .swipeIntoScreen(tabsLocator,UP)
                .tab(tabsLocator)
                .tab(scrollableLocator)
                .swipeIntoElement(swipedElementLocator, LEFT, TAB25Locator)
                .tab(TAB25Locator)
                .swipeIntoElement(swipedElementLocator, RIGHT, TAB1Locator)
                .tab(TAB1Locator);

        Assert.assertTrue(readText(driver, textField).contains("Tab 1"));
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

    @Test
    public void clipBoard() throws IOException, ParseException {
        By app = AppiumBy.accessibilityId("App");
        By alertDialogs = AppiumBy.accessibilityId("Alert Dialogs");
        By ok_CanceldialogAlert = AppiumBy.accessibilityId("OK Cancel dialog with Holo Light theme");
        By listDialog = AppiumBy.accessibilityId("List dialog");
        By commandOne = AppiumBy.androidUIAutomator("new UiSelector().text(\"Command one\")");
        By singleChoiceList =AppiumBy.accessibilityId("Single choice list");
        By streetviewChoice = AppiumBy.androidUIAutomator("new UiSelector().text(\"Street view\")");

        String textInClipboard = "My Name is Yehia";
        ((AndroidDriver)driver).setClipboardText(textInClipboard);
    }

    @Test
    public void test() throws IOException, ParseException {
        NativeAndroidActionBot bot = new NativeAndroidActionBot(driver);
       bot
                        .tab(TEXT,"Views", VERTICAL)
                        .tab(TEXT,"Tabs", VERTICAL)
                        .tab(TEXT,"5. Scrollable", VERTICAL);
                 //.swipeIntoElemenAndTap(RESOURCE_ID,"android:id/tabs", TEXT,"TAB 25", HORIZONTAL)
                   //     .swipeIntoElemenAndTap(RESOURCE_ID,"android:id/tabs", TEXT,"TAB 10", HORIZONTAL)
                     //   .swipeIntoElemenAndTap(RESOURCE_ID,"android:id/tabs", TEXT,"TAB 20", HORIZONTAL)
                       // .swipeIntoElemenAndTap(RESOURCE_ID,"android:id/tabs", TEXT,"TAB 4", HORIZONTAL);

    }

    @Test
    public void test2() throws IOException, ParseException {
        NativeAndroidActionBot bot = new NativeAndroidActionBot(driver);
        bot
                .tab(TEXT,"Views", VERTICAL)
                .tab(TEXT,"Splitting Touches across Views", VERTICAL);
               // .swipeIntoElemenAndTap(RESOURCE_ID,"io.appium.android.apis:id/list1", TEXT,"Bougon", VERTICAL)
             //   .swipeIntoElemenAndTap(RESOURCE_ID,"io.appium.android.apis:id/list1", TEXT,"Babybel", VERTICAL);

    }



}
