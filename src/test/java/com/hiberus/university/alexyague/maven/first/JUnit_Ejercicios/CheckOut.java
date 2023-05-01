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

import java.util.ArrayList;
import java.util.List;

public class CheckOut extends Locators{

    WebDriver driver;
    WebDriverWait wait;
    String urlLogin = "http://www.saucedemo.com/";
    String urlMain = "https://www.saucedemo.com/inventory.html";
    String urlCarrito = "https://www.saucedemo.com/cart.html";
    String urlCheckout1 = "https://www.saucedemo.com/checkout-step-one.html";
    String urlCheckout2 = "https://www.saucedemo.com/checkout-step-two.html";
    String urlCheckoutComplete = "https://www.saucedemo.com/checkout-complete.html";
    String msgCompleteExpected = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 5);

        driver.get(urlLogin);
    }

    @Test
    public void comprobarPrecioFinalCheckoutVariosProd(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlMain, driver.getCurrentUrl());

        // Paso 5: Agregar al carrito 2 productos elegidos al azar
        Utils.esperarElementoClickable(driver, wait, INVENTORYLISTITEMNAME);
        ArrayList<WebElement> listaInventario = (ArrayList<WebElement>) Utils.getListaElementosNoWait(driver, BUTTONADDCARTPRODLIST);
        List<WebElement> listaProdRandom = Utils.randomElementsOfList(listaInventario, 3);
        Utils.clickElements(listaProdRandom);

        // Paso 6: Ir al carrito
        WebElement carritoButton = Utils.esperarElementoClickable(driver, wait, CARTBUTTON);
        carritoButton.click();
        Assert.assertEquals("La redirección al carrito no fue realizada correctamente", urlCarrito, driver.getCurrentUrl());

        // Paso 7: Realizar el Checkout del producto.


        // Paso 8: Rellenar datos del checkout y continuar


        // Paso 9: Finalizar Checkout


        // Paso 10:  Validar que el precio total del pedido (Item total) es la suma del importe de los productos seleccionados en el inventario



    }

    @Test
    public void realizarPedido(){

        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlMain, driver.getCurrentUrl());

        // Paso 5: Agregar al carrito 2 productos elegidos al azar
        Utils.esperarElementoClickable(driver, wait, INVENTORYLISTITEMNAME);
        ArrayList<WebElement> listaInventario = (ArrayList<WebElement>) Utils.getListaElementosNoWait(driver, BUTTONADDCARTPRODLIST);
        List<WebElement> listaProdRandom = Utils.randomElementsOfList(listaInventario, 1);
        Utils.clickElements(listaProdRandom);

        // Paso 6: Ir al carrito
        WebElement carritoButton = Utils.esperarElementoClickable(driver, wait, CARTBUTTON);
        carritoButton.click();
        Assert.assertEquals("La redirección al carrito no fue realizada correctamente", urlCarrito, driver.getCurrentUrl());

        // Paso 7: Realizar el Checkout del producto.


        // Paso 8: Rellenar datos del checkout y continuar


        // Paso 9: Finalizar Checkout


        // Paso 10: Validar que el pedido a finalizado correctamente mostrando el mensaje “Your order has been dispatched, and will arrive just as fast as the pony can get there!”

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
