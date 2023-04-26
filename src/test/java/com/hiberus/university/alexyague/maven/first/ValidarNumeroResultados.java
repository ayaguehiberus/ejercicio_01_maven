package com.hiberus.university.alexyague.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ValidarNumeroResultados {

    static WebDriver driver;

    static String urlInicial = "https://www.saucedemo.com/";
    static String urlPosterior = "https://www.saucedemo.com/inventory.html";
    static By textfieldUsername = By.xpath("//input[@data-test='username']");
    static By textfieldPassword = By.xpath("//input[@data-test='password']");
    static By buttonLogin = By.xpath("//input[@data-test='login-button']");
    static By inventoryListBy = By.xpath("//div[@class='inventory_item']");
    static String usuarioS = "standard_user";
    static String passwordS = "secret_sauce";
    static int inventoryListNumExp = 6;

    public static void main(String[] args) {

        // Paso 1
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        driver.get(urlInicial);

        // Paso 2
        WebElement username = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(textfieldUsername)));
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
        List<WebElement> inventoryList = driver.findElements(inventoryListBy);
        Boolean inventoryListNumCorrect = inventoryListNumExp == inventoryList.size();
        System.out.println("¿Encontramos " + inventoryListNumExp + " productos en la página? " + inventoryListNumCorrect);

        // Paso 7
        driver.quit();

    }

    public static void esperarElemento(){

    }
}
