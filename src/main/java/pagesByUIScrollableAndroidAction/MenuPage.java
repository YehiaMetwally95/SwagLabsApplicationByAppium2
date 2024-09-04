package pagesByUIScrollableAndroidAction;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

public class MenuPage {
    //Variables
    AppiumDriver driver;

    //Constructor
    public MenuPage(AppiumDriver driver) {
        this.driver = driver;
    }

    //Actions
    @Step("Log Out")
    public LoginPage logout()
    {

        return new LoginPage(driver);
    }

}
