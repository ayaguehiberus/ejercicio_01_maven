package com.hiberus.university.alexyague.maven.first.pageobjectmodel.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/surefire-reports/cucumber.json"
        },
        tags = "@Login or @Cart or @Logout",
        glue = {"com.hiberus.university.alexyague.maven.first.pageobjectmodel.stepdefs",
                "com.hiberus.university.alexyague.maven.first.pageobjectmodel.support"
        },
        features = {"src/test/resources"}
)

public class CucumberRunnerTest {

}
