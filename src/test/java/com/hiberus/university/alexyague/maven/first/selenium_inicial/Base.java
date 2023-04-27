package com.hiberus.university.alexyague.maven.first.selenium_inicial;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {


    static By textfieldUsername = By.xpath("//input[@data-test='username']");
    static By textfieldPassword = By.xpath("//input[@data-test='password']");
    static By buttonLogin = By.xpath("//input[@data-test='login-button']");
    static By errorMsg = By.xpath("//h3[@data-test='error']");
    static By inventoryListBy = By.xpath("//div[@class='inventory_item']");
    static By buttonAddCart = By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    static By buttonAddCart2 = By.xpath("//button[@data-test='add-to-cart-sauce-labs-onesie']");
    static By buttonRemoveCart = By.xpath("//button[@data-test='remove-sauce-labs-onesie']");
    static By badgeCart = By.xpath("//span[@class='shopping_cart_badge']");
    static By cartIconChilds = By.xpath("//a[@class='shopping_cart_link']/child::span");
    static String usuarioS = "standard_user";
    static String passwordS = "secret_sauce";
}
