package testCasesByNativeAndroidActions;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pagesByNativeAndroidActions.LoginPage;
import yehiaEngine.managers.JsonManager;

import static yehiaEngine.driverManager.AppiumFactory.getDriver;

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
                .addProductToCartByDragAndDrop(json.getData("Products[1].Name"))
                .addProductToCartByDragAndDrop(json.getData("Products[2].Name"))
                .openCartPageFromHeader()
                .removeProductFromCartByButton(json.getData("Products[2].Name"))
                .assertProductIsRemovedFromCart(json.getData("Products[2].Name"));
    }

    @Test
    public void removeProductFromCartBySwipe(){
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()

                .addProductToCartByButton(json.getData("Products[0].Name"))
                .addProductToCartByDragAndDrop(json.getData("Products[1].Name"))
                .addProductToCartByDragAndDrop(json.getData("Products[2].Name"))
                .openCartPageFromHeader()
                .removeProductFromCartBySwipe(json.getData("Products[2].Name"))
                .assertProductIsRemovedFromCart(json.getData("Products[2].Name"));
    }

    @Test
    public void removeAllProductsFromCart() {
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()

                .addProductToCartByButton(json.getData("Products[0].Name"))
                .addProductToCartByDragAndDrop(json.getData("Products[1].Name"))
                .addProductToCartByDragAndDrop(json.getData("Products[2].Name"))

                .openCartPageFromHeader()
                .removeProductFromCartBySwipe(json.getData("Products[2].Name"))
                .removeProductFromCartBySwipe(json.getData("Products[0].Name"))
                .removeProductFromCartBySwipe(json.getData("Products[1].Name"))

                .assertProductIsRemovedFromCart(json.getData("Products[0].Name"))
                .assertProductIsRemovedFromCart(json.getData("Products[2].Name"))
                .assertProductIsRemovedFromCart(json.getData("Products[1].Name"));
    }
}
