package pagesByNativeAndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomSoftAssert;

import static yehiaEngine.elementActions.NativeAndroidActions.LocatorType.*;
import static yehiaEngine.elementActions.NativeAndroidActions.ScrollDirection.*;

public class ProductDetailsPage extends HomePage {

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
        action.tap(ACCESSIBILITY_ID,"test-ADD TO CART",VERTICAL);
        return this;
    }

    @Step("Remove Product From Cart By Button")
    public ProductDetailsPage removeProductFromCart()
    {
        action.tap(ACCESSIBILITY_ID,"test-REMOVE",VERTICAL);
        return this;
    }

    @Step("Zoom In Product Picture")
    public ProductDetailsPage zoomInProductPicture(int distance)
    {
        action.zoomIn(ACCESSIBILITY_ID,"test-Image Container",VERTICAL,distance);
        return this;
    }

    @Step("Zoom Out Product Picture")
    public ProductDetailsPage zoomOutProductPicture(int distance)
    {
        action.zoomOut(ACCESSIBILITY_ID,"test-Image Container",VERTICAL,distance);
        return this;
    }

    @Step("Return Back To Products Page")
    public ProductsPage returnBackToProductPage()
    {
        action.tap(ACCESSIBILITY_ID,"test-BACK TO PRODUCTS",VERTICAL);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Verify Product Details")
    public ProductDetailsPage verifyProductDetails(String name, String description, String price)
    {
        verifyProductName(name)
                .verifyProductDescription(description)
                .verifyProductPrice(price);
        return this;
    }

    @Step("Verify Product Name")
    public ProductDetailsPage verifyProductName(String name) {
        String actualName =action.swipeIntoScreen(TEXT,name,VERTICAL)
                .readText(productName);
        CustomSoftAssert.assertEquals(actualName,name);
        return this;
    }

    @Step("Verify Product Description")
    public ProductDetailsPage verifyProductDescription(String description) {
        String actualDescription =action.swipeIntoScreen(ACCESSIBILITY_ID,"test-Description",VERTICAL)
                .readText(productDescription);
        CustomSoftAssert.assertEquals(actualDescription,description);
        return this;
    }

    @Step("Verify Product Price")
    public ProductDetailsPage verifyProductPrice(String price) {
        String actualPrice =action.readText(ACCESSIBILITY_ID,"test-Price",VERTICAL);
        CustomSoftAssert.assertEquals(actualPrice,price);
        return this;
    }
}
