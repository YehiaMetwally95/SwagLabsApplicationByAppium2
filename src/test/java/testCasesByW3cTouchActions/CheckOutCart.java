package testCasesByW3cTouchActions;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pagesByW3cTouchActions.LoginPage;
import baseTest.BaseTest;
import yehiaEngine.managers.JsonManager;
import static yehiaEngine.driverManager.AppiumFactory.getDriver;
import static yehiaEngine.elementActions.W3CTouchActions.Direction.DOWN;
import static yehiaEngine.elementActions.W3CTouchActions.Direction.UP;

@Epic("SwagLabs Android App")
@Feature("Checkout")
@Story("Verify to Checkout Cart")
public class CheckOutCart extends BaseTest {

    //Variables
    String jsonFilePathForAddToCart = "src/test/resources/TestDataJsonFiles/CheckOutCartTestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddToCart);

    @Test
     public void checkOutCart() {
        // Login with Valid Credentials
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()

        // Add 3 Products to Cart
                .addProductToCartByButton(json.getData("Products[0].Name"))
                .scrollToAddToCartButton(json.getData("Products[1].Name"),DOWN)
                .addProductToCartByDragAndDrop(json.getData("Products[1].Name"))
                .scrollToProduct(json.getData("Products[2].Name"),UP)
                .openProductDetailsPage(json.getData("Products[2].Name"))
                .zoomInProductPicture(100)
                .zoomOutProductPicture(100)
                .addProductToCart()

        // Open Cart Page and Assert Products are Added to Cart then Proceed
                .openCartPageFromHeader()
                .assertProductIsAddedToCart(json.getData("Products[0].Name"))
                .assertProductIsAddedToCart(json.getData("Products[1].Name"))
                .assertProductIsAddedToCart(json.getData("Products[2].Name"))
                .scrollToCheckoutButton(DOWN)
                .proceedToFillUserInfo()

        // Fill User Info and Proceed to Checkout Overview Page
                .fillOutUserInfo(json.getData("CheckOutInfo.FirstName"),json.getData("CheckOutInfo.LastName"),json.getData("CheckOutInfo.PostalCode"))
                .continueToCheckoutOverview()

        // Remove Product from Cart
                .removeProductFromCartBySwipe(json.getData("Products[0].Name"))

        // Assert Products are Added to Cart
                .assertProductIsRemovedFromCart(json.getData("Products[0].Name"))
                .assertProductIsAddedToCart(json.getData("Products[1].Name"))
                .assertProductIsAddedToCart(json.getData("Products[2].Name"))

        // Verify Products Info
                .scrollToProduct(json.getData("Products[2].Name"),UP)
                .verifyProductInfo(json.getData("Products[2].Name"), json.getData("Products[2].Price"), json.getData("Products[2].Quantity"),json.getData("Products[2].Description"))
                .verifyProductInfo(json.getData("Products[1].Name"), json.getData("Products[1].Price"), json.getData("Products[1].Quantity"),json.getData("Products[1].Description"))

        // Verify Payment and Shipping Info
                .scrollToPaymentInfoView(DOWN)
                .verifyPaymentInfo(json.getData("Massages.PaymentInfo"))
                .scrollToShippingInfoView(DOWN)
                .verifyShippingMethod(json.getData("Massages.ShippingMethod"))

        // Verify The Total Price
                .scrollToTotalPriceView(DOWN)
                .verifyTotalPriceOfProducts(Double.parseDouble(json.getData("Products[2].Price")) + Double.parseDouble(json.getData("Products[1].Price")))

        // Finish CheckOut Cart
                .scrollToFinishButton(DOWN)
                .finishCartCheckOut()
                .assertCheckoutSuccessfulMessage(json.getData("Massages.CheckoutSuccessfulMessage"));
    }
}
