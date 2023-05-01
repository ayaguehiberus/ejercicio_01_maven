package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Inventario extends Locators{

    WebDriver driver;
    WebDriverWait wait;
    String url = "http://www.saucedemo.com/";
    String urlExpected = "https://www.saucedemo.com/inventory.html";
    int numProductosExp = 6;
    String productName3 = "Sauce Labs Bolt T-Shirt";
    String[] optionsOrderDropdown = {"az", "za", "lohi", "hilo"};

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
        Utils.esperarElementoVisible(driver, wait, INVENTORYLISTITEM); // Espero a que se carguen elementos de la lista
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
        Assert.assertNotNull("El producto " + productName3 + " no se encuentra en la lista", prod); // Valido (Los métodos esperarElementoVisible y esperarElementoClickable devuelven null si no se encuentra)
    }

    @Test
    public void validarProductoAgregadoCarrito(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());

        // Paso 5: Agregar al carrito el producto Sauce Labs Bolt T-Shirt
        WebElement addProdToCart = Utils.esperarElementoClickable(driver, wait, BUTTONADDCARTPROD3);
        Assert.assertNotNull("El botón de añadir producto no se encuentra para el producto " + productName3, addProdToCart); // Valido (Los métodos esperarElementoVisible y esperarElementoClickable devuelven null si no se encuentra)
        addProdToCart.click();

        // Paso 6: Validar que, en el icono del carrito, se ha agregado 1 producto.
        WebElement elementosCarrito = Utils.esperarElementoVisible(driver, wait, CARTSIZE);
        Assert.assertEquals("El número de elementos agregados al carrito es distinto de 1", "1", elementosCarrito.getText());
    }

    @Test
    public void validarProductoEliminadoCarrito(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());

        // Paso 5: Agregar al carrito el producto Sauce Labs Bolt T-Shirt
        WebElement addProdToCart = Utils.esperarElementoClickable(driver, wait, BUTTONADDCARTPROD3);
        Assert.assertNotNull("El botón de añadir producto no se encuentra para el producto " + productName3, addProdToCart); // Valido (Los métodos esperarElementoVisible y esperarElementoClickable devuelven null si no se encuentra)
        addProdToCart.click();

        // Paso 6: Eliminar el producto Sauce Labs Bolt T-Shirt (Boton remove)
        WebElement removeProdToCart = Utils.esperarElementoClickable(driver, wait, BUTTONREMOVECARTPROD3);
        Assert.assertNotNull("El botón de remover producto no se encuentra para el producto " + productName3, removeProdToCart); // Valido (Los métodos esperarElementoVisible y esperarElementoClickable devuelven null si no se encuentra)
        removeProdToCart.click();

        // Paso 7:  Validar que en el icono del carrito se ha eliminado el producto.
        List<WebElement> carritoSpan = Utils.getListaElementosNoWait(driver, CARTSIZE); //
        Assert.assertTrue("El span de elementos del carrito existe, por lo que el elemento " + productName3 + " no se borró", carritoSpan.size() == 0);
    }

    @Test
    public void agregar3ProductosRandomCarrito(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());

        // Paso 5: Agregar al carrito los 3 productos elegidos al azar
        Utils.esperarElementoClickable(driver, wait, INVENTORYLISTITEMNAME);
        ArrayList<WebElement> listaInventario = (ArrayList<WebElement>) Utils.getListaElementosNoWait(driver, BUTTONADDCARTPRODLIST);
        List<WebElement> listaProdRandom = Utils.randomElementsOfList(listaInventario, 3);
        Utils.clickElements(listaProdRandom);

        // Paso 6:
        List<WebElement> carritoSpan = Utils.getListaElementosNoWait(driver, CARTSIZE); //
        Assert.assertTrue("No se han añadido elementos al carrito", carritoSpan.size() > 0);
        Assert.assertEquals("No se han añadido 3 elementos al carrito. Elementos añadidos: " + carritoSpan.get(0).getText(), "3", carritoSpan.get(0).getText());
    }

    @Test
    public void ordenarInventarioAlfabeticamente(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());

        // Paso 5: Seleccionar el filtro NAME (Z TO A)
        WebElement orderDropdown = Utils.esperarElementoClickable(driver, wait, ORDERDROPDOWN);
        Assert.assertNotNull("El dropdown para ordenar los elementos no fue encontrado en esta dirección: " + ORDERDROPDOWN.toString(), orderDropdown);
        WebElement option = driver.findElement(By.xpath("//select[@data-test='product_sort_container']/option[@value='" + optionsOrderDropdown[1] + "']"));
        Assert.assertEquals("No se ha encontrado la opción requerida en el dropdown " + optionsOrderDropdown[1], optionsOrderDropdown[1], option.getAttribute("value"));
        Select select = new Select(orderDropdown);
        select.selectByValue(optionsOrderDropdown[1]);

        // Paso 6: Validar que el filtro seleccionado, ordena por el orden alfabético de la Z a la A
        List<WebElement> listaWebElemProd = Utils.getListaElementosNoWait(driver, INVENTORYLISTITEMNAME);
        ArrayList<String> listaProd = Utils.getTextOfWebElements(listaWebElemProd);
        ArrayList<String> listaExpec = Utils.sortListInverseAlphabetical(listaProd);
