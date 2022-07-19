package com.duotify.stepDefintions;

import com.duotify.pages.HomePage;
import com.duotify.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class LoginStepDefs {


    @When("I enter {string} as a username and {string} as password")
    public void i_enter_as_a_username_and_as_password(String user, String pass) {
        HomePage homePage = new HomePage();
        homePage.usernameLogin.sendKeys(user);
        homePage.passwordLogin.sendKeys(pass + Keys.ENTER);
    }
    @When("I enter lara.alissa as a username and lara12345 as password")
    public void i_enter_lara_alissa_as_a_username_and_lara12345_as_password() {
        HomePage homePage = new HomePage();
        homePage.usernameLogin.sendKeys("lara.alissa");
        homePage.passwordLogin.sendKeys("lara12345" + Keys.ENTER);
    }




    @Then("I should be able to login successfully")
    public void i_should_be_able_to_login_successfully() {

        Assert.assertEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());
    }


    @Given("I have {int} cucumbers")
    public void i_have_cucumbers(Integer int1) {
        System.out.println(int1 + "cucumbers");
    }
    @When("I add {int} more and change the weight to {double} pounds")
    public void i_add_more_and_change_the_weight_to_pounds(Integer int1, Double double1) {

    }
    @Then("I should have {int} cucumbers")
    public void i_should_have_cucumbers(Integer int1) {

    }

}
