package linearTests;

import baseTest.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.elementActions.NativeAndroidActions;

import static yehiaEngine.driverManager.AppiumFactory.getDriver;
import static yehiaEngine.elementActions.NativeAndroidActions.LocatorType.*;
import static yehiaEngine.elementActions.NativeAndroidActions.ScrollDirection.*;

public class LinearTestsByUIScrollableAndroidAction extends BaseTest {

    @Test
    public void swipeIntoElementHorizontally() {
        NativeAndroidActions action = new NativeAndroidActions(getDriver(isolatedDriver));

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By tabsLocator = AppiumBy.accessibilityId("Tabs");
        By scrollableLocator = AppiumBy.accessibilityId("5. Scrollable");
        By swipedElementLocator = By.xpath("//android.widget.TabWidget[@resource-id=\"android:id/tabs\"]");
        By TAB3Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 3\")");
        By TAB25Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 25\")");
        By massageLocator = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/tabcontent']//android.widget.TextView");

        CustomAssert.assertTrue(
                action.tap(TEXT,"Views",VERTICAL)
                        .tap(TEXT,"Tabs",VERTICAL)
                        .tap(TEXT,"5. Scrollable",VERTICAL)
                        .tap(TEXT,"Tab 20",HORIZONTAL,RESOURCE_ID,"android:id/tabs")
                        .tap(TEXT,"Tab 3",HORIZONTAL,RESOURCE_ID,"android:id/tabs")
                        .tap(TEXT,"Tab 15",HORIZONTAL,RESOURCE_ID,"android:id/tabs")
                        .tap(TEXT,"Tab 6",HORIZONTAL,RESOURCE_ID,"android:id/tabs")
                        .readText(massageLocator)
                        .contains("Tab 6")
        );
    }

    @Test
    public void swipeIntoElementVertically() {
        NativeAndroidActions action = new NativeAndroidActions(getDriver(isolatedDriver));

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By tabsLocator = AppiumBy.accessibilityId("Splitting Touches across Views");
        By swipedElementLocatorListTwo = By.xpath("//android.widget.ListView[@resource-id=\"io.appium.android.apis:id/list2\"]");
        By swipedElementLocatorListOne = By.xpath("//android.widget.ListView[@resource-id=\"io.appium.android.apis:id/list1\"]");
        By cellLocatorListTwo = AppiumBy.xpath("(//android.widget.TextView[@text=\"Abbaye du Mont des Cats\"])[2]");
        By cellLocatorListOne = AppiumBy.xpath("(//android.widget.TextView[@text=\"Abbaye du Mont des Cats\"])[1]");
        By massageLocator = AppiumBy.xpath("//android.widget.Toast");

        Assert.assertTrue(
                action.tap(TEXT,"Views",VERTICAL)
                        .tap(TEXT,"Splitting Touches across Views",VERTICAL)
                        .tap(TEXT,"Caboc",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list2")
                        .tap(TEXT,"Aragon",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list2")
                        .tap(TEXT,"Baladi",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list2")
                        .tap(TEXT,"Aragon",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list2")

                        .tap(TEXT,"Caboc",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list1")
                        .tap(TEXT,"Acorn",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list1")
                        .tap(TEXT,"Baladi",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list1")
                        .tap(TEXT,"Acorn",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list1")

                        .readText(massageLocator)
                        .contains("Acorn")
        );
    }
}
