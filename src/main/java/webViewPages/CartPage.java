package webViewPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import yehiaEngine.assertions.CustomSoftAssert;

import java.io.IOException;
import java.util.List;

import static yehiaEngine.browserActions.WindowManager.refreshWindow;

public class CartPage extends HomePage {
    //Variables

    //Locators
    private By checkOutButton = By.linkText("Proceed To Checkout");
    private By removeAllProductsFromCartButton = By.className("cart_quantity_delete");
    private By ShoppingCartHeaderLocator = By.className("active");
    private By productNamesLocator = By.cssSelector(".cart_description a");

    private By productNameLocator (String productName)
    {
        return By.linkText(productName);
    }
    private By productPriceLocator (String productName)
    {
        return RelativeLocator.with(By.className("cart_price")).toRightOf(productNameLocator(productName));
       //return  By.xpath("//td[contains(.,'"+productName+"')]/following-sibling::td[@class='cart_price']");
    }
    private By productQuantityLocator (String productName)
    {
        //return RelativeLocator.with(By.tagName("button")).toRightOf(productNameLocator(productName));
        return By.xpath("//td[contains(.,'"+productName+"')]/following-sibling::td[@class='cart_quantity']/button");
    }
    private By productTotalPriceLocator (String productName)
    {
        return RelativeLocator.with(By.className("cart_total_price")).toRightOf(productNameLocator(productName));
        //return By.xpath("//td[contains(.,'"+productName+"')]/following-sibling::td[@class='cart_total']/p");
    }
    private By removeProductFromCartButton (String productName)
    {
        return RelativeLocator.with(By.className("cart_quantity_delete")).toRightOf(productNameLocator(productName));
        //return By.xpath("//td[contains(.,'"+productName+"')]/following-sibling::td[@class='cart_delete']/a");
    }

    //Constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Proceed to CheckOut")
    public CheckoutPage proceedToCheckOut() {
        bot.press(checkOutButton);
        return new CheckoutPage(driver);
    }

    @Step("Remove Product from Cart")
    public CartPage removeProductFromCart(String productName) throws IOException {
        bot.press(removeProductFromCartButton(productName));
        return this;
    }

    @Step("Remove All Old Products from Cart")
    public CartPage removeAllOldProductsFromCart() {
        if (bot.isElementDisplayed(removeAllProductsFromCartButton))
        {
            List<WebElement> elements = bot.getAllMatchedElements(removeAllProductsFromCartButton);
            for (WebElement element : elements) {
                bot.press(element);
            }
        }
        return this;
    }



    //Validations
    @Step("Verify Cart Page Is Opened")
    public CartPage verifyCartPageIsOpened(String header) throws IOException {
        verifyShoppingCartHeader(header);
        return this;
    }

    @Step("Verify All Product Details")
    public CartPage verifyAllProductDetails(String productName, String price, String quantity, String totalPrice) throws IOException {
        verifyProductName(productName).
                verifyProductPrice(productName,price).
                verifyProductQuantity(productName,quantity).
                verifyProductTotalPrice(productName,totalPrice);
        return this;
    }

    @Step("Assert Product is Added to Cart")
    public CartPage assertProductIsAddedToCart(String productName) {
        CustomSoftAssert.assertTrue(bot.isElementDisplayed(productNameLocator(productName)));
       /* List<WebElement> elements = bot.getAllMatchedElements(productNamesLocator);
        List<String> productNames= new ArrayList<>();
        for (WebElement element : elements) {
            productNames.add(bot.readText(element));
        }
        CustomAssert.assertListContainsObject(productNames,productName,"Element is Not Added");*/
        return this;
    }

    @Step("Assert Product is Removed from Cart")
    public CartPage assertProductIsRemovedFromCart(String productName) {
        CustomSoftAssert.assertTrue(bot.isElementNotDisplayed(productNameLocator(productName)));
       /* if (bot.isElementDisplayed(productNamesLocator))
        {
            List<WebElement> elements = bot.getAllMatchedElements(productNamesLocator);
            List<String> productNames= new ArrayList<>();
            for (WebElement element : elements) {
                productNames.add(bot.readText(element));
            }
            CustomAssert.assertListNotContainsObject(productNames,productName,"Element is Not Removed");
        }*/
        return this;
    }

    //Private Methods
    @Step("Verify Product Name")
    private CartPage verifyProductName(String productName) {
        CustomSoftAssert.assertEquals(
                bot.readText(productNameLocator(productName)),productName
        );
        return this;
    }

    @Step("Verify Product Price")
    private CartPage verifyProductPrice(String productName,String productPrice) throws IOException {
        CustomSoftAssert.assertEquals(
                bot.readText(productPriceLocator(productName)),productPrice
        );
        return this;
    }

    @Step("Verify Product Quantity")
    private CartPage verifyProductQuantity(String productName,String productQuantity) throws IOException {
        CustomSoftAssert.assertEquals(bot.readText(productQuantityLocator(productName)),productQuantity);
        return this;
    }

    @Step("Verify Product Total Price")
    private CartPage verifyProductTotalPrice(String productName,String totalPrice) throws IOException {
        CustomSoftAssert.assertEquals(
                bot.readText(productTotalPriceLocator(productName)),"Rs. "+totalPrice
        );
        return this;
    }

    @Step("Verify Shopping Cart Header")
    private CartPage verifyShoppingCartHeader(String header) throws IOException {
        CustomSoftAssert.assertEquals(bot.readText(ShoppingCartHeaderLocator),header);
        return this;
    }

    public CartPage refreshCart() throws IOException {
        refreshWindow(driver);
        return this;
    }

}
