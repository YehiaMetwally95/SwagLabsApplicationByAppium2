package pagesByNativeAndroidUIScrollable;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import java.io.IOException;

import static utils.NativeAndroidActionBot.LocatorType.ACCESSIBILITY_ID;
import static utils.NativeAndroidActionBot.ScrollDirection.VERTICAL;

public class CartCheckOutCompletePage extends HomePage {
    //Locators
    By backHomeLocator = AppiumBy.accessibilityId("test-BACK HOME");
    By successfulMassage = AppiumBy.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]");

    //Constructor
    public CartCheckOutCompletePage(AppiumDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductsPage()
    {
        action.tab(ACCESSIBILITY_ID, "test-BACK HOME", VERTICAL);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Assert CheckOut Successful Massage")
    public CartCheckOutCompletePage assertCheckoutSuccessfulMessage(String text) throws IOException {
        Assert.assertEquals(action.readText(successfulMassage),text);
        return this;
    }
}
