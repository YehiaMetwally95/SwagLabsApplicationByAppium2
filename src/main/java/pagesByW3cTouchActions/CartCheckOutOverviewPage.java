package pagesByW3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.W3CTouchActions;

import java.io.IOException;

import static utils.CustomSoftAssert.softAssert;
import static utils.W3CTouchActions.Direction.*;

public class CartCheckOutOverviewPage extends HomePage{
    //Variables
    String productName;

    //Locators
    By productItem;
    By productPriceLocator;
    By productDescriptionLocator;
    By productQuantityLocator;

    By removeFromCartSwipe = AppiumBy.accessibilityId("test-Delete");
    By backToProductsButton = AppiumBy.accessibilityId("test-CANCEL");
    By finishButton = AppiumBy.accessibilityId("test-FINISH");
    By paymentInfoLocator = AppiumBy.xpath("(//*[@text=\"Payment Information:\"]/following-sibling::android.widget.TextView)[1]");
    By shippingInfoLocator = AppiumBy.xpath("(//*[@text=\"Shipping Information:\"]/following-sibling::android.widget.TextView)[1]");
    By totalPriceLocator = AppiumBy.xpath("//*[contains(@text,'Total')]");

    //Constructor
    public CartCheckOutOverviewPage(AppiumDriver driver) {
        super(driver);
    }

    //Actions
    private void defineLocatorsByProductName(String productName)
    {
        this.productName = productName;
        productItem = AppiumBy.xpath("//*[@text= '"+productName+"']/ancestor::*[@content-desc='test-Item']");
        productPriceLocator = AppiumBy.xpath("//*[@text= '"+productName+"']/parent::*/following-sibling::*[@content-desc='test-Price']//android.widget.TextView");
        productDescriptionLocator = AppiumBy.xpath("//*[@text= '"+productName+"']/following-sibling::android.widget.TextView");
        productQuantityLocator = AppiumBy.xpath("//*[@text= '"+productName+"']/ancestor::*[@content-desc='test-Description']/preceding-sibling::*[@content-desc='test-Amount']/android.widget.TextView");
    }

    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductsPage()
    {
        action.tab(backToProductsButton);
        return new ProductsPage(driver);
    }

    @Step("Remove Product From Cart By Swipe")
    public CartCheckOutOverviewPage removeProductFromCartBySwipe(String productName)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoElement(productItem,LEFT,removeFromCartSwipe)
                .tab(removeFromCartSwipe);
        return this;
    }

    @Step("Finish Cart Checkout")
    public CartCheckOutCompletePage finishCartCheckOut()
    {
        action.tab(finishButton);
        return new CartCheckOutCompletePage(driver);
    }

    //Validations
    @Step("Assert Product Is Added To Cart")
    public CartCheckOutOverviewPage assertProductIsAddedToCart(String productName)
    {
        defineLocatorsByProductName(productName);
        Assert.assertTrue(action.isElementPresent(productItem,DOWN)||action.isElementPresent(productItem,UP));
        return this;
    }

    @Step("Assert Product Is Removed From Cart")
    public CartCheckOutOverviewPage assertProductIsRemovedFromCart(String productName)
    {
        defineLocatorsByProductName(productName);
        Assert.assertFalse(action.isElementPresent(productItem,DOWN)||action.isElementPresent(productItem,UP));
        return this;
    }

    @Step("Verify Product Price")
    public CartCheckOutOverviewPage verifyProductPrice(String productName,String price) throws IOException {
        defineLocatorsByProductName(productName);
        String actualPrice = action.readText(productPriceLocator).split("\\$",2)[1];
        softAssert.assertEquals(actualPrice,price);
        return this;
    }

    @Step("Verify Product Quantity")
    public CartCheckOutOverviewPage verifyProductQuantity(String productName,String quantity) throws IOException {
        defineLocatorsByProductName(productName);
        softAssert.assertEquals(action.readText(productQuantityLocator),quantity);
        return this;
    }

    @Step("Verify Product Description")
    public CartCheckOutOverviewPage verifyProductDescription(String productName,String description) throws IOException {
        defineLocatorsByProductName(productName);
        softAssert.assertEquals(action.readText(productDescriptionLocator),description);
        return this;
    }

    @Step("Verify Total Price of Products")
    public CartCheckOutOverviewPage verifyTotalPriceOfProducts(String totalPrice) throws IOException {
        String actualTotalPrice = action.readText(totalPriceLocator).split("\\$",2)[1];
        softAssert.assertEquals(actualTotalPrice,totalPrice);
        return this;
    }

    @Step("Verify the Payment Info")
    public CartCheckOutOverviewPage verifyPaymentInfo(String paymentInfo) throws IOException {
        softAssert.assertEquals(action.readText(paymentInfoLocator),paymentInfo);
        return this;
    }

    @Step("Verify the Shipping Method")
    public CartCheckOutOverviewPage verifyShippingMethod(String shippingMethod) throws IOException {
        softAssert.assertEquals(action.readText(shippingInfoLocator),shippingMethod);
        return this;
    }

    //Scrolling
    @Step("Scroll to Product")
    public CartCheckOutOverviewPage scrollToProduct(String productName, W3CTouchActions.Direction direction)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(productItem,direction);
        return this;
    }

    @Step("Scroll to Cancel Button")
    public CartCheckOutOverviewPage scrollToCancelButton(W3CTouchActions.Direction direction)
    {
        action.swipeIntoScreen(backToProductsButton,direction);
        return this;
    }

    @Step("Scroll to Finish Button")
    public CartCheckOutOverviewPage scrollToFinishButton(W3CTouchActions.Direction direction)
    {
        action.swipeIntoScreen(finishButton,direction);
        return this;
    }

    @Step("Scroll to Total Price View")
    public CartCheckOutOverviewPage scrollToTotalPriceView(W3CTouchActions.Direction direction)
    {
        action.swipeIntoScreen(totalPriceLocator,direction);
        return this;
    }

    @Step("Scroll to Payment Info View")
    public CartCheckOutOverviewPage scrollToPaymentInfoView(W3CTouchActions.Direction direction)
    {
        action.swipeIntoScreen(paymentInfoLocator,direction);
        return this;
    }

    @Step("Scroll to Shipping Info View")
    public CartCheckOutOverviewPage scrollToShippingInfoView(W3CTouchActions.Direction direction)
    {
        action.swipeIntoScreen(shippingInfoLocator,direction);
        return this;
    }
}
