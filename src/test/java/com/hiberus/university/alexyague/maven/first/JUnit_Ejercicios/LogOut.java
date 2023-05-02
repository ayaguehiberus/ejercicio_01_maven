package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOut extends Locators{

    WebDriver driver;
    WebDriverWait wait;
    String url = "https://www.saucedemo.com/";
    String urlExpected = "https://www.saucedemo.com/inventory.html";
    String validUsername = "standard_user";
    String invalidUsername = "standard_use";
    String validPassword = "secret_sauce";

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 5);

        // Paso 1 Ir a la página https://www.saucedemo.com
        driver.get(url);
    }

    @Test
    public void comprobarLogout(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());

        // Paso 5: Realizar el Logout
        Utils.esperarElementoClickable(driver, wait, BUTTON_MENU).click();
        Utils.esperarElementoClickable(driver, wait, BUTTON_LOGOUT).click();

        // Paso 6: Validar que el logout se ha realizado llevándonos a la página del Login
        Assert.assertEquals("El logout no fue realizado correctamente", url, driver.getCurrentUrl());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
