package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.Config;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.assertTrue;

public class CartLogoutTest extends TestBase {

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
     3. Login as any valid user
     4. Go back to home page
     5. Add any product in the homepage to the cart
     6. Hover over the cart icon
     7. Verify that cart contains the product
     8. Log out
     9. Verify the cart contains the word empty
     */

    @Test
    public void cartLogout(){
        //2. Go to http://automationpractice.com/index.php
        homePage.open();

        // 3. Login as any valid user
        signInPage.login(Config.getProperty("email"), Config.getProperty("password"));

        //4. Go back to home page
        homePage.logoButton.click();

        // 5. Add any product in the homepage to the cart
        homePage.itemOnSale.click();
        //switch to iframe
        Driver.getDriver().switchTo().frame(homePage.iframe);
        homePage.addToCartButton.click();
        TestBase.wait(3);
        homePage.xIcon.click();
        Driver.getDriver().switchTo().defaultContent();

        //6. Hover over the cart icon
        actions.moveToElement(homePage.shoppingCart).perform();

        // 7. Verify that cart contains the product
        assertTrue(homePage.productBeforeCart.getText().
                startsWith(homePage.productInCart.getText().substring(0,10)));

        //8. Log out
        homePage.signOutButton.click();

        //9. Verify the cart contains the word empty
        assertTrue(homePage.empty.getText().contains("empty"));



    }
}
