package pagesByW3cTouchActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yehiaEngine.assertions.CustomSoftAssert;
import yehiaEngine.elementActions.W3CTouchActions.Direction;

public class ProductsPage extends HomePage {

    //Variables

    //Locators
    By productItem;
    By dragButton;
    By dropButton = AppiumBy.accessibilityId("test-Cart drop zone");
    By addToCardButton;
    By removeFromCartButton;
    By products = AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");

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
        addToCardButton = AppiumBy.xpath("//*[@text='"+productName+"']/following-sibling::android.view.ViewGroup[@content-desc='test-ADD TO CART']");
        removeFromCartButton= AppiumBy.xpath("//*[@text='"+productName+"']/following-sibling::android.view.ViewGroup[@content-desc='test-REMOVE']");
        dragButton =AppiumBy.xpath("//*[@text='"+productName+"']/following-sibling::android.view.ViewGroup[@content-desc='test-Drag Handle']");
    }

    @Step("Add Product To Cart By Drag & Drop")
    public ProductsPage addProductToCartByDragAndDrop(String productName)
    {
        defineLocatorsByProductName(productName);
        action.dragAndDrop(dragButton,dropButton);
        return this;
    }

    @Step("Add Product To Cart By Button")
    public ProductsPage addProductToCartByButton(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(addToCardButton);
        return this;
    }

    @Step("Remove Product From Cart By Button")
    public ProductsPage removeProductFromCartByButton(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(removeFromCartButton);
        return this;
    }

    @Step("Open Product Details Page")
    public ProductDetailsPage openProductDetailsPage(String productName)
    {
        defineLocatorsByProductName(productName);
        action.tap(productItem);
        return new ProductDetailsPage(driver);
    }

    //Validation
    @Step("Verify Products Page is Opened")
    public ProductsPage verifyProductsPageIsOpened() {
        CustomSoftAssert.assertTrue(action.isElementDisplayed(menuIcon));
        return this;
    }

    //Scrolling
    @Step("Scroll to Product")
    public ProductsPage scrollToProduct (String productName, Direction direction)
    {
        defineLocatorsByProductName(productName);
        action.swipeIntoScreen(addToCardButton,direction);
        return this;
    }
}
