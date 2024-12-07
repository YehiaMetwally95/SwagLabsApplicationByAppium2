package linearTests;

import baseTest.BaseTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.elementActions.NativeAndroidActions;

import java.util.HashMap;
import java.util.Map;

import static yehiaEngine.driverManager.AppiumFactory.getDriver;
import static yehiaEngine.elementActions.NativeAndroidActions.LocatorType.*;
import static yehiaEngine.elementActions.NativeAndroidActions.ScrollDirection.*;

public class LinearTestsByNativeAndroidActions extends BaseTest {

    @Test
    public void swipeIntoElementHorizontally() {
        NativeAndroidActions action = new NativeAndroidActions(getDriver(isolatedDriver));

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By tabsLocator = AppiumBy.accessibilityId("Tabs");
        By scrollableLocator = AppiumBy.accessibilityId("5. Scrollable");
        By swipedElementLocator = By.xpath("//android.widget.TabWidget[@resource-id=\"android:id/tabs\"]");
        By TAB3Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 3\")");
        By TAB6Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 6\")");
        By TAB15Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 15\")");
        By TAB25Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 25\")");

        By massageLocator = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/tabcontent']//android.widget.TextView");

        CustomAssert.assertTrue(
                action
                        .tap(viewsLocator)
                        .tap(tabsLocator,VERTICAL)
                        .tap(scrollableLocator)
                        .tap(TAB3Locator,HORIZONTAL,swipedElementLocator)
                        .tap(TAB25Locator,HORIZONTAL,swipedElementLocator)
                        .tap(TAB6Locator,HORIZONTAL,swipedElementLocator)
                        .tap(TAB15Locator,HORIZONTAL,swipedElementLocator)
                        .readText(massageLocator)
                        .contains("Tab 15")
        );
    }

    @Test
    public void swipeIntoElementVertically() {
        NativeAndroidActions action = new NativeAndroidActions(getDriver(isolatedDriver));

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By splittingLocator = AppiumBy.accessibilityId("Splitting Touches across Views");
        By swipedElementLocatorListOne = AppiumBy.id("io.appium.android.apis:id/list1");
        By swipedElementLocatorListTwo = AppiumBy.id("io.appium.android.apis:id/list2");
        By cabocLocator1 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list1']/*[@text='Caboc']");
        By cabocLocator2 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list2']/*[@text='Caboc']");
        By aragonLocator1 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list1']/*[@text='Aragon']");
        By aragonLocator2 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list2']/*[@text='Aragon']");
        By baladiLocator1 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list1']/*[@text='Baladi']");
        By baladiLocator2 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list2']/*[@text='Baladi']");
        By acornLocator1 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list1']/*[@text='Acorn']");

        action
                .tap(viewsLocator)
                .tap(splittingLocator,VERTICAL)
                .tap(aragonLocator2,VERTICAL,swipedElementLocatorListTwo)
                .tap(cabocLocator2,VERTICAL,swipedElementLocatorListTwo)
                .tap(aragonLocator2,VERTICAL,swipedElementLocatorListTwo)
                .tap(baladiLocator1,VERTICAL,swipedElementLocatorListOne)
                .tap(cabocLocator1,VERTICAL,swipedElementLocatorListOne);
    }

    @Test
    public void swipeIntoElementHorizontally_2() {
        NativeAndroidActions action = new NativeAndroidActions(getDriver(isolatedDriver));

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By tabsLocator = AppiumBy.accessibilityId("Tabs");
        By scrollableLocator = AppiumBy.accessibilityId("5. Scrollable");
        By swipedElementLocator = By.xpath("//android.widget.TabWidget[@resource-id=\"android:id/tabs\"]");
        By TAB3Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 3\")");
        By TAB25Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 25\")");
        By massageLocator = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/tabcontent']//android.widget.TextView");

        CustomAssert.assertTrue(
                action
                        .tap(viewsLocator)
                        .tap(TEXT,"Tabs",VERTICAL)
                        .tap(scrollableLocator)
                        .tap(TEXT,"Tab 20",HORIZONTAL,RESOURCE_ID,"android:id/tabs")
                        .tap(TEXT,"Tab 3",HORIZONTAL,RESOURCE_ID,"android:id/tabs")
                        .tap(TEXT,"Tab 15",HORIZONTAL,RESOURCE_ID,"android:id/tabs")
                        .tap(TEXT,"Tab 6",HORIZONTAL,RESOURCE_ID,"android:id/tabs")
                        .readText(massageLocator)
                        .contains("Tab 6")
        );
    }

    @Test
    public void swipeIntoElementVertically_2() {
        NativeAndroidActions action = new NativeAndroidActions(getDriver(isolatedDriver));

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By splittingLocator = AppiumBy.accessibilityId("Splitting Touches across Views");
        By swipedElementLocatorListOne = AppiumBy.id("io.appium.android.apis:id/list1");
        By swipedElementLocatorListTwo = AppiumBy.id("io.appium.android.apis:id/list2");
        By cabocLocator1 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list1']/*[@text='Caboc']");
        By cabocLocator2 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list2']/*[@text='Caboc']");
        By aragonLocator1 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list1']/*[@text='Aragon']");
        By aragonLocator2 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list2']/*[@text='Aragon']");
        By baladiLocator1 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list1']/*[@text='Baladi']");
        By baladiLocator2 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list2']/*[@text='Baladi']");
        By acornLocator1 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list1']/*[@text='Acorn']");
        By acornLocator2 = AppiumBy.xpath("//*[@resource-id='io.appium.android.apis:id/list2']/*[@text='Acorn']");
        By massageLocator = AppiumBy.xpath("//android.widget.Toast");

        action
                .tap(viewsLocator)
                .tap(TEXT,"Splitting Touches across Views",VERTICAL)
                .tap(TEXT,"Aragon",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list2")
                .tap(TEXT,"Caboc",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list2")
                .tap(TEXT,"Colby",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list2")
                .tap(TEXT,"Aragon",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list2")

                .tap(TEXT,"Acorn",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list1")
                .tap(TEXT,"Baladi",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list1")
                .tap(TEXT,"Caboc",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list1")
                .tap(TEXT,"Acorn",VERTICAL,RESOURCE_ID,"io.appium.android.apis:id/list1");
    }
}
