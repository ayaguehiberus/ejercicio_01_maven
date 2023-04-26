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

public class ValidarNumeroResultados extends Base {

    static WebDriver driver;

    static String urlInicial = "https://www.saucedemo.com/";
    static String urlPosterior = "https://www.saucedemo.com/inventory.html";
    static int inventoryListNumExp = 6;

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
        List<WebElement> inventoryList = driver.findElements(inventoryListBy);

        if (inventoryList.size() > 0){
            Boolean inventoryListNumCorrect = inventoryListNumExp == inventoryList.size();
            System.out.println("¿Encontramos " + inventoryListNumExp + " productos en la página? " + inventoryListNumCorrect);
        }

        else{
            System.out.println("La lista no aparece");
        }

        // Paso 7
        driver.quit();
    }


}
