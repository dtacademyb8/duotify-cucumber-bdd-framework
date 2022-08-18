package com.videogamedb;

import com.duotify.utilities.ConfigReader;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestVideoGameDBNegative {

    static {
        baseURI = "http://ec2-18-191-149-148.us-east-2.compute.amazonaws.com:8080/app";
    }







    @Test
    public void testPostNewVideoGameNegative(){





        given().
                header("Accept", "application/json").
//                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body("{\n" +
                        "  \"id\": "+1+",\n" +
                        "  \"name\": \"Half-Life\",\n" +
                        "  \"releaseDate\": \"2022-08-15T22:09:16.280Z\",\n" +
                        "  \"reviewScore\": 99,\n" +
                        "  \"category\": \"FPS\",\n" +
                        "  \"rating\": \"PG13\"\n" +
                        "}").
        when(). log().all().
                post("/videogames").
        then(). log().all().
                statusCode(not(200)).
                header("Content-Type", "application/json;charset=UTF-8").
                header("connection", "close").

                body("error", equalTo("Internal Server Error"));






    }









}
