package com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


}
