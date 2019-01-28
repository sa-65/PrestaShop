package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ErrorMessageValidationTest extends TestBase {


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
     * 2.Go to http://automationpractice.com/index.php
     * 3.Click Sign inlink
     * 4.Enter newvalidemail to the email field
     * 5.Click on Create Account
     * 6.Fill all the required steps except for first name
     * 7.Click on Register
     * 8.Verify that error messagefirstnameis required.is displayed*/
    @Test
    public void errorMessage(){
       // 2.Go to http://automationpractice.com/index.php
        homePage.open();

        //3.Click Sign inlink
        homePage.signInButton.click();

        //4.Enter newvalidemail to the email field
        signInPage.signupEmailInput.sendKeys(emailAddress);

        //5.Click on Create Account
        signInPage.createAccountButton.click();

        //6.Fill all the required steps except for first name
        registrationPage.genderMr.click();
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

        //7.Click on Register
        registrationPage.registerButton.click();

        //8.Verify that error messagefirstnameis required.is displayed
        System.out.println(registrationPage.errorMessage.isDisplayed());



    }
}
