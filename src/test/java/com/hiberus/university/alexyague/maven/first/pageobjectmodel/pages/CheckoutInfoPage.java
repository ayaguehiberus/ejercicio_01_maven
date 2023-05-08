package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class CheckoutInfoPage extends AbstractPage {

    public static final String PAGE_URL = "https://www.saucedemo.com/checkout-step-one.html";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement buttonBurgerMenu;
    @FindBy(className = "shopping_cart_link")
    private WebElement buttonCartLink;
    @FindBy(className = "shopping_cart_badge")
    private WebElement spanCartNumber;
    @FindBy (id = "first-name")
    private WebElement inputFirstname;
    @FindBy (id = "last-name")
    private WebElement inputLastname;
    @FindBy (id = "postal-code")
    private WebElement inputPostalcode;
    @FindBy (id = "continue")
    private WebElement buttonContinue;

    public CheckoutInfoPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
}
