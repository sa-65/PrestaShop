package com.prestashop.tests.smoke_tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class ProductInformation {
    WebDriver driver;
    public static String productName="";
    public static String productName1="";
    public static String productNameAfterOrder="";
    public static String userName="";

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



    @Test
    public void priceAndName(){

        //PRODUCT NAME

        productName=driver.findElement(By.linkText("Faded Short Sleeve T-shirts")).getText();
        String priceBeforeClick = driver.findElement(By.xpath("(//span[@class='price product-price'])[2]")).getText();
        driver.findElement(By.linkText("Faded Short Sleeve T-shirts")).click();
        productName1=driver.findElement(By.xpath("//h1[.='Faded Short Sleeve T-shirts']")).getText();
        Assert.assertEquals(productName,productName1);

        //PRICE
        String priceAfterClick = driver.findElement(By.xpath("//span[@id='our_price_display']")).getText();
        Assert.assertEquals(priceBeforeClick,priceAfterClick);


    }

    //QUANTITY
    @Test
    public void defaultQuantity(){
        driver.findElement(By.linkText("Faded Short Sleeve T-shirts")).click();
        //QUANTITY
        String  quantity= driver.findElement(By.id("quantity_wanted")).getAttribute("value");
        System.out.println(quantity);

        Assert.assertEquals(quantity,"1");
    }

    //SIZE
    @Test
    public void defaultSize(){
        driver.findElement(By.linkText("Faded Short Sleeve T-shirts")).click();

        WebElement  sizes = driver.findElement(By.id("group_1"));
        Select select=new Select(sizes);

        String defaultSize=select.getFirstSelectedOption().getText();
        Assert.assertEquals(defaultSize,"S");
    }

    //SIZE OPTIONS
    @Test
    public void allSize(){

        driver.findElement(By.linkText("Faded Short Sleeve T-shirts")).click();

        WebElement  sizes = driver.findElement(By.id("group_1"));
        Select select=new Select(sizes);
        List<WebElement> options=select.getOptions();

        String temp="";

        for(WebElement option:options){
            temp+=option.getText();
        }
        System.out.println(temp);
        Assert.assertTrue(temp.contains("SML"));
    }


    @Test
    public void adToCart()throws Exception{
        productName = driver.findElement(By.linkText("Faded Short Sleeve T-shirts")).getText();
        driver.findElement(By.linkText("Faded Short Sleeve T-shirts")).click();
        Thread.sleep(3000);
        productName1 = driver.findElement(By.xpath("//h1[.='Faded Short Sleeve T-shirts']")).getText();
        driver.findElement(By.id("add_to_cart")).click();
        Thread.sleep(3000);

        //VERIFY THE CONFIRMATION MESSAGE
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']//h2"));
        String actualConfirmationMessage = confirmationMessage.getAttribute("textContent").trim();
        System.out.println(actualConfirmationMessage);
        Assert.assertTrue(actualConfirmationMessage.equals("Product successfully added to your shopping cart"));

        //verify default quantity after order
        String defaultQuantity = driver.findElement(By.id("layer_cart_product_quantity")).getText();
        Assert.assertEquals(defaultQuantity,"1");
        //verify default size
        String defaultSize = driver.findElement(By.id("layer_cart_product_attributes")).getText();
        Assert.assertTrue(defaultSize.contains(" S"));
        //verify the names of the products
        productNameAfterOrder = driver.findElement(By.xpath("//span[@id='layer_cart_product_title']")).getText();
        Assert.assertEquals(productName,productNameAfterOrder);


    }
}
