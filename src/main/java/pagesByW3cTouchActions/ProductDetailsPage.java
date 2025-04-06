package pagesByW3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomSoftAssert;
import yehiaEngine.elementActions.W3CFingerActions.Direction;

import static yehiaEngine.elementActions.W3CFingerActions.Direction.DOWN;

public class ProductDetailsPage extends HomePage{

    //Variables

    //Locators
    By addToCardButton = AppiumBy.accessibilityId("test-ADD TO CART");
    By removeFromCartButton = AppiumBy.accessibilityId("test-REMOVE");
    By productPicture = AppiumBy.accessibilityId("test-Image Container");
    By backToProductsButton = AppiumBy.accessibilityId("test-BACK TO PRODUCTS");
    By productPrice = AppiumBy.accessibilityId("test-Price");
    By productName = AppiumBy.xpath("(//*[@content-desc='test-Description']//android.widget.TextView)[1]");
    By productDescription = AppiumBy.xpath("(//*[@content-desc='test-Description']//android.widget.TextView)[2]");

    //Constructor
    public ProductDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Add Product To Cart By Button")
    public ProductDetailsPage addProductToCart()
    {
        action.tap(addToCardButton, Direction.DOWN);
        return this;
    }

    @Step("Remove Product From Cart By Button")
    public ProductDetailsPage removeProductFromCart()
    {
        action.tap(removeFromCartButton, Direction.DOWN);
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
        action.tap(backToProductsButton);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Verify Product Details")
    public ProductDetailsPage verifyProductDetails(String name,String description,String price)
    {
        scrollToProductDescription(DOWN)
                .verifyProductName(name)
                .verifyProductDescription(description)
                .verifyProductPrice(price);
        return this;
    }

    @Step("Verify Product Name")
    private ProductDetailsPage verifyProductName(String name) {
        CustomSoftAssert.assertEquals(action.readText(productName),name);
        return this;
    }

    @Step("Verify Product Description")
    private ProductDetailsPage verifyProductDescription(String description) {
        CustomSoftAssert.assertEquals(action.readText(productDescription),description);
        return this;
    }

    @Step("Verify Product Price")
    private ProductDetailsPage verifyProductPrice(String price) {
        CustomSoftAssert.assertEquals(action.readText(productPrice),price);
        return this;
    }

    //Scrolling
    @Step("Scroll to Product Description")
    public ProductDetailsPage scrollToProductDescription (Direction direction)
    {
        action.swipeIntoScreen(productPrice,direction);
        return this;
    }
}
