package com.prestashop.pages;


import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalInformationPage {

    public PersonalInformationPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private String pageUrl = "http://automationpractice.com/index.php?controller=identity";
    private String pageTitle = "Identity - My Store";

    @FindBy (id = "firstname")
    public WebElement firstnameInput;

    @FindBy (id = "lastname")
    public WebElement lastnameInput;

    @FindBy (id = "email")
    public WebElement emailInput;


    @FindBy (name = "submitIdentity")
    public WebElement saveButton;

    @FindBy (css = ".footer_links >li:nth-of-type(1) >a")
    public WebElement backToYourAccountButton;

    @FindBy (css = ".footer_links >li:nth-of-type(2) >a")
    public WebElement backToHomeButton;

    @FindBy (css = ".alert >ol > li")
    public WebElement errorMessageLine1;

    public String getPageUrl() {
        return pageUrl;
    }
    public String getPageTitle() {
        return pageTitle;
    }
}
