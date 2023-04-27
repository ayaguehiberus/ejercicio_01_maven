package com.hiberus.university.alexyague.maven.first.selenium_inicial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ComandosNavegador {

    static WebDriver driver;

    static String urlInicial = "https://www.hiberus.com/";
    static String urlPosterior = "https://www.hiberus.com/desarrollo-y-outsourcing-tecnologico";
    static By buttonAcceptCookies = By.xpath("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
    static By linkAppManagement = By.xpath("//a[@href='/desarrollo-y-outsourcing-tecnologico' and @title]");


    public static void main(String[] args) throws InterruptedException {

        // Paso 1

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        // Paso 2
        viajandoAUrl(urlInicial);
        driver.get(urlInicial);
        isUrlCorrect(urlInicial);

        // Paso 3
        driver.findElement(buttonAcceptCookies).click();
        Thread.sleep(2000);
        driver.findElement(linkAppManagement).click();
        viajandoAUrl(urlPosterior);
        Thread.sleep(2000);
        isUrlCorrect(urlPosterior);

        // Paso 4
        driver.navigate().back();
        viajandoAUrl(urlInicial);
        Thread.sleep(2000);
        isUrlCorrect(urlInicial);

        // Paso 5
        driver.navigate().forward();
        viajandoAUrl(urlPosterior);
        Thread.sleep(2000);
        isUrlCorrect(urlPosterior);

        // Paso 6
        driver.navigate().to(urlInicial);
        viajandoAUrl(urlInicial);
        Thread.sleep(2000);
        isUrlCorrect(urlInicial);

        // Paso 7
        driver.navigate().refresh();
        viajandoAUrl(urlInicial);
        Thread.sleep(2000);
        isUrlCorrect(urlInicial);

        // Paso 8
        driver.quit();
    }

    public static void isUrlCorrect(String urlExp){
        Boolean resul = urlExp.equals(driver.getCurrentUrl());
        System.out.println("Estoy en la URL " + urlExp + " ? " + resul);
    }

    public static void viajandoAUrl(String urlTo){
        System.out.println("Viajando a la URL " + urlTo);
    }


}
