package yehiaEngine.elementActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import yehiaEngine.elementActions.Helpers.NativeAndroidActionsHelper;
import yehiaEngine.loggers.LogHelper;

import java.util.HashMap;
import java.util.Map;

import static yehiaEngine.elementActions.Helpers.NativeAndroidActionsHelper.*;
import static yehiaEngine.elementActions.Helpers.WaitsManager.getFluentWait;
import static yehiaEngine.elementActions.Helpers.WaitsManager.getSwipeWait;

public class NativeAndroidActions {

    AppiumDriver driver;

    public enum ScrollDirection {
        HORIZONTAL, VERTICAL
    }

    @AllArgsConstructor
    @Getter
    public enum Direction {
        LEFT("left"),
        RIGHT("right"),
        UP("up"),
        DOWN("down");

        private final String x;
    }

    public enum LocatorType {
        TEXT, RESOURCE_ID, ACCESSIBILITY_ID, CLASS_NAME
    }

    public NativeAndroidActions(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * *********************************  Tap Actions  ****************************************
     */
    // Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeAndroidActions tap(LocatorType locatorType, String locatorValue, ScrollDirection direction) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locatorType, locatorValue, direction);
        // Get Element Name
        String elementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, elementName);
        //Execute the Tap Action
        try {
            getFluentWait(driver).until(f ->
            {

                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null)))).getId());

                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(getUiAutomatorQuery(locatorType,locatorValue,null))));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: clickGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Tapping on Element [" + elementName + "]");
        return this;
    }

    // Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeAndroidActions tap(By locator, ScrollDirection direction) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locator, direction);
        // Get Element Name
        String elementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, elementName);

        //Execute the Tap Action
        try {
            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());


                Dimension elementSize = getElementSize(driver.findElement(locator));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);
                driver.executeScript("mobile: clickGesture", param);

                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Tapping on Element [" + elementName + "]");
        return this;
    }

    //Tap on Button or Link by Swiping into swiped element & Log Tapping Action
    public NativeAndroidActions tap(LocatorType locatorType, String locatorValue, ScrollDirection direction, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locatorType, locatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue);
        // Get Element Name
        String elementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, elementName);
        //Execute the Tap Action
        try {
            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null)))).getId());

                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(getUiAutomatorQuery(locatorType,locatorValue,null))));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: clickGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Tapping on Element [" + elementName + "]");
        return this;
    }

    // Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeAndroidActions tap(By locator, ScrollDirection direction, By swipedElementLocator) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locator, direction, swipedElementLocator);
        // Get Element Name
        String elementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, elementName);

        //Execute the Tap Action
        try {
            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());

                Dimension elementSize = getElementSize(driver.findElement(locator));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: clickGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Tapping on Element [" + elementName + "]");
        return this;
    }

    //Tap on Button or Link without Swipe & Log Tapping Action
    public NativeAndroidActions tap(By locator) {
        new NativeAndroidActions(driver).tap(locator,null);
        return this;
    }

    /**
     * *********************************  Long Tap Actions  ****************************************
     */
    // Long Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeAndroidActions longTap(LocatorType locatorType, String locatorValue, ScrollDirection direction) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locatorType, locatorValue, direction);
        // Get Element Name
        String elementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, elementName);
        //Execute the Long Tap Action
        try {
            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null)))).getId());
                param.put("duration", 2000);

                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(getUiAutomatorQuery(locatorType,locatorValue,null))));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: longClickGesture", param);

                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Long Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Long Tapping on Element [" + elementName + "]");
        return this;
    }

    // Long Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeAndroidActions longTap(By locator, ScrollDirection direction) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locator, direction);
        // Get Element Name
        String elementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, elementName);
        //Execute the Long Tap Action
        try {

                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
                param.put("duration", 2000);

                Dimension elementSize = getElementSize(driver.findElement(locator));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: longClickGesture", param);

        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Long Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Long Tapping on Element [" + elementName + "]");
        return this;
    }

    //Long Tap on Button or Link by Swiping into swiped element & Log Tapping Action
    public NativeAndroidActions longTap(LocatorType locatorType, String locatorValue, ScrollDirection direction, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locatorType, locatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue);
        // Get Element Name
        String elementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, elementName);
        //Execute the Long Tap Action
        try {
            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null)))).getId());
                param.put("duration", 2000);
                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(getUiAutomatorQuery(locatorType,locatorValue,null))));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: longClickGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Long Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Long Tapping on Element [" + elementName + "]");
        return this;
    }

    //Long Tap on Button or Link by Swiping into swiped element & Log Tapping Action
    public NativeAndroidActions longTap(By locator, ScrollDirection direction, By swipedElementLocator) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locator, direction, swipedElementLocator);
        // Get Element Name
        String elementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, elementName);
        //Execute the Long Tap Action
        try {
            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
                param.put("duration", 2000);

                Dimension elementSize = getElementSize(driver.findElement(locator));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: longClickGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Long Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Long Tapping on Element [" + elementName + "]");
        return this;
    }

    //Long Tap on Button or Link without Swipe & Log Tapping Action
    public NativeAndroidActions longTap(By locator) {
        new NativeAndroidActions(driver).longTap(locator, null);
        return this;
    }

    /**
     * *********************************  Double Tap Actions  ****************************************
     */
    // Double Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeAndroidActions doubleTap(LocatorType locatorType, String locatorValue, ScrollDirection direction) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locatorType, locatorValue, direction);
        // Get Element Name
        String elementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, elementName);
        //Execute the Double Tap Action
        try {
            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null)))).getId());

                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(getUiAutomatorQuery(locatorType,locatorValue,null))));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: doubleClickGesture", param);

                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Double Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Double Tapping on Element [" + elementName + "]");
        return this;
    }

    // Double Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeAndroidActions doubleTap(By locator, ScrollDirection direction) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locator, direction);
        // Get Element Name
        String elementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, elementName);
        //Execute the Double Tap Action
        try {
            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());

                Dimension elementSize = getElementSize(driver.findElement(locator));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: doubleClickGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Double Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Double Tapping on Element [" + elementName + "]");
        return this;
    }

    //Double Tap on Button or Link by Swiping into swiped element & Log Tapping Action
    public NativeAndroidActions doubleTap(LocatorType locatorType, String locatorValue, ScrollDirection direction, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locatorType, locatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue);
        // Get Element Name
        String elementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, elementName);
        //Execute the Double Tap Action
        try {
            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null)))).getId());

                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(getUiAutomatorQuery(locatorType,locatorValue,null))));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: doubleClickGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Double Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Double Tapping on Element [" + elementName + "]");
        return this;
    }

    //Double Tap on Button or Link by Swiping into swiped element & Log Tapping Action
    public NativeAndroidActions doubleTap(By locator, ScrollDirection direction, By swipedElementLocator) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locator, direction, swipedElementLocator);
        // Get Element Name
        String elementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, elementName);
        //Execute the Double Tap Action
        try {
            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
                Dimension elementSize = getElementSize(driver.findElement(locator));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: doubleClickGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Double Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Double Tapping on Element [" + elementName + "]");
        return this;
    }

    //Double Tap on Button or Link without Swipe & Log Tapping Action
    public NativeAndroidActions doubleTap(By locator) {
        new NativeAndroidActions(driver).doubleTap(locator, null);
        return this;
    }

    /**
     * *********************************  Type Actions  *************************************
     */
    //Clear TextBox then Typing on it & Log Typing Action "Swipe Into Screen"
    public NativeAndroidActions type(LocatorType locatorType, String locatorValue, ScrollDirection direction, String text) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locatorType, locatorValue, direction);
        // Get Element Name
        String elementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, elementName);

        //Execute the Typing Tap Action
        clearText(driver, locatorType, locatorValue, elementName);
        writeText(driver, locatorType, locatorValue, elementName, text);
        return this;
    }

    //Clear TextBox then Typing on it & Log Typing Action "Swipe Into Screen"
    public NativeAndroidActions type(By locator, ScrollDirection direction, String text) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locator, direction);
        // Get Element Name
        String elementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, elementName);

        //Execute the Typing Tap Action
        clearText(driver, locator, elementName);
        writeText(driver, locator, elementName, text);
        return this;
    }

    //Clear TextBox then Typing on it & Log Typing Action "Swipe Into Element"
    public NativeAndroidActions type(LocatorType locatorType, String locatorValue, ScrollDirection direction, String text, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locatorType, locatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue);
        // Get Element Name
        String elementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, elementName);

        //Execute the Typing Tap Action
        clearText(driver, locatorType, locatorValue, elementName);
        writeText(driver, locatorType, locatorValue, elementName, text);
        return this;
    }

    //Clear TextBox then Typing on it & Log Typing Action "Swipe Into Element"
    public NativeAndroidActions type(By locator, ScrollDirection direction, String text, By swipedElementLocator) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locator, direction, swipedElementLocator);
        // Get Element Name
        String elementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, elementName);

        //Execute the Typing Tap Action
        clearText(driver, locator, elementName);
        writeText(driver, locator, elementName, text);
        return this;
    }

    //Clear TextBox then Typing on it & Log Typing Action "Without Swiping"
    public NativeAndroidActions type(By locator, String text) {
        new NativeAndroidActions(driver).type(locator, null, text);
        return this;
    }

    /**
     * *********************************  Read Text Actions  *************************************
     */
    //Get Text from Element by Swiping into Screen & Log the Text
    public String readText(LocatorType locatorType, String locatorValue, ScrollDirection direction) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locatorType, locatorValue, direction);

        //Execute the Read Text Action
        try {
            String text = getElementName(driver, locatorType, locatorValue);

            LogHelper.logInfoStep("Getting Text " + "[" + text + "] from Element located by [" + locatorType + " - " + locatorValue + "]");
            return text.replace("\n", "");
        } catch (Exception e) {
            LogHelper.logErrorStep("Cannot Get Text from Element located by [" + locatorType + " - " + locatorValue + "]", e);
            return null;
        }
    }

    //Get Text from Element by Swiping into Screen & Log the Text
    public String readText(By locator, ScrollDirection direction) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locator, direction);

        //Execute the Read Text Action
        try {
            String text = getElementName(driver, locator);

            LogHelper.logInfoStep("Getting Text " + "[" + text + "] from Element located by [" + locator.toString() + "]");
            return text.replace("\n", "");
        } catch (Exception e) {
            LogHelper.logErrorStep("Cannot Get Text from Element located by [" + locator.toString() + "]", e);
            return null;
        }
    }

    //Get Text from Element by Swiping into Element & Log the Text
    public String readText(LocatorType locatorType, String locatorValue, ScrollDirection direction, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locatorType, locatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue);

        //Execute the Read Text Action
        try {
            String text = getElementName(driver, locatorType, locatorValue);

            LogHelper.logInfoStep("Getting Text " + "[" + text + "] from Element located by [" + locatorType + " - " + locatorValue + "]");
            return text.replace("\n", "");
        } catch (Exception e) {
            LogHelper.logErrorStep("Cannot Get Text from Element located by [" + locatorType + " - " + locatorValue + "]", e);
            return null;
        }
    }

    //Get Text from Element by Swiping into Element & Log the Text
    public String readText(By locator, ScrollDirection direction, By swipedElementLocator) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locator, direction, swipedElementLocator);

        //Execute the Read Text Action
        try {
            String text = getElementName(driver, locator);

            LogHelper.logInfoStep("Getting Text " + "[" + text + "] from Element located by [" + locator.toString() + "]");
            return text.replace("\n", "");
        } catch (Exception e) {
            LogHelper.logErrorStep("Cannot Get Text from Element located by [" + locator.toString() + "]", e);
            return null;
        }
    }

    //Get Text from Element Without Swiping & Log the Text
    public String readText(By locator) {
        return new NativeAndroidActions(driver).readText(locator, null);
    }

    //Get Toast Text Without Swiping
    public String readToastText(By locator) {
        return NativeAndroidActionsHelper.getElementName(driver, locator);
    }

    /**
     * *********************************  Check Element Displayed Actions  *************************************
     */
    // Verify Element is Displayed on Page With swipe into screen
    public boolean isElementDisplayed(LocatorType locatorType, String locatorValue, ScrollDirection direction) {
        try {
            LogHelper.logInfoStep("Swiping (" + direction + ") into Screen");
            By locator = AppiumBy.androidUIAutomator(getUiAutomatorQuery(locatorType, locatorValue, direction));
            //Wait until Element is Displayed on Page
            getFluentWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
            //Get Element Name
            String elementName = getElementName(driver, locatorType, locatorValue);
            LogHelper.logInfoStep("The Element [" + elementName + "] is Displayed");
        } catch (TimeoutException e) {
            LogHelper.logInfoStep("The Element located by [" + locatorType + " - " + locatorValue + "] is not Displayed");
            return false;
        }
        return true;
    }

    // Verify Element is Displayed on Page With swipe into screen
    public boolean isElementDisplayed(By locator, ScrollDirection direction) {
        try {
            LogHelper.logInfoStep("Swiping (" + direction + ") into Screen");
            swipeTillElementDisplayed(driver, locator, direction);
            //Wait until Element is Displayed on Page
            getFluentWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
            //Get Element Name
            String elementName = getElementName(driver, locator);
            LogHelper.logInfoStep("The Element [" + elementName + "] is Displayed");
        } catch (TimeoutException e) {
            LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is not Displayed");
            return false;
        }
        return true;
    }

    // Verify Element is Displayed on Page With swipe into Element
    public boolean isElementDisplayed(LocatorType locatorType, String locatorValue, ScrollDirection direction, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        try {
            String swipedElementName = getElementName(driver, swipedElementLocatorType, swipedElementLocatorValue);
            LogHelper.logInfoStep("Swiping (" + direction + ") into Element [" + swipedElementName + "]");
            By locator = AppiumBy.androidUIAutomator(getUiAutomatorQuery(locatorType, locatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue));
            //Wait until Element is Displayed on Page
            getFluentWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
            //Get Element Name
            String elementName = getElementName(driver, locatorType, locatorValue);
            LogHelper.logInfoStep("The Element [" + elementName + "] is Displayed");
        } catch (TimeoutException e) {
            LogHelper.logInfoStep("The Element located by [" + locatorType + " - " + locatorValue + "] is not Displayed");
            return false;
        }
        return true;
    }

    // Verify Element is Displayed on Page With swipe into Element
    public boolean isElementDisplayed(By locator, ScrollDirection direction, By swipedElementLocator) {
        try {
            String swipedElementName = getElementName(driver, swipedElementLocator);
            LogHelper.logInfoStep("Swiping (" + direction + ") into Element [" + swipedElementName + "]");
            swipeTillElementDisplayed(driver, locator, direction, swipedElementLocator);
            //Wait until Element is Displayed on Page
            getFluentWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
            //Get Element Name
            String elementName = getElementName(driver, locator);
            LogHelper.logInfoStep("The Element [" + elementName + "] is Displayed");
        } catch (TimeoutException e) {
            LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is not Displayed");
            return false;
        }
        return true;
    }

    // Verify Element is Displayed on Page Without Swipe
    public boolean isElementDisplayed(By locator) {
        try {
            //Wait until Element is Displayed on Page
            getFluentWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
            //Get Element Name
            String elementName = getElementName(driver, locator);
            LogHelper.logInfoStep("The Element [" + elementName + "] is Displayed");
        } catch (TimeoutException e) {
            LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is not Displayed");
            return false;
        }
        return true;
    }

    /**
     * *********************************  Check Element Not Displayed Actions  *************************************
     */
    // Verify Element is Not Displayed on Page With swipe into screen
    public boolean isElementNotDisplayed(LocatorType locatorType, String locatorValue, ScrollDirection direction) {
        try {
            LogHelper.logInfoStep("Swiping (" + direction + ") into Screen");
            By locator = AppiumBy.androidUIAutomator(getUiAutomatorQuery(locatorType, locatorValue, direction));
            //Wait until Element is Displayed on Page
            getFluentWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
            LogHelper.logInfoStep("The Element located by [" + locatorType + " - " + locatorValue + "] is Not Displayed");
        } catch (TimeoutException e) {
            LogHelper.logInfoStep("The Element located by [" + locatorType + " - " + locatorValue + "] is Displayed");
            return false;
        }
        return true;
    }

    // Verify Element is Not Displayed on Page With swipe into screen
    public boolean isElementNotDisplayed(By locator, ScrollDirection direction) {
        // Swipe till Element is Displayed into View or till Timeout or till Reach end of Page
        try {
            String[] previousPageSource = {""};
            final int[] flag = {0};
            getSwipeWait(driver).until(f -> {
                if (flag[0] == 1) {
                    driver.findElement(AppiumBy.androidUIAutomator(getUiAutomatorQueryForward(direction)));
                }

                String currentPageSource = driver.getPageSource();
                if (currentPageSource.equalsIgnoreCase(previousPageSource[0]))
                    // The page source hasn't changed, so we've reached the bottom
                    throw new TimeoutException();
                else
                    previousPageSource[0] = currentPageSource;

                flag[0] = 1;
                return driver.findElement(locator).isDisplayed();
            });
            LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is Displayed");
            return false;
        } catch (TimeoutException e) {
            // Scroll in Opposite Direction till Element is Displayed into View or till Timeout or till Reach end of Page
            try {
                String[] previousPageSource = {""};
                getSwipeWait(driver).until(f -> {
                    driver.findElement(AppiumBy.androidUIAutomator(getUiAutomatorQueryBackward(direction)));

                    String currentPageSource = driver.getPageSource();
                    if (currentPageSource.equalsIgnoreCase(previousPageSource[0]))
                        // The page source hasn't changed, so we've reached the bottom
                        throw new TimeoutException();
                    else
                        previousPageSource[0] = currentPageSource;

                    return driver.findElement(locator).isDisplayed();
                });
                LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is Displayed");
                return false;
            } catch (TimeoutException f) {
                LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is not Displayed");
                return true;
            }
        }
    }

    // Verify Element is Not Displayed on Page With swipe into Element
    public boolean isElementNotDisplayed(LocatorType locatorType, String locatorValue, ScrollDirection direction, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        try {
            String swipedElementName = getElementName(driver, swipedElementLocatorType, swipedElementLocatorValue);
            LogHelper.logInfoStep("Swiping (" + direction + ") into Element [" + swipedElementName + "]");
            By locator = AppiumBy.androidUIAutomator(getUiAutomatorQuery(locatorType, locatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue));
            //Wait until Element is Displayed on Page
            getFluentWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
            LogHelper.logInfoStep("The Element located by [" + locatorType + " - " + locatorValue + "] is Not Displayed");
        } catch (TimeoutException e) {
            LogHelper.logInfoStep("The Element located by [" + locatorType + " - " + locatorValue + "] is Displayed");
            return false;
        }
        return true;
    }

    // Verify Element is Not Displayed on Page With swipe into Element
    public boolean isElementNotDisplayed(By locator, ScrollDirection direction, By swipedElementLocator) {
        // Swipe till Element is Displayed into View or till Timeout or till Reach end of Page
        try {
            String[] previousPageSource = {""};
            final int[] flag = {0};
            getSwipeWait(driver).until(f -> {
                if (flag[0] == 1) {
                    driver.findElement(AppiumBy.androidUIAutomator(getUiAutomatorQueryForward(direction, driver, swipedElementLocator)));
                }

                String currentPageSource = driver.getPageSource();
                if (currentPageSource.equalsIgnoreCase(previousPageSource[0]))
                    // The page source hasn't changed, so we've reached the bottom
                    throw new TimeoutException();
                else
                    previousPageSource[0] = currentPageSource;

                flag[0] = 1;
                return driver.findElement(locator).isDisplayed();
            });
            LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is Displayed");
            return false;
        } catch (TimeoutException e) {
            // Scroll in Opposite Direction till Element is Displayed into View or till Timeout or till Reach end of Page
            try {
                String[] previousPageSource = {""};
                getSwipeWait(driver).until(f -> {
                    driver.findElement(AppiumBy.androidUIAutomator(getUiAutomatorQueryBackward(direction, driver, swipedElementLocator)));

                    String currentPageSource = driver.getPageSource();
                    if (currentPageSource.equalsIgnoreCase(previousPageSource[0]))
                        // The page source hasn't changed, so we've reached the bottom
                        throw new TimeoutException();
                    else
                        previousPageSource[0] = currentPageSource;

                    return driver.findElement(locator).isDisplayed();
                });
                LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is Displayed");
                return false;
            } catch (TimeoutException f) {
                LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is not Displayed");
                return true;
            }
        }
    }

    // Verify Element is Not Displayed on Page Without Swipe
    public boolean isElementNotDisplayed(By locator) {
        try {
            //Wait until Element is Displayed on Page
            getFluentWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
            LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is Not Displayed");
        } catch (TimeoutException e) {
            LogHelper.logInfoStep("The Element located by [" + locator.toString() + "] is Displayed");
            return false;
        }
        return true;
    }

    /**
     * *********************************  Zoom In Actions  *************************************
     */
    //Zoom In Element by given distance "Swipe into Screen"
    public NativeAndroidActions zoomIn(LocatorType locatorType, String locatorValue, ScrollDirection direction, double zoomingPercentage) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locatorType, locatorValue, direction);
        // Get Element Name
        String targetElementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, targetElementName);

        //Execute the Zoom In Action
        try {

            getFluentWait(driver).until(f ->
            {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null)))).getId());
                param.put("percent", zoomingPercentage);

                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null))));
                param.put("width",elementSize.getWidth());
                param.put("height",elementSize.getHeight());

                driver.executeScript("mobile: pinchOpenGesture", param);

                return true;
            });

        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom In the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming In the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom In Element by given distance "Swipe into Screen"
    public NativeAndroidActions zoomIn(By locator, ScrollDirection direction, double zoomingPercentage) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locator, direction);
        // Get Element Name
        String targetElementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, targetElementName);

        //Execute the Zoom In Action
        try {
            getFluentWait(driver).until(f -> {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
                param.put("percent", zoomingPercentage);
                Dimension elementSize = getElementSize(driver.findElement(locator));
                param.put("width",elementSize.getWidth());
                param.put("height",elementSize.getHeight());

                driver.executeScript("mobile: pinchOpenGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom In the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming In the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom In Element by given distance "Swipe into Element"
    public NativeAndroidActions zoomIn(LocatorType locatorType, String locatorValue, ScrollDirection direction, double zoomingPercentage, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locatorType, locatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue);
        // Get Element Name
        String targetElementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, targetElementName);

        //Execute the Zoom In Action
        try {
            getFluentWait(driver).until(f -> {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null)))).getId());
                param.put("percent", zoomingPercentage);
                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null))));
                param.put("width",elementSize.getWidth());
                param.put("height",elementSize.getHeight());

                driver.executeScript("mobile: pinchOpenGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom In the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming In the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom In Element by given distance "Swipe into Element"
    public NativeAndroidActions zoomIn(By locator, ScrollDirection direction, double zoomingPercentage, By swipedElementLocator) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locator, direction, swipedElementLocator);
        // Get Element Name
        String targetElementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, targetElementName);

        //Execute the Zoom In Action
        try {
            getFluentWait(driver).until(f -> {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
                param.put("percent", zoomingPercentage);
                Dimension elementSize = getElementSize(driver.findElement(locator));
                param.put("width",elementSize.getWidth());
                param.put("height",elementSize.getHeight());

                driver.executeScript("mobile: pinchOpenGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom In the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming In the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom In Element by given distance Without Swipe
    public NativeAndroidActions zoomIn(By locator, double zoomingPercentage) {
        new NativeAndroidActions(driver).zoomIn(locator, null, zoomingPercentage);
        return this;
    }

    /**
     * *********************************  Zoom Out Actions  *************************************
     */
    //Zoom Out Element by given distance "Swipe into Screen"
    public NativeAndroidActions zoomOut(LocatorType locatorType, String locatorValue, ScrollDirection direction, double zoomingPercentage) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locatorType, locatorValue, direction);
        // Get Element Name
        String targetElementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, targetElementName);

        //Execute the Zoom Out Action
        try {
            getFluentWait(driver).until(f -> {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null)))).getId());
                param.put("percent", zoomingPercentage);
                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null))));
                param.put("width",elementSize.getWidth());
                param.put("height",elementSize.getHeight());

                driver.executeScript("mobile: pinchCloseGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom Out the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming Out the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom Out Element by given distance "Swipe into Screen"
    public NativeAndroidActions zoomOut(By locator, ScrollDirection direction, double zoomingPercentage) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(locator, direction);
        // Get Element Name
        String targetElementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, targetElementName);

        //Execute the Zoom Out Action
        try {
            getFluentWait(driver).until(f -> {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
                param.put("percent", zoomingPercentage);
                Dimension elementSize = getElementSize(driver.findElement(locator));
                param.put("width",elementSize.getWidth());
                param.put("height",elementSize.getHeight());

                driver.executeScript("mobile: pinchCloseGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom Out the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming Out the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom Out Element by given distance "Swipe into Element"
    public NativeAndroidActions zoomOut(LocatorType locatorType, String locatorValue, ScrollDirection direction, double zoomingPercentage, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locatorType, locatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue);
        // Get Element Name
        String targetElementName = getElementName(driver, locatorType, locatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locatorType, locatorValue, targetElementName);

        //Execute the Zoom Out Action
        try {
            getFluentWait(driver).until(f -> {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null)))).getId());
                param.put("percent", zoomingPercentage);
                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(locatorType, locatorValue, null))));
                param.put("width",elementSize.getWidth());
                param.put("height",elementSize.getHeight());

                driver.executeScript("mobile: pinchCloseGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom Out the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming Out the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom Out Element by given distance "Swipe into Element"
    public NativeAndroidActions zoomOut(By locator, ScrollDirection direction, double zoomingPercentage, By swipedElementLocator) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(locator, direction, swipedElementLocator);
        // Get Element Name
        String targetElementName = getElementName(driver, locator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, locator, targetElementName);

        //Execute the Zoom Out Action
        try {
            getFluentWait(driver).until(f -> {
                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
                param.put("percent", zoomingPercentage);
                Dimension elementSize = getElementSize(driver.findElement(locator));
                param.put("width",elementSize.getWidth());
                param.put("height",elementSize.getHeight());

                driver.executeScript("mobile: pinchCloseGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom Out the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming Out the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom Out Element by given distance Without Swipe
    public NativeAndroidActions zoomOut(By locator, double zoomingPercentage) {
        new NativeAndroidActions(driver).zoomOut(locator, null, zoomingPercentage);
        return this;
    }

    /**
     * *********************************  Drag & Drop Actions  *************************************
     */
    //Drag the Source Element then Drop it to the Destination Element "Swipe into Screen"
    public NativeAndroidActions dragAndDrop(By targetLocator, By destinationLocator, ScrollDirection direction) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(targetLocator, direction);
        // Get Element Name
        String targetElementName = getElementName(driver, targetLocator);
        String destinationElementName = getElementName(driver, destinationLocator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, targetLocator, targetElementName);

        //Execute the Drag&Drop Action
        try {
            getFluentWait(driver).until(f -> {

                Dimension elementSize = getElementSize(driver.findElement(targetLocator));
                Point endPoint = getElementCenter(driver, driver.findElement(destinationLocator));

                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(targetLocator)).getId());
                param.put("startX", elementSize.getWidth()/2);
                param.put("startY", elementSize.getHeight()/2);
                param.put("endX", endPoint.getX());
                param.put("endY", endPoint.getY());

                driver.executeScript("mobile: dragGesture", param);

                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Drag the Element [" + targetElementName + "] then Drop it to Element [" + destinationElementName + "]", e);
        }
        LogHelper.logInfoStep("Dragging the Element [" + targetElementName + "] then Dropping it to Element [" + destinationElementName + "]");
        return this;
    }

    //Drag the Source Element then Drop it to the Destination Element "Swipe into Screen"
    public NativeAndroidActions dragAndDrop(LocatorType targetLocatorType, String targetLocatorValue, LocatorType destinationLocatorType, String destinationLocatorValue, ScrollDirection direction) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoScreen(targetLocatorType, targetLocatorValue, direction);
        // Get Element Name
        String targetElementName = getElementName(driver, targetLocatorType, targetLocatorValue);
        String destinationElementName = getElementName(driver, destinationLocatorType, destinationLocatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, targetLocatorType, targetLocatorValue, targetElementName);

        //Execute the Drag&Drop Action
        try {
            getFluentWait(driver).until(f -> {
                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(targetLocatorType, targetLocatorValue, null))));

                Point endPoint = getElementCenter(driver, driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(destinationLocatorType, destinationLocatorValue, null))));

                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(targetLocatorType, targetLocatorValue, null)))).getId());
                param.put("startX", elementSize.getWidth()/2);
                param.put("startY", elementSize.getHeight()/2);
                param.put("endX", endPoint.getX());
                param.put("endY", endPoint.getY());

                driver.executeScript("mobile: dragGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Drag the Element [" + targetElementName + "] then Drop it to Element [" + destinationElementName + "]", e);
        }
        LogHelper.logInfoStep("Dragging the Element [" + targetElementName + "] then Dropping it to Element [" + destinationElementName + "]");
        return this;
    }

    //Drag the Source Element then Drop it to the Destination Element "Swipe into Element"
    public NativeAndroidActions dragAndDrop(LocatorType targetLocatorType, String targetLocatorValue, LocatorType destinationLocatorType, String destinationLocatorValue, ScrollDirection direction, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(targetLocatorType, targetLocatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue);
        // Get Element Name
        String targetElementName = getElementName(driver, targetLocatorType, targetLocatorValue);
        String destinationElementName = getElementName(driver, destinationLocatorType, destinationLocatorValue);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, targetLocatorType, targetLocatorValue, targetElementName);

        //Execute the Drag&Drop Action
        try {
            getFluentWait(driver).until(f -> {
                Dimension elementSize = getElementSize(driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(targetLocatorType, targetLocatorValue, null))));
                Point endPoint = getElementCenter(driver, driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(destinationLocatorType, destinationLocatorValue, null))));

                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(AppiumBy.androidUIAutomator(
                        getUiAutomatorQuery(targetLocatorType, targetLocatorValue, null)))).getId());
                param.put("startX", elementSize.getWidth()/2);
                param.put("startY", elementSize.getHeight()/2);
                param.put("endX", endPoint.getX());
                param.put("endY", endPoint.getY());

                driver.executeScript("mobile: dragGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Drag the Element [" + targetElementName + "] then Drop it to Element [" + destinationElementName + "]", e);
        }
        LogHelper.logInfoStep("Dragging the Element [" + targetElementName + "] then Dropping it to Element [" + destinationElementName + "]");
        return this;
    }

    //Drag the Source Element then Drop it to the Destination Element "Swipe into Element"
    public NativeAndroidActions dragAndDrop(By targetLocator, By destinationLocator, ScrollDirection direction, By swipedElementLocator) {
        //Swipe "if needed" Till Element is Displayed into View
        swipeIntoElement(targetLocator, direction, swipedElementLocator);
        // Get Element Name
        String targetElementName = getElementName(driver, targetLocator);
        String destinationElementName = getElementName(driver, destinationLocator);
        //Check if Element is Enabled on Page (Not Disabled)
        checkElementEnabled(driver, targetLocator, targetElementName);

        //Execute the Drag&Drop Action
        try {
            getFluentWait(driver).until(f -> {
                Dimension elementSize = getElementSize(driver.findElement(targetLocator));
                Point endPoint = getElementCenter(driver, driver.findElement(destinationLocator));

                Map<String, Object> param = new HashMap<>();

                param.put("elementId", ((RemoteWebElement) driver.findElement(targetLocator)).getId());
                param.put("startX", elementSize.getWidth()/2);
                param.put("startY", elementSize.getHeight()/2);
                param.put("endX", endPoint.getX());
                param.put("endY", endPoint.getY());

                driver.executeScript("mobile: dragGesture", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Drag the Element [" + targetElementName + "] then Drop it to Element [" + destinationElementName + "]", e);
        }
        LogHelper.logInfoStep("Dragging the Element [" + targetElementName + "] then Dropping it to Element [" + destinationElementName + "]");
        return this;
    }

    //Drag the Source Element then Drop it to the Destination Element Without Swipe
    public NativeAndroidActions dragAndDrop(By targetLocator, By destinationLocator) {
        new NativeAndroidActions(driver).dragAndDrop(targetLocator, destinationLocator, null);
        return this;
    }

    /**
     * *********************************  Swiping Actions  *************************************
     */
    public NativeAndroidActions singleSwipe(By locator, Direction direction) {
        // If No Swipe Needed, Check if Element is Displayed into View
        checkElementDisplayed(driver, locator);

        Map<String, Object> param = new HashMap<>();
        Dimension elementSize = getElementSize(driver.findElement(locator));

        param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
        param.put("direction", direction.toString());
        param.put("width",elementSize.getWidth());
        param.put("height",elementSize.getHeight());
        param.put("percent", 0.75);

        driver.executeScript("mobile: swipeGesture", param);

        LogHelper.logInfoStep("Single Swiping (" + direction + ") into Screen");

        return this;
    }

    /**
     * *********************************  Scrolling Actions  *************************************
     */
    //Scroll Into Screen till reach a Button or Link in a given direction
    public NativeAndroidActions swipeIntoScreen(LocatorType locatorType, String locatorValue, ScrollDirection direction) {
        // If No Swipe Needed, Check if Element is Displayed into View
        if (direction == null)
            checkElementDisplayed(driver, locatorType, locatorValue);

            //If Swipe is Needed, Execute the Swipe Actions
        else {
            // Check Element is Displayed into View, Otherwise Swipe till Displayed into View
            LogHelper.logInfoStep("Swiping (" + direction + ") into Screen");
            swipeTillElementDisplayed(driver, locatorType, locatorValue, direction);
        }
        return this;
    }

    //Scroll Into Screen till reach a Button or Link in a given direction
    public NativeAndroidActions swipeIntoScreen(By locator, ScrollDirection direction) {
        // If No Swipe Needed, Check if Element is Displayed into View
        if (direction == null)
            checkElementDisplayed(driver, locator);

            //If Swipe is Needed, Execute the Swipe Actions
        else {
            // Check Element is Displayed into View, Otherwise Swipe till Displayed into View
            LogHelper.logInfoStep("Swiping (" + direction + ") into Screen");
            swipeTillElementDisplayed(driver, locator, direction);
        }
        return this;
    }

    //Scroll Into an Element till reach a Button or Link in a given direction
    public NativeAndroidActions swipeIntoElement(LocatorType locatorType, String locatorValue, ScrollDirection direction, LocatorType swipedElementLocatorType, String swipedElementLocatorValue) {
        // If No Swipe Needed, Check if Element is Displayed into View
        if (direction == null)
            checkElementDisplayed(driver, locatorType, locatorValue);

            //If Swipe is Needed, Execute the Swipe Actions
        else {
            // Check Element is Displayed into View, Otherwise Swipe till Displayed into View
            String swipedElementName = getElementName(driver, swipedElementLocatorType, swipedElementLocatorValue);
            LogHelper.logInfoStep("Swiping (" + direction + ") into Element [" + swipedElementName + "]");
            swipeTillElementDisplayed(driver, locatorType, locatorValue, direction, swipedElementLocatorType, swipedElementLocatorValue);
        }
        return this;
    }

    //Scroll Into an Element till reach a Button or Link in a given direction
    public NativeAndroidActions swipeIntoElement(By locator, ScrollDirection direction, By swipedElementLocator) {
        // If No Swipe Needed, Check if Element is Displayed into View
        if (direction == null)
            checkElementDisplayed(driver, locator);

            //If Swipe is Needed, Execute the Swipe Actions
        else {
            // Check Element is Displayed into View, Otherwise Swipe till Displayed into View
            String swipedElementName = getElementName(driver, swipedElementLocator);
            LogHelper.logInfoStep("Swiping (" + direction + ") into Element [" + swipedElementName + "]");
            swipeTillElementDisplayed(driver, locator, direction, swipedElementLocator);
        }
        return this;
    }

    /**
     * *********************************  Android Keys Actions  *************************************
     */
    public NativeAndroidActions pressOnAndroidKey(AndroidKey key) {
        ((AndroidDriver) driver).pressKey(new KeyEvent(key));
        return this;
    }
}