package com.hiberus.university.alexyague.maven.first.pageobjectmodel.stepdefs;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.CheckoutInfoPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.InventoryPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import io.cucumber.java.en.And;

public class CheckoutInfoPageSteps {

    PageFactory pageFactory = PageFactory.getInstance();
    CheckoutInfoPage checkoutInfoPage = pageFactory.getCheckoutInfoPage();

    @And("the user fills the checkout form")
    public void theUserFillsTheCheckoutForm() {
        checkoutInfoPage.fillTheForm();
    }

    @And("the user goes to second checkout page")
    public void theUserGoesToSecondCheckoutPage() {
        checkoutInfoPage.checkoutButtonClick();
    }
}
