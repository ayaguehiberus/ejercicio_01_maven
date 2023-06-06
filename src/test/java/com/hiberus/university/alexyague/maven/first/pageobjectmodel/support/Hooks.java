package com.hiberus.university.alexyague.maven.first.pageobjectmodel.support;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.pages.PageFactory;
import com.hiberus.university.alexyague.maven.first.pageobjectmodel.utils.Flags;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.remote.BrowserType.EDGE;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

@Slf4j
public class Hooks {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void before(Scenario scenario){
        log.info("Starting scenario" + scenario.getName());

//        String browser = Flags.getInstance().getBrowser();
//        boolean isHeadless = Flags.getInstance().isHeadless();

//        switch (browser){
////            case FIREFOX:
////                WebDriverManager.firefoxdriver().setup();
////                FirefoxOptions firefoxOptions = new FirefoxOptions();
////                if (isHeadless){
////                    firefoxOptions.addArguments("--headless");
////                }
////                driver = new FirefoxDriver(firefoxOptions);
////                break;
////
////            case EDGE:
////                WebDriverManager.edgedriver().setup();
////                EdgeOptions edgeOptions = new EdgeOptions();
////                driver = new EdgeDriver(edgeOptions);
////                break;
//
//            default:
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();

//        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        PageFactory.start(driver);
    }

    @After(order = 0)
    public void after(Scenario scenario){
        log.info("Ending scenario " + scenario.getName());
        driver.manage().deleteAllCookies();
        TestDataContext.cleanDataContext();
        driver.quit();
    }

    @After(order = 1)
    public void takeScreenshotOnFailure(Scenario scenario) {

        if (scenario.isFailed()) {

            TakesScreenshot ts = (TakesScreenshot) driver;

            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }
    }

    // Comentario para probar Jenkins
}
