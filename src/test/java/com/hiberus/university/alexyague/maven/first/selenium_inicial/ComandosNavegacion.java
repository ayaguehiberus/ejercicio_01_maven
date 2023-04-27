package com.hiberus.university.alexyague.maven.first.selenium_inicial;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ComandosNavegacion {

    static String urlEsperada = "https://www.saucedemo.com/";

    public static void main(String[] args) {

        // Paso 1
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        // Paso 2
        driver.get(urlEsperada);

    }


}
