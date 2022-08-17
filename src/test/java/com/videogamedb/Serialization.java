package com.videogamedb;

import com.videogamedb.pojos.VideoGame;
import com.videogamedb.pojos.VideoGameGenerated;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Serialization {


    static {
        baseURI = "http://ec2-18-191-149-148.us-east-2.compute.amazonaws.com:8080/app";
    }


    @Test
    public void testSerializationUSingString(){
        int id  =  100 + new Random().nextInt(10000);

        given().log().all().
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
    public void testSerializationUsingMap(){
        int id  =  100 + new Random().nextInt(10000);

        Map<String, Object> payload  = new HashMap<>();

        payload.put("id", id);
        payload.put("name", "Half_life");
        payload.put("releaseDate", "2022-08-15T22:09:16.280Z");
        payload.put("reviewScore", 99);
        payload.put("category", "FPS");
        payload.put("rating", "PG13");


        given().log().all().
                header("Accept", "application/json").
//                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(payload).
                when(). log().all().
                post("/videogames").
                then(). log().all().
                statusCode(200).
                header("Content-Type", "application/json").
                body("status", equalTo("Record Added Successfully"));

    }

    @Test
    public void testSerializationUsingPojo(){
        int id  =  100 + new Random().nextInt(10000);

//        VideoGame videoGame = new VideoGame(
//                id,
//                "Half_Life",
//                "2022-08-15T22:09:16.280Z",
//                 99,
//                "FPS",
//                "PG13"
//
//        );

        VideoGameGenerated videoGameGenerated = new VideoGameGenerated(
                id,
                "Half_Life",
                "2022-08-15T22:09:16.280Z",
                 99,
                "FPS",
                "PG13"

        );


        given().log().all().
                header("Accept", "application/json").
//                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(videoGameGenerated).
                when(). log().all().
                post("/videogames").
                then(). log().all().
                statusCode(200).
                header("Content-Type", "application/json").
                body("status", equalTo("Record Added Successfully"));

    }


    @Test
    public void testSerializationUsingJsonFile(){


     //static payloads can be stored in a json file and sent as a file


        given().log().all().
                header("Accept", "application/json").
//                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(new File("src/test/java/com/videogamedb/jsonFiles/VideoGamePayload.json")).
                when(). log().all().
                post("/videogames").
                then(). log().all().
                statusCode(200).
                header("Content-Type", "application/json").
                body("status", equalTo("Record Added Successfully"));

    }


}
