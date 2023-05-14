package com.hiberus.university.alexyague.maven.first.pageobjectmodel.stepdefs;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.CheckoutOverviewPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.support.TestDataContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CheckoutOverviewPageSteps {

    PageFactory pageFactory = PageFactory.getInstance();
    CheckoutOverviewPage checkoutOverviewPage = pageFactory.getCheckoutOverviewPage();

    @Then("the subtotal price in the second checkout page match the price of the elements in the inventory page")
    public void theSubtotalPriceInTheSecondCheckoutPageMatchThePriceOfTheElementsInTheInventoryPage() {
        Assert.assertEquals("El total sin impuestos no coincide con la suma de precios en el inventario",
                checkoutOverviewPage.getSubtotalPrice(),
                TestDataContext.getTotalPriceOfCart(),
                0.009);
    }

    @And("the user completes checkout")
    public void theUserCompletesCheckout() {
        checkoutOverviewPage.checkoutFinish();
    }
}
