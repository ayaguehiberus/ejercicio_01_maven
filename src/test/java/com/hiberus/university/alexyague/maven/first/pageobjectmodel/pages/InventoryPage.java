package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    public int getInventoryItemPosition(String name){
        int cont = 0;
        if (inventoryItemList.size() < 1){
            return -1;
        }

        for (WebElement elem:
             inventoryItemNameList) {
            cont++;
            if (elem.getText() == name){
                return cont;
            }
        }
        return -1;
    }

    public boolean addOrRemoveProductToCart(String name){
        int productPosition = getInventoryItemPosition(name);
        if (productPosition == -1){
            return false;
        }
        try {
            inventoryItemButtons.get(productPosition).click();
        } catch (TimeoutException toe){
            log.info("Botón para añadir o remover del carrito no encontrado");
            toe.printStackTrace();
            return false;
        }

        return true;
    }

    public void addRandomProductsToCart(int cant){
        List<Integer> lista = Utils.randomNumbers(0, inventoryItemAddButton.size(), cant);
        for (Integer index:
             lista) {
            inventoryItemAddButton.get(index).click();
        }
    }

}
