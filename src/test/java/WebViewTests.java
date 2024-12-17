import baseTest.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesByNativeAndroidActions.LoginPage;
import webViewPages.HomePage;
import yehiaEngine.managers.JsonManager;

import static yehiaEngine.driverManager.AppiumFactory.*;

public class WebViewTests extends BaseTest {
    //Variables
    String jsonFilePathForLoginTests = "src/test/resources/TestDataJsonFiles/LoginTestData.json";
    String jsonFilePathForLoginWebView = "src/test/resources/TestDataJsonFiles/LoginTestDataForWebView.json";

    JsonManager json1 = new JsonManager(jsonFilePathForLoginTests);
    JsonManager json2 = new JsonManager(jsonFilePathForLoginWebView);


    @BeforeMethod
    public void openWebApp() {
        AppiumDriver myDriver = getDriver(isolatedDriver);
        new LoginPage(myDriver)
                .loginWithValidUser(json1.getData("Users[0].Username"), json1.getData("Users[0].Password"))
                .verifyProductsPageIsOpened()
                .openMenuFromHeader()
                .NavigateToWebViewPage()
                .NavigateToWebViewURL("www.AutomationExercise.com");
    }

    @Test
    public void loginWithValidUser() {
        AppiumDriver myDriver = getDriver(isolatedDriver);

        // Switch to Web View
        var contexts = getAllAvailableContexts(myDriver);
        System.out.println(contexts);
        switchToWebView(myDriver,"WEBVIEW_com.swaglabsmobileapp");

        new HomePage(myDriver)
                .verifyHomePageIsOpened()
                .openLoginSignupPage()
                .verifyLoginSignupPageIsOpened(json2.getData("Messages.LoginHeader"), json2.getData("Messages.SignupHeader"))
                .loginWithValidUser(json2.getData("Users[0].Email"), json2.getData("Users[0].Password"))
                .assertUserIsLoggedIn(json2.getData("Users[0].Name"));

        // Switch Back to Native View
        switchToNativeView(myDriver,"NATIVE_APP");
    }
}
