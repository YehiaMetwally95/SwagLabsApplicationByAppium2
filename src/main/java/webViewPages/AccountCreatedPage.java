package webViewPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yehiaEngine.assertions.CustomSoftAssert;

import java.io.IOException;

public class AccountCreatedPage extends HomePage{
        //Locator
        private By continueButton = By.xpath("//a[contains(@data-qa,'continue')]");
        private By successMessage= By.xpath("//*[@data-qa='account-created']");

        //Constructor
        public AccountCreatedPage(WebDriver driver) {
            super(driver);
        }

        //Action
        @Step("Continue To Home Page")
        public HomePage continueToHomePage() throws IOException {
            bot.press(continueButton);
            return new HomePage(driver);
        }

        //Validation
        @Step("Verify Account Created Success Message")
        public AccountCreatedPage verifyAccountCreatedMessage(String message) throws IOException {
            CustomSoftAssert.assertEquals(bot.readText(successMessage),message);
            return this;
        }
}
