package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class LoginPage extends AbstractPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/";
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";

    //ELEMENTOS
    @FindBy(id = "user-name")
    private WebElement inputUsername;
    @FindBy(id = "password")
    private WebElement inputPassword;
    @FindBy(id = "login-button")
    private WebElement buttonLogin;
    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement msgError;

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return buttonLogin;
    }

    public void enterUsername(String username){
        log.info("Introduciendo username");
        inputUsername.sendKeys(username);
    }

    public void enterPassword(String password){
        log.info("Introduciendo password");
        inputUsername.sendKeys(password);
    }

    public void clickLoginButton(){
        log.info("Logging in...");
        try {
            buttonLogin.click();
        } catch (TimeoutException toe){
            log.info("Timeout clicking login button" + toe.getClass().getSimpleName());
        }
    }

    public boolean hasUsernamePasswordError(){
        return msgError.isDisplayed();
    }

    public void loginCorrecto(){
        enterUsername(this.USERNAME);
        enterPassword(this.PASSWORD);
        clickLoginButton();
    }
}
