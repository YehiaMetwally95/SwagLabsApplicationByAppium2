package webViewPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yehiaEngine.assertions.CustomSoftAssert;

public class ProductDetailsPage extends HomePage {
    //Variables

    //Locators
    By productNameLocator = By.cssSelector(".product-information h2");
    By productCategorizationLocator = By.xpath("//div[@class='product-details']/descendant::p[contains(.,'Category')]");
    By productPriceLocator = By.xpath("(//div[@class='product-details']/descendant::span[contains(.,'Rs')])[2]");
    By productAvailabilityLocator = By.xpath("//div[@class='product-details']/descendant::p[contains(.,'Availability')]");
    By productConditionLocator = By.xpath("//div[@class='product-details']/descendant::p[contains(.,'Condition')]");
    By productBrandLocator = By.xpath("//div[@class='product-details']/descendant::p[contains(.,'Brand')]");
    By productQuantityTextBox = By.id("quantity");
    By addToCartButton = By.xpath("//button[contains(@class,'cart')]");
    By continueShoppingButton = By.xpath("//button[@data-dismiss='modal']");
    By viewCartButton = By.xpath("//div[@id='cartModal']/descendant::a");
    By writeYourReviewSection = By.xpath("//a[contains(.,'Review')]");

    //Constructor
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Add Product To Cart")
    public ProductDetailsPage addProductToCart()   {
        bot.press(addToCartButton);
        return this;
    }

    @Step("Click On Continue Shopping")
    public ProductDetailsPage continueShopping()   {
        bot.press(continueShoppingButton);
        return this;
    }

    @Step("Click On View Cart")
    public CartPage viewCart()   {
        bot.press(viewCartButton);
        return new CartPage(driver);
    }

    @Step("Set Product Quantity")
    public ProductDetailsPage setProductQuantity(String quantity)   {
        bot.type(productQuantityTextBox,quantity);
        return this;
    }

    //Validations
    @Step("Verify All Product Details")
    public ProductDetailsPage verifyAllProductDetails(String name,String price,String availability,
                                                       String condition,String brand,String category,
                                                       String subCategory)   {
        verifyProductName(name).
                verifyProductPrice(price).
                verifyProductAvailability(availability).
                verifyProductCondition(condition).
                verifyProductBrand(brand).
                verifyProductCategory(category).
                verifyProductSubCategory(subCategory);
        return this;
    }

    @Step("Verify Product Details Page is Opened")
    public ProductDetailsPage verifyProductDetailsPageIsOpened(String reviewSectionHeader)   {
        verifyReviewSection(reviewSectionHeader);
        return this;
    }

    //Private Methods
    @Step("Verify Product Name")
    private ProductDetailsPage verifyProductName(String productName)   {
        CustomSoftAssert.assertEquals(bot.readText(productNameLocator),productName);
        return this;
    }

    @Step("Verify Product Price")
    private ProductDetailsPage verifyProductPrice(String price)   {
        CustomSoftAssert.assertEquals(bot.readText(productPriceLocator),price);
        return this;
    }

    @Step("Verify Product Availability")
    private ProductDetailsPage verifyProductAvailability(String availability)   {
        CustomSoftAssert.assertEquals(bot.readText(productAvailabilityLocator),("Availability: "+availability));
        return this;
    }

    @Step("Verify Product Condition")
    private ProductDetailsPage verifyProductCondition(String condition)   {
        CustomSoftAssert.assertEquals(bot.readText(productConditionLocator),("Condition: "+condition));
        return this;    }

    @Step("Verify Product Brand")
    private ProductDetailsPage verifyProductBrand(String brand)   {
        CustomSoftAssert.assertEquals(bot.readText(productBrandLocator),("Brand: "+brand));
        return this;    }

    @Step("Verify Product Category")
    private ProductDetailsPage verifyProductCategory(String category)   {
        CustomSoftAssert.assertTrue(bot.readText(productCategorizationLocator).contains(category));
        return this;    }

    @Step("Verify Product SubCategory")
    private ProductDetailsPage verifyProductSubCategory(String subCategory)   {
        CustomSoftAssert.assertTrue(bot.readText(productCategorizationLocator).contains(subCategory));
        return this;
    }

    @Step("Verify Review Section Header")
    private ProductDetailsPage verifyReviewSection(String reviewSectionHeader)   {
        CustomSoftAssert.assertEquals(bot.readText(writeYourReviewSection),reviewSectionHeader);
        return this;
    }

}
