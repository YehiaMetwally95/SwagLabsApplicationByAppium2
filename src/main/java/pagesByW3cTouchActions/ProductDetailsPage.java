package pagesByW3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import utils.W3CTouchActions;

import java.io.IOException;

import static utils.CustomSoftAssert.softAssert;


public class ProductDetailsPage extends HomePage{

    //Variables

    //Locators
    By addToCardButton = AppiumBy.accessibilityId("test-ADD TO CART");
    By removeFromCartButton = AppiumBy.accessibilityId("test-REMOVE");
    By productPicture = AppiumBy.accessibilityId("test-Image Container");
    By backToProductsButton = AppiumBy.accessibilityId("test-BACK TO PRODUCTS");
    By productPrice = AppiumBy.accessibilityId("test-Price");
    By productDescription = AppiumBy.xpath("(//*[@content-desc='test-Description']//android.widget.TextView)[1]");

    //Constructor
    public ProductDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Add Product To Cart By Button")
    public ProductDetailsPage addProductToCart()
    {
        action.tab(addToCardButton);
        return this;
    }

    @Step("Remove Product From Cart By Button")
    public ProductDetailsPage removeProductFromCart()
    {
        action.swipeIntoScreen(removeFromCartButton, W3CTouchActions.Direction.UP)
        .tab(removeFromCartButton);
        return this;
    }

    @Step("Zoom In Product Picture")
    public ProductDetailsPage zoomInProductPicture(int distance)
    {
        action.zoomIn(productPicture,distance);
        return this;
    }

    @Step("Zoom Out Product Picture")
    public ProductDetailsPage zoomOutProductPicture(int distance)
    {
        action.zoomOut(productPicture,distance);
        return this;
    }

    @Step("Return Back To Products Page")
    public ProductsPage returnBackToProductPage()
    {
        action.tab(backToProductsButton);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Verify Product Description")
    public ProductDetailsPage verifyProductDescription(String description) throws IOException {
        action.swipeIntoScreen(productDescription, W3CTouchActions.Direction.UP);
        softAssert.assertEquals(action.readText(productDescription),description);
        return this;
    }

    @Step("Verify Product Price")
    public ProductDetailsPage verifyProductPrice(String price) throws IOException {
        action.swipeIntoScreen(productPrice, W3CTouchActions.Direction.UP);
        softAssert.assertEquals(action.readText(productPrice),price);
        return this;
    }

    //Scrolling
    @Step("Scroll to Add To Cart Button")
    public ProductDetailsPage scrollToAddToCartButton(W3CTouchActions.Direction direction) throws IOException {
        action.swipeIntoScreen(addToCardButton, direction);
        return this;
    }

    @Step("Scroll to Product Picture")
    public ProductDetailsPage scrollToProductPicture(W3CTouchActions.Direction direction) throws IOException {
        action.swipeIntoScreen(productPicture, direction);
        return this;
    }
}
