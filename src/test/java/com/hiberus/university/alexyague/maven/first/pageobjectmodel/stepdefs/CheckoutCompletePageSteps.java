package com.hiberus.university.alexyague.maven.first.pageobjectmodel.stepdefs;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.CheckoutCompletePage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CheckoutCompletePageSteps {

    PageFactory pageFactory = PageFactory.getInstance();
    CheckoutCompletePage checkoutCompletePage = pageFactory.getCheckoutCompletePage();

    @Then("the user is in the finalized checkout page")
    public void theUserIsInTheFinalizedCheckoutPage() {
        Assert.assertEquals("El usuario no se encuentra en la página de checkout completo",
                CheckoutCompletePage.PAGE_URL,
                checkoutCompletePage.getCurrentURL());
    }

    @And("there is a message of successful order")
    public void thereIsAMessageOfSuccessfulOrder() {
        Assert.assertEquals("El mensaje mostrado no coincide con lo esperado",
                CheckoutCompletePage.MSG_COMPLETE,
                checkoutCompletePage.getMsgComplete());
    }
}
