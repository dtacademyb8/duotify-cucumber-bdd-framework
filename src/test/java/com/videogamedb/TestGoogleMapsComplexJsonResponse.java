package com.videogamedb;

import com.duotify.utilities.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestGoogleMapsComplexJsonResponse {





    @Test
    public void testComplexJson(){

    baseURI  ="https://maps.googleapis.com/maps/api";

        JsonPath jsonPath = given().
                queryParam("fields", "formatted_address,name,rating,opening_hours,geometry,photos").
                queryParam("input", "Cedars").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                when().log().all().
                get("/place/findplacefromtext/json").
                then().log().all().
                statusCode(200).
                body("candidates[0].formatted_address", is("8625 Lee Hwy, Fairfax, VA 22031, United States")).
                body("candidates[0].geometry.location.lat", is(38.8727611F)).
                body("candidates[0].geometry.viewport.northeast.lat", is(38.87416202989272F)).extract().jsonPath();


        Map<Object, Object> map = jsonPath.getMap("candidates[0].geometry.location");
        System.out.println(map);
        Float f = (Float)(map.get("lat"));
        System.out.println(f);

        System.out.println((List)(jsonPath.get("candidates")));
        System.out.println((Integer)(jsonPath.get("candidates.size()")));

    }

    @Test
    public void testComplexJson2(){



            baseURI = "https://api.github.com";


        JsonPath jsonPath = given().
                header("Accept", "application/vnd.github+json").
                header("Authorization", "token " + ConfigReader.getProperty("github_bearer_token")).


                when().log().all().
                get("/users").
                then().log().all().
                statusCode(200).
                body("[0].login", is("mojombo")).
                body("login", hasSize(30)).
                body("login", hasItem("ivey")).
                body("login", hasItems("mojombo","defunkt"))
                .extract().jsonPath();


        List login = jsonPath.get("login");

        System.out.println(login);
    }


}
