package pagesByNativeAndroidUIScrollable;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static utils.NativeAndroidActionBot.LocatorType.*;
import static utils.NativeAndroidActionBot.ScrollDirection.*;
import java.io.IOException;

import static utils.CustomSoftAssert.softAssert;


public class ProductDetailsPage extends HomePage {

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
        action.tab(ACCESSIBILITY_ID,"test-ADD TO CART",VERTICAL);
        return this;
    }

    @Step("Remove Product From Cart By Button")
    public ProductDetailsPage removeProductFromCart()
    {
        action.tab(ACCESSIBILITY_ID,"test-REMOVE",VERTICAL);
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
        action.tab(ACCESSIBILITY_ID,"test-BACK TO PRODUCTS",VERTICAL);
        return new ProductsPage(driver);
    }

    //Validations
    @Step("Verify Product Description")
    public ProductDetailsPage verifyProductDescription(String description) throws IOException {
        String actualDescription
                =action.readChildText(ACCESSIBILITY_ID,"test-Description",VERTICAL,"TextView");
        softAssert.assertEquals(actualDescription,description);
        return this;
    }

    @Step("Verify Product Price")
    public ProductDetailsPage verifyProductPrice(String price) throws IOException {
        String actualPrice
                =action.readText(ACCESSIBILITY_ID,"test-Price",VERTICAL);
        softAssert.assertEquals(actualPrice,price);
        return this;
    }
}
