package com.hiberus.university.alexyague.maven.first;

import org.openqa.selenium.By;

public class Base {

    static By textfieldUsername = By.xpath("//input[@data-test='username']");
    static By textfieldPassword = By.xpath("//input[@data-test='password']");
    static By buttonLogin = By.xpath("//input[@data-test='login-button']");
    static By inventoryListBy = By.xpath("//div[@class='inventory_item']");
    static String usuarioS = "standard_user";
    static String passwordS = "secret_sauce";
}
