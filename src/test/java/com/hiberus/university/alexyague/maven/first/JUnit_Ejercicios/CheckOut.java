package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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

    String firstName = "Alex";
    String lastName = "Yagüe";
    String postalCode = "12345";
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

        // Paso 5: Agregar al carrito 3 productos elegidos al azar
        Utils.esperarElementoClickable(driver, wait, INVENTORYLISTITEMNAME);
        ArrayList<WebElement> listaInventario = (ArrayList<WebElement>) Utils.getListaElementosNoWait(driver, BUTTONADDCARTPRODLIST);
        List<WebElement> listaProdRandom = Utils.randomElementsOfList(listaInventario, 3);

        // Paso 5.5: Guardar el precio de los productos para la validación en el paso 10
        By rutaRelativaPrecio = By.xpath("preceding-sibling::div");
        ArrayList<WebElement> listaPreciosFromListaRandom = Utils.searchElementsFromOtherElements((ArrayList<WebElement>) listaProdRandom, rutaRelativaPrecio);
        ArrayList<String> listaPreciosString = Utils.getTextOfWebElements(listaPreciosFromListaRandom);
        ArrayList<Float> listaPreciosF = parseStringPriceToFloat(listaPreciosString);

        // Final paso 5
        Utils.clickElements(listaProdRandom); // Agregar los productos

        // Paso 6: Ir al carrito
        WebElement carritoButton = Utils.esperarElementoClickable(driver, wait, CARTBUTTON);
        carritoButton.click();
        Assert.assertEquals("La redirección al carrito no fue realizada correctamente", urlCarrito, driver.getCurrentUrl());

        // Paso 7: Realizar el Checkout del producto.
        Utils.esperarElementoClickable(driver, wait, CHECKOUTBUTTON).click();
        Assert.assertEquals("No se realizó correctamente la redirección al checkout 1", urlCheckout1, driver.getCurrentUrl());

        // Paso 8: Rellenar datos del checkout y continuar
        Utils.esperarElementoVisible(driver, wait, FORM_FIRSTNAME).sendKeys(firstName);
        Utils.esperarElementoVisible(driver, wait, FORM_LASTNAME).sendKeys(lastName);
        Utils.esperarElementoVisible(driver, wait, FORM_POSTALCODE).sendKeys(postalCode);
        Utils.esperarElementoClickable(driver, wait, FORM_SUBMIT).click();
        Assert.assertEquals("No se realizó correctamente la redirección al checkout 2", urlCheckout2, driver.getCurrentUrl());

        // Paso 9: Finalizar Checkout
        WebElement labelPrecioTotalNoTax = Utils.esperarElementoVisible(driver, wait, LABEL_ITEMTOTALWITHOUTTAX);
        float precioTotalNoTaxesCheckOut = parseStringPriceToFloat(labelPrecioTotalNoTax.getText());
        Utils.esperarElementoClickable(driver, wait, BUTTON_FINISH).click();

        // Paso 10:  Validar que el precio total del pedido (Item total) es la suma del importe de los productos seleccionados en el inventario
        System.out.println("Precios en el inventario de productos:");
        Utils.iterateFloatList(listaPreciosF);
        float sumaPreciosInv = Utils.sumarFloatList(listaPreciosF);
        System.out.println("Suma Total: " + sumaPreciosInv);
        System.out.println("------------------------------------------");
        System.out.println("Precio en el checkout: " + precioTotalNoTaxesCheckOut);
        Assert.assertEquals(
                "La suma del precio de los productos en el inventario (" + sumaPreciosInv + ") no coincide con el total del checkout (" + precioTotalNoTaxesCheckOut + ")",
                sumaPreciosInv,
                precioTotalNoTaxesCheckOut,
                0.009
        );


    }

    @Test
    public void realizarPedido(){

        // Paso 1, 2, 3, 4 (Login)
        Utils.realizarLogin(driver, wait);
        Assert.assertEquals("Fallo al realizar el login", urlMain, driver.getCurrentUrl());

        // Paso 5: Agregar al carrito 1 producto elegido al azar
        Utils.esperarElementoClickable(driver, wait, INVENTORYLISTITEMNAME);
        ArrayList<WebElement> listaInventario = (ArrayList<WebElement>) Utils.getListaElementosNoWait(driver, BUTTONADDCARTPRODLIST);
        List<WebElement> listaProdRandom = Utils.randomElementsOfList(listaInventario, 1);
        Utils.clickElements(listaProdRandom);

        // Paso 6: Ir al carrito
        WebElement carritoButton = Utils.esperarElementoClickable(driver, wait, CARTBUTTON);
        carritoButton.click();
        Assert.assertEquals("La redirección al carrito no fue realizada correctamente", urlCarrito, driver.getCurrentUrl());

        // Paso 7: Realizar el Checkout del producto.
        Utils.esperarElementoClickable(driver, wait, CHECKOUTBUTTON).click();
        Assert.assertEquals("No se realizó correctamente la redirección al checkout 1", urlCheckout1, driver.getCurrentUrl());

        // Paso 8: Rellenar datos del checkout y continuar
        Utils.esperarElementoVisible(driver, wait, FORM_FIRSTNAME).sendKeys(firstName);
        Utils.esperarElementoVisible(driver, wait, FORM_LASTNAME).sendKeys(lastName);
        Utils.esperarElementoVisible(driver, wait, FORM_POSTALCODE).sendKeys(postalCode);
        Utils.esperarElementoClickable(driver, wait, FORM_SUBMIT).click();
        Assert.assertEquals("No se realizó correctamente la redirección al checkout 2", urlCheckout2, driver.getCurrentUrl());

        // Paso 9: Finalizar Checkout
        Utils.esperarElementoClickable(driver, wait, BUTTON_FINISH).click();
        Assert.assertEquals("No se realizó correctamente la redirección al checkout complete", urlCheckoutComplete, driver.getCurrentUrl());


        // Paso 10: Validar que el pedido a finalizado correctamente mostrando el mensaje “Your order has been dispatched, and will arrive just as fast as the pony can get there!”
        String mensaje = Utils.esperarElementoVisible(driver, wait, TEXT_MSGCOMPLETE).getText();
        Assert.assertEquals("El mensaje no coincide con el mensaje esperado", msgCompleteExpected, mensaje);

    }

    @After
    public void tearDown(){
        driver.quit();
    }



    public static ArrayList<Float> parseStringPriceToFloat(ArrayList<String> lista){
        ArrayList<Float> nuevaLista = new ArrayList<>();
        try {
            for (String elem : lista) {
//                String nuevoElem = elem.replace("$", "");
                String nuevoElem = elem.replace("Item total: ", "");
                nuevoElem = nuevoElem.replace("$", "");
                System.out.println(nuevoElem);
                nuevaLista.add(Float.parseFloat(nuevoElem));
            }
        } catch (NullPointerException npe){
            System.out.println("ERROR: La lista de strings proporcionada está vacía");
        }

        return nuevaLista;
    }

    public static float parseStringPriceToFloat(String precio){
        String nuevoPrecio = precio.replace("Item total: ", "");
        nuevoPrecio = nuevoPrecio.replace("$", "");

        return Float.parseFloat(nuevoPrecio);
    }
}
