package webViewPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import yehiaEngine.assertions.CustomSoftAssert;

public class LoginSignupPage extends HomePage{
    //Variables

    //Locators
    By loginEmailText = By.xpath("//input[@data-qa='login-email']");
    By loginPasswordText = By.xpath("//input[@data-qa='login-password']");
    By signUpNameText = By.xpath("//input[@data-qa='signup-name']");
    By signUpEmailText = By.xpath("//input[@data-qa='signup-email']");
    By loginButton = By.xpath("//button[@data-qa='login-button']");
    By signUpButton = By.xpath("//button[@data-qa='signup-button']");
    By loginHeaderLocator = RelativeLocator.with(By.tagName("h2")).above(loginButton);
    By signupHeaderLocator = RelativeLocator.with(By.tagName("h2")).above(signUpNameText);
    By loginErrorMassage = By.xpath("//form[@action = '/login']/p[@style='color: red;']");
    By signupErrorMassage = By.xpath("//form[@action = '/signup']/p[@style='color: red;']");


    //Constructor
    public LoginSignupPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    @Step("Login with Valid User")
    public HomePage loginWithValidUser(String email, String password)   {
        enterEmailLogin(email)
                .enterPasswordLogin(password)
                .clickOnLoginButtonForSuccess();
        return new HomePage(driver);
    }

    @Step("Login with Invalid User")
    public LoginSignupPage loginWithInvalidUser(String email, String password)   {
        enterEmailLogin(email)
                .enterPasswordLogin(password)
                .clickOnLoginButtonForFailure();
        return this;
    }

    @Step("Signup with New User")
    public RegisterPage signupWithNewUser(String name, String email)   {
        enterNameSignup(name)
                .enterEmailSignup(email)
                .clickOnSignupButtonForSuccess();
        return new RegisterPage(driver);
    }

    @Step("Signup with Existing User")
    public LoginSignupPage signupWithExistingUser(String name, String email)   {
        enterNameSignup(name)
                .enterEmailSignup(email)
                .clickOnSignupButtonForFailure();
        return this;
    }

    //Validations
    @Step("Verify Login Signup Page is Opened")
    public LoginSignupPage verifyLoginSignupPageIsOpened(String loginHeader,String signupHeader){
        verifyLoginHeader(loginHeader).verifySignupHeader(signupHeader);
        return this;
    }

    @Step("Assert Incorrect Login Massage")
    public LoginSignupPage assertIncorrectLoginMassage(String massage)   {
        Assert.assertEquals(
                bot.readText(loginErrorMassage)
                ,massage
        );
        return this;
    }

    @Step("Assert Incorrect Signup Massage")
    public LoginSignupPage assertIncorrectSignupMassage(String massage)   {
        Assert.assertEquals(
                bot.readText(signupErrorMassage)
                ,massage
        );
        return this;
    }

    //Private Actions
    @Step("Enter Email for Login")
    private LoginSignupPage enterEmailLogin(String email)   {
        bot.type(loginEmailText,email);
        return this;
    }

    @Step("Enter Password for Login")
    private LoginSignupPage enterPasswordLogin (String password)   {
        bot.type(loginPasswordText,password);
        return this;
    }

    @Step("Click on Login Button")
    private HomePage clickOnLoginButtonForSuccess()   {
        bot.press(loginButton);
        return new HomePage(driver);
    }

    @Step("Click on Login Button")
    private LoginSignupPage clickOnLoginButtonForFailure()   {
        bot.press(loginButton);
        return this;
    }

    @Step("Enter Name for Signup")
    private LoginSignupPage enterNameSignup(String name)   {
        bot.type(signUpNameText,name);
        return this;
    }

    @Step("Enter Email for Signup")
    private LoginSignupPage enterEmailSignup(String email)   {
        bot.type(signUpEmailText,email);
        return this;
    }

    @Step("Click on Signup Button")
    private RegisterPage clickOnSignupButtonForSuccess()   {
        bot.press(signUpButton);
        return new RegisterPage(driver);
    }

    @Step("Click on Signup Button")
    private LoginSignupPage clickOnSignupButtonForFailure()   {
        bot.press(signUpButton);
        return this;
    }

    @Step("Verify Login Header")
    private LoginSignupPage verifyLoginHeader(String loginHeader)   {
        CustomSoftAssert.assertEquals(bot.readText(loginHeaderLocator),loginHeader);
        return this;
    }

    @Step("Verify Signup Header")
    private LoginSignupPage verifySignupHeader(String signupHeader)   {
        CustomSoftAssert.assertEquals(bot.readText(signupHeaderLocator),signupHeader);
        return this;
    }

}