//        System.out.println("Lista ordenada esperada");
//        Utils.iterateStringList(listaExpec);
//        System.out.println("Lista ordenada real");
//        Utils.iterateStringList(listaProd);
        Assert.assertEquals("La lista no fue ordenada adecuadamente", listaExpec, listaProd);
    }

    @Test
    public void ordenarInventarioPrecioMenorAMayor(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());

        // Paso 5: Seleccionar el filtro PRICE (low to high)
        WebElement orderDropdown = Utils.esperarElementoClickable(driver, wait, ORDERDROPDOWN);
        Assert.assertNotNull("El dropdown para ordenar los elementos no fue encontrado en esta dirección: " + ORDERDROPDOWN.toString(), orderDropdown);
        WebElement option = driver.findElement(By.xpath("//select[@data-test='product_sort_container']/option[@value='" + optionsOrderDropdown[2] + "']"));
        Assert.assertEquals("No se ha encontrado la opción requerida en el dropdown " + optionsOrderDropdown[2], optionsOrderDropdown[2], option.getAttribute("value"));
        Select select = new Select(orderDropdown);
        select.selectByValue(optionsOrderDropdown[2]);

        // Paso 6: Validar que el filtro seleccionado, ordena por el orden de precio de menor a mayor
        List<WebElement> listaWebElemProd = Utils.getListaElementosNoWait(driver, INVENTORYLISTITEMPRICE);
        ArrayList<Float> listaPrecios = parseStringPriceToFloat(Utils.getTextOfWebElements(listaWebElemProd));
        ArrayList<Float> listaPreciosExpec = Utils.sortListLoHi(listaPrecios);
        Assert.assertEquals("La lista no fue ordenada adecuadamente", listaPreciosExpec, listaPrecios);
    }

    @Test
    public void ordenarInventarioPrecioMayorAMenor(){
        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlExpected, driver.getCurrentUrl());

        // Paso 5: Seleccionar el filtro PRICE (high to low)
        WebElement orderDropdown = Utils.esperarElementoClickable(driver, wait, ORDERDROPDOWN);
        Assert.assertNotNull("El dropdown para ordenar los elementos no fue encontrado en esta dirección: " + ORDERDROPDOWN.toString(), orderDropdown);
        WebElement option = driver.findElement(By.xpath("//select[@data-test='product_sort_container']/option[@value='" + optionsOrderDropdown[3] + "']"));
        Assert.assertEquals("No se ha encontrado la opción requerida en el dropdown " + optionsOrderDropdown[3], optionsOrderDropdown[3], option.getAttribute("value"));
        Select select = new Select(orderDropdown);
        select.selectByValue(optionsOrderDropdown[3]);

        // Paso 6: Validar que el filtro seleccionado, ordena por el orden de precio de menor a mayor
        List<WebElement> listaWebElemProd = Utils.getListaElementosNoWait(driver, INVENTORYLISTITEMPRICE);
        ArrayList<Float> listaPrecios = parseStringPriceToFloat(Utils.getTextOfWebElements(listaWebElemProd));
        ArrayList<Float> listaPreciosExpec = Utils.sortListHiLo(listaPrecios);
        Assert.assertEquals("La lista no fue ordenada adecuadamente", listaPreciosExpec, listaPrecios);
    }

    @After
    public void tearDown(){
        driver.quit();
    }



    //--------------------------------------------

    // Metodos propios de esta testsuite

    public static ArrayList<Float> parseStringPriceToFloat(ArrayList<String> lista){
        ArrayList<Float> nuevaLista = new ArrayList<>();
        try {
            for (String elem : lista) {
                String nuevoElem = elem.replace("$", "");
                nuevaLista.add(Float.parseFloat(nuevoElem));
            }
        } catch (NullPointerException npe){
            System.out.println("ERROR: La lista de strings proporcionada está vacía");
        }

        return nuevaLista;
    }
}
