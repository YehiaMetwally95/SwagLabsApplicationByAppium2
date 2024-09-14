package pagesByNativeAndroidUIScrollable;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import static utils.NativeAndroidActionBot.LocatorType.*;
import static utils.NativeAndroidActionBot.ScrollDirection.*;
import java.io.IOException;

import static utils.CustomSoftAssert.softAssert;

public class CartCheckOutOverviewPage extends HomePage {
    //Variables
    String productName;

    //Locators
    By productItem;
    By productPriceLocator;
    By productDescriptionLocator;
    By productQuantityLocator;

    By removeFromCartSwipe = AppiumBy.accessibilityId("test-Delete");
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
        action.tab(ACCESSIBILITY_ID,"test-CANCEL",VERTICAL);
        return new ProductsPage(driver);
    }

    @Step("Remove Product From Cart By Swipe")
    public CartCheckOutOverviewPage removeProductFromCartBySwipe(String productName)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(TEXT,productName,VERTICAL)
                .swipeIntoElement(TEXT,productName,ACCESSIBILITY_ID,"test-Delete",HORIZONTAL)
                .tab(removeFromCartSwipe);
        return this;
    }

    @Step("Finish Cart Checkout")
    public CartCheckOutCompletePage finishCartCheckOut()
    {
        action.tab(ACCESSIBILITY_ID,"test-FINISH",VERTICAL);
        return new CartCheckOutCompletePage(driver);
    }

    //Validations
    @Step("Verify Product Is Added To Cart")
    public CartCheckOutOverviewPage verifyProductIsAddedToCart(String productName)
    {
        defineLocatorsByProductName(productName);
        softAssert.assertTrue(action.isElementPresent(TEXT,productName,VERTICAL));
        return this;
    }

    @Step("Verify Product Is Removed From Cart")
    public CartCheckOutOverviewPage verifyProductIsRemovedFromCart(String productName)
    {
        defineLocatorsByProductName(productName);
        softAssert.assertFalse(action.isElementPresent(TEXT,productName,VERTICAL));
        return this;
    }

    @Step("Verify Product Price")
    public CartCheckOutOverviewPage verifyProductPrice(String productName,String price) throws IOException {
        defineLocatorsByProductName(productName);
        String actualPrice = action.swipeIntoScreen(TEXT,productName,VERTICAL)
                .readText(productPriceLocator).split("\\$",2)[1];
        softAssert.assertEquals(actualPrice,price);
        return this;
    }

    @Step("Verify Product Quantity")
    public CartCheckOutOverviewPage verifyProductQuantity(String productName,String quantity) throws IOException {
        String actualQuantity = action.swipeIntoScreen(TEXT,productName,VERTICAL)
                .readText(productQuantityLocator);
        softAssert.assertEquals(actualQuantity,quantity);
        return this;
    }

    @Step("Verify Product Description")
    public CartCheckOutOverviewPage verifyProductDescription(String productName,String description) throws IOException {
        defineLocatorsByProductName(productName);
        String actualDescription = action.swipeIntoScreen(TEXT,productName,VERTICAL)
                .readText(productDescriptionLocator);
        softAssert.assertEquals(actualDescription,description);
        return this;
    }

    @Step("Verify Total Price of Products")
    public CartCheckOutOverviewPage verifyTotalPriceOfProducts(String totalPrice) throws IOException {
        String actualTotalPrice = action.swipeIntoScreen(TEXT,"Total",VERTICAL).
                readText(totalPriceLocator).
                split("\\$",2)[1];
        softAssert.assertEquals(actualTotalPrice,totalPrice);
        return this;
    }

    @Step("Verify the Payment Info")
    public CartCheckOutOverviewPage verifyPaymentInfo(String paymentInfo) throws IOException {
        String actualPaymentInfo = action.swipeIntoScreen(TEXT,"Payment",VERTICAL).
                readText(paymentInfoLocator);
        softAssert.assertEquals(actualPaymentInfo,paymentInfo);
        return this;
    }

    @Step("Verify the Shipping Method")
    public CartCheckOutOverviewPage verifyShippingMethod(String shippingMethod) throws IOException {
        String actualShippingInfo = action.swipeIntoScreen(TEXT,"Shipping",VERTICAL).
                readText(shippingInfoLocator);
        softAssert.assertEquals(actualShippingInfo,shippingMethod);
        return this;
    }
}
