package yehiaEngine.elementActions;

import io.appium.java_client.AppiumDriver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import yehiaEngine.elementActions.Helpers.NativeIOSActionsHelper;
import yehiaEngine.loggers.LogHelper;

import java.util.HashMap;
import java.util.Map;

import static yehiaEngine.elementActions.Helpers.NativeIOSActionsHelper.*;
import static yehiaEngine.elementActions.Helpers.WaitsManager.getFluentWait;
import static yehiaEngine.elementActions.Helpers.WaitsManager.getSwipeWait;

public class NativeIOSActions {

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

    public NativeIOSActions(AppiumDriver driver) {
        this.driver = driver;
    }

    /**
     * *********************************  Tap Actions  ****************************************
     */

    // Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeIOSActions tap(By locator, ScrollDirection direction) {
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
                driver.executeScript("mobile: tap", param);

                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Tapping on Element [" + elementName + "]");
        return this;
    }

    // Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeIOSActions tap(By locator, ScrollDirection direction, By swipedElementLocator) {
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

                driver.executeScript("mobile: tap", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Tapping on Element [" + elementName + "]");
        return this;
    }

    //Tap on Button or Link without Swipe & Log Tapping Action
    public NativeIOSActions tap(By locator) {
        new NativeIOSActions(driver).tap(locator,null);
        return this;
    }

    /**
     * *********************************  Long Tap Actions  ****************************************
     */

    // Long Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeIOSActions longTap(By locator, ScrollDirection direction) {
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
                param.put("duration", 2);

                Dimension elementSize = getElementSize(driver.findElement(locator));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: touchAndHold", param);

        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Long Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Long Tapping on Element [" + elementName + "]");
        return this;
    }

    //Long Tap on Button or Link by Swiping into swiped element & Log Tapping Action
    public NativeIOSActions longTap(By locator, ScrollDirection direction, By swipedElementLocator) {
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
                param.put("duration", 2);

                Dimension elementSize = getElementSize(driver.findElement(locator));

                param.put("x",elementSize.getWidth()/2);
                param.put("y",elementSize.getHeight()/2);

                driver.executeScript("mobile: touchAndHold", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Long Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Long Tapping on Element [" + elementName + "]");
        return this;
    }

    //Long Tap on Button or Link without Swipe & Log Tapping Action
    public NativeIOSActions longTap(By locator) {
        new NativeIOSActions(driver).longTap(locator, null);
        return this;
    }

    /**
     * *********************************  Double Tap Actions  ****************************************
     */

    // Double Tap on Button or Link by Swiping into Screen & Log Tapping Action
    public NativeIOSActions doubleTap(By locator, ScrollDirection direction) {
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

                driver.executeScript("mobile: doubleTap", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Double Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Double Tapping on Element [" + elementName + "]");
        return this;
    }

    //Double Tap on Button or Link by Swiping into swiped element & Log Tapping Action
    public NativeIOSActions doubleTap(By locator, ScrollDirection direction, By swipedElementLocator) {
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

                driver.executeScript("mobile: doubleTap", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Double Tap on Element [" + elementName + "]", e);
        }
        LogHelper.logInfoStep("Double Tapping on Element [" + elementName + "]");
        return this;
    }

    //Double Tap on Button or Link without Swipe & Log Tapping Action
    public NativeIOSActions doubleTap(By locator) {
        new NativeIOSActions(driver).doubleTap(locator, null);
        return this;
    }

    /**
     * *********************************  Type Actions  *************************************
     */

    //Clear TextBox then Typing on it & Log Typing Action "Swipe Into Screen"
    public NativeIOSActions type(By locator, ScrollDirection direction, String text) {
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
    public NativeIOSActions type(By locator, ScrollDirection direction, String text, By swipedElementLocator) {
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
    public NativeIOSActions type(By locator, String text) {
        new NativeIOSActions(driver).type(locator, null, text);
        return this;
    }

    /**
     * *********************************  Read Text Actions  *************************************
     */
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
        return new NativeIOSActions(driver).readText(locator, null);
    }

    //Get Toast Text Without Swiping
    public String readToastText(By locator) {
        return NativeIOSActionsHelper.getElementName(driver, locator);
    }

    /**
     * *********************************  Check Element Displayed Actions  *************************************
     */

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
    public boolean isElementNotDisplayed(By locator, ScrollDirection direction) {
        // Swipe till Element is Displayed into View or till Timeout or till Reach end of Page
        try {
            String[] previousPageSource = {""};
            final int[] flag = {0};
            getSwipeWait(driver).until(f -> {
                if (flag[0] == 1) {

                    Map<String, Object> param = new HashMap<>();
                    param.put("velocity",500);
                    if (direction == ScrollDirection.VERTICAL)
                        param.put("direction", "up");
                    else if (direction == ScrollDirection.HORIZONTAL)
                        param.put("direction","left");
                    driver.executeScript("mobile: swipe", param);

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

                    Map<String, Object> param = new HashMap<>();
                    param.put("velocity",500);
                    if (direction == ScrollDirection.VERTICAL)
                        param.put("direction", "down");
                    else if (direction == ScrollDirection.HORIZONTAL)
                        param.put("direction","right");
                    driver.executeScript("mobile: swipe", param);

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
    public boolean isElementNotDisplayed(By locator, ScrollDirection direction, By swipedElementLocator) {
        // Swipe till Element is Displayed into View or till Timeout or till Reach end of Page
        try {
            String[] previousPageSource = {""};
            final int[] flag = {0};
            getSwipeWait(driver).until(f -> {
                if (flag[0] == 1) {

                    Map<String, Object> param = new HashMap<>();
                    param.put("elementId",((RemoteWebElement)driver.findElement(swipedElementLocator)).getId());
                    param.put("velocity",500);
                    if (direction == ScrollDirection.VERTICAL)
                        param.put("direction", "up");
                    else if (direction == ScrollDirection.HORIZONTAL)
                        param.put("direction","left");
                    driver.executeScript("mobile: swipe", param);

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

                    Map<String, Object> param = new HashMap<>();
                    param.put("elementId",((RemoteWebElement)driver.findElement(swipedElementLocator)).getId());
                    param.put("velocity",500);
                    if (direction == ScrollDirection.VERTICAL)
                        param.put("direction", "down");
                    else if (direction == ScrollDirection.HORIZONTAL)
                        param.put("direction","right");
                    driver.executeScript("mobile: swipe", param);

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
    public NativeIOSActions zoomIn(By locator, ScrollDirection direction) {
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
                param.put("scale", 1.5);
                param.put("velocity", 2.2);

                driver.executeScript("mobile: pinch", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom In the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming In the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom In Element by given distance "Swipe into Element"
    public NativeIOSActions zoomIn(By locator, ScrollDirection direction, By swipedElementLocator) {
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
                param.put("scale", 1.5);
                param.put("velocity", 2.2);

                driver.executeScript("mobile: pinch", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom In the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming In the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom In Element by given distance Without Swipe
    public NativeIOSActions zoomIn(By locator) {
        new NativeIOSActions(driver).zoomIn(locator, null);
        return this;
    }

    /**
     * *********************************  Zoom Out Actions  *************************************
     */
    //Zoom Out Element by given distance "Swipe into Screen"
    public NativeIOSActions zoomOut(By locator, ScrollDirection direction) {
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
                param.put("scale", 0.5);
                param.put("velocity", -2.2);

                driver.executeScript("mobile: pinch", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom Out the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming Out the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom Out Element by given distance "Swipe into Element"
    public NativeIOSActions zoomOut(By locator, ScrollDirection direction , By swipedElementLocator) {
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
                param.put("scale", 0.5);
                param.put("velocity", -2.2);

                driver.executeScript("mobile: pinch", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Zoom Out the Element [" + targetElementName + "]", e);
        }
        LogHelper.logInfoStep("Zooming Out the Element [" + targetElementName + "]");
        return this;
    }

    //Zoom Out Element by given distance Without Swipe
    public NativeIOSActions zoomOut(By locator) {
        new NativeIOSActions(driver).zoomOut(locator, null);
        return this;
    }

    /**
     * *********************************  Drag & Drop Actions  *************************************
     */
    //Drag the Source Element then Drop it to the Destination Element "Swipe into Screen"
    public NativeIOSActions dragAndDrop(By targetLocator, By destinationLocator, ScrollDirection direction) {
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

                Point startPoint = getElementCenter(driver, driver.findElement(targetLocator));
                Point endPoint = getElementCenter(driver, driver.findElement(destinationLocator));

                Map<String, Object> param = new HashMap<>();

                param.put("duration",1);
                param.put("fromX", startPoint.getX());
                param.put("fromY", startPoint.getY());
                param.put("toX", endPoint.getX());
                param.put("toY", endPoint.getY());

                driver.executeScript("mobile: dragFromToForDuration", param);

                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Drag the Element [" + targetElementName + "] then Drop it to Element [" + destinationElementName + "]", e);
        }
        LogHelper.logInfoStep("Dragging the Element [" + targetElementName + "] then Dropping it to Element [" + destinationElementName + "]");
        return this;
    }

    //Drag the Source Element then Drop it to the Destination Element "Swipe into Element"
    public NativeIOSActions dragAndDrop(By targetLocator, By destinationLocator, ScrollDirection direction, By swipedElementLocator) {
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
                Point startPoint = getElementCenter(driver, driver.findElement(targetLocator));
                Point endPoint = getElementCenter(driver, driver.findElement(destinationLocator));

                Map<String, Object> param = new HashMap<>();

                param.put("duration",1);
                param.put("fromX", startPoint.getX());
                param.put("fromY", startPoint.getY());
                param.put("toX", endPoint.getX());
                param.put("toY", endPoint.getY());

                driver.executeScript("mobile: dragFromToForDuration", param);
                return true;
            });
        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Drag the Element [" + targetElementName + "] then Drop it to Element [" + destinationElementName + "]", e);
        }
        LogHelper.logInfoStep("Dragging the Element [" + targetElementName + "] then Dropping it to Element [" + destinationElementName + "]");
        return this;
    }

    //Drag the Source Element then Drop it to the Destination Element Without Swipe
    public NativeIOSActions dragAndDrop(By targetLocator, By destinationLocator) {
        new NativeIOSActions(driver).dragAndDrop(targetLocator, destinationLocator, null);
        return this;
    }

    /**
     * *********************************  Swiping Actions  *************************************
     */
    public NativeIOSActions singleSwipe(By locator, Direction direction) {
        // If No Swipe Needed, Check if Element is Displayed into View
        checkElementDisplayed(driver, locator);

        Map<String, Object> param = new HashMap<>();

        param.put("velocity",500);
        param.put("direction", direction.toString());

        driver.executeScript("mobile: swipe", param);

        LogHelper.logInfoStep("Single Swiping (" + direction + ") into Screen");

        return this;
    }

    /**
     * *********************************  Scrolling Actions  *************************************
     */
    //Scroll Into Screen till reach a Button or Link in a given direction
    public NativeIOSActions swipeIntoScreen(By locator, ScrollDirection direction) {
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
    public NativeIOSActions swipeIntoElement(By locator, ScrollDirection direction, By swipedElementLocator) {
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

}