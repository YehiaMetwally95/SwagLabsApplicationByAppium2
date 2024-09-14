package pagesByNativeAndroidUIScrollable;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import static utils.NativeAndroidActionBot.LocatorType.*;
import static utils.NativeAndroidActionBot.ScrollDirection.*;
import java.io.IOException;

public class ProductsPage extends HomePage {

    //Variables

    //Locators
    By productItem;
    By dragButton;
    By dropButton = AppiumBy.accessibilityId("test-Cart drop zone");
    By addToCartButton;
    By removeFromCartButton;

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
        action.swipeIntoScreen(TEXT,productName,VERTICAL)
                .dragAndDrop(dragButton,dropButton);
        return this;
    }

    @Step("Add Product To Cart By Button")
    public ProductsPage addProductToCartByButton(String productName)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(TEXT,productName,VERTICAL).
                tab(addToCartButton);
        return this;
    }

    @Step("Remove Product From Cart By Button")
    public ProductsPage removeProductFromCartByButton(String productName)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(TEXT,productName,VERTICAL)
                .tab(removeFromCartButton);
        return this;
    }

    @Step("Open Product Details Page")
    public ProductDetailsPage openProductDetailsPage(String productName)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(TEXT,productName,VERTICAL)
                .tab(productItem);
        return new ProductDetailsPage(driver);
    }

    //Validation
    @Step("Assert Home Page is Opened")
    public ProductsPage assertHomePageIsOpened() throws IOException {
        Assert.assertTrue(action.isElementDisplayed(menuIcon));
        return this;
    }
}
