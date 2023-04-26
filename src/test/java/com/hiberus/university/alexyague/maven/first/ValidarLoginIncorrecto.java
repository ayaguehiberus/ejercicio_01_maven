package com.hiberus.university.alexyague.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ValidarLoginIncorrecto {

    static WebDriver driver;

    static String urlInicial = "https://www.saucedemo.com/";
    static String urlPosterior = "https://www.saucedemo.com/inventory.html";
    static By textfieldUsername = By.xpath("//input[@data-test='username']");
    static By textfieldPassword = By.xpath("//input[@data-test='password']");
    static By buttonLogin = By.xpath("//input[@data-test='login-button']");
    static By errorMsg = By.xpath("//h3[@data-test='error']");
    static String usuarioS = "standar_use";
    static String passwordS = "secret_sauce";

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
        Boolean isErrorMsgDisplayed = driver.findElement(errorMsg).isDisplayed();
        System.out.println("¿Ha salido el mensaje de error? " + isErrorMsgDisplayed);

    }
}
