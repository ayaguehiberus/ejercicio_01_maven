package com.hiberus.university.alexyague.maven.first;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ComandosNavegador {

    static String urlEsperada = "https://www.saucedemo.com/";
    static String tituloEsperado = "Swag Labs";



    public static void main(String[] args) throws InterruptedException {

        // Paso 1
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        // Paso 2
        driver.get(urlEsperada);

        // Paso 3
        String titulo = driver.getTitle();

        // Paso 4
        System.out.println("El titulo de la página es: " + titulo + " y su longitud es " + titulo.length());
        System.out.println("¿Se corresponden los titulos? " + tituloEsperado.equals(titulo));

        // Paso 5
        String url = driver.getCurrentUrl();
        System.out.println("Esta es la URL: " + url);
        System.out.println("¿Se corresponden las URL? " + url.equals(urlEsperada));

        // Paso 6
        String codFuente = driver.getPageSource();
        int codFuenteLong = codFuente.length();

        // Paso 7
        System.out.println("La longitud del código fuente de la página es: " + codFuenteLong);

        Thread.sleep(2000);

        // Paso 8
        driver.quit();
    }


}
