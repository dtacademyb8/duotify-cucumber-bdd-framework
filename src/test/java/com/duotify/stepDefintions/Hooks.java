package com.duotify.stepDefintions;

import com.duotify.utilities.ConfigReader;
import com.duotify.utilities.DBUtils;
import com.duotify.utilities.Driver;
import io.cucumber.java.*;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.time.Duration;

public class Hooks {

    @BeforeAll    // runs once before all scenarios, must be static
    public static void setupDb(){
        DBUtils.createConnection();
    }

    @AfterAll   // runs once after all scenarios have completed, must be static
    public static void closeDB(){
          DBUtils.close();
    }

    @Before ("not @api")   // runs before each scenario
    public void setup(){


        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Before ("@api")
    public void setupApi(){
        RestAssured.baseURI = ConfigReader.getProperty("base_uri");
    }


//    @Before ("@db")
//    public void setupDB(){
//
//
//        System.out.println("Setting up DB");
//    }



    @After  ("not @api") // runs after each scenario
    public void tearDown(Scenario scenario){

        if(scenario.isFailed()){

            byte[] screenshotAs = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotAs,"image/png", "screenshotOfFailure");
        }



        Driver.quitDriver();
    }

//    @After ("@db")
//    public void tearDownDB(){
//
//        System.out.println("Tearing doen db connection");
//    }


}
