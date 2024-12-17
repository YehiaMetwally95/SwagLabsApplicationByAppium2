package webViewPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import yehiaEngine.assertions.CustomAssert;
import yehiaEngine.assertions.CustomSoftAssert;

import java.io.IOException;

public class ContactusPage extends HomePage {
    //Variables

    //Locators
    By contactUsHeaderLocator = By.cssSelector(".col-sm-12 h2");
    By nameLocator = By.name("name");
    By emailLocator = By.name("email");
    By subjectLocator = By.name("subject");
    By messageLocator = By.name("message");
    By submitButton = By.name("submit");
    By successMassageLocator = By.cssSelector(".contact-form .alert-success");
    By homeButton = By.xpath("//div[@id='form-section']//a[.=' Home']");
    By uploadFileControl= By.name("upload_file");

    //Constructor
    public ContactusPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Submit ContactUs Request")
    public ContactusPage submitContactUsRequest(String name, String email , String subject , String message, String filepath) throws IOException {
        enterContactDetails(name,email,subject,message,filepath)
                .clickOnSubmitButton();
        return this;
    }

    //Validations
    @Step("Verify Contact Us Page is Opened")
    public ContactusPage verifyContactUsPageIsOpened(String header) throws IOException {
        verifyContactUsHeader(header);
        return this;
    }

    @Step("Assert Contact Success Massage")
    public ContactusPage assertContactSuccessMassage(String message) throws IOException {
        CustomAssert.assertEquals(
                bot.readText(successMassageLocator)
                ,message
        );
        return this;
    }

    //Private Methods
    @Step("Enter Contact Details")
    private ContactusPage enterContactDetails(String name, String email , String subject , String message, String filepath) throws IOException {
        bot.
                type(nameLocator,name).
                type(emailLocator,email).
                type(subjectLocator,subject).
                type(messageLocator,message).
                type(uploadFileControl,System.getProperty("user.dir")+filepath);
        return this;
    }

    @Step("Click on Submit Button")
    private ContactusPage clickOnSubmitButton() throws IOException {
        bot.
                press(submitButton).
                acceptAlert();
        return this;
    }

    @Step("Verify Contact Us Header")
    private ContactusPage verifyContactUsHeader(String header) throws IOException {
        CustomSoftAssert.assertEquals(bot.readText(contactUsHeaderLocator),header);
        return this;
    }

}
