package com.prestashop.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import static org.junit.Assert.*;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    protected Actions actions;
    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected Faker faker;
    protected Select select;
    protected Random random;
    protected static int second;

    @BeforeMethod
    public void setUpClass() {
        driver = Driver.getDriver();
        softAssert = new SoftAssert();
        actions = new Actions(driver);
        faker = new Faker();
    }


    @AfterMethod
    public void tearDownMethod() {
         //Driver.closeDriver();
        softAssert.assertAll();
    }

    public static void wait(int second){

        try {
            second*=1000;
            Thread.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void implicitlyWait(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}