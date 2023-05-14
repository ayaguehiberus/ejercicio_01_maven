package com.hiberus.university.alexyague.maven.first.pageobjectmodel.support;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Slf4j
public class Hooks {

    public static WebDriver driver;
    @Before
    public void before(Scenario scenario){
        log.info("Starting scenario" + scenario.getName());
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        PageFactory.start(driver);
    }

    @After
    public void after(Scenario scenario){
        log.info("Ending scenario " + scenario.getName());
        driver.manage().deleteAllCookies();
        TestDataContext.cleanDataContext();
        driver.quit();
    }
}
