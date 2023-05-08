package com.hiberus.university.alexyague.maven.first.selenium_otros;

import com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class VentanasEmergentes {

    WebDriver driver;
    WebDriverWait wait;
    String url = "https://demoqa.com/alerts";
    String url2 = "https://the-internet.herokuapp.com/context_menu";
    String url3 = "https://demoqa.com/droppable/";
    String url4 = "https://demoqa.com/menu/";
    String url5 = "https://the-internet.herokuapp.com/hovers";
    String url6 = "https://demoqa.com/text-box";

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 5);

    }

    @Test
    public void escribirenAlert(){
        driver.get(url);
        Utils.esperarElementoClickable(driver, wait, By.cssSelector("#promtButton"));
    }

    @Test
    public void hacerClickDerecho(){
        driver.get(url2);
        Actions actions = new Actions(driver);
        WebElement cuadro = Utils.esperarElementoClickable(driver, wait, By.cssSelector("#hot-spot"));
        actions.contextClick(cuadro).perform();
        driver.switchTo().alert().accept();
    }

    @Test
    public void dragAndDropExample1(){
        driver.get(url3);
        Actions actions = new Actions(driver);
        WebElement elem1 = Utils.esperarElementoVisible(driver, wait, By.cssSelector("#draggable"));
        WebElement elem2 = Utils.esperarElementoVisible(driver, wait, By.cssSelector("#droppable"));
        actions.dragAndDrop(elem1, elem2).perform();
        WebElement elem3 = Utils.esperarElementoVisible(driver, wait, By.xpath("//div[@id='droppable' and contains(@class, 'ui-state-highlight')]"));
        Assert.assertNotNull("El elemento no fue arrastrado correctamente", elem3);
    }

    @Test
    public void mouseHoverExample1() throws InterruptedException {
        driver.get(url4);
        Actions actions = new Actions(driver);
//        By link1 = By.xpath("//a[text()='Main Item 2']");
        By link1 = By.cssSelector("a:contains('Main Item 2')");
        By link2 = By.xpath("//a[text()='SUB SUB LIST »']");
        By link3 = By.xpath("//a[text()='Sub Sub Item 2']");

        WebElement link1Elem = Utils.esperarElementoVisible(driver, wait, link1);
        actions.moveToElement(link1Elem).perform();
        Thread.sleep(2000);

        WebElement link2Elem = Utils.esperarElementoVisible(driver, wait, link2);
        actions.moveToElement(link2Elem).perform();
        Thread.sleep(2000);

        WebElement link3Elem = Utils.esperarElementoVisible(driver, wait, link3);
        actions.moveToElement(link3Elem).perform();
        Thread.sleep(2000);

    }

    @Test
    public void mouseHoverExample2(){
        driver.get(url5);
        Actions actions = new Actions(driver);

        By imagenes = By.xpath("//img[@alt='User Avatar']");
        By viewProfile = By.xpath("//a[@href='/users/3']");

        ArrayList<WebElement> listaImg = (ArrayList<WebElement>) Utils.getListaElementosNoWait(driver, imagenes);
        actions.moveToElement(listaImg.get(2)).perform();

        WebElement viewProfileElem = Utils.esperarElementoClickable(driver, wait, viewProfile);
        Assert.assertNotNull("Elemento no encontrado", viewProfileElem);
        actions.moveToElement(viewProfileElem).perform();
        viewProfileElem.click();

        String urlEsperada = "https://the-internet.herokuapp.com/users/3";

        Assert.assertEquals("No nos ha redirigido a la página correcta", urlEsperada, driver.getCurrentUrl());

    }

    @Test
    public void keyEventsExample1(){
        driver.get(url4);
        Actions actions = new Actions(driver);

        By inputUsername = By.cssSelector("#userName");
        By inputUsermail = By.cssSelector("#userEmail");
        By inputCurrentAd = By.cssSelector("#currentAddress");
        By inputPermanentAd = By.cssSelector("#permanentAddress");
        By submit = By.cssSelector("#submit");

        actions.sendKeys(Utils.esperarElementoVisible(driver, wait, inputUsername), "Alex").perform();
        actions.sendKeys(Utils.esperarElementoVisible(driver, wait, inputUsermail), "blabla@gbla.com").perform();
        actions.sendKeys(Utils.esperarElementoVisible(driver, wait, inputCurrentAd), "C/Actual").perform();

        String currentAd = driver.findElement(inputCurrentAd).getText();

        actions.sendKeys(Utils.esperarElementoVisible(driver, wait, inputPermanentAd), currentAd).perform();

    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
