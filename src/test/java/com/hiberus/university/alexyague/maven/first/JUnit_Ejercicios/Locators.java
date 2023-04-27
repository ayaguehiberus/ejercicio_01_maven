package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import org.openqa.selenium.By;

public class Locators {

    static By TEXTFIELDUSERNAME = By.xpath("//input[@data-test='username']");
    static By TEXTFIELDPASSWORD = By.xpath("//input[@data-test='password']");
    static By BUTTONLOGIN = By.xpath("//input[@data-test='login-button']");
    static By ERRORMSG = By.xpath("//h3[@data-test='error']");
    static By INVENTORYLIST = By.xpath("//div[@class='inventory_item']");
    static By BUTTONADDCART = By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    static By BUTTONADDCART2 = By.xpath("//button[@data-test='add-to-cart-sauce-labs-onesie']");
    static By BUTTONREMOVECART = By.xpath("//button[@data-test='remove-sauce-labs-onesie']");
    static By BADGECART = By.xpath("//span[@class='shopping_cart_badge']");
    static By CARTICONCHILDS = By.xpath("//a[@class='shopping_cart_link']/child::span");
}
