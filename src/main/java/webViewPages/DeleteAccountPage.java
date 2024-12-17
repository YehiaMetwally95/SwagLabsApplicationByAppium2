package webViewPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yehiaEngine.assertions.CustomSoftAssert;

import java.io.IOException;

public class DeleteAccountPage extends HomePage {
    //Variables

    //Locators
    By deleteMassageLocator = By.xpath("//h2[@data-qa='account-deleted']");
    By continueButton = By.xpath("//a[@data-qa='continue-button']");

    //Constructor
    public DeleteAccountPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Navigate to Home Page")
    public HomePage navigateToHomePage() throws IOException {
        bot.press(continueButton);
        return new HomePage(driver);
    }

    //Validations
    @Step("Verify Account Deleted Massage")
    public DeleteAccountPage verifyDeleteMassage(String massage) throws IOException {
        CustomSoftAssert.assertEquals(
                bot.readText(deleteMassageLocator)
                ,massage
        );
        return this;
    }
}
