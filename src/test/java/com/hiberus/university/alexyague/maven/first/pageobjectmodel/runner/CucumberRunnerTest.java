package com.hiberus.university.alexyague.maven.first.pageobjectmodel.runner;


import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"com.hiberus.university.alexyague.maven.first.pageobjectmodel.stepdefs",
                "com.hiberus.university.alexyague.maven.first.pageobjectmodel.support"
        },
        features = {"src/test/resources"}
)

public class CucumberRunnerTest {

    public static WebDriver driver;

    @BeforeClass
    public static void setUp(){

    }

    @AfterClass
    public static void tearDown(){

    }
}
