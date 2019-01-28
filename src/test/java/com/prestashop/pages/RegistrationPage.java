package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class RegistrationPage {


    public RegistrationPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy (id = "id_gender1")
    public WebElement genderMr;

    @FindBy (xpath = "//*[@id=\"center_column\"]/div/ol/li")
    public WebElement errorMessage;

    @FindBy (id = "customer_firstname")
    public WebElement firstnameInput;

    @FindBy (id = "customer_lastname")
    public WebElement lastnameInput;

    @FindBy (id = "email")
    public WebElement emailInput;

    @FindBy (id = "passwd")
    public WebElement passwordInput;

    @FindBy (id = "days")
    public WebElement days;

    public void  daysDropDown(){
        Select birthDay = new Select(days);
        Random random = new Random();
        int randomNumber = random.nextInt(30) + 1;
        birthDay.selectByIndex(randomNumber);
    }

    @FindBy (id = "months")
    public WebElement months;

    public void monthsDropDown(){
        Select birthDay = new Select(months);
        Random random = new Random();
       int randomNumber = random.nextInt(12) + 1;
        birthDay.selectByIndex(randomNumber);
    }

    @FindBy (id = "years")
    public WebElement years;

    public void yearsDropDown(){
        Select birthDay = new Select(years);
        Random random = new Random();
        int randomNumber = random.nextInt(95) + 1;
        birthDay.selectByIndex(randomNumber);
    }

    @FindBy (id = "company")
    public WebElement companyInput;

    @FindBy (id = "address1")
    public WebElement addressStreetInput;


    @FindBy (id = "city")
    public WebElement cityInput;

    @FindBy (id = "id_state")
    public WebElement states;


    @FindBy (id = "postcode")
    public WebElement zipCodeInput;


    @FindBy (id = "phone_mobile")
    public WebElement phoneMobileinput;


    @FindBy (id = "submitAccount")
    public WebElement registerButton;


}
