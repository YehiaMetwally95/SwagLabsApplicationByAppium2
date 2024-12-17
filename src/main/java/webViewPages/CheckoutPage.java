package webViewPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;

import java.io.IOException;
import java.util.List;

public class CheckoutPage extends HomePage{
    //Variables

    //Locators
    By deliveryAddressHeaderLocator = By.className("page-subheading");
    By reviewOrderHeaderLocator = By.xpath("(//h2[@class='heading'])[2]");
    By fullNameLocator = RelativeLocator.with(By.className("address_firstname")).below(deliveryAddressHeaderLocator);
    By companyLocator = RelativeLocator.with(By.className("address_address1")).below(deliveryAddressHeaderLocator);
    By address1Locator = By.xpath("(//ul[@id='address_delivery']/li[contains(@class,'address_address1')])[2]");
    By address2Locator = By.xpath("(//ul[@id='address_delivery']/li[contains(@class,'address_address1')])[3]");
    By stateCityLocator = RelativeLocator.with(By.className("address_city")).below(deliveryAddressHeaderLocator);
    By countryLocator = RelativeLocator.with(By.className("address_country_name")).below(deliveryAddressHeaderLocator);
    By mobileNumberLocator = RelativeLocator.with(By.className("address_phone")).below(deliveryAddressHeaderLocator);
    By totalAmountHeaderLocator = By.xpath("//h4[contains(.,'Total Amount')]");
    By totalPriceLocator = RelativeLocator.with(By.tagName("p")).toRightOf(totalAmountHeaderLocator);
    By placeOrderButton = By.linkText("Place Order");
    By productPricesLocator = By.xpath("//tr[contains(@id,'product')]/descendant::p[@class='cart_total_price']");
    By productNamesLocator = By.cssSelector(".cart_description a");

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
        return RelativeLocator.with(By.tagName("button")).toRightOf(productNameLocator(productName));
        //return By.xpath("//td[contains(.,'"+productName+"')]/following-sibling::td[@class='cart_quantity']/button");
    }
    private By productTotalPriceLocator (String productName)
    {
        return RelativeLocator.with(By.className("cart_total_price")).toRightOf(productNameLocator(productName));
        //return By.xpath("//td[contains(.,'"+productName+"')]/following-sibling::td[@class='cart_total']/p");
    }

    //Constructor
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    //Actions

    @Step("Place Order")
    public PaymentPage placeOrder() throws IOException {
        bot.press(placeOrderButton);
        return new PaymentPage(driver);
    }

    //Validations
    @Step("Verify CheckOut Page Is Opened")
    public CheckoutPage verifyCheckOutPageIsOpened(String header) throws IOException {
        verifyViewOrderHeader(header);
        return this;
    }

    @Step("Verify All Product Details")
    public CheckoutPage verifyAllProductDetails(String productName,String price,String quantity,String totalPrice) throws IOException {
        verifyProductName(productName).
                verifyProductPrice(productName,price).
                verifyProductQuantity(productName,quantity).
                verifyProductTotalPrice(productName,totalPrice);
        return this;
    }

    @Step("Verify Total Price of All Products")
    public CheckoutPage verifyTotalPriceOfAllProducts() throws IOException {
        int total = 0;
        String totalPrice;
        List<WebElement> elements = bot.getAllMatchedElements(productPricesLocator);
        for (WebElement element : elements) {
            total = total + Integer.parseInt(element.getText().split("Rs. ",2)[1]);
        }
        totalPrice = Integer.toString(total);
        CustomSoftAssert.assertEquals(bot.readText(totalPriceLocator),"Rs. "+totalPrice);
        return this;
    }

    @Step("Verify Address Details")
    public CheckoutPage verifyAddressDetails(String firstName,String lastName,String company,
                                             String address1,String address2,String state,String city,
                                             String zipCode,String country,String mobileNumber) throws IOException {
        CustomSoftAssert.assertTrue(bot.readText(fullNameLocator).contains(firstName));
        CustomSoftAssert.assertTrue(bot.readText(fullNameLocator).contains(lastName));
        CustomSoftAssert.assertEquals(bot.readText(companyLocator),company);
        CustomSoftAssert.assertEquals(bot.readText(address1Locator),address1);
        CustomSoftAssert.assertEquals(bot.readText(address2Locator),address2);
        CustomSoftAssert.assertTrue(bot.readText(stateCityLocator).contains(state));
        CustomSoftAssert.assertTrue(bot.readText(stateCityLocator).contains(city));
        CustomSoftAssert.assertTrue(bot.readText(stateCityLocator).contains(zipCode));
        CustomSoftAssert.assertEquals(bot.readText(countryLocator),country);
        CustomSoftAssert.assertEquals(bot.readText(mobileNumberLocator),mobileNumber);
        return this;
    }

    @Step("Assert Product is Added to Cart")
    public CheckoutPage assertProductIsAddedToCart(String productName) throws IOException {
        CustomAssert.assertTrue(bot.isElementDisplayed(productNameLocator(productName)));
        return this;
    }

    @Step("Assert Product is Removed from Cart")
    public CheckoutPage assertProductIsRemovedFromCart(String productName) throws IOException {
        CustomAssert.assertTrue(bot.isElementNotDisplayed(productNameLocator(productName)));
        return this;
    }

    //Private Methods
    @Step("Verify View Order Header")
    private CheckoutPage verifyViewOrderHeader(String header) throws IOException {
        CustomSoftAssert.assertEquals(bot.readText(reviewOrderHeaderLocator),header);
        return this;
    }

    @Step("Verify Product Name")
    private CheckoutPage verifyProductName(String productName) throws IOException {
        CustomSoftAssert.assertEquals(bot.readText(productNameLocator(productName)),productName);
        return this;
    }

    @Step("Verify Product Price")
    private CheckoutPage verifyProductPrice(String productName,String productPrice) throws IOException {
        CustomSoftAssert.assertEquals(bot.readText(productPriceLocator(productName)),productPrice);
        return this;
    }

    @Step("Verify Product Quantity")
    private CheckoutPage verifyProductQuantity(String productName,String productQuantity) throws IOException {
        CustomSoftAssert.assertEquals(bot.readText(productQuantityLocator(productName)),productQuantity);
        return this;
    }

    @Step("Verify Product Total Price")
    private CheckoutPage verifyProductTotalPrice(String productName,String totalPrice) throws IOException {
        CustomSoftAssert.assertEquals(bot.readText(productTotalPriceLocator(productName)),"Rs. "+totalPrice);
        return this;
    }
}
