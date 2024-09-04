package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;
import static utils.PropertiesManager.getPropertiesValue;
import static utils.RandomDataGenerator.generateInteger;
import static utils.Screenshot.takeElementScreenshot;
import static utils.Waits.getFluentWait;

public class NativeAndroidActionBot {

    AppiumDriver driver;

    static String textBoxesPath = "src/test/resources/Screenshots/TextBoxes/";
    static String pressedButtonsPath ="src/test/resources/Screenshots/PressedButtons/";
    static String retrievedTextPath ="src/test/resources/Screenshots/RetrievedTexts/";

    public enum ScrollDirection {
        HORIZONTAL,VERTICAL
    }
    public enum LocatorType {
        TEXT,RESOURCE_ID,ACCESSIBILITY_ID
    }

    public NativeAndroidActionBot(AppiumDriver driver)
    {
        this.driver = driver;
    }


    public NativeAndroidActionBot tab (LocatorType type, String locator, ScrollDirection direction)
    {
        getFluentWait(driver).until(f->
        {
            System.out.println("Clicking On " + driver.findElement(AppiumBy.androidUIAutomator(
                    getChildTextQuery(type,locator,direction,"TextView"))).getText()+" Button");
            try {
                takeElementScreenshot(driver,driver.findElement(AppiumBy.androidUIAutomator(
                        getLocatorQuery(type,locator,direction))),pressedButtonsPath,generateInteger());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Actions(driver).moveToElement(driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(type,locator,direction)))).click().perform();
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot tab (By locator)
    {
        getFluentWait(driver).until(f->
        {
            try {
                takeElementScreenshot(driver,driver.findElement(locator),pressedButtonsPath,generateInteger());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Actions(driver).moveToElement(driver.findElement(locator)).click().perform();
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot longTab (LocatorType type, String locator, ScrollDirection direction)
    {
        getFluentWait(driver).until(f->
        {
            System.out.println("Long Clicking On " + driver.findElement(AppiumBy.androidUIAutomator(
                    getChildTextQuery(type,locator,direction,"TextView"))).getText()+" Button");
            try {
                takeElementScreenshot(driver,driver.findElement(AppiumBy.androidUIAutomator(
                        getLocatorQuery(type,locator,direction))),pressedButtonsPath,generateInteger());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Actions(driver).moveToElement(driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(type,locator,direction)))).clickAndHold().perform();
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot longTab (By locator)
    {
        getFluentWait(driver).until(f->
        {
            try {
                takeElementScreenshot(driver,driver.findElement(locator),pressedButtonsPath,generateInteger());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Actions(driver).moveToElement(driver.findElement(locator)).clickAndHold().perform();
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot doubleTab (LocatorType type, String locator, ScrollDirection direction)
    {
        getFluentWait(driver).until(f->
        {
            System.out.println("Double Clicking On " + driver.findElement(AppiumBy.androidUIAutomator(
                    getChildTextQuery(type,locator,direction,"TextView"))).getText()+" Button");
            try {
                takeElementScreenshot(driver,driver.findElement(AppiumBy.androidUIAutomator(
                        getLocatorQuery(type,locator,direction))),pressedButtonsPath,generateInteger());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Actions(driver).moveToElement(driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(type,locator,direction)))).doubleClick().perform();
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot doubleTab (By locator)
    {
        getFluentWait(driver).until(f->
        {
            try {
                takeElementScreenshot(driver,driver.findElement(locator),pressedButtonsPath,generateInteger());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Actions(driver).moveToElement(driver.findElement(locator)).doubleClick().perform();
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot type (LocatorType type, String locator, ScrollDirection direction, String text) throws IOException {
        getFluentWait(driver).until(f->
        {
            driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(type,locator,direction))).clear();
            driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(type,locator,direction))).sendKeys(text);
            return true;
        });
        System.out.println("Typing " + text);
        takeElementScreenshot(driver,driver.findElement(AppiumBy.androidUIAutomator(
                getLocatorQuery(type,locator,direction))),textBoxesPath,generateInteger());        return this;
    }

    public NativeAndroidActionBot type (By locator,String text) throws IOException {
        Waits.getFluentWait(driver).until(f -> {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
            return true;
        });
        System.out.println("Typing " + text);
        takeElementScreenshot(driver,locator,textBoxesPath,generateInteger());
        return this;
    }

    public String readText (LocatorType type,String locator,ScrollDirection direction) throws IOException {
        getFluentWait(driver).until(f->
        {
            driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(type,locator,direction))).getText();
            return true;
        });
        takeElementScreenshot(driver,driver.findElement(AppiumBy.androidUIAutomator(
                getLocatorQuery(type,locator,direction))),retrievedTextPath,generateInteger());
        return driver.findElement(AppiumBy.androidUIAutomator(
                getLocatorQuery(type,locator,direction))).getText();
    }

    public String readChildText (LocatorType type,String locator,ScrollDirection direction,String widgetClassName) throws IOException {
        getFluentWait(driver).until(f->
        {
            driver.findElement(AppiumBy.androidUIAutomator(
                    getChildTextQuery(type,locator,direction,widgetClassName))).getText();
            return true;
        });
        takeElementScreenshot(driver,driver.findElement(AppiumBy.androidUIAutomator(
                getChildTextQuery(type,locator,direction,"TextView"))),retrievedTextPath,generateInteger());
        return driver.findElement(AppiumBy.androidUIAutomator(
                getChildTextQuery(type,locator,direction,widgetClassName))).getText();
    }

    public String readText (By locator) throws IOException {
        getFluentWait(driver).until(f -> {
            driver.findElement(locator).getText();
            return true;
        });
        takeElementScreenshot(driver,locator,retrievedTextPath,generateInteger());
        return driver.findElement(locator).getText().replace("\n","");
    }

    public NativeAndroidActionBot swipeIntoScreen (LocatorType type, String targetLocator, ScrollDirection direction)
    {
        getFluentWait(driver).until(f->
        {
            driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(type,targetLocator,direction)));
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot swipeIntoElement(LocatorType scrollableType, String scrollableLocator, LocatorType targetType, String targetLocator, ScrollDirection direction)
    {
        getFluentWait(driver).until(f->
        {
            driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(scrollableType,scrollableLocator,targetType,targetLocator,direction)));
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot swipeIntoElementAndTap(LocatorType scrollableType, String scrollableLocator, LocatorType targetType, String targetLocator, ScrollDirection direction)
    {
        getFluentWait(driver).until(f->
        {
            System.out.println("Clicking On " + driver.findElement(AppiumBy.androidUIAutomator(
                    getChildTextQuery(targetType,targetLocator,direction,"TextView"))).getText()+" Button");
            try {
                takeElementScreenshot(driver,driver.findElement(AppiumBy.androidUIAutomator(
                        getLocatorQuery(targetType,targetLocator,direction))),pressedButtonsPath,generateInteger());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Actions(driver).moveToElement(driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(scrollableType,scrollableLocator,targetType,targetLocator,direction)))).click().perform();
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot swipeIntoElementAndType(LocatorType scrollableType, String scrollableLocator, LocatorType targetType, String targetLocator, ScrollDirection direction, String text) throws IOException {
        getFluentWait(driver).until(f->
        {
            driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(scrollableType,scrollableLocator,targetType,targetLocator,direction))).clear();
            new Actions(driver).moveToElement(driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(scrollableType,scrollableLocator,targetType,targetLocator,direction)))).sendKeys(text).perform();
            return true;
        });
        System.out.println("Typing " + text);
        takeElementScreenshot(driver,driver.findElement(AppiumBy.androidUIAutomator(
                getLocatorQuery(targetType,targetLocator,direction))),textBoxesPath,generateInteger());
        return this;
    }

    public String swipeIntoElementAndReadText(LocatorType scrollableType, String scrollableLocator, LocatorType targetType, String targetLocator, ScrollDirection direction) throws IOException {
        getFluentWait(driver).until(f->
        {
            driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(scrollableType,scrollableLocator,targetType,targetLocator,direction))).getText();
            return true;
        });
        takeElementScreenshot(driver,driver.findElement(AppiumBy.androidUIAutomator(
                getLocatorQuery(targetType,targetLocator,direction))),retrievedTextPath,generateInteger());
        return driver.findElement(AppiumBy.androidUIAutomator(
                getLocatorQuery(scrollableType,scrollableLocator,targetType,targetLocator,direction))).getText();
    }

    public NativeAndroidActionBot zoomIn (LocatorType type, String locator, ScrollDirection direction, int distance)
    {
        getFluentWait(driver).until(f->
        {
            Point elementCenter = getElementCenter(driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(type,locator,direction))));
            Point start1 = new Point(elementCenter.getX()-50,elementCenter.getY());
            Point start2 = new Point(elementCenter.getX()+50,elementCenter.getY());

            var x = start1.getX()+ W3CTouchActions.Direction.LEFT.getX()*distance;
            var y = start1.getY()+ W3CTouchActions.Direction.LEFT.getY()*distance;
            Point end1 = new Point(x,y);

            var a = start1.getX()+ W3CTouchActions.Direction.RIGHT.getX()*distance;
            var b = start1.getY()+ W3CTouchActions.Direction.RIGHT.getY()*distance;
            Point end2 = new Point(a, b);

            Sequence sequence1 = singleFingerSwipe("finger-1",start1,end1);
            Sequence sequence2 = singleFingerSwipe("finger-2",start2,end2);

            driver.perform(List.of(sequence1,sequence2));
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot zoomOut (LocatorType type, String locator, ScrollDirection direction, int distance)
    {
        getFluentWait(driver).until(f->
        {
            Point elementCenter = getElementCenter(driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(type,locator,direction))));
            Point start1 = new Point(elementCenter.getX()-50,elementCenter.getY());
            Point start2 = new Point(elementCenter.getX()+50,elementCenter.getY());

            var x = start1.getX()+ W3CTouchActions.Direction.LEFT.getX()*distance;
            var y = start1.getY()+ W3CTouchActions.Direction.LEFT.getY()*distance;
            Point end1 = new Point(x,y);

            var a = start1.getX()+ W3CTouchActions.Direction.RIGHT.getX()*distance;
            var b = start1.getY()+ W3CTouchActions.Direction.RIGHT.getY()*distance;
            Point end2 = new Point(a, b);

            Sequence sequence1 = singleFingerSwipe("finger-1",end1,start1);
            Sequence sequence2 = singleFingerSwipe("finger-2",end2,start2);

            driver.perform(List.of(sequence1,sequence2));
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot dragAndDrop(LocatorType sourceLocatorType, String sourceLocator, ScrollDirection sourceDirection, LocatorType destinationLocatorType, String destinationLocator, ScrollDirection destinationDirection)
    {
        Waits.getFluentWait(driver).until(f-> {
            Point startPoint = getElementCenter(driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(sourceLocatorType,sourceLocator,sourceDirection))));
            Point endPoint = getElementCenter(driver.findElement(AppiumBy.androidUIAutomator(
                    getLocatorQuery(destinationLocatorType,destinationLocator,destinationDirection))));

            Sequence sequence = singleFingerSwipe("finger-1",startPoint,endPoint);
            driver.perform(List.of(sequence));
            return true;
        });
        return this;
    }

    public NativeAndroidActionBot dragAndDrop(By sourceLocator, By destinationLocator)
    {
        Waits.getFluentWait(driver).until(f-> {
            Point startPoint = getElementCenter(driver.findElement(sourceLocator));
            Point endPoint = getElementCenter(driver.findElement(destinationLocator));

            Sequence sequence = singleFingerSwipe("finger-1",startPoint,endPoint);
            driver.perform(List.of(sequence));
            return true;
        });
        return this;
    }

    public boolean isElementDisplayed(By locator){
        try{
            Waits.getFluentWait(driver).until(f -> {
                driver.findElement(locator).isDisplayed();
                return true;
            });
        }catch(TimeoutException e){
            return false;
        }
        return true;
    }

    public boolean isElementPresent(LocatorType type,String locator,ScrollDirection direction)
    {
        try{
            getFluentWait(driver).until(f->
            {
                driver.findElement(AppiumBy.androidUIAutomator(getLocatorQuery(type,locator,direction)));
                return true;
            });

        }catch (TimeoutException e)
        {
            return false;
        }
        return true;
    }

    private String getLocatorQuery(LocatorType type, String locator, ScrollDirection direction)
    {
        String query = null;
        switch(type)
        {
            case LocatorType.TEXT:
            {
                if(direction ==ScrollDirection.VERTICAL)
                {
                    query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                            "new UiSelector().textContains(\"" + locator + "\"))";
                }

                else if(direction==ScrollDirection.HORIZONTAL)
                {

                    query = "new UiScrollable(new UiSelector()).setAsHorizontalList().scrollIntoView("
                            + "new UiSelector().textContains(\"" + locator + "\"))";
                }
                break;
            }

            case LocatorType.ACCESSIBILITY_ID:
            {
                if(direction ==ScrollDirection.VERTICAL)
                {
                    query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                            "new UiSelector().description(\"" + locator + "\"))";
                }

                else if(direction==ScrollDirection.HORIZONTAL)
                {

                    query = "new UiScrollable(new UiSelector()).setAsHorizontalList().scrollIntoView("
                            + "new UiSelector().description(\"" + locator + "\"))";
                }
                break;
            }

            case LocatorType.RESOURCE_ID:
            {
                if(direction ==ScrollDirection.VERTICAL)
                {
                    query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                            "new UiSelector().resourceId(\"" + locator + "\"))";
                }

                else if(direction==ScrollDirection.HORIZONTAL)
                {

                    query = "new UiScrollable(new UiSelector()).setAsHorizontalList().scrollIntoView("
                            + "new UiSelector().resourceId(\"" + locator + "\"))";
                }
                break;
            }

        }
        return query;
    }

    private String getLocatorQuery(LocatorType scrollableType, String scrollableLocator, LocatorType targetType, String targetLocator, ScrollDirection direction)
    {
        String query = null;
        if (direction==ScrollDirection.VERTICAL)
        {
            switch (scrollableType)
            {
                case TEXT:
                {
                    if(targetType==LocatorType.TEXT)
                    {
                        query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                + "new UiSelector().textMatches(\".*"+scrollableLocator+"\")).textContains(\"" + targetLocator + "\")";
                    }
                    else if(targetType==LocatorType.ACCESSIBILITY_ID)
                    {
                        query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                + "new UiSelector().textMatches(\".*"+scrollableLocator+"\")).description(\"" + targetLocator + "\")";
                    }
                    else if(targetType==LocatorType.RESOURCE_ID)
                    {
                        query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                + "new UiSelector().textMatches(\".*"+scrollableLocator+"\")).resourceId(\"" + targetLocator + "\")";
                    }
                    break;
                }

                case ACCESSIBILITY_ID:
                {
                    if(targetType==LocatorType.TEXT)
                    {
                        query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                + "new UiSelector().descriptionMatches(\".*"+scrollableLocator+"\")).textContains(\"" + targetLocator + "\")";
                    }
                    else if(targetType==LocatorType.ACCESSIBILITY_ID)
                    {
                        query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                + "new UiSelector().descriptionMatches(\".*"+scrollableLocator+"\")).description(\"" + targetLocator + "\")";
                    }
                    else if(targetType==LocatorType.RESOURCE_ID)
                    {
                        query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                + "new UiSelector().descriptionMatches(\".*"+scrollableLocator+"\")).resourceId(\"" + targetLocator + "\")";
                    }
                    break;
                }

                case RESOURCE_ID:
                {
                    if(targetType==LocatorType.TEXT)
                    {
                        query = "new UiScrollable(new UiSelector().resourceIdMatches(\".*"+scrollableLocator+"\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+targetLocator+"\"))";
                    }
                    else if(targetType==LocatorType.ACCESSIBILITY_ID)
                    {
                        query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                + "new UiSelector().resourceIdMatches(\".*"+scrollableLocator+"\")).description(\"" + targetLocator + "\")";
                    }
                    else if(targetType==LocatorType.RESOURCE_ID)
                    {
                        query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                + "new UiSelector().resourceIdMatches(\".*"+scrollableLocator+"\")).resourceId(\"" + targetLocator + "\")";
                    }
                    break;
                }
            }
        }

        else if(direction==ScrollDirection.HORIZONTAL)
        {
            switch (scrollableType)
            {
                case TEXT:
                {
                    if(targetType==LocatorType.TEXT)
                    {

                        query = "new UiScrollable(new UiSelector().textMatches(\".*"+scrollableLocator+"\")).setAsHorizontalList().scrollIntoView(new UiSelector().textContains(\""+targetLocator+"\"))";
                    }
                    else if(targetType==LocatorType.ACCESSIBILITY_ID)
                    {
                        query = "new UiScrollable(new UiSelector().textMatches(\".*"+scrollableLocator+"\")).setAsHorizontalList().scrollIntoView(new UiSelector().description(\""+targetLocator+"\"))";
                    }
                    else if(targetType==LocatorType.RESOURCE_ID)
                    {
                        query = "new UiScrollable(new UiSelector().textMatches(\".*"+scrollableLocator+"\")).setAsHorizontalList().scrollIntoView(new UiSelector().resourceID(\""+targetLocator+"\"))";
                    }
                    break;
                }

                case ACCESSIBILITY_ID:
                {
                    if(targetType==LocatorType.TEXT)
                    {

                        query = "new UiScrollable(new UiSelector().descriptionMatches(\".*"+scrollableLocator+"\")).setAsHorizontalList().scrollIntoView(new UiSelector().textContains(\""+targetLocator+"\"))";
                    }
                    else if(targetType==LocatorType.ACCESSIBILITY_ID)
                    {
                        query = "new UiScrollable(new UiSelector().descriptionMatches(\".*"+scrollableLocator+"\")).setAsHorizontalList().scrollIntoView(new UiSelector().description(\""+targetLocator+"\"))";
                    }
                    else if(targetType==LocatorType.RESOURCE_ID)
                    {
                        query = "new UiScrollable(new UiSelector().descriptionMatches(\".*"+scrollableLocator+"\")).setAsHorizontalList().scrollIntoView(new UiSelector().resourceID(\""+targetLocator+"\"))";
                    }
                    break;
                }

                case RESOURCE_ID:
                {
                    if(targetType==LocatorType.TEXT)
                    {

                      query = "new UiScrollable(new UiSelector().resourceIdMatches(\".*"+scrollableLocator+"\")).setAsHorizontalList().scrollIntoView(new UiSelector().textContains(\""+targetLocator+"\"))";
                    }
                    else if(targetType==LocatorType.ACCESSIBILITY_ID)
                    {
                        query = "new UiScrollable(new UiSelector().resourceIdMatches(\".*"+scrollableLocator+"\")).setAsHorizontalList().scrollIntoView(new UiSelector().description(\""+targetLocator+"\"))";
                    }
                    else if(targetType==LocatorType.RESOURCE_ID)
                    {
                        query = "new UiScrollable(new UiSelector().resourceIdMatches(\".*"+scrollableLocator+"\")).setAsHorizontalList().scrollIntoView(new UiSelector().resourceID(\""+targetLocator+"\"))";
                    }
                    break;
                }
            }
        }

        return query;
    }

    private String getChildTextQuery(LocatorType type, String locator, ScrollDirection direction,String widgetClassName)
    {
        String query = null;
        switch(type)
        {
            case LocatorType.TEXT:
            {
                if(direction ==ScrollDirection.VERTICAL)
                {
                    query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                            "new UiSelector().textContains(\"" + locator + "\").childSelector(new UiSelector().className(android.widget."+widgetClassName+")))";
                }

                else if(direction==ScrollDirection.HORIZONTAL)
                {

                    query = "new UiScrollable(new UiSelector()).setAsHorizontalList.scrollIntoView(" +
                            "new UiSelector().textContains(\"" + locator + "\").childSelector(new UiSelector().className(android.widget."+widgetClassName+")))";
                }
                break;
            }

            case LocatorType.ACCESSIBILITY_ID:
            {
                if(direction ==ScrollDirection.VERTICAL)
                {
                    query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                            "new UiSelector().description(\"" + locator + "\").childSelector(new UiSelector().className(android.widget."+widgetClassName+")))";
                }

                else if(direction==ScrollDirection.HORIZONTAL)
                {

                    query = "new UiScrollable(new UiSelector()).setAsHorizontalList.scrollIntoView(" +
                            "new UiSelector().description(\"" + locator + "\").childSelector(new UiSelector().className(android.widget."+widgetClassName+")))";
                }
                break;
            }

            case LocatorType.RESOURCE_ID:
            {
                if(direction ==ScrollDirection.VERTICAL)
                {
                    query = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                            "new UiSelector().resourceId(\"" + locator + "\").childSelector(new UiSelector().className(android.widget."+widgetClassName+")))";
                }

                else if(direction==ScrollDirection.HORIZONTAL)
                {

                    query = "new UiScrollable(new UiSelector()).setAsHorizontalList.scrollIntoView(" +
                            "new UiSelector().resourceId(\"" + locator + "\").childSelector(new UiSelector().className(android.widget."+widgetClassName+")))";
                }
                break;
            }

        }
        return query;
    }

    private Sequence singleFingerSwipe(String fingerName, Point start , Point end)
    {
        PointerInput finger = new PointerInput(TOUCH,fingerName);
        Sequence sequence = new Sequence(finger,0);

        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0),viewport(),start.getX(),start.getY()));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        if (end != null)
        {
            sequence.addAction(new Pause(finger,Duration.ofMillis(500)));
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(Long.parseLong(getPropertiesValue("SwipeRate"))),viewport(),end.getX(),end.getY()));
        }
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        return sequence;
    }

    private Point getElementCenter (WebElement element) {
        Point location = element.getLocation ();
        Dimension size = element.getSize ();
        int x = (size.getWidth () / 2) + location.getX ();
        int y = (size.getHeight () / 2) + location.getY ();
        return getCorrectedCoordinates (element, new Point (x, y));
    }

    private Point getCorrectedCoordinates (WebElement element, Point point) {
        Dimension screenSize = getScreenSize ();
        int x = point.getX ();
        int y = point.getY ();
        int w = screenSize.getWidth ();
        int h = screenSize.getHeight ();

        if (element != null) {
            final var size = element.getSize ();
            final var location = element.getLocation ();
            w = size.getWidth () + location.getX ();
            h = size.getHeight () + location.getY ();
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
        return new Point (x, y);
    }

    private Dimension getScreenSize () {
        return driver.manage ()
                .window ()
                .getSize ();
    }

}
