package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAddressesPage {
    public MyAddressesPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (css = ".box >li:nth-of-type(2) >span:nth-of-type(1)")
    public WebElement firstnameDisplayed;

    @FindBy (css = ".box >li:nth-of-type(2) >span:nth-of-type(2)")
    public WebElement lastnameDisplayed;

    @FindBy (css = ".box >li:nth-of-type(4) >span:nth-of-type(1)")
    public WebElement streetDisplayed;

    @FindBy (css = ".box >li:nth-of-type(5) >span:nth-of-type(1)")
    public WebElement cityDisplayed;

    @FindBy (css = ".box >li:nth-of-type(5) >span:nth-of-type(2)")
    public WebElement stateDisplayed;

    @FindBy (css = ".box >li:nth-of-type(5) >span:nth-of-type(3)")
    public WebElement zipCodeDisplayed;

    @FindBy (css = ".box >li:nth-of-type(6) >span")
    public WebElement countryDisplayed;

    @FindBy (css = ".box >li:nth-of-type(7) >span")
    public WebElement phoneHomeDisplayed;

    @FindBy (css = ".box >li:nth-of-type(8) >span")
    public WebElement phoneMobileDisplayed;

    @FindBy(linkText ="Sign out" )
    public WebElement signOut;

    @FindBy (css = "a[title='Update']")
    public WebElement updateButton;

    @FindBy (css = "a[title='Delete']")
    public WebElement deleteButton;

    @FindBy (css = ".main-page-indent >a")
    public WebElement addNewAddressButton;

    @FindBy (css = ".footer_links >li:nth-of-type(1) >a")
    public WebElement backToYourAccountButton;

    @FindBy (css = ".footer_links >li:nth-of-type(2) >a")
    public WebElement backToHomeButton;
}