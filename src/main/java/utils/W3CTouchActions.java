package utils;

import io.appium.java_client.AppiumDriver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

import static utils.PropertiesManager.getPropertiesValue;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.Origin.*;

public class W3CTouchActions {
    AppiumDriver driver;

    @AllArgsConstructor
    @Getter
    public enum Direction {
        LEFT (1, 0),
        RIGHT (-1, 0),
        UP (0, 1),
        DOWN (0, -1);

        private int x;
        private int y;
    }

    public W3CTouchActions(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public W3CTouchActions tab (By targetLocator)
    {
        Waits.getSwipeWait(driver).until(f->{
            Point startPoint = getElementCenter(driver.findElement(targetLocator));
            Point endPoint = null;
            Sequence sequence = singleFingerSwipe("finger-1",startPoint,endPoint);
            driver.perform(List.of(sequence));
            return true;
        });
        return this;
    }

    public W3CTouchActions longTab (By targetLocator)
    {
        Waits.getSwipeWait(driver).until(f->{
            Point start = getElementCenter(driver.findElement(targetLocator));
            PointerInput finger = new PointerInput(TOUCH,"finger-1");

            Sequence sequence = new Sequence(finger,0);
            sequence.addAction(finger.createPointerMove(Duration.ofMillis(0),viewport(),start.getX(),start.getY()));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            sequence.addAction(new Pause(finger,Duration.ofMillis(1000)));
            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(List.of(sequence));

            return true;
        });
        return this;
    }

    public W3CTouchActions swipeIntoScreen(By targetLocator, Direction direction)
    {
        Point startPoint;
        Point endPoint;
        Dimension screenSize = getScreenSize ();
        var x = screenSize.getWidth () / 2;
        var y = screenSize.getHeight () / 2;
        startPoint = new Point(x,y);

        var a = x+ direction.getX()*Integer.parseInt(getPropertiesValue("SwipeDistance"));
        var b = y+ direction.getY()*Integer.parseInt(getPropertiesValue("SwipeDistance"));
        endPoint = getCorrectedCoordinates(null,new Point(a,b));

       Waits.getSwipeWait(driver).until(f->{
            Sequence sequence = singleFingerSwipe("finger-1",startPoint,endPoint);
            driver.perform(List.of(sequence));
            return driver.findElement(targetLocator).isDisplayed();
        });
        return this;
    }

    public W3CTouchActions swipeIntoElement(By swippedElementLocator, Direction direction, By targetLocator)
    {
        Waits.getSwipeWait(driver).until(f->{
            Point startPoint;
            Point endPoint;

            startPoint = getElementCenter(driver.findElement(swippedElementLocator));

            var a = startPoint.getX()+ direction.getX()*Integer.parseInt(getPropertiesValue("SwipeDistance"));
            var b = startPoint.getY()+ direction.getY()*Integer.parseInt(getPropertiesValue("SwipeDistance"));
            endPoint = getCorrectedCoordinates(driver.findElement(swippedElementLocator),new Point(a,b));

            Sequence sequence = singleFingerSwipe("finger-1",startPoint,endPoint);
            driver.perform(List.of(sequence));
            return  driver.findElement(targetLocator).isDisplayed();
        });
        return this;
    }

    public W3CTouchActions dragAndDrop(By sourceLocator , By destinationLocator)
    {
        Waits.getSwipeWait(driver).until(f-> {

            Point startPoint = getElementCenter(driver.findElement(sourceLocator));
            Point endPoint = getElementCenter(driver.findElement(destinationLocator));

            Sequence sequence = singleFingerSwipe("finger-1",startPoint,endPoint);
            driver.perform(List.of(sequence));
            return true;
        });
        return this;
    }

    public W3CTouchActions zoomIn(By locator, int distance)
    {
        Waits.getSwipeWait(driver).until(f-> {

            Point elementCenter = getElementCenter(driver.findElement(locator));
            Point start1 = new Point(elementCenter.getX()-50,elementCenter.getY());
            Point start2 = new Point(elementCenter.getX()+50,elementCenter.getY());

            var x = start1.getX()+ Direction.LEFT.getX()*distance;
            var y = start1.getY()+ Direction.LEFT.getY()*distance;
            Point end1 = new Point(x,y);

            var a = start1.getX()+ Direction.RIGHT.getX()*distance;
            var b = start1.getY()+ Direction.RIGHT.getY()*distance;
            Point end2 = new Point(a, b);

            Sequence sequence1 = singleFingerSwipe("finger-1",start1,end1);
            Sequence sequence2 = singleFingerSwipe("finger-2",start2,end2);

            driver.perform(List.of(sequence1,sequence2));
            return true;
        });
        return this;
    }

    public W3CTouchActions zoomOut(By locator, int distance)
    {
        Waits.getSwipeWait(driver).until(f-> {

            Point elementCenter = getElementCenter(driver.findElement(locator));
            Point start1 = new Point(elementCenter.getX()-50,elementCenter.getY());
            Point start2 = new Point(elementCenter.getX()+50,elementCenter.getY());

            var x = start1.getX()+ Direction.LEFT.getX()*distance;
            var y = start1.getY()+ Direction.LEFT.getY()*distance;
            Point end1 = new Point(x,y);

            var a = start1.getX()+ Direction.RIGHT.getX()*distance;
            var b = start1.getY()+ Direction.RIGHT.getY()*distance;
            Point end2 = new Point(a, b);

            Sequence sequence1 = singleFingerSwipe("finger-1",end1,start1);
            Sequence sequence2 = singleFingerSwipe("finger-2",end2,start2);

            driver.perform(List.of(sequence1,sequence2));
            return true;
        });
        return this;
    }

    public boolean isElementPresent(By targetLocator,Direction direction)
    {
        Point startPoint;
        Point endPoint;

        Dimension screenSize = getScreenSize ();
        var x = screenSize.getWidth () / 2;
        var y = screenSize.getHeight () / 2;
        startPoint = new Point(x,y);

        var a = x+ direction.getX()*Integer.parseInt(getPropertiesValue("SwipeDistance"));
        var b = y+ direction.getY()*Integer.parseInt(getPropertiesValue("SwipeDistance"));
        endPoint = getCorrectedCoordinates(null,new Point(a,b));

        try{
            Waits.getSwipeWait(driver).until(g->{
                Sequence sequence = singleFingerSwipe("finger-1",startPoint,endPoint);
                driver.perform(List.of(sequence));
                return (driver.findElement(targetLocator).isDisplayed());
            });
        }catch (TimeoutException e){
            return false;
        }
        return true;
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
