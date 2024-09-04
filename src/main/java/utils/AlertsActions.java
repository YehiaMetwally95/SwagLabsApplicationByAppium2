package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;

public class AlertsActions {

    public static void acceptAlert(WebDriver driver)
    {
        Waits.getFluentWait(driver).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public static void dismissAlert(WebDriver driver)
    {
        Waits.getFluentWait(driver).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public static void typeTextInAlert(WebDriver driver,String text)
    {
        Waits.getFluentWait(driver).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
    }

    public static void typeTextInAlert(WebDriver driver,String text1,String text2)
    {
        Waits.getFluentWait(driver).until(ExpectedConditions.alertIsPresent());
        Actions action = new Actions(driver);
        driver.switchTo().alert().sendKeys(text1);
        action.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
        driver.switchTo().alert().sendKeys(text2);
    }

    public static String getTextInAlert(WebDriver driver)
    {
        Waits.getFluentWait(driver).until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }
}
