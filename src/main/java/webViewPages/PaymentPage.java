package webViewPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;

import java.io.IOException;

public class PaymentPage extends HomePage{
    //Variables

    //Locators
    By cardNameText = By.name("name_on_card");
    By cardNumberText = By.name("card_number");
    By cardCVCText = By.name("cvc");
    By cardExpirationMonthText = By.name("expiry_month");
    By cartExpirationYearText = By.name("expiry_year");
    By confirmOrderButton = By.id("submit");
    By successMessageLocator = By.cssSelector("#success_message .alert");
    By paymentHeaderLocator=By.className("heading");

    //Constructor
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Perform Payment")
    public PaymentDonePage performPayment(String name,String number,String cvc,String month,String year) throws IOException {
        enterCardName(name).
                enterCardNumber(number).
                enterCardCVC(cvc).
                enterCardExpirationMonth(month).
                enterCardExpirationYear(year).
                clickOnConfirmOrderButton();
        return new PaymentDonePage(driver);
    }

    //Validations
    @Step("Verify Payment Page is Opened")
    public PaymentPage verifyPaymentPageIsOpened(String header) throws IOException {
        verifyPaymentHeader(header);
        return this;
    }

    @Step("Assert Payment Success Massage")
    public PaymentPage assertPaymentSuccessMassage(String message) throws IOException {
        CustomAssert.assertEquals(bot.readText(successMessageLocator),message);
        return this;
    }

    //Private Methods
    @Step("Enter Card Name")
    private PaymentPage enterCardName(String name) throws IOException {
        bot.type(cardNameText,name);
        return this;
    }

    @Step("Enter Card Number")
    private PaymentPage enterCardNumber(String number) throws IOException {
        bot.type(cardNumberText,number);
        return this;
    }

    @Step("Enter Card CVC")
    private PaymentPage enterCardCVC(String cvc) throws IOException {
        bot.type(cardCVCText,cvc);
        return this;
    }

    @Step("Enter Card Expiration Month")
    private PaymentPage enterCardExpirationMonth(String month) throws IOException {
        bot.type(cardExpirationMonthText,month);
        return this;
    }

    @Step("Enter Card Expiration Year")
    private PaymentPage enterCardExpirationYear(String year) throws IOException {
        bot.type(cartExpirationYearText,year);
        return this;
    }

    @Step("Click on Confirm Order Button")
    private PaymentPage clickOnConfirmOrderButton() throws IOException {
        bot.press(confirmOrderButton);
        return this;
    }

    @Step("Verify Payment Header")
    private PaymentPage verifyPaymentHeader(String header) throws IOException {
        CustomSoftAssert.assertEquals(bot.readText(paymentHeaderLocator),header);
        return this;
    }
}
