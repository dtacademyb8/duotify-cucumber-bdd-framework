package com.duotify.stepDefintions.db;

import com.duotify.pages.HomePage;
import com.duotify.pages.USerDetailsPage;
import com.duotify.pages.WelcomePage;
import com.duotify.utilities.ConfigReader;
import com.duotify.utilities.DBUtils;
import com.duotify.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataMappingStepDefs {

    SoftAssertions softAssertions;

    @Then("The database should also have correctly mapped info")
    public void the_database_should_also_have_correctly_mapped_info(List<Map<String,String>> expectedList) throws SQLException {

        Map<String, String> expected = expectedList.get(0);
        String expectedUsername= expected.get("username");
        String expectedFirst= expected.get("firstname");
        String expectedLast= expected.get("lastname");
        String expectedEmail= expected.get("email");
        String expectedPassword= DigestUtils.md5Hex(expected.get("password"));

        System.out.println("MD5 pass: " + expectedPassword);

        String query = "SELECT username, firstName, lastName, email, password FROM users where username='"+expectedUsername+"'";
        List<Map<String, Object>> actualList = DBUtils.getQueryResultListOfMaps(query);

        Map<String, Object> actual = actualList.get(0);

        String actualUsername = (String)(actual.get("username"));
        String actualFirst = (String)(actual.get("firstName"));
        String actualLast = (String)(actual.get("lastName"));
        String actualEmail = (String)(actual.get("email"));
        String actualPass = (String)(actual.get("password"));

        softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualUsername ).isEqualTo(expectedUsername);
        softAssertions.assertThat(actualFirst ).isEqualTo(expectedFirst);
        softAssertions.assertThat(actualLast).isEqualTo(expectedLast);
        softAssertions.assertThat(actualEmail).isEqualTo(expectedEmail);
        softAssertions.assertThat(actualPass).isEqualTo(expectedPassword);


        DBUtils.executeUpdate("DELETE from users where username='"+expectedUsername+"'");


        softAssertions.assertAll();









    }

    Map<String, String> data;
    @When("I add a new user to the database with the following info")
    public void i_add_a_new_user_to_the_database_with_the_following_info(List<Map<String, String>> dataTable) throws SQLException {

        data = dataTable.get(0);

        String query = "INSERT INTO users (username, firstName, lastName, email, password ) values ( '"+data.get("username")+"', '"+data.get("first")+"', '"+data.get("last")+"', '"+data.get("email")+"', '"+DigestUtils.md5Hex(data.get("password"))+"')";

        System.out.println(query);


        DBUtils.executeUpdate(query);


    }
    @Then("I should be able to log in with the same credentials on the UI")
    public void i_should_be_able_to_log_in_with_the_same_credentials_on_the_ui() {
           Driver.getDriver().get(ConfigReader.getProperty("url"));

           new HomePage().login(data.get("username"), data.get("password"));
           Assert.assertEquals("http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?", Driver.getDriver().getCurrentUrl());
    }
    @Then("The firstname, lastname and email also should be correct")
    public void the_firstname_lastname_and_email_also_should_be_correct() {

        WelcomePage welcomePage = new WelcomePage();
        String actualFirst = welcomePage.username.getText().split(" ")[0];
        String actualLast = welcomePage.username.getText().split(" ")[1];
        welcomePage.username.click();
        welcomePage.userDetailsButton.click();

        String actualEmail =  new USerDetailsPage().emailField.getAttribute("value");

        softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualEmail).isEqualTo(data.get("email"));
        softAssertions.assertThat(actualFirst).isEqualTo(data.get("first"));
        softAssertions.assertThat(actualLast).isEqualTo(data.get("last"));






    }

}
