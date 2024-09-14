package linearTests;

import io.appium.java_client.AppiumBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testCasesByNativeAndroidUIScrollable.BaseTest;
import utils.NativeAndroidActionBot;
import static utils.NativeAndroidActionBot.LocatorType.*;
import static utils.NativeAndroidActionBot.ScrollDirection.*;
import java.io.IOException;

@Listeners(utils.TestNGListners.class)
public class LinearTestsByUIScrollableAndroidAction extends BaseTest {

    @Test
    public void swipeIntoElementHorizontally() throws IOException, ParseException {
        NativeAndroidActionBot action = new NativeAndroidActionBot(driver);

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By tabsLocator = AppiumBy.accessibilityId("Tabs");
        By scrollableLocator = AppiumBy.accessibilityId("5. Scrollable");
        By swipedElementLocator = By.xpath("//android.widget.TabWidget[@resource-id=\"android:id/tabs\"]");
        By TAB3Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 3\")");
        By TAB25Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 25\")");
        By massageLocator = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/tabcontent']//android.widget.TextView");

        Assert.assertTrue(
                action.tab(TEXT,"Views",VERTICAL)
                        .tab(TEXT,"Tabs",VERTICAL)
                        .tab(TEXT,"5. Scrollable",VERTICAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"android:id/tabs",TEXT,"Tab 20",HORIZONTAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"android:id/tabs",TEXT,"Tab 3",HORIZONTAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"android:id/tabs",TEXT,"Tab 15",HORIZONTAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"android:id/tabs",TEXT,"Tab 6",HORIZONTAL)
                        .readChildText(RESOURCE_ID,"android:id/tabcontent",VERTICAL,"TextView")
                        .contains("Tab 6")
        );
    }

    @Test
    public void swipeIntoElementVertically() throws IOException, ParseException {
        NativeAndroidActionBot action = new NativeAndroidActionBot(driver);

        By viewsLocator = AppiumBy.accessibilityId("Views");
        By tabsLocator = AppiumBy.accessibilityId("Splitting Touches across Views");
        By swipedElementLocatorListTwo = By.xpath("//android.widget.ListView[@resource-id=\"io.appium.android.apis:id/list2\"]");
        By swipedElementLocatorListOne = By.xpath("//android.widget.ListView[@resource-id=\"io.appium.android.apis:id/list1\"]");
        By cellLocatorListTwo = AppiumBy.xpath("(//android.widget.TextView[@text=\"Abbaye du Mont des Cats\"])[2]");
        By cellLocatorListOne = AppiumBy.xpath("(//android.widget.TextView[@text=\"Abbaye du Mont des Cats\"])[1]");
        By massageLocator = AppiumBy.xpath("//android.widget.Toast");

        Assert.assertTrue(
                action.tab(TEXT,"Views",VERTICAL)
                        .tab(TEXT,"Splitting Touches across Views",VERTICAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"io.appium.android.apis:id/list2",TEXT,"Caboc",VERTICAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"io.appium.android.apis:id/list2",TEXT,"Aragon",VERTICAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"io.appium.android.apis:id/list2",TEXT,"Baladi",VERTICAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"io.appium.android.apis:id/list2",TEXT,"Aragon",VERTICAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"io.appium.android.apis:id/list1",TEXT,"Caboc",VERTICAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"io.appium.android.apis:id/list1",TEXT,"Acorn",VERTICAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"io.appium.android.apis:id/list1",TEXT,"Baladi",VERTICAL)
                        .swipeIntoElementAndTap(RESOURCE_ID,"io.appium.android.apis:id/list1",TEXT,"Acorn",VERTICAL)
                        .readText(massageLocator)
                        .contains("Acorn")
        );
    }
}
