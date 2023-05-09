package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class CheckoutOverviewPage extends AbstractPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/checkout-step-two.html";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement buttonBurgerMenu;
    @FindBy(className = "shopping_cart_link")
    private WebElement buttonCartLink;
    @FindBy(className = "shopping_cart_badge")
    private WebElement spanCartNumber;
    @FindBy(className = "summary_subtotal_label")
    private WebElement subtotalPriceLabel;
    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutOverviewPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Override
    public WebElement getPageLoadedTestElement() {
        return subtotalPriceLabel;
    }

    public float getSubtotalPrice(){
        return Utils.priceStringToFloat(subtotalPriceLabel.getText());
    }
    public void checkoutFinish(){
        try {
            finishButton.click();
        } catch (TimeoutException toe){
            toe.printStackTrace();
            log.info("El botón finalizar del checkout no se ha encontrado");
        }
    }
}
