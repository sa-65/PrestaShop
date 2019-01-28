package com.prestashop.tests.functional_tests.login;

import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.*;

import com.github.javafaker.Faker;


public class RegistrationTest extends TestBase {

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


    /**
     * Registration Test
     * 1.Open browser
     * 2.Go to http://automationpractice.com/index.php
     * 3.Click Sign in link
     * 4.Enter new valid email to the email field
     * 5.Click on Create Account
     * 6.Verify that that email link displays current email
     * 7.Fill out all the required steps
     * 8.Click on Register
     * 9.Verify that correct first and last name is displayed correctly on top right
     * 10.Click on My personal information
     * 11.Verify that personal information is displayed correctly
     * 12.Click on Back to your account
     * 13.Click on My addresses verify that address information is displayed correctly
     * 14.Click on sign out link
     * 15.Login using the email and password if the current user
     * 16.Verify that correct first and last name is displayed correctly on top right
     */


    @Test
    public void registrationTest() {
        //// 2.Go to http://automationpractice.com/index.php
        homePage.open();

        // 3 Click on sign in
        homePage.signInButton.click();

        //4 Enter valid email address
        signInPage.signupEmailInput.sendKeys(emailAddress);

        //5 Clock on crate account
        signInPage.createAccountButton.click();

        TestBase.wait(2);

        //6 Verify that that email link displays current email
        String actualEmail = registrationPage.emailInput.getAttribute("value");
        assertEquals(actualEmail, emailAddress);

        //7 Fill out all the required steps
        registrationPage.genderMr.click();
        registrationPage.firstnameInput.sendKeys(firstName);
        registrationPage.lastnameInput.sendKeys(lastName);
        registrationPage.passwordInput.sendKeys(password);
        registrationPage.daysDropDown();
        registrationPage.monthsDropDown();
        registrationPage.yearsDropDown();
        registrationPage.companyInput.sendKeys(company);
        registrationPage.addressStreetInput.sendKeys(streetAddress);
        registrationPage.cityInput.sendKeys(city);
        registrationPage.states.sendKeys(state);
        registrationPage.zipCodeInput.sendKeys(zipCode);
        registrationPage.phoneMobileinput.sendKeys(phoneNumber);

        //8 Click on Register
        registrationPage.registerButton.click();

        TestBase.wait(2);
        //9 Verify that correct first and last name is displayed correctly on top right

        assertEquals(myAccountPage.userFullname.getText(), firstName + " " + lastName);

        //10 Click on My personal information
        myAccountPage.myPersonalInfoButton.click();

        //11 Verify that personal information is displayed correctly
        assertEquals(personalInformationPage.firstnameInput.getAttribute("value"), firstName);
        assertEquals(personalInformationPage.lastnameInput.getAttribute("value"), lastName);
        assertEquals(personalInformationPage.emailInput.getAttribute("value"), emailAddress);

        //12 Click on Back to your account
        personalInformationPage.backToYourAccountButton.click();

        //13 Click on My addresses verify that address information is displayed correctly
        myAccountPage.myAddressesButton.click();
        assertEquals(myAddressesPage.firstnameDisplayed.getText(), firstName);
        assertEquals(myAddressesPage.lastnameDisplayed.getText(), lastName);
        assertEquals(myAddressesPage.streetDisplayed.getText(), streetAddress);
        assertEquals(myAddressesPage.cityDisplayed.getText(), city + ",");
        assertEquals(myAddressesPage.stateDisplayed.getText(), state);
        assertEquals(myAddressesPage.countryDisplayed.getText(), "United States");
        assertEquals(myAddressesPage.zipCodeDisplayed.getText(), zipCode);
        assertEquals(myAddressesPage.phoneMobileDisplayed.getText(), phoneNumber);

        //14 Click on sign out link
        myAddressesPage.signOut.click();

        //15 Login using the email and password if the current user
        signInPage.login(emailAddress, password);

        //16Verify that correct first and last name is displayed correctly on top right
        assertEquals(myAccountPage.userFullname.getText(),firstName+" "+lastName);
    }


}
