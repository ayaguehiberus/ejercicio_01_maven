package com.hiberus.university.alexyague.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class IncrementoCarrito extends Base {

    static WebDriver driver;

    static String urlInicial = "https://www.saucedemo.com/";
    static String urlPosterior = "https://www.saucedemo.com/inventory.html";
    static String usuarioS = "standard_user";
    static String passwordS = "secret_sauce";
    static String numCarritoExp = "1";

    public static void main(String[] args) {

        // Paso 1
        Utils.iniciarDriver(driver, urlInicial);

        // Paso 2
        WebElement username = Utils.esperarElementoClickable(driver, textfieldUsername, 10L);
        username.click();
        username.sendKeys(usuarioS);

        // Paso 3
        WebElement password = Utils.esperarElementoClickable(driver, textfieldPassword, 10L);
        password.click();
        password.sendKeys(passwordS);

        // Paso 4
        Utils.esperarElementoClickable(driver, buttonLogin, 10L);

        // Paso 5
        Boolean isUrlCorrect = driver.getCurrentUrl().equals(urlPosterior);
        System.out.println("¿Hemos realizado correctamente el login? " + isUrlCorrect);

        // Paso 6
        Utils.esperarElementoClickable(driver, buttonAddCart, 10L).click();

        // Paso 7
        WebElement badgeCartElem = Utils.esperarElementoVisible(driver, badgeCart, 10L);

        if (badgeCartElem.isDisplayed()){
            Boolean numeroCoincide = badgeCartElem.getText().equals(numCarritoExp);
            System.out.println("Validación correcta. Existe " + badgeCartElem.getText() + " elementos en el carrito");
        }

        // Paso 7
        driver.quit();

    }
}
