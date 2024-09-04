package pagesByW3cTouchActions;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartCheckOutCompletePage extends HomePage {
    //Locators
    By removeFromCartButton;

    //Constructor
    public CartCheckOutCompletePage(AppiumDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Return Back to Products Page")
    public ProductsPage returnBackToProductsPage()
    {

        return new ProductsPage(driver);
    }

    //Validations
    @Step("Verify CheckOut Successful Massage")
    public void verifyCheckoutSuccessfulMessage()
    {

    }
}
