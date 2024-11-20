package pagesByNativeAndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomAssert;

import static yehiaEngine.elementActions.NativeAndroidActions.LocatorType.*;
import static yehiaEngine.elementActions.NativeAndroidActions.ScrollDirection.*;

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
    private void defineLocatorsByProductName(String productName) {
        this.productName = productName;
        productItem = AppiumBy.xpath("//*[@text='" + productName + "']/ancestor::*[@content-desc='test-Item']");
        removeFromCartButton = AppiumBy.xpath("//*[@text='" + productName + "']/parent::*/following-sibling::*[@content-desc='test-Price']//*[@content-desc='test-REMOVE']");
    }

    @Step("Remove Product From Cart By Button")
    public CartPage removeProductFromCartByButton(String productName) {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(TEXT, productName, VERTICAL)
                .tap(removeFromCartButton);
        return this;
    }

    @Step("Remove Product From Cart By Swipe")
    public CartPage removeProductFromCartBySwipe(String productName) {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(TEXT, productName, VERTICAL)
                .tap(ACCESSIBILITY_ID,"test-Delete",HORIZONTAL,TEXT,productName);
        return this;
    }

    @Step("Proceed To Fill User Info")
    public CartCheckOutInfoPage proceedToFillUserInfo() {
        action.tap(ACCESSIBILITY_ID, "test-CHECKOUT", VERTICAL);
        return new CartCheckOutInfoPage(driver);
    }

    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductPage() {
        action.tap(ACCESSIBILITY_ID, "test-CONTINUE SHOPPING", VERTICAL);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Assert Product Is Added To Cart")
    public CartPage assertProductIsAddedToCart(String productName) {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementDisplayed(TEXT, productName, VERTICAL));
        return this;
    }

    @Step("Assert Product Is Removed To Cart")
    public CartPage assertProductIsRemovedFromCart(String productName) {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementNotDisplayed(TEXT, productName, VERTICAL));
        return this;
    }
}
