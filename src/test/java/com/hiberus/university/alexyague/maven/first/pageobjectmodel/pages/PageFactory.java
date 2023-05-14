package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {

    private static PageFactory pageFactory;
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final InventoryPage inventoryPage;
    private final CartPage cartPage;
    private final CheckoutInfoPage checkoutInfoPage;
    private final CheckoutOverviewPage checkoutOverviewPage;
    private final CheckoutCompletePage checkoutCompletePage;
    private PageFactory(WebDriver driver){
        this.driver = driver;
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutInfoPage = new CheckoutInfoPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    public static void start(WebDriver driver){
        pageFactory = new PageFactory(driver);
    }

    public WebDriver getDriver(){
        return driver;
    }

    public static PageFactory getInstance(){
        return pageFactory;
    }
    public LoginPage getLoginPage(){
        return loginPage;
    }
    public InventoryPage getInventoryPage(){
        return inventoryPage;
    }
    public CartPage getCartPage(){
        return cartPage;
    }
    public CheckoutInfoPage getCheckoutInfoPage() { return checkoutInfoPage; }
    public CheckoutOverviewPage getCheckoutOverviewPage() { return checkoutOverviewPage; }
    public CheckoutCompletePage getCheckoutCompletePage() { return checkoutCompletePage; }

}
