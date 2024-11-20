package testCasesByW3cTouchActions;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pagesByW3cTouchActions.LoginPage;
import yehiaEngine.managers.JsonManager;
import static yehiaEngine.driverManager.AppiumFactory.*;
import static yehiaEngine.utilities.RandomDataGenerator.*;

@Epic("SwagLabs Android App")
@Feature("User Login")
@Story("Verify User Login")
public class LoginTests extends BaseTest {

    //Variables
    String jsonFilePathForAddToCart = "src/test/resources/TestDataJsonFiles/LoginTestData.json";
    JsonManager json = new JsonManager(jsonFilePathForAddToCart);

    @Test
    public void successfulLogin() {
        new LoginPage(getDriver(isolatedDriver))
                .loginWithValidUser(json.getData("Users[0].Username"),json.getData("Users[0].Password"))
                .verifyProductsPageIsOpened();
    }

    @Test
    public void invalidUserLogin() {
        new LoginPage(getDriver(isolatedDriver))
                .loginWithInvalidUser(generateName(),generateStrongPassword())
                .assertErrorMassageForInvalidCredentials(json.getData("Massages.InvalidUserLogin"));
    }

    @Test
    public void lockedUserLogin() {
        new LoginPage(getDriver(isolatedDriver))
                .loginWithInvalidUser(json.getData("Users[2].Username"),json.getData("Users[2].Password"))
                .assertErrorMassageForLockedUser(json.getData("Massages.LockedUserLogin"));
    }
}
