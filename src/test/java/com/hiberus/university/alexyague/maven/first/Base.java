package com.hiberus.university.alexyague.maven.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {


    static By textfieldUsername = By.xpath("//input[@data-test='username']");
    static By textfieldPassword = By.xpath("//input[@data-test='password']");
    static By buttonLogin = By.xpath("//input[@data-test='login-button']");
    static By inventoryListBy = By.xpath("//div[@class='inventory_item']");
    static By buttonAddCart = By.xpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    static By badgeCart = By.xpath("//span[@class='shopping_cart_badge']");
    static String usuarioS = "standard_user";
    static String passwordS = "secret_sauce";
}
