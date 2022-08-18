package com.videogamedb;

import com.duotify.utilities.ConfigReader;
import com.videogamedb.pojos.VideoGame;
import io.cucumber.java.it.Ma;
import io.restassured.common.mapper.TypeRef;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Deserialization {



    @Test
    public void testDeserializationAsString(){

        baseURI = "https://api.github.com";

        String loc = "San Fransisco, CA";
        String name = "Duotify";
        boolean hire = false;
        String responseAsString = given().
                header("Accept", "application/vnd.github+json").
                header("Authorization", "token " + ConfigReader.getProperty("github_bearer_token")).
                body("{\n" +
                        "     \"location\": \"" + loc + "\",\n" +
                        "     \"company\": \"" + name + "\",\n" +
                        "     \"hireable\" : " + hire + "\n" +
                        "}").


                when().log().all().
                patch("/user").
                then().log().all().
                statusCode(200).
                header("Content-Type", "application/json; charset=utf-8").
                header("Server", "GitHub.com").
//                cookie("logged_in", "no").
        body("company", equalTo(name)).
                body("location", equalTo(loc)).
                body("hireable", equalTo(null)).extract().asPrettyString();


        System.out.println(responseAsString);


    }

    @Test
    public void DeserializeAsRawMap(){

        baseURI = "https://api.github.com";

        String loc = "San Fransisco, CA";
        String name = "Duotify";
        boolean hire = false;
       Map responseAsRawMap = given().
                header("Accept", "application/vnd.github+json").
                header("Authorization", "token " + ConfigReader.getProperty("github_bearer_token")).
                body("{\n" +
                        "     \"location\": \"" + loc + "\",\n" +
                        "     \"company\": \"" + name + "\",\n" +
                        "     \"hireable\" : " + hire + "\n" +
                        "}").


                when().log().all().
                patch("/user").
                then().log().all().
                statusCode(200).
                header("Content-Type", "application/json; charset=utf-8").
                header("Server", "GitHub.com").
//                cookie("logged_in", "no").
        body("company", equalTo(name)).
                body("location", equalTo(loc)).
                body("hireable", equalTo(null)).extract().as(Map.class);


        System.out.println(responseAsRawMap);

        String company = (String)(responseAsRawMap.get("company"));

        Assert.assertEquals("Duotify", company );

        System.out.println(responseAsRawMap.keySet());






    }

    @Test
    public void DeserializeAsSpecificTypeOfMap(){

        baseURI = "https://api.github.com";

        String loc = "San Fransisco, CA";
        String name = "Duotify";
        boolean hire = false;
        Map<String, Object> responseAsSpecificTypeOFMap = given().
                header("Accept", "application/vnd.github+json").
                header("Authorization", "token " + ConfigReader.getProperty("github_bearer_token")).
                body("{\n" +
                        "     \"location\": \"" + loc + "\",\n" +
                        "     \"company\": \"" + name + "\",\n" +
                        "     \"hireable\" : " + hire + "\n" +
                        "}").


                when().log().all().
                patch("/user").
                then().log().all().
                statusCode(200).
                header("Content-Type", "application/json; charset=utf-8").
                header("Server", "GitHub.com").
//                cookie("logged_in", "no").
        body("company", equalTo(name)).
                body("location", equalTo(loc)).
                body("hireable", equalTo(null)).extract().as(new TypeRef<Map<String, Object>>() {
                });


        System.out.println(responseAsSpecificTypeOFMap.getClass());

        String company = (String)responseAsSpecificTypeOFMap.get("company");

        Assert.assertEquals("Duotify", company );

        System.out.println(responseAsSpecificTypeOFMap.keySet());
        System.out.println(responseAsSpecificTypeOFMap.values());
        System.out.println(responseAsSpecificTypeOFMap.entrySet());


        Map<String, Object> plan = (Map<String, Object>) (responseAsSpecificTypeOFMap.get("plan"));

        System.out.println(plan);

        Assert.assertEquals(976562499, plan.get("space"));


        // Find all keys whose value is null

        List<String> nullValueKeys =  new ArrayList<>();

        for (String key: responseAsSpecificTypeOFMap.keySet()){

            if(responseAsSpecificTypeOFMap.get(key) == null){
                nullValueKeys.add(key);
            }
        }

        System.out.println(nullValueKeys);
        System.out.println(nullValueKeys.size());




    }


    @Test
    public void DeserializeRawList() {


        baseURI = "http://ec2-18-191-149-148.us-east-2.compute.amazonaws.com:8080/app";

//        List responseAsRawList = given().   // request specifications (headers,parameters, body)
//                header("Accept", "application/json").
//                when().log().all(). // request type
//                        get(Endpoints.VIDEOGAMES + "").
//                then().log().all(). // assertions on Returned Response
//                        statusCode(200).extract().as(List.class);
        List<Map<String, Object>> reponseAsSpecificList = given().   // request specifications (headers,parameters, body)
                header("Accept", "application/json").
                when().log().all(). // request type
                        get(Endpoints.VIDEOGAMES + "").
                then().log().all(). // assertions on Returned Response
                        statusCode(200).extract().as(new TypeRef<List<Map<String, Object>>>() {
                });


        System.out.println(reponseAsSpecificList);

        List<String> actual = new ArrayList<>();

        for (Map<String, Object> each : reponseAsSpecificList) {

            actual.add((String)(each.get("name")));
        }

        System.out.println(actual);









    }

    @Test
    public void DeserializePOJO(){


        baseURI = "http://ec2-18-191-149-148.us-east-2.compute.amazonaws.com:8080/app";

        Integer id = 9;


        VideoGame responseAsVideoGAmeObject = given().
                header("Accept", "application/json").
                pathParam("videoGameId", id).
                when().log().all(). // request type
//                get("/videogames/5").
        get("/videogames/{videoGameId}").
                then().log().all(). // assertions on Returned Response
                        statusCode(200).
                header("Content-Type", "application/json").
//                header("Content-Length", "138").
//                cookie("key", "value")
        body("id", equalTo(id)).extract().as(VideoGame.class);

        System.out.println(responseAsVideoGAmeObject);

        Assert.assertEquals(id, responseAsVideoGAmeObject.getId());
        Assert.assertEquals("1997-08-20", responseAsVideoGAmeObject.getReleaseDate());
//



    }

    @Test
    public void DeserializeListOfPOJOs() {


        baseURI = "http://ec2-18-191-149-148.us-east-2.compute.amazonaws.com:8080/app";

        List<VideoGame> returnedAsListOfVidoeoGames = given().   // request specifications (headers,parameters, body)
                header("Accept", "application/json").
                when().log().all(). // request type
                        get(Endpoints.VIDEOGAMES + "").
                then().log().all(). // assertions on Returned Response
                        statusCode(200).extract().as(new TypeRef<List<VideoGame>>() {
                });

        System.out.println(returnedAsListOfVidoeoGames);


    }


    }
