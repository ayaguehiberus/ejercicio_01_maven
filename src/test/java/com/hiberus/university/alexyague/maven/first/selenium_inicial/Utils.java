package com.hiberus.university.alexyague.maven.first.selenium_inicial;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    public static void iniciarDriver(WebDriver driver, String url){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(url);
    }

    public static WebElement esperarElementoVisible(WebDriver driver, By loc, Long timeout){

        WebElement element = null;

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(loc)));
            element = driver.findElement(loc);

        } catch(Exception e){
            e.printStackTrace();
        }

        return element;
    }


    public static WebElement esperarElementoClickable(WebDriver driver, By loc, Long timeout){

        WebElement element = null;

        try {

            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(loc));
            element = driver.findElement(loc);

        } catch (Exception e){
            e.printStackTrace();
        }

        return element;
    }
}
