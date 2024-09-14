package pagesByNativeAndroidUIScrollable;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static utils.NativeAndroidActionBot.LocatorType.*;
import static utils.NativeAndroidActionBot.ScrollDirection.*;

public class CartPage extends HomePage {

    //Variables
    String productName;

    //Locators
    By removeFromCartButton;
    By removeFromCartSwipe = AppiumBy.accessibilityId("test-Delete");
    By backToProductsButton = AppiumBy.accessibilityId("test-CONTINUE SHOPPING");
    By checkOutButton = AppiumBy.accessibilityId("test-CHECKOUT");
    By productItem;

    //Constructor
    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    //Actions
    private void defineLocatorsByProductName(String productName)
    {
        this.productName = productName;
        productItem = AppiumBy.xpath("//*[@text='"+productName+"']/ancestor::*[@content-desc='test-Item']");
        removeFromCartButton = AppiumBy.xpath("//*[@text='"+productName+"']/parent::*/following-sibling::*[@content-desc='test-Price']//*[@content-desc='test-REMOVE']");
    }

    @Step("Remove Product From Cart By Button")
    public CartPage removeProductFromCartByButton(String productName)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(TEXT,productName,VERTICAL)
                .tab(removeFromCartButton);
        return this;
    }

    @Step("Remove Product From Cart By Swipe")
    public CartPage removeProductFromCartBySwipe(String productName)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(TEXT,productName,VERTICAL)
                .swipeIntoElement(TEXT,productName,ACCESSIBILITY_ID,"test-Delete",HORIZONTAL)
                .tab(removeFromCartSwipe);
        return this;
    }

    @Step("Proceed To Fill User Info")
    public CartCheckOutInfoPage proceedToFillUserInfo()
    {
        action.tab(ACCESSIBILITY_ID,"test-CHECKOUT",VERTICAL);
        return new CartCheckOutInfoPage(driver);
    }

    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductPage()
    {
        action.tab(ACCESSIBILITY_ID,"test-CONTINUE SHOPPING",VERTICAL);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Assert Product Is Added To Cart")
    public CartPage assertProductIsAddedToCart(String productName)
    {
        defineLocatorsByProductName(productName);
       Assert.assertTrue(action.isElementPresent(TEXT,productName,VERTICAL));
        return this;
    }

    @Step("Assert Product Is Removed To Cart")
    public CartPage assertProductIsRemovedFromCart(String productName)
    {
        defineLocatorsByProductName(productName);
        Assert.assertFalse(action.isElementPresent(TEXT,productName,VERTICAL));
        return this;
    }
}
