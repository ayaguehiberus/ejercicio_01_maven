package com.hiberus.university.alexyague.maven.first.pageobjectmodel.stepdefs;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.InventoryPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.LoginPage;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;


@Slf4j
public class LoginPageSteps {

    PageFactory pageFactory = PageFactory.getInstance();
    LoginPage loginPage = pageFactory.getLoginPage();

    @Given("the user goes to the main page")
    public void theUserGoesToTheMainPage() {
        loginPage.navigateTo(loginPage.PAGE_URL);
    }

    @And("the user provides the username {string}")
    public void theUserProvidesTheUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("the user provides the password {string}")
    public void theUserProvidesThePassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("the user is in the inventory page")
    public void theUserIsInTheInventoryPage() {
        Assert.assertEquals("El usuario no está en la página de inventario",
                InventoryPage.PAGE_URL, loginPage.getCurrentURL());
    }

    @And("a error message has displayed")
    public void aErrorMessageHasDisplayed() {
        Assert.assertTrue("No se mostró mensaje de error con login erroneo",
                loginPage.hasUsernamePasswordError());
    }

    @Then("the user is in the home page")
    public void theUserIsInTheHomePage() {
        Assert.assertEquals("El usuario no se encuentra en la página principal",
                loginPage.PAGE_URL,
                loginPage.getCurrentURL());
    }
    @And("the user logs succesfully in the main page")
    public void theUserLogsSuccesfullyInTheMainPage() {
        loginPage.loginCorrecto();
    }
}
