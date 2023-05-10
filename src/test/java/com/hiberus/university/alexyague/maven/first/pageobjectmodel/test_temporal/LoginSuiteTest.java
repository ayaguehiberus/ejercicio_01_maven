package com.hiberus.university.alexyague.maven.first.pageobjectmodel.test_temporal;

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

import java.util.concurrent.TimeUnit;

public class LoginSuiteTest {

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

        driver.get(LoginPage.PAGE_URL);
    }

    @Test
    public void loginTest(){
        loginPage.enterUsername(LoginPage.USERNAME);
        loginPage.enterPassword(LoginPage.PASSWORD);
        loginPage.clickLoginButton();

        Assert.assertEquals("La página destino no coincide con la esperada",
                InventoryPage.PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    public void invalidLogin(){
        loginPage.enterUsername("standard");
        loginPage.enterPassword(LoginPage.PASSWORD);
        loginPage.clickLoginButton();

        Assert.assertTrue("El mensaje de error no aparece tras login incorrecto",
                loginPage.hasUsernamePasswordError());
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
