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
    static By INVENTORYLISTITEMPRICE = By.xpath("//div[@class='inventory_item_price']");
    static By SPECIFICPRODUCTNAME = By.xpath("//div[@class='inventory_list']/descendant::div[@class='inventory_item_name'][text()='Sauce Labs Bolt T-Shirt']");
    static By BUTTONADDCARTPROD3 = By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    static By BUTTONADDCARTPRODLIST = By.xpath("//button[contains(@data-test, 'add-to-cart')]");
    static By BUTTONREMOVECARTPROD3 = By.xpath("//button[@data-test='remove-sauce-labs-bolt-t-shirt']");
    static By CARTBUTTON = By.xpath("//a[@class='shopping_cart_link']");
    static By CARTSIZE = By.xpath("//a[@class='shopping_cart_link']/child::span");
    static By ORDERDROPDOWN = By.xpath("//select[@data-test='product_sort_container']");
    static By BUTTON_MENU = By.xpath("//button[@id='react-burger-menu-btn']");
    static By BUTTON_LOGOUT = By.xpath("//a[@id='logout_sidebar_link']");

    // Carrito
    static By CARTBUTTONREMOVECARTLIST = By.xpath("//button[contains(@data-test, 'remove')]");
    static By CHECKOUTBUTTON = By.xpath("//button[@data-test='checkout']");

    // Checkout 1
    static By FORM_FIRSTNAME = By.xpath("//input[@data-test='firstName']");
    static By FORM_LASTNAME = By.xpath("//input[@data-test='lastName']");
    static By FORM_POSTALCODE = By.xpath("//input[@data-test='postalCode']");
    static By FORM_SUBMIT = By.xpath("//input[@type='submit' and @data-test='continue']");

    // Checkout 2
    static By LABEL_ITEMTOTALWITHOUTTAX = By.xpath("//div[@class='summary_subtotal_label']");
    static By BUTTON_FINISH = By.xpath("//button[@data-test='finish']");

    // Checkout Complete
    static By TEXT_MSGCOMPLETE = By.xpath("//div[@class='complete-text']");

}
