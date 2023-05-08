package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class CartPage extends AbstractPage{

    public static final String PAGE_URL = "https://www.saucedemo.com/cart.html";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement buttonBurgerMenu;
    @FindBy(className = "shopping_cart_link")
    private WebElement buttonCartLink;
    @FindBy(className = "shopping_cart_badge")
    private WebElement spanCartNumber;
    @FindBy(className = "inventory_item_price")
    private List<WebElement> cartItemPriceList;
    @FindBy(xpath = "//button[contains(@id, 'remove')]")
    private List<WebElement> cartItemRemoveButton;
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Override
    public WebElement getPageLoadedTestElement() {
        return null;
    }
}
