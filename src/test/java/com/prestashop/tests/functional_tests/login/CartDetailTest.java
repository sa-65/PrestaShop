package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.*;

public class CartDetailTest extends TestBase  {
    Faker faker=new Faker();
    SoftAssert softAssert;
    HomePage homePage;
    SignInPage signInPage;
    RegistrationPage registrationPage;
    MyAccountPage myAccountPage;
    PersonalInformationPage personalInformationPage;
    MyAddressesPage myAddressesPage;

    String emailAddress = faker.internet().emailAddress();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String password = faker.internet().password(5, 10);
    String company = faker.company().name();
    String streetAddress = faker.address().streetAddress();
    String city = faker.address().city();
    String state = faker.address().state();
    String zipCode = faker.address().zipCode().substring(0, 5);
    String phoneNumber = faker.phoneNumber().cellPhone();


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

    /**1.Open browser
     * 2.Go tohttp://automationpractice.com/index.php
     * 3.Click on any product that is not on sale
     * 4.Enter a random quantity between 2 and 5
     * 5.Select a different size
     * 6.Click on add to cart
     * 7.Verifyconfirmation message Product successfully added to your shopping cart
     * 8.Dismiss the confirmation window by clicking on the x icon
     * 9.Click on the company logo
     * 10.Click on any productthat isonsale
     * 11.Enter a random quantity between 2and 5
     * 12.Select a different size
     * 13.Click on add to cart
     * 14.Verify confirmation message Product successfully added to your shopping cart
     * 15.Dismiss the confirmation window by clicking on the x icon
     * 16.Hover over the Cart icon
     * 17.Verify that correct total is displayed
     * 18.Verify that total is correct based on the price and itemNotOnSale count of the products you added to cart.
     * (Shipping is always $2)
     */
    @Test
    public void cartDetail(){
        //2.Go tohttp://automationpractice.com/index.php
        homePage.open();

       // 3.Click on any product that is not on sale
        homePage.itemNotOnSale.click();

        //4.Enter a random quantity between 2 and 5
        //5.Select a different size
        homePage.sizeAndQuantity();

        //6.Click on add to cart
        homePage.addToCartButton.click();

        //7.Verify confirmation message Product successfully added to your shopping cart
        assertTrue(homePage.confirmationMessage.getText().
                contains("Product successfully added to your shopping cart"));

        TestBase.wait(2);

        //8.Dismiss the confirmation window by clicking on the x icon
        homePage.xIcon.click();

        //9.Click on the company logo
        homePage.logoButton.click();

        //10.Click on any product that is on sale
        homePage.itemOnSale.click();

        //11.Enter a random quantity between 2and 5
        //12.Select a different size
        homePage.sizeAndQuantity();

        //13.Click on add to cart
        homePage.addToCartButton.click();

        //14.Verify confirmation message Product successfully added to your shopping cart
        assertTrue(homePage.confirmationMessage.getText().
                contains("Product successfully added to your shopping cart"));

        TestBase.wait(2);

        //15.Dismiss the confirmation window by clicking on the x icon
        homePage.xIcon.click();

        //16.Hover over the Cart icon
        actions.moveToElement(homePage.shoppingCart).perform();

        TestBase.wait(2);

        //17.Verify that correct total is displayed
        //System.out.println(homePage.totalWithinCart.isDisplayed());

        //18.Verify that total is correct based on the price and itemNotOnSale count of the products you added to cart.
        assertTrue(homePage.verifyTotal());





    }


}
