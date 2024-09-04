package pagesByW3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.W3CTouchActions;

import static utils.W3CTouchActions.Direction.*;

public class CartPage extends HomePage{

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
        action.tab(removeFromCartButton);
        return this;
    }

    @Step("Remove Product From Cart By Swipe")
    public CartPage removeProductFromCartBySwipe(String productName)
    {
        defineLocatorsByProductName(productName);
       //action.scrollIntoElementHorizontally("test-Delete",productName)
            //   .tab(removeFromCartButton);

        /* action.swipeIntoElement(productItem,LEFT,removeFromCartSwipe)
                .tab(removeFromCartSwipe);*/
        return this;
    }

    @Step("Proceed To Fill User Info")
    public CartCheckOutInfoPage proceedToFillUserInfo()
    {
        action.swipeIntoScreen(checkOutButton,UP)
                .tab(checkOutButton);
        return new CartCheckOutInfoPage(driver);
    }

    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductPage()
    {
        action.swipeIntoScreen(backToProductsButton,UP)
                .tab(backToProductsButton);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Assert Product Is Added To Cart")
    public CartPage assertProductIsAddedToCart(String productName)
    {
        defineLocatorsByProductName(productName);
       // Assert.assertTrue(action.IsElementPresent(productName),VERTICAL);
        return this;
    }

    @Step("Assert Product Is Removed To Cart")
    public CartPage assertProductIsRemovedFromCart(String productName)
    {
        defineLocatorsByProductName(productName);
       // Assert.assertFalse(action.IsElementPresent(productName));
        return this;
    }

    //Scrolling
    @Step("Scroll to Product")
    public CartPage scrollToProduct(String productName, W3CTouchActions.Direction direction)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(productItem,direction);
        return this;
    }

    @Step("Scroll to Checkout Button")
    public CartPage scrollToCheckoutButton(W3CTouchActions.Direction direction)
    {
        action.swipeIntoScreen(checkOutButton,direction);
        return this;
    }

    @Step("Scroll to Continue Button")
    public CartPage scrollToContinueButton(W3CTouchActions.Direction direction)
    {
        action.swipeIntoScreen(backToProductsButton,direction);
        return this;
    }

}
