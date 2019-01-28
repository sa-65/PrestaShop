package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartIconDeleteTest extends TestBase {

    SoftAssert softAssert;
    HomePage homePage;
    SignInPage signInPage;
    RegistrationPage registrationPage;
    MyAccountPage myAccountPage;
    PersonalInformationPage personalInformationPage;
    MyAddressesPage myAddressesPage;


    @BeforeMethod
    public void setPage() {
        softAssert = new SoftAssert();
        homePage = new HomePage();
        signInPage = new SignInPage();
        registrationPage = new RegistrationPage();
        myAccountPage = new MyAccountPage();
        personalInformationPage = new PersonalInformationPage();
        myAddressesPage = new MyAddressesPage();
    }

    @AfterMethod
    public void pageTeardown() {
        homePage = null;
        signInPage = null;
        registrationPage = null;
        myAccountPage = null;
        personalInformationPage = null;
        myAddressesPage = null;
    }

    /**1. Open browser
     2. Go to http://automationpractice.com/index.php
     3. Add any product in the homepage to the cart
     4. Click on Continue shopping
     5. Hover over the cart icon
     6. Click the x to delete the product
     7. Verify word empty is displayed in the Cart icon
     */

    @Test
    public void iconDelete(){
        //2. Go to http://automationpractice.com/index.php
        homePage.open();

        //3. Add any product in the homepage to the cart
        homePage.itemOnSale.click();
        //switch to iframe
        Driver.getDriver().switchTo().frame(homePage.iframe);
        homePage.addToCartButton.click();
        TestBase.wait(3);
        homePage.xIcon.click();
        Driver.getDriver().switchTo().defaultContent();

        //4. Click on Continue shopping
        homePage.contShopping.click();

        // 5. Hover over the cart icon
        actions.moveToElement(homePage.shoppingCart).perform();

        //6. Click the x to delete the product
        homePage.clickX.click();

        //7. Verify word empty is displayed in the Cart icon
        System.out.println(homePage.empty.isDisplayed());

    }
}
