package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Inventario {

    WebDriver driver;
    WebDriverWait wait;
    String url = "http://www.saucedemo.com/";

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 5);

        driver.get(url);
    }

    @Test
    public void validarNumeroItems(){

    }

    @Test
    public void validarProductoInventario(){

    }

    @Test
    public void validarProductoAgregadoCarrito(){

    }

    @Test
    public void validarProductoEliminadoCarrito(){

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
