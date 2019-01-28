package com.prestashop.pages;


import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    public MyAccountPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }



    @FindBy(xpath = "//div[@class='header_user_info']//span")
    public  WebElement userFullname;

    @FindBy(xpath = "//span[@class='price']")
    public static WebElement priceAfterLogin;

    public WebElement companyLogoClickable;

    @FindBy(id = "search_query_top")
    public WebElement searchBoxInput;

    @FindBy(name = "submit_search")
    public WebElement searchButton;

    @FindBy(css = "a[title='Orders']")
    public WebElement orderHistoryButton;

    @FindBy(css = "a[title='Credit slips']")
    public WebElement myCreditSlipsButton;

    @FindBy(css = "a[title='Addresses']")
    public WebElement myAddressesButton;

    @FindBy(css = "a[title='Information']")
    public WebElement myPersonalInfoButton;

    @FindBy(css = "a[title='My wishlists']")
    public WebElement myWishlistsButton;

    @FindBy(css = "a[title='Home']")
    public WebElement toHomeButton;

    private WebDriver driver;




}
