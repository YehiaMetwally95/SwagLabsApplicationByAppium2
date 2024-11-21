package pagesByW3cTouchActions;

import com.github.javafaker.App;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.elementActions.W3CTouchActions.Direction;

import static yehiaEngine.elementActions.W3CTouchActions.Direction.*;

public class CartPage extends HomePage{

    //Variables

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
        productItem = AppiumBy.xpath("//*[@text='"+productName+"']");
        removeFromCartButton = AppiumBy.xpath("//*[@text='"+productName+"']/parent::*/following-sibling::*[@content-desc='test-Price']//*[@content-desc='test-REMOVE']");
    }


    @Step("Remove Product From Cart By Button")
    public CartPage removeProductFromCartByButton(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(removeFromCartButton);
        return this;
    }

    @Step("Remove Product From Cart By Swipe")
    public CartPage removeProductFromCartBySwipe(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(removeFromCartSwipe,LEFT,productItem);
        return this;
    }

    @Step("Proceed To Fill User Info")
    public CartCheckOutInfoPage proceedToFillUserInfo()
    {
        action.tap(checkOutButton,DOWN);
        return new CartCheckOutInfoPage(driver);
    }

    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductPage()
    {
        action.tap(backToProductsButton,DOWN);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Assert Product Is Added To Cart")
    public CartPage assertProductIsAddedToCart(String productName)
    {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementDisplayed(productItem,DOWN));
        return this;
    }

    @Step("Assert Product Is Removed To Cart")
    public CartPage assertProductIsRemovedFromCart(String productName)
    {
        defineLocatorsByProductName(productName);
        CustomAssert.assertTrue(action.isElementNotDisplayed(productItem,DOWN));
        return this;
    }

    //Scrolling
    @Step("Scroll to Product")
    public CartPage scrollToProduct(String productName, Direction direction)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(removeFromCartButton,direction);
        return this;
    }

    @Step("Scroll to Checkout Button")
    public CartPage scrollToCheckoutButton(Direction direction)
    {
        action.swipeIntoScreen(checkOutButton,direction);
        return this;
    }

    @Step("Scroll to Continue Button")
    public CartPage scrollToContinueButton(Direction direction)
    {
        action.swipeIntoScreen(backToProductsButton,direction);
        return this;
    }

}
