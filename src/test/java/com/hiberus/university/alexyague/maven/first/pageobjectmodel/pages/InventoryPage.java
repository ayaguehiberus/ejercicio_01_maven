package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class InventoryPage extends AbstractPage {

    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html";
    public static int numItemsCart;
    public static String productName;
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
        numItemsCart = 0;
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

    public boolean addOrRemoveProductToCart(String name){
        int productPosition = Utils.getListItemPosition(name, inventoryItemNameList);
        if (productPosition == -1){
            log.info("No puede agregarse o removerse el producto porque no existe");
            return false;
        }
        try {
            String textButton = inventoryItemButtons.get(productPosition).getText();
            if (textButton.equalsIgnoreCase("remove")){
                log.info("Removiendo producto...");
            } else {
                log.info("Añadiendo producto...");
            }
            inventoryItemButtons.get(productPosition).click();
        } catch (TimeoutException toe){
            log.info("Botón para añadir o remover del carrito no encontrado");
            toe.printStackTrace();
            return false;
        }

        return true;
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
            inventoryItemAddButton.get(index - cont).click();
            cont++;
        }
    }

    public List<String> getInventoryNameList(){
        return Utils.getTextOfWebElements(inventoryItemNameList);
    }
    public List<String> getInventoryPriceList(){
        return Utils.getTextOfWebElements(inventoryItemPriceList);
    }

    public void sortInventoryList(String sortType){
        Select select = new Select(selectSortProducts);
        select.selectByValue(sortType);
    }
    public List<String> getSortedInventoryList(String sortType){
        switch (sortType){
            case SORTZA:
                log.info("Obteniendo lista de elementos ordenados alfabeticamente inversamente");
                List<String> lista1 = Utils.getTextOfWebElements(inventoryItemNameList);
                return Utils.sortListInverseAlphabetical(lista1);
            case SORTHILO:
                log.info("Obteniendo lista de precios ordenados de mayor a menor");
                List<String> lista2 = Utils.getTextOfWebElements(inventoryItemPriceList);
                List<Float> listaFloat1 = Utils.stringListToFloat(lista2);
                return Utils.floatListToString(Utils.sortListHiLo(listaFloat1));
            case SORTLOHI:
                log.info("Obteniendo lista de precios ordenados de menor a mayor");
                List<String> lista3 = Utils.getTextOfWebElements(inventoryItemPriceList);
                List<Float> listaFloat2 = Utils.stringListToFloat(lista3);
                return Utils.floatListToString(Utils.sortListLoHi(listaFloat2));
        }
        log.info("Método de ordenación no implementado o incorrecto");
        return null;
    }

    public int getNumItemsCart(){
        int resul;

        try {
            resul = Integer.parseInt(spanCartNumber.getText());
        } catch (TimeoutException toe){
            toe.printStackTrace();
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
    public void logout(){
        try {
            buttonBurgerMenu.click();
            logoutButton.click();
        } catch (TimeoutException toe){
            toe.printStackTrace();
            log.info("No pudo realizarse logout. Alguno de los botones no fue encontrado");
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