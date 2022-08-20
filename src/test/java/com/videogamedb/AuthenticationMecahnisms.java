package com.videogamedb;

import com.duotify.utilities.ConfigReader;
import io.netty.handler.codec.base64.Base64Encoder;
import io.restassured.path.json.JsonPath;
import org.junit.Test;


import java.util.Base64;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class AuthenticationMecahnisms {


    @Test
    public void basicAuth(){


        baseURI = "https://postman-echo.com";

        String encoded =  Base64.getEncoder().encodeToString("postman:password".getBytes());

        given().
//                auth().basic("postman", "password").
                header("Authorization" , "Basic " + encoded).
        when().
                get("/basic-auth").
                then().statusCode(200);

    }

    @Test
    public void apiKey(){

        baseURI  ="https://maps.googleapis.com/maps/api";

        given().
                queryParam("fields", "formatted_address,name,rating,opening_hours,geometry,photos").
                queryParam("input", "Cedars").
                queryParam("inputtype", "textquery").
                queryParam("key", "AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8").
                when().log().all().
                get("/place/findplacefromtext/json").
                then().log().all().
                statusCode(200);
    }

    public void bearerToken(){

        baseURI = "https://api.github.com";


      given().
                header("Accept", "application/vnd.github+json").
                header("Authorization", "token " + ConfigReader.getProperty("github_bearer_token")).


                when().log().all().
                get("/user").
                then().log().all().
                statusCode(200);

    }

    @Test
    public void bearerToken2(){

        baseURI = "http://qa-duobank.us-east-2.elasticbeanstalk.com/api";


        given().


                body("{\n" +
                        "    \"email\": \"momo@momo.com\",\n" +
                        "    \"password\":\"gojciP-zowkud-2fuhny\"\n" +
                        "}").


                when().log().all().
                post("/login.php").
                then().log().all().
                statusCode(200);

    }

    @Test
    public void oath2point0(){


        baseURI = "http://coop.apps.symfonycasts.com";

        String token =  given().
                param("client_id", "IntelliJ").
                param("client_secret", "b6bb49ce8b2e64017973c9fd7f807b08").
                param("grant_type", "client_credentials").
        when().log().all().
                post("/token").
        then().log().all().statusCode(200).extract().jsonPath().get("access_token");


        given().
                auth().oauth2(token).
        when(). log().all().
                post("/api/3696/chickens-feed").
             then().log().all().
                statusCode(200);




    }




}
