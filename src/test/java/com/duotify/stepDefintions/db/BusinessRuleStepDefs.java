package com.duotify.stepDefintions.db;

import com.duotify.utilities.DBUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleStepDefs {

    List<String> actual;
    @When("I send a request to retrieve genres from genres table")
    public void i_send_a_request_to_retrieve_genres_from_genres_table() {


        List<List<Object>> list = DBUtils.getQueryResultAsListOfLists("SELECT name from genres");



        actual =  new ArrayList<>();

        for (List<Object> row : list) {
            actual.add((String)(row.get(0)));
        }



    }
    @Then("It should be the following expected genres")
    public void it_should_be_the_following_expected_genres(List<String> expected) {

        Assert.assertEquals(expected,actual);
    }

    List<List<Object>> actualList;
    @When("I send a request to retrieve duplicate usernames")
    public void i_send_a_request_to_retrieve_duplicate_usernames() {

        actualList = DBUtils.getQueryResultAsListOfLists("SELECT username, count(*) FROM users GROUP BY username HAVING (COUNT(*) > 1)");



    }
    @Then("The result should be empty")
    public void the_result_should_be_empty() {
        Assert.assertTrue(actualList.isEmpty());
    }


}
