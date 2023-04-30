package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Inventario extends Locators{

    WebDriver driver;
    WebDriverWait wait;
    String url = "http://www.saucedemo.com/";
    String urlExpected = "https://www.saucedemo.com/inventory.html";
    int numProductosExp = 6;
    String productName3 = "Sauce Labs Bolt T-Shirt";

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
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());

        // Paso 5: Validar que el numero de productos mostrados es igual a 6
        Utils.esperarElementoVisible(driver, wait, INVENTORYLIST); // Espero a que la lista esté cargada
        List<WebElement> listaProductos = driver.findElements(INVENTORYLISTITEM); // Almaceno la lista de productos
        Assert.assertEquals("La cantidad de productos en la lista no es " + numProductosExp, numProductosExp, listaProductos.size()); // Validación
    }

    @Test
    public void validarProductoInventario(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());

        // Paso 5: Validar que el producto Sauce Labs Bolt T-Shirt aparece en el inventario.
        Utils.esperarElementoVisible(driver, wait, INVENTORYLISTITEMNAME); // Espero a que algún elemento de la lista esté cargado
        List<WebElement> listaProductos = driver.findElements(INVENTORYLISTITEMNAME); // Almaceno la lista de productos
        Assert.assertTrue("El producto no se encuentra en el inventario", Utils.elementoExisteEnLista(listaProductos, productName3)); // Valido buscando el elemento en la lista
    }

    @Test
    public void validarProductoInventarioSinLista(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());

        // Paso 5: Validar que el producto Sauce Labs Bolt T-Shirt aparece en el inventario.
        WebElement prod = Utils.esperarElementoVisible(driver, wait, SPECIFICPRODUCTNAME); // Busco el elemento a través de su localizador
        Assert.assertNotNull("El producto no se encuentra en la lista", prod); // Valido (Los métodos esperarElementoVisible y esperarElementoClickable devuelven null si no se encuentra)
    }

    @Test
    public void validarProductoAgregadoCarrito(){
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());
    }

    @Test
    public void validarProductoEliminadoCarrito(){
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
