package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login extends Locators{

    WebDriver driver;
    WebDriverWait wait;
    String url = "http://www.saucedemo.com/";
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
    public void validarLoginCorrecto() {

        // Paso 2 Escribir el username standard_user
        WebElement username = Utils.esperarElementoClickable(driver, wait, TEXTFIELDUSERNAME);
        username.sendKeys(validUsername);

        // Paso 3 Escribir el password secret_sauce
        WebElement password = Utils.esperarElementoClickable(driver, wait, TEXTFIELDPASSWORD);
        password.sendKeys(validPassword);

        // Paso 4 Pulsar en el botón del Login.
        Utils.esperarElementoClickable(driver, wait, BUTTONLOGIN).click();

        // Paso 5 Validar que hemos accedido correctamente a la página, comprobando que se muestra la URL https://www.saucedemo.com/inventory.html
        Assert.assertEquals("ERROR: No nos encontramos en la página main. Login inválido", urlExpected, driver.getCurrentUrl());
    }

    @Test
    public void validarLoginIncorrecto(){

        // Paso 2 Escribir el username standard (Introducir mal el usuario para forzar el error)
        WebElement username = Utils.esperarElementoClickable(driver, wait, TEXTFIELDUSERNAME);
        username.sendKeys(invalidUsername);

        // Paso 3 Escribir el password secret_sauce
        WebElement password = Utils.esperarElementoClickable(driver, wait, TEXTFIELDPASSWORD);
        password.sendKeys(validPassword);

        // Paso 4 Pulsar en el botón del Login
        Utils.esperarElementoClickable(driver, wait, BUTTONLOGIN).click();

        // Paso 5 Validar que aparece el WebElement del mensaje de error
        WebElement msgError = Utils.esperarElementoVisible(driver, wait, ERRORMSG);
        Assert.assertNotNull("ERROR: No aparece el mensaje de error", msgError);

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
