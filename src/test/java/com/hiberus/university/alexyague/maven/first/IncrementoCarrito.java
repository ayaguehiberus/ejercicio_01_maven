package com.hiberus.university.alexyague.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class IncrementoCarrito {

    static WebDriver driver;

    static String urlInicial = "https://www.saucedemo.com/";
    static String urlPosterior = "https://www.saucedemo.com/inventory.html";
    static By textfieldUsername = By.xpath("//input[@data-test='username']");
    static By textfieldPassword = By.xpath("//input[@data-test='password']");
    static By buttonLogin = By.xpath("//input[@data-test='login-button']");
    static By buttonAddCart = By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    static By badgeCart = By.xpath("//span[@class='shopping_cart_badge']");
    static String usuarioS = "standard_user";
    static String passwordS = "secret_sauce";
    static String numCarritoExp = "1";

    public static void main(String[] args) {

        // Paso 1
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get(urlInicial);

        // Paso 2
        WebElement username = driver.findElement(textfieldUsername);
        username.click();
        username.sendKeys(usuarioS);

        // Paso 3
        WebElement password = driver.findElement(textfieldPassword);
        password.click();
        password.sendKeys(passwordS);

        // Paso 4
        driver.findElement(buttonLogin).click();

        // Paso 5
        Boolean isUrlCorrect = driver.getCurrentUrl().equals(urlPosterior);
        System.out.println("¿Hemos realizado correctamente el login? " + isUrlCorrect);

        // Paso 6
        driver.findElement(buttonAddCart).click();

        // Paso 7
        Boolean existeElBadge = driver.findElement(badgeCart).isDisplayed();

        if (existeElBadge){
            Boolean numeroCoincide = driver.findElement(badgeCart).getText().equals(numCarritoExp);
            System.out.println("");
        }

        // Paso 7
        driver.quit();

    }
}
