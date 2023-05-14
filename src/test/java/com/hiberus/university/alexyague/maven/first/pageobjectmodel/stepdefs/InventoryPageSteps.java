package com.hiberus.university.alexyague.maven.first.pageobjectmodel.stepdefs;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.InventoryPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.LoginPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class InventoryPageSteps {

    PageFactory pageFactory = PageFactory.getInstance();
    InventoryPage inventoryPage = pageFactory.getInventoryPage();

    @When("the user sees the inventory list")
    public void theUserSeesTheInventoryList() {
        inventoryPage.getProductListSize();
    }

    @Then("the list has {int} elements")
    public void theListHasElements(int cant) {
        Assert.assertEquals("El inventario no contiene " + cant + " elementos",
                cant,
                inventoryPage.getProductListSize());
    }

    @Then("the list contains {string}")
    public void theListContains(String productName) {
        Assert.assertTrue("El inventario no contiene el producto " + productName,
                inventoryPage.isProductInInventoryList(productName));
    }

    @When("the user add {string} to cart")
    public void theUserAddToCart(String itemName) {
        inventoryPage.addOrRemoveProductToCart(itemName);
    }

    @Then("the cart has {int} element")
    public void theCartHasElement(int cant) {
        Assert.assertEquals("El carrito no contiene " + cant + " elementos",
                cant,
                inventoryPage.getNumItemsCart());
    }

    @And("the user removes {string} from cart")
    public void theUserRemovesFromCart(String itemName) {
        inventoryPage.addOrRemoveProductToCart(itemName);
    }

    @When("the user add any {int} items to the cart")
    public void theUserAddAnyItemsToTheCart(int cant) {
        inventoryPage.addRandomProductsToCart(cant);
    }

    @When("the user sort the list of products {string}")
    public void theUserSortTheListOfProducts(String sortType) {
        inventoryPage.sortInventoryList(sortType);
    }


    @Then("the list of products shows accordingly to sort type {string}")
    public void theListOfProductsShowsAccordinglyToSortType(String sortType) {
        Assert.assertEquals("La lista no fue ordenada adecuadamente con el tipo de ordenación " + sortType,
                inventoryPage.getItemList(sortType),
                inventoryPage.getSortedInventoryList(sortType));
    }

    @And("the user goes to cart page")
    public void theUserGoesToCartPage() {
        inventoryPage.goToCart();
    }

    @When("the user clicks in the burger menu")
    public void theUserClicksInTheBurgerMenu() {
        inventoryPage.openBurguerMenu();
    }

    @And("the user selects the logout option")
    public void theUserSelectsTheLogoutOption() {
        inventoryPage.clickLogout();
    }
}
