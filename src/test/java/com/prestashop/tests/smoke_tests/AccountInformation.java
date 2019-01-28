package com.prestashop.tests.smoke_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AccountInformation {

     WebDriver driver;
    public static String myName="";

    @BeforeClass
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @BeforeMethod
    public  void beforeMethod() throws InterruptedException {
        //PRODUCT
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);

    }
    @AfterMethod
    public void close(){
        driver.findElement(By.xpath("//a[@title='Log me out']")).click();
    }



    @Test
    public void loginMyAccount(){
       driver.findElement(By.xpath("//a[@class='login']")).click();
       driver.findElement(By.id("email")).sendKeys("yarenzack@gmail.com");
       driver.findElement(By.id("passwd")).sendKeys("cybertek2019");
       driver.findElement(By.id("SubmitLogin")).click();

       //VERIFY NAME

        String myName=driver.findElement(By.linkText("zeki yaren")).getText();
        Assert.assertTrue(myName.equals("zeki yaren"));
    }


    public static void justLogin(WebDriver driver){
        driver.findElement(By.xpath("//a[@class='login']")).click();
        driver.findElement(By.id("email")).sendKeys("yaren.zack@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("cybertek2018");
        driver.findElement(By.id("SubmitLogin")).click();

    }

    @Test
    public void personelInformation(){


        justLogin(driver);
        driver.findElement(By.xpath("//i[@class='icon-user']")).click();

        Assert.assertTrue(driver.getTitle().contains("Identity"));
        myName=driver.findElement(By.linkText("Lionel Messi")).getText();

        String firstName=driver.findElement(By.id("firstname")).getAttribute("value");
        Assert.assertTrue(myName.contains(firstName));

        String lastName=driver.findElement(By.id("lastname")).getAttribute("value");

        //CLICK SAVE BUTTON
        Assert.assertTrue(myName.contains(lastName));
        driver.findElement(By.xpath("//button[@name='submitIdentity']")).click();

        //CONFIRM THE MESSAGE
        String  errorMessage=driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertTrue(errorMessage.contains("The password you entered is incorrect."));

        //BACK TO YOUR ACCOUNT
        driver.findElement(By.linkText("Back to your account")).click();

        Assert.assertTrue(driver.getTitle().contains("My account"));
        //driver.findElement(By.xpath("//a[@title='Log me out']")).click();

    }

    @Test

    public void loiginMyAddress()throws InterruptedException{

        justLogin(driver);
        driver.findElement(By.linkText("My addresses")).click();

        //ADD A NEW ADDRESS
        driver.findElement(By.xpath("//a[@title='Add an address']")).click();

        //VERIFY FIRST NAME AND LAST NAME
        myName=driver.findElement(By.linkText("Lionel Messi")).getText();
        String firstName=driver.findElement(By.id("firstname")).getAttribute("value");
        String lastName=driver.findElement(By.id("lastname")).getAttribute("value");
        Assert.assertEquals(myName,firstName+" "+lastName);

        //DELETE THE FIRST NAME
        driver.findElement(By.id("firstname")).clear();

        Thread.sleep(3000);
        //CLICK SAVE BUTTON
        driver.findElement(By.xpath("//button[@id='submitAddress']")).click();

        //VERIFY ERROR MESSAGE
       String firstNameError= driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
       Assert.assertTrue(firstNameError.contains("firstname is required."));


        //driver.findElement(By.xpath("//a[@title='Log me out']")).click();


    }




}
