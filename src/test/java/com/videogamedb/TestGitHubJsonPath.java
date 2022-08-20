package com.videogamedb;

import com.duotify.utilities.ConfigReader;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestGitHubJsonPath {





    @Test
    public void testJsonPath(){

        baseURI = "https://api.github.com";


        JsonPath jsonPath = given().
                header("Accept", "application/vnd.github+json").
                header("Authorization", "token " + ConfigReader.getProperty("github_bearer_token")).
                queryParam("per_page", 100).


                when().log().all().
                get("/users").
                then().log().all().

                statusCode(200).extract().jsonPath();


               List list = jsonPath.get(""); // return the response as list

                System.out.println(list);

        Map<String, Object> firstResult = jsonPath.get("[0]"); // return the first object from the list

        System.out.println(firstResult);

        String node_id = jsonPath.get("[0].node_id"); // return the first object's node_id value

        System.out.println(node_id);


        List allUrls = jsonPath.get("url");  // return url values of all objects in a list

        System.out.println(allUrls);

        String str = jsonPath.get("login.find{it == 'jnicklas'}");  // return the first login value thta is equal to jnicklas

        System.out.println(str);

       List allOrgs = jsonPath.get("findAll{it.type == 'Organization'}.login");  // return all object's login values whose type is equal to Organization

        System.out.println(allOrgs);



        List dsadc = jsonPath.get("findAll{it.login.contains('bb') }.login"); // return the login values of all objects whose login value contains ('bb')

        System.out.println(dsadc);

        List dsf = jsonPath.get("findAll{it.login.startsWith('c') }.id"); // return the login values of all objects whose login value starts with ('c')

        System.out.println(dsf);





    }

    @Test
    public void testJsonPath2(){
        baseURI = "https://api.github.com";


        JsonPath jsonPath = given().
                header("Accept", "application/vnd.github+json").
                header("Authorization", "token " + ConfigReader.getProperty("github_bearer_token")).


                when().log().all().
                get("/user").
                then().log().all().
                statusCode(200).extract().jsonPath();

             List list = jsonPath.get("findAll{it.getValue() == null}.keySet().toList()");  // return the list of all keys whose value is null
             System.out.println(list);
//

    }


}
