package com.duotify.stepDefintions;

import com.duotify.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.time.Duration;

public class Hooks {


    @Before
    public void setup(){

        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }



    @After
    public void tearDown(){

        Driver.quitDriver();
    }


}
