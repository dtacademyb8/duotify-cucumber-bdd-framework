package com.duotify.stepDefintions.ui;

import com.duotify.pages.HomePage;
import com.duotify.pages.WelcomePage;
import com.duotify.utilities.CSVReader;
import com.duotify.utilities.ConfigReader;
import com.duotify.utilities.Driver;
import com.duotify.utilities.SeleniumUtils;
import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
//        Assert.assertEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat( Driver.getDriver().getCurrentUrl()).isEqualTo("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?");
        softAssertions.assertAll();
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
        Assert.assertEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());

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

    @When("I navigate to signup page")
    public void i_navigate_to_signup_page() {
        HomePage homePage = new HomePage();
        homePage.clickOnSignUpPageLink();
    }
    @When("I enter the following credentials")
    public void i_enter_the_following_credentials(List<List<String>> dataTable) {

      List<String > secondRow =  dataTable.get(1);
        System.out.println(dataTable);
        new HomePage().enterCredentialsAndClick(secondRow.get(0),
                secondRow.get(1),
                secondRow.get(2),
                secondRow.get(3),
                secondRow.get(4));

    }



    @When("I enter the following credentials as list of maps")
    public void i_enter_the_following_credentials_as_list_of_maps(List<Map<String, String>> dataTable) {
        System.out.println(dataTable);

       Map<String,String> map = dataTable.get(0);

        new HomePage().enterCredentialsAndClick(map.get("username"),
                map.get("firstname"),
                map.get("lastname"),
                map.get("email"),
                map.get("password"));
    }



    @When("I pass this information as list of maps")
    public void i_pass_this_information_as_list_of_maps(List<Map<String, String>> dataTable) {

        System.out.println(dataTable);

        System.out.println(dataTable.get(1).get("lastName"));
    }


    @When("I pass this information as maps")
    public void i_pass_this_information_as_maps(Map<String,String> dataTable) {

        System.out.println(dataTable);
        System.out.println(dataTable.get("KSFO"));
    }

    @When("I pass this information as map of <String, List<String>>")
    public void i_pass_this_information_as_map_of_string_list_string(Map<String, List<String>> dataTable) {


        System.out.println(dataTable);
    }

    @When("I pass this information as map of <String, Map<String,String>>")
    public void i_pass_this_information_as_map_of_string_map_string_string(Map<String, Map<String, String>> dataTable) {


        System.out.println(dataTable);

        System.out.println(dataTable.get("KMSY").get("lon"));
    }


    @When("I pass this information as list")
    public void i_pass_this_information_as_list(List<String> dataTable) {


        System.out.println(dataTable.getClass());

        List<String> modifieble = new ArrayList<>(dataTable);
        Collections.sort(modifieble);

        System.out.println(modifieble);
    }



    @Then("I read the signup information from csv file and enter the details and should be able to sign up successfully")
    public void i_read_the_signup_information_from_csv_file_and_enter_the_details_and_should_be_able_to_sign_up_successfully() throws IOException {

        SoftAssertions softAssertions = new SoftAssertions();
        List<List<String>> data = CSVReader.readFromCSV("src/test/resources/testData/userData.csv");

        for (List<String> each : data) {

            new HomePage().enterCredentialsAndClick(each.get(0),
                    each.get(1),
                    each.get(2),
                    each.get(3),
                    each.get(4));


            SeleniumUtils.waitFor(1);
            softAssertions.assertThat( Driver.getDriver().getCurrentUrl()).isEqualTo("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?");


            new WelcomePage().logout();
            SeleniumUtils.waitFor(1);
            new HomePage().clickOnSignUpPageLink();
            SeleniumUtils.waitFor(1);

        }

        softAssertions.assertAll();


    }







}
