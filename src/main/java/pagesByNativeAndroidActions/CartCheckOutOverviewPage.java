package pagesByNativeAndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;

import static yehiaEngine.elementActions.NativeAndroidActions.LocatorType.*;
import static yehiaEngine.elementActions.NativeAndroidActions.ScrollDirection.*;

public class CartCheckOutOverviewPage extends HomePage {
    //Variables
    String productName;

    //Locators
    By productItem;
    By productPriceLocator;
    By productDescriptionLocator;
    By productQuantityLocator;
    By shippingInfoLocator = AppiumBy.xpath("(//*[@text=\"Shipping Information:\"]/following-sibling::android.widget.TextView)[1]");

    //Constructor
    public CartCheckOutOverviewPage(AppiumDriver driver) {
        super(driver);
    }

    //Actions
    private void defineLocatorsByProductName(String productName) {
        this.productName = productName;
        productItem = AppiumBy.xpath("//*[@text= '" + productName + "']/ancestor::*[@content-desc='test-Item']");
        productPriceLocator = AppiumBy.xpath("//*[@text= '" + productName + "']/parent::*/following-sibling::*[@content-desc='test-Price']//android.widget.TextView");
        productDescriptionLocator = AppiumBy.xpath("//*[@text= '" + productName + "']/following-sibling::android.widget.TextView");
        productQuantityLocator = AppiumBy.xpath("//*[@text= '" + productName + "']/ancestor::*[@content-desc='test-Description']/preceding-sibling::*[@content-desc='test-Amount']/android.widget.TextView");
    }

    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductsPage()
    {
        action.tap(ACCESSIBILITY_ID,"test-CANCEL",VERTICAL);
        return new ProductsPage(driver);
    }

    @Step("Remove Product From Cart By Swipe")
    public CartCheckOutOverviewPage removeProductFromCartBySwipe(String productName)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(TEXT, productName, VERTICAL)
                .tap(ACCESSIBILITY_ID,"test-Delete",HORIZONTAL,TEXT,productName);
        return this;
    }

    @Step("Finish Cart Checkout")
    public CartCheckOutCompletePage finishCartCheckOut()
    {
        action.tap(ACCESSIBILITY_ID,"test-FINISH",VERTICAL);
        return new CartCheckOutCompletePage(driver);
    }

    //Validations
    @Step("Assert Product Is Added To Cart")
    public CartCheckOutOverviewPage assertProductIsAddedToCart(String productName) {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementDisplayed(TEXT, productName, VERTICAL));
        return this;
    }

    @Step("Assert Product Is Removed To Cart")
    public CartCheckOutOverviewPage assertProductIsRemovedFromCart(String productName) {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementNotDisplayed(TEXT, productName, VERTICAL));
        return this;
    }

    @Step("Verify Product Info")
    public CartCheckOutOverviewPage verifyProductInfo(String productName, String price, String quantity, String description)
    {
        verifyProductPrice(productName,price)
                .verifyProductQuantity(productName,quantity)
                .verifyProductDescription(productName,description);
        return this;
    }

    @Step("Verify Product Price")
    private CartCheckOutOverviewPage verifyProductPrice(String productName, String price) {
        defineLocatorsByProductName(productName);
        String actualPrice = action
                .swipeIntoScreen(TEXT,productName,VERTICAL)
                .readText(productPriceLocator).split("\\$",2)[1];
        CustomSoftAssert.assertEquals(actualPrice,price);
        return this;
    }

    @Step("Verify Product Quantity")
    private CartCheckOutOverviewPage verifyProductQuantity(String productName, String quantity) {
        defineLocatorsByProductName(productName);
        CustomSoftAssert.assertEquals(action
                .swipeIntoScreen(TEXT,productName,VERTICAL)
                .readText(productQuantityLocator),quantity);
        return this;
    }

    @Step("Verify Product Description")
    private CartCheckOutOverviewPage verifyProductDescription(String productName, String description) {
        defineLocatorsByProductName(productName);
        CustomSoftAssert.assertEquals(action
                .swipeIntoScreen(TEXT,productName,VERTICAL)
                .readText(productDescriptionLocator),description);
        return this;
    }

    @Step("Verify Total Price of Products")
    public CartCheckOutOverviewPage verifyTotalPriceOfProducts(double totalPrice) {
        double actualTotalPrice = Double.parseDouble( action
                .readText(TEXT,"total",VERTICAL)
                .split("\\$",2)[1]);
        CustomSoftAssert.assertEquals(actualTotalPrice,totalPrice);
        return this;
    }

    @Step("Verify the Payment Info")
    public CartCheckOutOverviewPage verifyPaymentInfo(String paymentInfo) {
        String actualPaymentInfo = action.readText(TEXT,"SauceCard",VERTICAL);
        CustomSoftAssert.assertEquals(actualPaymentInfo,paymentInfo);
        return this;
    }

    @Step("Verify the Shipping Method")
    public CartCheckOutOverviewPage verifyShippingMethod(String shippingMethod) {
        String actualShippingInfo = action
                .swipeIntoScreen(TEXT,"Shipping",VERTICAL)
                .readText(shippingInfoLocator);
        CustomSoftAssert.assertEquals(actualShippingInfo,shippingMethod);
        return this;
    }
}
