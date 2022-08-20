package com.duotify.stepDefintions.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetUsersStepDefs {
    RequestSpecification header;
    Response response;
    @Given("The following headers are added")
    public void the_following_headers_are_added(Map<String, String> dataTable) {
        header = given().
                header("Accept", dataTable.get("Accept"));

    }

    @Given("the query parameter {string} is set to {int}")
    public void the_query_parameter_is_set_to(String key, Integer value) {
       header =  header.given().queryParam(key, value);
    }
    @When("I send a GET request to users endpoint")
    public void i_send_a_get_request_to_users_endpoint() {
       response = header.when().log().all().get(GithubEndpoints.USERS);
    }
    @Then("The response should have {int} users")
    public void the_response_should_have_users(Integer size) {
          response.then().assertThat(). log().all().
                  body("$.size()", lessThanOrEqualTo(size));

          // $ or "" represents the root array/ anonymous array response body
    }


    @Then("The response payload should contain ids greater than {int}")
    public void theResponsePayloadShouldContainIdsGreaterThan(int id) {

        JsonPath jsonPath = response.then().log().all().
                body("id", not(hasItemInArray(lessThanOrEqualTo(id)))).
                statusCode(200).extract().jsonPath();

      List<Integer> list = jsonPath.getList("id");

        for (Integer o : list) {
            Assert.assertTrue(o > id);
        }
    }
}
