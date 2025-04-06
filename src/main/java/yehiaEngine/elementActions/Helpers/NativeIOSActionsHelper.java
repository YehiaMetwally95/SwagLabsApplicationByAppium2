package yehiaEngine.elementActions.Helpers;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import yehiaEngine.elementActions.NativeIOSActions;
import yehiaEngine.loggers.LogHelper;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;
import static yehiaEngine.elementActions.Helpers.WaitsManager.getFluentWait;
import static yehiaEngine.managers.PropertiesManager.getPropertiesValue;

public class NativeIOSActionsHelper {

    /**
     * *********************************  Helper Methods  *************************************
     */
    // Write Text on TextBox and Log the Action
    public static void writeText(AppiumDriver driver,By locator, String elementName, String text) {
        // Write Text on TextBox Element using the Selenium sendKeys method
        try {
            getFluentWait(driver).until(f -> {
                driver.findElement(locator).sendKeys(text);
                return true;
            });
            LogHelper.logInfoStep("Typing [" + text + "] on Element [" + elementName + "]");

        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Type [" + text + "] on Element [" + elementName + "]", e);
        }
    }

    // Clear Text from TextBox and Log the Action
    public static void clearText(AppiumDriver driver,By locator, String elementName) {
        // Clear Text on TextBox Element using the Selenium sendKeys method
        try {
            getFluentWait(driver).until(f -> {
                driver.findElement(locator).clear();
                return true;
            });
            LogHelper.logInfoStep("Clearing Text from Element [" + elementName + "]");

        } catch (Exception e) {
            LogHelper.logErrorStep("Failed to Clear Text from Element [" + elementName + "]", e);
        }
    }

