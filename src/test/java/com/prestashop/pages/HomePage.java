package com.prestashop.pages;

import com.prestashop.tests.functional_tests.Test;
import com.prestashop.utilities.Config;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class HomePage {



    //locators
    @FindBy(xpath = "(//img[@class='replace-2x img-responsive'])[2]")
    public WebElement itemNotOnSale;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[2]/div/div[2]/div[1]/span")
    public WebElement itemNotOnSalePrice;

    @FindBy(xpath = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[1]/div/div[1]/span/span")
    public WebElement itemNotOnSaleQuantity;

    @FindBy(xpath = "//iframe[@class='fancybox-iframe']")
    public WebElement iframe;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[5]/div/div[2]/h5/a")
    public static WebElement productBeforeCart;

    @FindBy(xpath = "//a[@class='cart_block_product_name']")
    public static WebElement productInCart;

    @FindBy(xpath = "//span[@class='price']")
    public static WebElement priceAfterLogin;

    @FindBy(xpath = "//span[@class='price']")
    public static WebElement priceBeforeLogin;

    @FindBy(xpath = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt/div/div[2]/a")
    public static WebElement colorAndSizeBeforeLogin;

    @FindBy(xpath = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt/div/div[2]/a")
    public static WebElement colorAndSizeAfterLogin;


    @FindBy(css = "#group_1")
    public WebElement sizeSelect;

    @FindBy(id = "quantity_wanted")
    public WebElement quantityInput;

    @FindBy(xpath = "//button[@class='exclusive']/span")
    public WebElement addToCartButton;

    public void sizeAndQuantity(){
        Driver.getDriver().switchTo().frame(iframe);
        TestBase.wait(2);
        Select select;
        select=new Select(sizeSelect);
        select.selectByIndex(2);

        quantityInput.click();
        quantityInput.clear();
        Random random;
        random=new Random();
        int quantity=random.nextInt(4)+2;
        String num1=quantity+"";

        //Thread.sleep(3000);
        quantityInput.sendKeys(num1);
    }

    @FindBy(xpath = "//h2")
    public WebElement confirmationMessage;

    @FindBy(xpath = "//span[@title='Close window']")
    public WebElement xIcon;

    @FindBy(css = "#header_logo")
    public WebElement logoButton;

    @FindBy(xpath = "(//img[@class='replace-2x img-responsive'])[5]")
    public WebElement itemOnSale;

    @FindBy(xpath = "//*[@id=\"homefeatured\"]/li[5]/div/div[2]/div[1]/span[1]")
    public WebElement itemOnSalePrice;

    @FindBy(xpath = "//*[@id=\"header\"]/div[3]/div/div/div[3]/div/div/div/div/dl/dt[2]/div/div[1]/span/span")
    public WebElement itemOnSaleQuantity;

    @FindBy(className = "login")
    public WebElement signInButton;

    @FindBy(className = "logout")
    public WebElement signOutButton;

    @FindBy(xpath = "//div[@class='shopping_cart']/a/span[5]")
    public static WebElement empty;

    @FindBy(xpath = "(//div[@id='layer_cart']/div[1]/div[2]//span)[8]")
    public static WebElement contShopping;

    @FindBy(xpath = "//span[@class='remove_link']/a")
    public static WebElement clickX;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")
    public static WebElement checkOut;

    @FindBy(xpath = "//h1[@id='cart_title']/span")
    public static WebElement message;

    @FindBy(xpath = "//*[@id=\"product_5_19_0_0\"]/td[7]")
    public static WebElement deleteIcon;

    @FindBy(xpath = "//*[@id=\"cart_title\"]/span")
    public static WebElement message2;

    @FindBy(xpath = "//p[@class='alert alert-warning']")
    public static WebElement message3;

    @FindBy(xpath = "//tr[@id='product_2_7_0_0']/td[7]/div/a")
    public static WebElement deleteIcon2;

    @FindBy(css = ".shopping_cart > a")
    public WebElement shoppingCart;

    @FindBy(css = ".price.cart_block_shipping_cost")
    public WebElement shipping;

    @FindBy(css = ".price.cart_block_total")
    public WebElement totalWithinCart;


    public boolean verifyTotal(){
        double totalPrice=Double.parseDouble(totalWithinCart.getText().replaceAll("\\D+",""));
        double productOnsalePrice=Double.parseDouble(itemOnSalePrice.getText().replaceAll("\\D+",""));
        double productNotOnsalePrice=Double.parseDouble(itemNotOnSalePrice.getText().replaceAll("\\D+",""));
        double shippingPrice=Double.parseDouble(shipping.getText().replaceAll("\\D+",""));
        double productOnSaleQuantity=Double.parseDouble(itemOnSaleQuantity.getText().replaceAll("\\D+",""));
        double productNotOnSaleQuantity=Double.parseDouble(itemNotOnSaleQuantity.getText().replaceAll("\\D+",""));

        return totalPrice==(productOnSaleQuantity*productOnsalePrice+productNotOnSaleQuantity*productNotOnsalePrice)+shippingPrice;
    }


    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    public  void open() {
        Driver.getDriver().get(Config.getProperty("url"));
    }

}