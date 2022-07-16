package com.duotify.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@smoke",
        features = "src/test/resources", // path to the folder where feature files are located
        glue = "com/duotify/stepDefintions" // path to the folder where step definitions are stored
//        ,dryRun = true // used for quickly generating step definition snippets without running the scenarios



)
public class CucumberRunner {

}
