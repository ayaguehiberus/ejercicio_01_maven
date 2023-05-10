package com.hiberus.university.alexyague.maven.first.pageobjectmodel.test_temporal;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.InventoryPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.LoginPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckoutTestSuite {

    WebDriver driver;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;


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

        driver.get(LoginPage.PAGE_URL);
    }

    @Test
    public void validatePriceCheckOut(){

    }

    @After
    public void tearDown(){
        driver.close();
    }
}
