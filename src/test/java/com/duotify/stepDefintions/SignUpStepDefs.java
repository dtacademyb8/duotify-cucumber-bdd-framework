package com.duotify.stepDefintions;

import com.duotify.pages.HomePage;
import com.duotify.utilities.ConfigReader;
import com.duotify.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.time.Duration;

public class SignUpStepDefs {



    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {

        Driver.getDriver().get(ConfigReader.getProperty("url"));

    }

    @When("I navigate to signup page and enter valid credentials")
    public void i_navigate_to_signup_page_and_enter_valid_credentials() {
        HomePage homePage = new HomePage();
        homePage.clickOnSignUpPageLink();
        Faker faker = new Faker();
        homePage.enterCredentialsAndClick(faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password());
    }
    @Then("I should be able to sign up successfully")
    public void i_should_be_able_to_sign_up_successfully() {
        Assert.assertEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());
    }


    @When("I navigate to signup page and enter invalid credentials")
    public void i_navigate_to_signup_page_and_enter_invalid_credentials() {
        HomePage homePage = new HomePage();
        homePage.clickOnSignUpPageLink();
        Faker faker = new Faker();
        homePage.enterCredentialsAndClick("lara.alissa",
                faker.name().firstName(),
                faker.name().lastName(),
                "dcsvvdsg",
                faker.internet().password());
    }
    @Then("I should not be able to sign up")
    public void i_should_not_be_able_to_sign_up() {
        Assert.assertNotEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());

    }

    @When("I navigate to signup page and enter no credentials")
    public void i_navigate_to_signup_page_and_enter_no_credentials() {
        HomePage homePage = new HomePage();
        homePage.clickOnSignUpPageLink();

        homePage.enterCredentialsAndClick("",
               "",
                "",
                "",
                "");
    }




}
