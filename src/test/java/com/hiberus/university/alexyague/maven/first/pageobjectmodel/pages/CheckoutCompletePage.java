package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class CheckoutCompletePage extends AbstractPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/checkout-complete.html";
    public static final String MSG_COMPLETE = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement buttonBurgerMenu;
    @FindBy(className = "shopping_cart_link")
    private WebElement buttonCartLink;
    @FindBy(className = "shopping_cart_badge")
    private WebElement spanCartNumber;
    @FindBy(className = "complete-text")
    private WebElement msgOrderCompleted;
    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public CheckoutCompletePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Override
    public WebElement getPageLoadedTestElement() {
        return backHomeButton;
    }

    public String getMsgComplete(){
        try {
            log.info("Capturando mensaje de compra realizada");
            return msgOrderCompleted.getText();
        } catch (TimeoutException toe){
            log.info("El elemento que contiene el mensaje no fue encontrado");
            return "";
        }
    }
    public void backToHome(){
        try {
            log.info("Volviendo a home");
            backHomeButton.click();
        } catch (TimeoutException toe){
            log.info("Botón de volver no encontrado");
        }
    }
}
