package linearTests;

import baseTest.BaseTest;
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
        By swipedElementLocatorListTwo = By.xpath("//android.widget.ListView[@resource-id=\"io.appium.android.apis:id/list2\"]");
        By swipedElementLocatorListOne = By.xpath("//android.widget.ListView[@resource-id=\"io.appium.android.apis:id/list1\"]");
        By cabocLocator2 = AppiumBy.xpath("(//android.widget.TextView[@text=\"Caboc\"])[2]");
        By cabocLocator1 = AppiumBy.xpath("(//android.widget.TextView[@text=\"Caboc\"])[1]");
        By aragonLocator2 = AppiumBy.xpath("(//android.widget.TextView[@text=\"Aragon\"])[2]");
        By aragonLocator1 = AppiumBy.xpath("(//android.widget.TextView[@text=\"Aragon\"])[1]");
        By baladiLocator2 = AppiumBy.xpath("(//android.widget.TextView[@text=\"Baladi\"])[2]");
        By baladiLocator1 = AppiumBy.xpath("(//android.widget.TextView[@text=\"Baladi\"])[1]");
        By acornLocator2 = AppiumBy.xpath("(//android.widget.TextView[@text=\"Acorn\"])[2]");
        By acornLocator1 = AppiumBy.xpath("(//android.widget.TextView[@text=\"Acorn\"])[1]");
        By massageLocator = AppiumBy.xpath("//android.widget.Toast");

        W3CTouchActions action = new W3CTouchActions(getDriver(isolatedDriver));

        CustomAssert.assertTrue(
                action.tap(viewsLocator)
                        .tap(splittingLocator, DOWN)
                        .tap(cabocLocator2, DOWN, swipedElementLocatorListTwo)
                        .tap(aragonLocator2, UP, swipedElementLocatorListTwo)
                        .tap(baladiLocator2, DOWN, swipedElementLocatorListTwo)
                        .tap(aragonLocator2, UP, swipedElementLocatorListTwo)

                        .tap(cabocLocator1, DOWN, swipedElementLocatorListOne)
                        .tap(acornLocator1, UP, swipedElementLocatorListOne)
                        .tap(baladiLocator1, DOWN, swipedElementLocatorListOne)
                        .tap(acornLocator1, UP, swipedElementLocatorListOne)
                        .readText(massageLocator)
                        .contains("Acorn")
        );
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
