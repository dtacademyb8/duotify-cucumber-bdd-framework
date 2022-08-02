package com.duotify.stepDefintions.db;

import com.duotify.utilities.DBUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class DataMappingStepDefs {



    @Then("The database should also have correctly mapped info")
    public void the_database_should_also_have_correctly_mapped_info(List<Map<String,String>> expectedList) {

        Map<String, String> expected = expectedList.get(0);
        String expectedUsername= expected.get("username");
        String expectedFirst= expected.get("firstname");
        String expectedLast= expected.get("lastname");

        String query = "SELECT username, firstName, lastName FROM users where username='"+expectedUsername+"'";
        List<Map<String, Object>> actualList = DBUtils.getQueryResultListOfMaps(query);

        Map<String, Object> actual = actualList.get(0);

        String actualUsername = (String)(actual.get("username"));
        String actualFirst = (String)(actual.get("firstName"));
        String actualLast = (String)(actual.get("lastName"));

        Assert.assertEquals(expectedUsername,actualUsername);
        Assert.assertEquals(expectedFirst,actualFirst);
        Assert.assertEquals(expectedLast,actualLast);

    }

}
