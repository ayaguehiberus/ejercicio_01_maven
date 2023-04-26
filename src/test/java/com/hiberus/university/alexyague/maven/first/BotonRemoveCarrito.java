package com.hiberus.university.alexyague.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BotonRemoveCarrito extends Base{

    static WebDriver driver;

    static String urlInicial = "https://www.saucedemo.com/";
    static String urlPosterior = "https://www.saucedemo.com/inventory.html";
    static String usuarioS = "standard_user";
    static String passwordS = "secret_sauce";

    public static void main(String[] args) {
        // Paso 1
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(urlInicial);

        // Paso 2
        WebElement username = Utils.esperarElementoClickable(driver, textfieldUsername, 10L);
        username.click();
        username.sendKeys(usuarioS);

        // Paso 3
        WebElement password = Utils.esperarElementoClickable(driver, textfieldPassword, 10L);
        password.click();
        password.sendKeys(passwordS);

        // Paso 4
        Utils.esperarElementoClickable(driver, buttonLogin, 10L).click();

        // Paso 5
        Boolean isUrlCorrect = driver.getCurrentUrl().equals(urlPosterior);
        System.out.println("¿Hemos realizado correctamente el login? " + isUrlCorrect);

        // Paso 6
        Utils.esperarElementoClickable(driver, buttonAddCart2, 10L).click();

        // Paso 7
        WebElement butRemoveCartElem = Utils.esperarElementoClickable(driver, buttonRemoveCart, 10L);
        System.out.println("¿Sale correctamente el botón de remover producto del carrito? " + butRemoveCartElem.isDisplayed());

        // Paso 8
        driver.quit();

    }
}
