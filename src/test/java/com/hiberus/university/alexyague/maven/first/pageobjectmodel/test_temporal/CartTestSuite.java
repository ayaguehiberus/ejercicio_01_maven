package com.hiberus.university.alexyague.maven.first.pageobjectmodel.test_temporal;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.CartPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.InventoryPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.LoginPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CartTestSuite {

    WebDriver driver;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;


    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        PageFactory.start(driver);
        PageFactory pageFactory = PageFactory.getInstance();
        loginPage = pageFactory.getLoginPage();
        inventoryPage = pageFactory.getInventoryPage();
        cartPage = pageFactory.getCartPage();

        driver.get(LoginPage.PAGE_URL);
    }

    @Test
    public void removeProductFromCart(){

        loginPage.loginCorrecto();
        inventoryPage.addRandomProductsToCart(2);
        inventoryPage.goToCart();
        cartPage.removeFirstProductFromCart();

        Assert.assertEquals("El número de elementos en el carrito no coincide",
                1,
                cartPage.getNumItemsCart());
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
