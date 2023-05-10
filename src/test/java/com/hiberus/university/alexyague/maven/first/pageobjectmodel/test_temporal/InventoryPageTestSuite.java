package com.hiberus.university.alexyague.maven.first.pageobjectmodel.test_temporal;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.CartPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.InventoryPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.LoginPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InventoryPageTestSuite {

    WebDriver driver;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;


    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        PageFactory.start(driver);
        PageFactory pageFactory = PageFactory.getInstance();
        loginPage = pageFactory.getLoginPage();
        inventoryPage = pageFactory.getInventoryPage();

        driver.get(LoginPage.PAGE_URL);
    }

    @Test
    public void validateInventoryItemNum(){
        int expected = 6;

        Assert.assertEquals("El número de items en inventario no es " + expected,
                expected,
                inventoryPage.getProductListSize());
    }
    @Test
    public void validateSpecificProduct(){
        String specProdName = "Sauce Labs Bolt T-Shirt";

        loginPage.loginCorrecto();

        Assert.assertTrue("El producto " + specProdName + " no se encuentra en el inventario",
                inventoryPage.isProductInInventoryList(specProdName));
    }
    @Test
    public void addToCartSpecificProduct() {
        String specProdName = "Sauce Labs Bolt T-Shirt";

        loginPage.loginCorrecto();

        inventoryPage.addOrRemoveProductToCart(specProdName);

        Assert.assertEquals("No se muestra el producto añadido en el carrito",
                1, inventoryPage.getNumItemsCart());
    }
    @Test
    public void removeProductFromCartInInventory() throws InterruptedException {
        String specProdName = "Sauce Labs Bolt T-Shirt";

        loginPage.loginCorrecto();

        inventoryPage.addOrRemoveProductToCart(specProdName); // Añadir
        inventoryPage.addOrRemoveProductToCart(specProdName); // Remover

        Assert.assertTrue("La operación de añadir y remover producto no fue realizada correctamente",
                inventoryPage.getNumItemsCart() == 0);
    }
    @Test
    public void addThreeRandomProducts(){
        int productsAdded = 3;

        loginPage.loginCorrecto();

        inventoryPage.addRandomProductsToCart(productsAdded);

        Assert.assertEquals("No se muestran en el carrito los " + productsAdded + " productos",
                productsAdded,
                inventoryPage.getNumItemsCart());
    }
    @Test
    public void checkOrderZA(){

        loginPage.loginCorrecto();

        List<String> lista = inventoryPage.getSortedInventoryList(inventoryPage.SORTZA);
        inventoryPage.sortInventoryList(inventoryPage.SORTZA);

        Assert.assertEquals("La lista no fue ordenada adecuadamente",
                lista,
                inventoryPage.getInventoryNameList());
    }
    @Test
    public void checkOrderHILO(){

        loginPage.loginCorrecto();

        List<String> lista = inventoryPage.getSortedInventoryList(inventoryPage.SORTHILO);
        inventoryPage.sortInventoryList(inventoryPage.SORTHILO);

        Assert.assertEquals("La lista no fue ordenada adecuadamente",
                lista,
                inventoryPage.getInventoryPriceList());
    }
    @Test
    public void checkOrderLOHI(){

        loginPage.loginCorrecto();

        List<String> lista = inventoryPage.getSortedInventoryList(inventoryPage.SORTLOHI);
        inventoryPage.sortInventoryList(inventoryPage.SORTLOHI);

        Assert.assertEquals("La lista no fue ordenada adecuadamente",
                lista,
                inventoryPage.getInventoryPriceList());
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
