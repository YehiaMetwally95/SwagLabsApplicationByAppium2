package linearTests;

import baseTest.BaseTest;
import com.github.javafaker.App;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.elementActions.W3CTouchActions;
import static yehiaEngine.driverManager.AppiumFactory.*;
import static yehiaEngine.elementActions.W3CTouchActions.Direction.*;

public class LinearTestsByW3cTouchActions extends BaseTest {

    @Test
    public void swipeIntoElementHorizontally() {
        By viewsLocator = AppiumBy.accessibilityId("Views");
        By tabsLocator = AppiumBy.accessibilityId("Tabs");
        By scrollableLocator = AppiumBy.accessibilityId("5. Scrollable");
        By swipedElementLocator = By.xpath("//android.widget.TabWidget[@resource-id=\"android:id/tabs\"]");
        By TAB20Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 20\")");
        By TAB3Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 3\")");
        By TAB15Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 15\")");
        By TAB6Locator = AppiumBy.androidUIAutomator("new UiSelector().text(\"TAB 6\")");
        By textField = AppiumBy.xpath("//android.widget.FrameLayout[@resource-id='android:id/tabcontent']//android.widget.TextView");

        W3CTouchActions action = new W3CTouchActions(getDriver(isolatedDriver));

        action.tap(viewsLocator)
                .tap(tabsLocator,DOWN)
                .tap(scrollableLocator)
                .tap(TAB20Locator,LEFT,swipedElementLocator)
                .tap(TAB3Locator,RIGHT,swipedElementLocator)
                .tap(TAB15Locator,LEFT,swipedElementLocator)
                .tap(TAB6Locator,RIGHT,swipedElementLocator);

        CustomAssert.assertEquals(action.readText(textField),"Content for tab with tag Tab 6");
    }

    @Test
    public void swipeIntoElementVertically() {
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

        W3CTouchActions action = new W3CTouchActions(getDriver(isolatedDriver));

        action.tap(viewsLocator)
                .tap(splittingLocator, DOWN)
                .tap(cabocLocator2, DOWN, swipedElementLocatorListTwo)
                .tap(aragonLocator2, UP, swipedElementLocatorListTwo)
                .tap(baladiLocator2, DOWN, swipedElementLocatorListTwo)
                .tap(aragonLocator2, UP, swipedElementLocatorListTwo)

                .tap(cabocLocator1, DOWN, swipedElementLocatorListOne)
                .tap(acornLocator1, UP, swipedElementLocatorListOne)
                .tap(baladiLocator1, DOWN, swipedElementLocatorListOne)
                .tap(baladiLocator1, UP, swipedElementLocatorListOne);
    }

    @Test
    public void dropDown() {
        By app = AppiumBy.accessibilityId("App");
        By menu = AppiumBy.accessibilityId("Menu");
        By inflate = AppiumBy.accessibilityId("Inflate from XML");
        By spinner = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"io.appium.android.apis:id/spinner\")");
        By groupsOption = AppiumBy.androidUIAutomator("new UiSelector().text(\"Groups\")");

        W3CTouchActions action = new W3CTouchActions(getDriver(isolatedDriver));
        action.tap(app)
                .tap(menu)
                .tap(inflate)
                .tap(spinner)
                .tap(groupsOption);
    }
}
