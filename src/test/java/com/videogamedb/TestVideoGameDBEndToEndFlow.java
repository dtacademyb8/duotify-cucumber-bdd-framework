package com.videogamedb;

import com.duotify.utilities.ConfigReader;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TestVideoGameDBEndToEndFlow {

    static {
        baseURI = "http://ec2-18-191-149-148.us-east-2.compute.amazonaws.com:8080/app";
    }



    @Test
    public void testGETVideogames(){

         // Set the BASE URI



          given().   // request specifications (headers,parameters, body)
                  header("Accept", "application/json").
          when(). log().all(). // request type
                  get(Endpoints.VIDEOGAMES.toString()).
          then(). log().all(). // assertions on Returned Response
                statusCode(200);






//        RequestSpecification requestSpecification = given().   // request specifications (headers,parameters, body)
//                header("Accept", "application/json");
//
//        Response response = requestSpecification.when().log().all(). // request type
//                get("/videogames");
//
//        response. then(). log().all(). // assertions on Returned Response
//                statusCode(200);



    }


    @Test
    public void testGetVideoGameById(){

        int id = 9;



        given().
                header("Accept", "application/json").
                pathParam("videoGameId" , id).
        when(). log().all(). // request type
//                get("/videogames/5").
                get("/videogames/{videoGameId}").
        then(). log().all(). // assertions on Returned Response
                statusCode(200).
                header("Content-Type", "application/json").
//                header("Content-Length", "138").
//                cookie("key", "value")
                body("id", equalTo(id)).
//                body("name", is("Final Fantasy VII")).
                time(lessThan(1000L));



    }


    @Test
    public void testPostNewVideoGame(){

      int id  =  100 + new Random().nextInt(10000);



        given().
                header("Accept", "application/json").
//                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body("{\n" +
                        "  \"id\": "+id+",\n" +
                        "  \"name\": \"Half-Life\",\n" +
                        "  \"releaseDate\": \"2022-08-15T22:09:16.280Z\",\n" +
                        "  \"reviewScore\": 99,\n" +
                        "  \"category\": \"FPS\",\n" +
                        "  \"rating\": \"PG13\"\n" +
                        "}").
        when(). log().all().
                post("/videogames").
        then(). log().all().
                statusCode(200).
                header("Content-Type", "application/json").
                body("status", equalTo("Record Added Successfully"));






    }

    @Test
    public void testPutRequest(){





        given().
                header("Accept", "application/json").
                header("Content-Type", "application/json").
                pathParam("videoGameId" , 11).
                body("{\n" +
                        "  \"id\": 11,\n" +
                        "  \"name\": \"Half-Life: Alyx\",\n" +
                        "  \"releaseDate\": \"2022-08-15\",\n" +
                        "  \"reviewScore\": 89,\n" +
                        "  \"category\": \"Adventure\",\n" +
                        "  \"rating\": \"General\"\n" +
                        "}").
        when(). log().all().
                put("/videogames/{videoGameId}").
        then(). log().all().
                statusCode(201).
                header("Content-Type", "application/json").
                body("id", equalTo(12));






    }

    @Test
    public void testDeleteRequest(){


        given().
                header("Accept", "application/json").
                pathParam("videoGameId" , 11).

        when(). log().all().
                delete(Endpoints.VIDEOGAME_BY_ID+"").
        then(). log().all().
                statusCode(200).
                header("Content-Type", "application/json").
                body("status", equalTo("Record Deleted Successfully"));






    }

    @Test
    public void testPatchRequest(){

        baseURI = "https://api.github.com";

        String loc = "San Fransisco, CA";
        String name = "Duotify";
        boolean hire = false;
        given().
                header("Accept", "application/vnd.github+json").
                header("Authorization", "token " + ConfigReader.getProperty("github_bearer_token")).
                body("{\n" +
                        "     \"location\": \""+loc+"\",\n" +
                        "     \"company\": \""+name+"\",\n" +
                        "     \"hireable\" : "+hire+"\n" +
                        "}").


        when(). log().all().
                patch("/user").
        then(). log().all().
                statusCode(200).
                header("Content-Type", "application/json; charset=utf-8").
                header("Server", "GitHub.com").
//                cookie("logged_in", "no").
                body("company", equalTo(name)).
                body("location", equalTo(loc)).
                body("hireable", equalTo(null));







    }







}
