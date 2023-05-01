package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.collections.CollectionUtils;
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

public class Carrito extends Locators{

    WebDriver driver;
    WebDriverWait wait;
    String urlLogin = "http://www.saucedemo.com/";
    String urlMain = "https://www.saucedemo.com/inventory.html";
    String urlCarrito = "https://www.saucedemo.com/cart.html";

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
    public void eliminarProductoDesdeCarrito(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlMain, driver.getCurrentUrl());

        // Paso 5: Agregar al carrito 2 productos elegidos al azar
        Utils.esperarElementoClickable(driver, wait, INVENTORYLISTITEMNAME);
        ArrayList<WebElement> listaInventario = (ArrayList<WebElement>) Utils.getListaElementosNoWait(driver, BUTTONADDCARTPRODLIST);
        List<WebElement> listaProdRandom = Utils.randomElementsOfList(listaInventario, 2);
        Utils.clickElements(listaProdRandom);

        // Paso 6: Ir al carrito
        WebElement carritoButton = Utils.esperarElementoClickable(driver, wait, CARTBUTTON);
        carritoButton.click();
        Assert.assertEquals("La redirección al carrito no fue realizada correctamente", urlCarrito, driver.getCurrentUrl());

        // Paso 7: Eliminar uno de los productos
        Utils.esperarElementoClickable(driver, wait, CARTBUTTONREMOVECARTLIST);
        List<WebElement> listaRemoveButton = Utils.getListaElementosNoWait(driver, CARTBUTTONREMOVECARTLIST);
        System.out.println("Productos en el carrito: ");
        Utils.iterateStringList(Utils.getAttributeOfWebElements(listaRemoveButton, "data-test"));
        List<WebElement> randomElementList = Utils.randomElementsOfList((ArrayList<WebElement>) listaRemoveButton, 1);
        System.out.println("Productos eliminados: ");
        Utils.iterateStringList(Utils.getAttributeOfWebElements(randomElementList, "data-test"));
        Utils.clickElements(randomElementList);

        // Paso 8: Validar que el producto eliminado, no aparece en el carrito
        List<WebElement> listaRemoveButtonAfterDelete = Utils.getListaElementosNoWait(driver, CARTBUTTONREMOVECARTLIST);
        Assert.assertFalse("El producto eliminado sigue apareciendo en el carrito", CollectionUtils.containsAny(listaRemoveButtonAfterDelete, randomElementList));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
