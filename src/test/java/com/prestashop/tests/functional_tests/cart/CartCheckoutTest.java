package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.*;
import com.prestashop.utilities.Config;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.junit.Assert.*;

public class CartCheckoutTest extends TestBase {

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
     5. Add another product in the homepage to the cart
     6. Click on Proceed to checkout
     7. Verify message Your shopping cart contains: 2 Products
     8. Click the delete icon to delete one of the products
     9. Verify message Your shopping cart contains: 1 Product
     10. Click the delete icon to delete the second product
     11. Verify message Your shopping cart is empty.
     */

    @Test
    public void checkOut(){

        //2. Go to http://automationpractice.com/index.php
        homePage.open();

        //3. Add any product in the homepage to the cart
        homePage.itemOnSale.click();
        //switch to iframe
        Driver.getDriver().switchTo().frame(homePage.iframe);
        homePage.addToCartButton.click();
        TestBase.wait(2);

        //4. Click on Continue shopping
        homePage.contShopping.click();
        TestBase.wait(2);

        //5. Add another product in the homepage to the cart
        homePage.itemNotOnSale.click();
        Driver.getDriver().switchTo().frame(homePage.iframe);
        homePage.addToCartButton.click();
        TestBase.wait(2);

        //6. Click on Proceed to checkout
        homePage.checkOut.click();

        //7. Verify message Your shopping cart contains: 2 Products
        assertTrue(homePage.message.getText().equals(Config.getProperty("message1")));

        //8. Click the delete icon to delete one of the products
        homePage.deleteIcon.click();
        TestBase.wait(2);
        //9. Verify message Your shopping cart contains: 1 Product
        System.out.println(homePage.message2.getText());
        assertTrue(homePage.message2.getText().equals(Config.getProperty("message2")));
        TestBase.wait(2);

        // 10. Click the delete icon to delete the second product
        homePage.deleteIcon2.click();
        TestBase.wait(2);

        // 11. Verify message Your shopping cart is empty.
        assertTrue(homePage.message3.getText().contains(Config.getProperty("message3")));

    }
}