    //Build the sequence of swipe by one finger according to W3c Touch Actions
    public static Sequence singleFingerSwipe(String fingerName, Point start, Point end) {
        PointerInput finger = new PointerInput(TOUCH, fingerName);
        Sequence sequence = new Sequence(finger, 0);

        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), viewport(), start.getX(), start.getY()));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        if (end != null) {
            sequence.addAction(new Pause(finger, Duration.ofMillis(500)));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(Long.parseLong(getPropertiesValue("SwipeRate"))), viewport(), end.getX(), end.getY()));
        }
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        return sequence;
    }

    //Get the Center Coordinate of the Element
    public static Point getElementCenter(AppiumDriver driver,WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        int x = (size.getWidth() / 2) + location.getX();
        int y = (size.getHeight() / 2) + location.getY();
        return getCorrectedCoordinates(driver,element, new Point(x, y));
    }

    //Get the Dimensions of Element Size
    public static Dimension getElementSize(WebElement element) {
        return element.getSize();
    }

    //Get the Center Coordinate of the Element and verify that it's not outside the screen
    public static Point getCorrectedCoordinates(AppiumDriver driver,WebElement element, Point point) {
        Dimension screenSize = getScreenSize(driver);
        int x = point.getX();
        int y = point.getY();
        int w = screenSize.getWidth();
        int h = screenSize.getHeight();

        if (element != null) {
            final var size = element.getSize();
            final var location = element.getLocation();
            w = size.getWidth() + location.getX();
            h = size.getHeight() + location.getY();
        }

        if (x >= w) {
            x = w - 5;
        }
        if (y >= h) {
            y = h - 5;
        }
        if (x < 0) {
            x = 5;
        }
        if (y < 0) {
            y = 5;
        }
        return new Point(x, y);
    }

    //Get the Dimensions of Screen Size
    public static Dimension getScreenSize(AppiumDriver driver) {
        return driver.manage()
                .window()
                .getSize();
    }

    /**
     * *********************************  Locating Element Methods Using By Locator  *************************************
     */
    // Check if Element is Displayed on Page
    public static void checkElementDisplayed(AppiumDriver driver,By locator)
    {
        try {
            getFluentWait(driver).until(f -> driver.findElement(locator).isDisplayed());
        } catch (TimeoutException e) {
            LogHelper.logErrorStep("The Element located by [" + locator.toString() + "] is not Displayed", e);
        }
    }

    // Check if Element is Displayed on Page with Swiping into Screen
    public static void swipeTillElementDisplayed (AppiumDriver driver,By locator, NativeIOSActions.ScrollDirection direction)  {
        String previousPageSource = "";
        String currentPageSource;
        try {
            // Loop to scroll and check if the element is visible
            while (true) {
                try {
                    // Try to find the element
                    if (driver.findElement(locator).isDisplayed())
                        break;
                    else
                        throw new NoSuchElementException("");

                } catch (NoSuchElementException e) {

                    //the element is not found, perform a scroll action in Opposite Direction
                    currentPageSource = driver.getPageSource();
                    if (currentPageSource.equalsIgnoreCase(previousPageSource))
                        // The page source hasn't changed, so we've reached the bottom
                        throw new TimeoutException();
                    else
                        previousPageSource = currentPageSource;
                }

                // If the element is not found, perform a scroll action using UIAutomator
                Map<String, Object> param = new HashMap<>();
                param.put("velocity",500);
                if (direction == NativeIOSActions.ScrollDirection.VERTICAL)
                    param.put("direction", "up");
                else if (direction == NativeIOSActions.ScrollDirection.HORIZONTAL)
                    param.put("direction","left");
                driver.executeScript("mobile: swipe", param);
            }

        } catch (TimeoutException e) {
            try{
                // Loop to scroll and check if the element is visible
                previousPageSource = "";
                while (true) {
                    try {
                        // Try to find the element
                        if (driver.findElement(locator).isDisplayed())
                            break;
                        else
                            throw new NoSuchElementException("");

                    } catch (NoSuchElementException ex) {

                        //the element is not found, perform a scroll action in Opposite Direction
                        currentPageSource = driver.getPageSource();
                        if (currentPageSource.equalsIgnoreCase(previousPageSource))
                        // The page source hasn't changed, so we've reached the bottom
                        {
                            throw new TimeoutException();
                        }
                        else
                            previousPageSource = currentPageSource;

                        // If the element is not found, perform a scroll action using UIAutomator
                        Map<String, Object> param = new HashMap<>();
                        param.put("velocity",500);
                        if (direction == NativeIOSActions.ScrollDirection.VERTICAL)
                            param.put("direction", "down");
                        else if (direction == NativeIOSActions.ScrollDirection.HORIZONTAL)
                            param.put("direction","right");
                        driver.executeScript("mobile: swipe", param);
                    }
                }
            }catch (TimeoutException ext){
                LogHelper.logErrorStep("The Element located by [" + locator.toString() + "] is not Displayed", ext);
            }
        }
    }

    // Check if Element is Displayed on Page with Swiping into Element
    public static void swipeTillElementDisplayed (AppiumDriver driver,By targetlocator, NativeIOSActions.ScrollDirection direction, By swipedElementLocator)  {
        String previousPageSource = "";
        String currentPageSource;
        try {
            // Loop to scroll and check if the element is visible
            while (true) {
                try {
                    // Try to find the element
                    if (driver.findElement(targetlocator).isDisplayed())
                        break;
                    else
                        throw new NoSuchElementException("");

                } catch (NoSuchElementException e) {

                    //the element is not found, perform a scroll action in Opposite Direction
                    currentPageSource = driver.getPageSource();
                    if (currentPageSource.equalsIgnoreCase(previousPageSource))
                        // The page source hasn't changed, so we've reached the bottom
                        throw new TimeoutException();
                    else
                        previousPageSource = currentPageSource;
                }

                // If the element is not found, perform a scroll action using UIAutomator
                Map<String, Object> param = new HashMap<>();
                param.put("elementId",((RemoteWebElement)driver.findElement(swipedElementLocator)).getId());
                param.put("velocity",500);
                if (direction == NativeIOSActions.ScrollDirection.VERTICAL)
                    param.put("direction", "up");
                else if (direction == NativeIOSActions.ScrollDirection.HORIZONTAL)
                    param.put("direction","left");
                driver.executeScript("mobile: swipe", param);
            }

        } catch (TimeoutException e) {
            try{
                // Loop to scroll and check if the element is visible
                previousPageSource = "";
                while (true) {
                    try {
                        // Try to find the element
                        if (driver.findElement(targetlocator).isDisplayed())
                            break;
                        else
                            throw new NoSuchElementException("");

                    } catch (NoSuchElementException ex) {

                        //the element is not found, perform a scroll action in Opposite Direction
                        currentPageSource = driver.getPageSource();
                        if (currentPageSource.equalsIgnoreCase(previousPageSource))
                        // The page source hasn't changed, so we've reached the bottom
                        {
                            throw new TimeoutException();
                        }
                        else
                            previousPageSource = currentPageSource;

                        // If the element is not found, perform a scroll action using UIAutomator
                        Map<String, Object> param = new HashMap<>();
                        param.put("elementId",((RemoteWebElement)driver.findElement(swipedElementLocator)).getId());
                        param.put("velocity",500);
                        if (direction == NativeIOSActions.ScrollDirection.VERTICAL)
                            param.put("direction", "down");
                        else if (direction == NativeIOSActions.ScrollDirection.HORIZONTAL)
                            param.put("direction","right");
                        driver.executeScript("mobile: swipe", param);
                    }
                }
            }catch (TimeoutException ext){
                LogHelper.logErrorStep("The Element located by [" + targetlocator.toString() + "] is not Displayed", ext);
            }
        }
    }

    //Check if Element is Enabled on Page
    public static void checkElementEnabled(AppiumDriver driver,By Locator, String elementName)
    {
        try {
            getFluentWait(driver).until(f -> driver.findElement(Locator).isEnabled());
        } catch (TimeoutException e) {
            LogHelper.logErrorStep("The Element [" + elementName + "] is not Enabled", e);
        }
    }

    // Get the Element Name
    public static String getElementName(AppiumDriver driver,By locator) {
        try {
            String elementName = getMainElementText(driver,locator);
            if (elementName != null && !elementName.isEmpty()) {
                return elementName;
            }

            //Classes Related to Android
            String[] elementTypes = {
                    ".//XCUIElementTypeTextField",
                    ".//XCUIElementTypeTextView",
//                        ".//XCUIElementTypeButton",
//                        ".//XCUIElementTypeStaticText",
//                        ".//XCUIElementTypeImage",
//                        ".//XCUIElementTypeTable",
//                        ".//XCUIElementTypeCollectionView",
//                        ".//XCUIElementTypeScrollView"
            };

            for (String elementType : elementTypes) {
                elementName = getChildElementText(driver, locator, elementType);
                if (elementName != null && !elementName.isEmpty()) {
                    return elementName;
                }
            }

        } catch (NoSuchElementException e) {
            // Log the exception and return the locator as fallback
            LogHelper.logErrorStep("Element not found: " + locator.toString());
        }

        return locator.toString();
    }

    // Helper method to get the main element text
    private static String getMainElementText(AppiumDriver driver,By locator) {
        return driver.findElement(locator).getText();
    }

    // Helper method to get the child element text
    private static String getChildElementText(AppiumDriver driver,By locator, String childXPath) {
        try {
            WebElement element = driver.findElement(locator);
            return element.findElement(By.xpath(childXPath)).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
