package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import org.openqa.selenium.By;

public class Locators {

    // Login locators
    static By TEXTFIELDUSERNAME = By.xpath("//input[@data-test='username']");
    static By TEXTFIELDPASSWORD = By.xpath("//input[@data-test='password']");
    static By BUTTONLOGIN = By.xpath("//input[@data-test='login-button']");
    static By ERRORMSG = By.xpath("//h3[@data-test='error']");

    // Main page locators
    static By INVENTORYLISTITEM = By.xpath("//div[@class='inventory_item']");
    static By INVENTORYLISTITEMNAME = By.xpath("//div[@class='inventory_item_name']");
    static By SPECIFICPRODUCTNAME = By.xpath("//div[@class='inventory_list']/descendant::div[@class='inventory_item_name'][text()='Sauce Labs Bolt T-Shirt']");
    static By BUTTONADDCARTPROD3 = By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    static By BUTTONADDCARTPRODLIST = By.xpath("//button[contains(@data-test, 'add-to-cart')]");
    static By BUTTONREMOVECARTPROD3 = By.xpath("//button[@data-test='remove-sauce-labs-bolt-t-shirt']");
    static By BADGECART = By.xpath("//span[@class='shopping_cart_badge']");
    static By CARTSIZE = By.xpath("//a[@class='shopping_cart_link']/child::span");
    static By ORDERDROPDOWN = By.xpath("//select[@data-test='product_sort_container']");
    static By ORDERDROPDOWNOPTION = By.xpath("//select[@data-test='product_sort_container']");
}
