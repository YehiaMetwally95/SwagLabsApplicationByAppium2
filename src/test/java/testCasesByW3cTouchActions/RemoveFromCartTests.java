package testCasesByW3cTouchActions;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pagesByW3cTouchActions.LoginPage;
import baseTest.BaseTest;
import yehiaEngine.managers.JsonManager;
import static yehiaEngine.driverManager.AppiumFactory.*;
import static yehiaEngine.elementActions.W3CTouchActions.Direction.DOWN;
import static yehiaEngine.elementActions.W3CTouchActions.Direction.UP;

@Epic("SwagLabs Android App")
@Feature("Cart")
@Story("Verify Remove Products From Cart")
public class RemoveFromCartTests extends BaseTest {
    //Variables
    String jsonFilePathForAddToCart = "src/test/resources/TestDataJsonFiles/RemoveFromCartTestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddToCart);

    @Test
    public void removeProductFromCartByButton() {
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()

                .addProductToCartByButton(json.getData("Products[0].Name"))
                .scrollToProduct(json.getData("Products[1].Name"),DOWN)
                .addProductToCartByDragAndDrop(json.getData("Products[1].Name"))
                .scrollToProduct(json.getData("Products[2].Name"),UP)
                .addProductToCartByDragAndDrop(json.getData("Products[2].Name"))

                .openCartPageFromHeader()
                .scrollToProduct(json.getData("Products[2].Name"),DOWN)
                .removeProductFromCartByButton(json.getData("Products[2].Name"))

                .assertProductIsRemovedFromCart(json.getData("Products[2].Name"));
    }

    @Test
    public void removeProductFromCartBySwipe()  {
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()

                .addProductToCartByButton(json.getData("Products[0].Name"))
                .scrollToProduct(json.getData("Products[1].Name"),DOWN)
                .addProductToCartByDragAndDrop(json.getData("Products[1].Name"))
                .scrollToProduct(json.getData("Products[2].Name"),UP)
                .addProductToCartByDragAndDrop(json.getData("Products[2].Name"))

                .openCartPageFromHeader()
                .scrollToProduct(json.getData("Products[2].Name"),DOWN)
                .removeProductFromCartBySwipe(json.getData("Products[2].Name"))

                .assertProductIsRemovedFromCart(json.getData("Products[2].Name"));
    }

    @Test
    public void removeAllProductsFromCart() {
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()

                .addProductToCartByButton(json.getData("Products[0].Name"))
                .scrollToProduct(json.getData("Products[1].Name"),DOWN)
                .addProductToCartByDragAndDrop(json.getData("Products[1].Name"))
                .scrollToProduct(json.getData("Products[2].Name"),UP)
                .addProductToCartByDragAndDrop(json.getData("Products[2].Name"))

                .openCartPageFromHeader()
                .removeProductFromCartBySwipe(json.getData("Products[0].Name"))
                .removeProductFromCartBySwipe(json.getData("Products[1].Name"))
                .removeProductFromCartBySwipe(json.getData("Products[2].Name"))

                .assertProductIsRemovedFromCart(json.getData("Products[0].Name"))
                .assertProductIsRemovedFromCart(json.getData("Products[2].Name"))
                .assertProductIsRemovedFromCart(json.getData("Products[1].Name"));
    }
}
