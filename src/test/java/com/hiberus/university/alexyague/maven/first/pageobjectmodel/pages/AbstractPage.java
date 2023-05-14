package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public abstract class AbstractPage {

    private final WebDriver driver;
    protected Wait<WebDriver> wait;

    public abstract WebElement getPageLoadedTestElement();
    AbstractPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10, 500);
    }

    protected WebDriver getDriver(){
        return this.driver;
    }

    public void waitForPageLoad(){
        WebElement testElement = getPageLoadedTestElement();
        wait.until(ExpectedConditions.visibilityOf(testElement));
    }

    protected Boolean isPageLoaded(WebElement element){
        boolean isLoaded = false;
        try {
            isLoaded = element.isDisplayed();
        } catch (NoSuchElementException nse){
            nse.printStackTrace();
        }
        return isLoaded;
    }


    public void navigateTo(String pageUrl) {
        try {
            driver.navigate().to(pageUrl);
        } catch (java.lang.Exception e) {
            if (e instanceof TimeoutException) {
                log.info("Timeout loading home page");
            } else if (e instanceof ScriptTimeoutException) {
                log.info("Script timeout loading home page");
            } else {
                log.error(e.getMessage());
            }
        }
    }
    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public void clicks(WebElement button){
        try {
            log.info("Haciendo click en boton " + button.getText());
            button.click();
        } catch (TimeoutException toe){
            toe.printStackTrace();
            log.info("Boton no encontrado");
        }
    }
}
