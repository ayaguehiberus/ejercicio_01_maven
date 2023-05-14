package com.hiberus.university.alexyague.maven.first.pageobjectmodel.stepdefs;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.CartPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.InventoryPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class CartPageSteps {

    PageFactory pageFactory = PageFactory.getInstance();
    CartPage cartPage = pageFactory.getCartPage();
    @When("the user removes a product from the cart")
    public void theUserRemovesAProductFromTheCart() {
        cartPage.removeFirstProductFromCart();
    }

    @And("the user goes to first checkout page")
    public void theUserGoesToFirstCheckoutPage() {
        cartPage.realizarCheckOut();
    }
}
