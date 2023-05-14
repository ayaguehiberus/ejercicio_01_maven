package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.model.InventoryItem;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.support.TestDataContext;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class InventoryPage extends AbstractPage {

    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";
    public static final String SORTAZ = "az";
    public static final String SORTZA = "za";
    public static final String SORTHILO = "hilo";
    public static final String SORTLOHI = "lohi";

    @FindBy(id = "react-burger-menu-btn")
    private WebElement buttonBurgerMenu;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;
    @FindBy(className = "shopping_cart_link")
    private WebElement buttonCartLink;
    @FindBy(className = "shopping_cart_badge")
    private WebElement spanCartNumber;
    @FindBy(className = "product_sort_container")
    private WebElement selectSortProducts;
    @FindBy(className = "inventory_item_description")
    private List<WebElement> inventoryItemList;
    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemNameList;
    @FindBy(className = "inventory_item_price")
    private List<WebElement> inventoryItemPriceList;
    @FindBy(xpath = "//button[contains(@id, 'remove')]")
    private List<WebElement> inventoryItemRemoveButton;
    @FindBy(xpath = "//button[contains(@id, 'add-to-cart')]")
    private List<WebElement> inventoryItemAddButton;
    @FindAll({
            @FindBy(xpath = "//button[contains(@id, 'remove')]"),
            @FindBy(xpath = "//button[contains(@id, 'add-to-cart')]")
    })
    private List<WebElement> inventoryItemButtons;

    public InventoryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Override
    public WebElement getPageLoadedTestElement() {
        return inventoryItemList.get(0);
    }

    public int getProductListSize(){
        return inventoryItemList.size();
    }

    public boolean isProductInInventoryList(String name){
        List<String> nameList = Utils.getTextOfWebElements(inventoryItemNameList);
        return searchForItemName(nameList, name);
    }
    public boolean searchForItemName(List<String> nameList, String name){
        for (String nameSearch:
             nameList) {
            if (nameSearch.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public void addOrRemoveProductToCart(String productName){
        InventoryItem invItem = extractInventoryItem(productName);
        if (invItem.getName().equals("")){
            log.info("No puede agregarse " + productName + " al carrito porque no se encontró el item");
            return;
        }
        try {
            WebElement boton = getAddOrRemoveButton(productName);
            if(boton.getText().equalsIgnoreCase("add to cart")){
                log.info("Añadiendo producto al carrito");
                boton.click();
                TestDataContext.addItem(invItem);
            } else if (boton.getText().equalsIgnoreCase("remove")) {
                log.info("Removiendo producto del carrito");
                boton.click();
                TestDataContext.removeItem(invItem);
            } else log.info("El botón encontrado no coincide con botón para añadir o eliminar del carrito");
        } catch (NullPointerException npe){
            npe.printStackTrace();
            log.info("Botón de añadir o remover producto no se pudo encontrar");
        }
    }

    public void addRandomProductsToCart(int cant){
        if (cant < 1){
            log.info("No puede agregarse menos de 1 producto al carrito");
            return;
        }
        List<Integer> lista = Utils.randomNumbers(0, inventoryItemAddButton.size(), cant);
        int cont = 0;
        for (Integer index:
             lista) {
            WebElement boton = inventoryItemAddButton.get(index - cont);
            String productName = boton.getAttribute("data-test").replace("-", " ").replace("add to cart ", "");
            TestDataContext.addItem(extractInventoryItem(productName));
            log.info("Añadiendo producto " + productName + " al contexto");
            boton.click();
            log.info("Producto añadido al carrito");
            cont++;
        }
        log.info("Items en el carrito:");
        for (InventoryItem item:
             TestDataContext.getInventoryItemListInCart()) {
            log.info("Item: {}", item);
        }
    }
    public WebElement getAddOrRemoveButton(String productName){
        String productNameNoDash = productName.replace("-", " ").toLowerCase();
        for (WebElement buttonItem:
             inventoryItemButtons) {
            if (buttonItem.getAttribute("data-test").replace("-", " ").contains(productNameNoDash)){
                log.info("Botón de " + buttonItem.getText() + " encontrado");
                return buttonItem;
            }
        }
        log.info("Botón para añadir o remover producto no encontrado para " + productName);
        return null;
    }

    public InventoryItem extractInventoryItem(String name){
        String nameNoDash = name.replace("-", " ");
        InventoryItem invItem = new InventoryItem();
        By infoItemBy = By.xpath("descendant::*[@*='inventory_item_name']");
        for (WebElement inventoryItem:
             inventoryItemList) {
            String itemName = inventoryItem.findElement(infoItemBy).getText().replace("-", " ");
            if (itemName.equalsIgnoreCase(nameNoDash)){
                invItem.setName(nameNoDash);
                infoItemBy = By.xpath("descendant::*[@*='inventory_item_price']");
                invItem.setPrice(Utils.priceStringToFloat(inventoryItem.findElement(infoItemBy).getText()));
                return invItem;
            }
        }
        log.info("Item de inventario {} extraído", invItem);
        return invItem;
    }

    public List<String> getInventoryNameList(){
        return Utils.getTextOfWebElements(inventoryItemNameList);
    }
    public List<String> getInventoryPriceList(){
        return Utils.floatListToString(Utils.stringListToFloat(Utils.getTextOfWebElements(inventoryItemPriceList)));
    }

    public List<String> getItemList(String sortType){
        switch (sortType){
            case SORTAZ:
            case SORTZA:
                log.info("Obteniendo lista de nombres del inventario");
                return Utils.getTextOfWebElements(inventoryItemNameList);
            case SORTHILO:
            case SORTLOHI:
                log.info("Obteniendo lista de precios del inventario");
                List<String> lista = Utils.getTextOfWebElements(inventoryItemPriceList);
                List<Float> listaFloat = Utils.stringListToFloat(lista);
                return Utils.floatListToString(listaFloat);
        }
        return null;
    }

    public void sortInventoryList(String sortType){
        Select select = new Select(selectSortProducts);
        select.selectByValue(sortType);
    }
    public List<String> getSortedInventoryList(String sortType){
        switch (sortType){
            case SORTAZ:
                log.info("Obteniendo lista de elementos ordenados alfabeticamente");
                List<String> lista1 = Utils.getTextOfWebElements(inventoryItemNameList);
                return Utils.sortListAlphabetical(lista1);
            case SORTZA:
                log.info("Obteniendo lista de elementos ordenados alfabeticamente inversamente");
                List<String> lista2 = Utils.getTextOfWebElements(inventoryItemNameList);
                return Utils.sortListInverseAlphabetical(lista2);
            case SORTHILO:
                log.info("Obteniendo lista de precios ordenados de mayor a menor");
                List<String> lista3 = Utils.getTextOfWebElements(inventoryItemPriceList);
                List<Float> listaFloat1 = Utils.stringListToFloat(lista3);
                return Utils.floatListToString(Utils.sortListHiLo(listaFloat1));
            case SORTLOHI:
                log.info("Obteniendo lista de precios ordenados de menor a mayor");
                List<String> lista4 = Utils.getTextOfWebElements(inventoryItemPriceList);
                List<Float> listaFloat2 = Utils.stringListToFloat(lista4);
                return Utils.floatListToString(Utils.sortListLoHi(listaFloat2));
        }
        log.info("Método de ordenación no implementado o incorrecto");
        return null;
    }

    public int getNumItemsCart(){
        int resul;

        try {
            resul = Integer.parseInt(spanCartNumber.getText());
        } catch (TimeoutException | NoSuchElementException ex){
            ex.printStackTrace();
            log.info("No hay items en el carrito");
            resul = 0;
        }

        return resul;
    }
    public float getPriceOfInventoryItems(List<WebElement> list){
        List<String> newList = Utils.getTextOfWebElements(list);
        List<Float> newListFl = new ArrayList<>();
        for (String elem :
                newList) {
            float newElem = Utils.priceStringToFloat(elem);
            newListFl.add(newElem);
        }
        return Utils.sumarFloatList(newListFl);
    }
    public void goToCart(){
        try {
            log.info("Dirigiendose al carrito");
            buttonCartLink.click();
        } catch (TimeoutException toe){
            toe.printStackTrace();
            log.info("Botón de ir al carrito no encontrado");
        }
    }
    public void openBurguerMenu(){
        try {
            buttonBurgerMenu.click();
        } catch (TimeoutException | NoSuchElementException ex){
            ex.printStackTrace();
            log.info("No pudo localizarse el botón del menu de hamburguesa");
        }
    }
    public void clickLogout(){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        } catch (TimeoutException | NoSuchElementException ex){
            ex.printStackTrace();
            log.info("No se localizó el botón de logout");
        }
    }


    //    public int getInventoryItemPosition(String name){
//        int cont = 0;
//        if (inventoryItemList.size() < 1){
//            return -1;
//        }
//
//        for (WebElement elem:
//             inventoryItemNameList) {
//            cont++;
//            if (elem.getText() == name){
//                return cont;
//            }
//        }
//        return -1;
//    }

}
