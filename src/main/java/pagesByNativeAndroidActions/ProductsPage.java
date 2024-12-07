package pagesByNativeAndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomSoftAssert;

import static yehiaEngine.elementActions.NativeAndroidActions.LocatorType.*;
import static yehiaEngine.elementActions.NativeAndroidActions.ScrollDirection.*;

public class ProductsPage extends HomePage {

    //Variables

    //Locators
    By productItem;
    By dragButton;
    By addToCartButton;
    By removeFromCartButton;
    By dropButton = AppiumBy.accessibilityId("test-Cart drop zone");

    //Locator Texts
    String productItemText;

    //Constructor
    public ProductsPage(AppiumDriver driver)
    {
        super(driver);
    }

    //Actions
    private void defineLocatorsByProductName(String productName)
    {
        this.productItemText = productName;
        productItem = AppiumBy.xpath("//*[@text='"+productName+"']/ancestor::android.view.ViewGroup[@content-desc='test-Item']");
        addToCartButton = AppiumBy.xpath("//*[@text='"+productName+"']/following-sibling::android.view.ViewGroup[@content-desc='test-ADD TO CART']");
        removeFromCartButton= AppiumBy.xpath("//*[@text='"+productName+"']/following-sibling::android.view.ViewGroup[@content-desc='test-REMOVE']");
        dragButton =AppiumBy.xpath("//*[@text='"+productName+"']/following-sibling::android.view.ViewGroup[@content-desc='test-Drag Handle']");
    }

    @Step("Add Product To Cart By Drag & Drop")
    public ProductsPage addProductToCartByDragAndDrop(String productName)
    {
        defineLocatorsByProductName(productName);
        action.dragAndDrop(dragButton,dropButton,VERTICAL);
        return this;
    }

    @Step("Add Product To Cart By Button")
    public ProductsPage addProductToCartByButton(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(addToCartButton,VERTICAL);
        return this;
    }

    @Step("Remove Product From Cart By Button")
    public ProductsPage removeProductFromCartByButton(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(removeFromCartButton,VERTICAL);
        return this;
    }

    @Step("Open Product Details Page")
    public ProductDetailsPage openProductDetailsPage(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(TEXT,productName,VERTICAL);
        return new ProductDetailsPage(driver);
    }

    //Validation
    @Step("Verify Products Page is Opened")
    public ProductsPage verifyProductsPageIsOpened() {
        CustomSoftAssert.assertTrue(action.isElementDisplayed(menuIcon));
        return this;
    }
}
