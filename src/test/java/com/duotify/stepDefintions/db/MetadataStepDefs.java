package com.duotify.stepDefintions.db;

import com.duotify.utilities.DBUtils;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.SQLException;
import java.util.List;

public class MetadataStepDefs {
    List<String> actualColumnNames;
    @When("I send a request to retrieve colum names for albums table")
    public void i_send_a_request_to_retrieve_colum_names_for_albums_table() {

        actualColumnNames = DBUtils.getColumnNames("SELECT * FROM albums limit 1");

        System.out.println(actualColumnNames);

    }
    @Then("It should be the following")
    public void it_should_be_the_following(List<String> expectedColumnNames) {

        Assert.assertEquals(expectedColumnNames, actualColumnNames);

    }
    StringBuilder sb;
    int unexpected;
    @When("I send a request to add a new title that is more than the expected length of {int}")
    public void i_send_a_request_to_add_a_new_title_that_is_more_than_the_indicated_length(int expected) throws SQLException {
         sb = new StringBuilder();

        for (int i = 0; i < expected + 100; i++) {
            sb.append('a');
        }



            DBUtils.executeUpdate("INSERT IGNORE INTO albums (title ) values ('"+sb+"')");




        unexpected = expected;
    }
    @Then("The data should be truncated to the expected length")
    public void the_data_should_be_truncated_to_expected_length() {


        List<List<Object>> queryResultAsListOfLists = DBUtils.getQueryResultAsListOfLists("SELECT title from albums order by id desc limit 1");

        int actualLength = ((String) (queryResultAsListOfLists.get(0).get(0))).length();

        Assert.assertEquals(unexpected, actualLength);
    }


}
