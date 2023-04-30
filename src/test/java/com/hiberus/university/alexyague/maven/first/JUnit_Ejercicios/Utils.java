package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Utils extends Locators{

    public static List<WebElement> randomElementsOfList(ArrayList<WebElement> lista, int elementos){
        if (lista.size() < elementos){
            return null;
        }
        if (lista.size() == elementos){
            return lista;
        }

        Random random = new Random();
        ArrayList<WebElement> nuevaLista = (ArrayList<WebElement>) lista.clone();
        List<WebElement> listaRandom = new ArrayList<>();

        for (int i = 0; i < elementos; i++){
            int rand = random.nextInt(nuevaLista.size());
            listaRandom.add(nuevaLista.get(rand));
            nuevaLista.remove(rand);
        }

        return listaRandom;
    }

    public static ArrayList<String> sortListInverseAlphabetical(ArrayList<String> lista){
        ArrayList<String> nuevaLista = (ArrayList<String>) lista.clone();
        nuevaLista.replaceAll(String::toLowerCase);
        Collections.sort(nuevaLista, Collections.reverseOrder());
        return nuevaLista;
    }

    public static void clickElements(List<WebElement> lista){
        for (WebElement elem : lista){
            elem.click();
        }
    }

    public static ArrayList<String> getTextOfWebElements(List<WebElement> lista){
        ArrayList<String> nuevaLista = new ArrayList<>();
        try {
            for (WebElement elem : lista) {
                nuevaLista.add(elem.getText());
            }
            nuevaLista.replaceAll(String::toLowerCase);
        } catch (NullPointerException npe){
            System.out.println("ERROR: La lista de WebElements proporcionada está vacía");
        }

        return nuevaLista;
    }

    public static WebElement esperarElementoClickable(WebDriver driver, WebDriverWait wait, By loc){

        WebElement element = null;

        try {

            wait.until(ExpectedConditions.elementToBeClickable(loc));
            element = driver.findElement(loc);

        } catch(TimeoutException toe){
            System.out.println("Timeout exception buscando el elemento " + loc.toString());
        }

        return element;
    }

    public static WebElement esperarElementoVisible(WebDriver driver, WebDriverWait wait, By loc){

        WebElement element = null;

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
            element = driver.findElement(loc);

        } catch(TimeoutException toe){
            System.out.println("Timeout exception buscando el elemento " + loc.toString());
        }

        return element;
    }

    public static List<WebElement> getListaElementosNoWait(WebDriver driver, By loc){
        return driver.findElements(loc);
    }
    
    public static Boolean elementoExisteEnLista(List<WebElement> lista, String text){

        if (lista.size() < 1){
            return false;
        }

        for (WebElement elem : lista){
            if (elem.getText().equals(text)){
                return true;
            }
        }
        return false;
    }

    public static void realizarLogin(WebDriver driver, WebDriverWait wait){
        // Paso 2 Escribir el username standard_user
        WebElement username = Utils.esperarElementoClickable(driver, wait, TEXTFIELDUSERNAME);
        username.sendKeys("standard_user");

        // Paso 3 Escribir el password secret_sauce
        WebElement password = Utils.esperarElementoClickable(driver, wait, TEXTFIELDPASSWORD);
        password.sendKeys("secret_sauce");

        // Paso 4 Pulsar en el botón del Login.
        Utils.esperarElementoClickable(driver, wait, BUTTONLOGIN).click();
    }
}
