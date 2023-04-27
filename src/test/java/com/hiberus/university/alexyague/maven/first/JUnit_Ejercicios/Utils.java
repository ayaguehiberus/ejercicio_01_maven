package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    public static WebElement esperarElementoClickable(WebDriver driver, WebDriverWait wait, By loc){

        WebElement element = null;

        try {

            wait.until(ExpectedConditions.elementToBeClickable(loc));
            element = driver.findElement(loc);

        } catch (Exception e){
            e.printStackTrace();
        }

        return element;
    }

    public static WebElement esperarElementoVisible(WebDriver driver, WebDriverWait wait, By loc){

        WebElement element = null;

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
            element = driver.findElement(loc);

        } catch(Exception e){
            e.printStackTrace();
        }

        return element;
    }
}
