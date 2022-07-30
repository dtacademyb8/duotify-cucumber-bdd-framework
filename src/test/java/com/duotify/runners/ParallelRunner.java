package com.duotify.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/parallel", // path to the folder where feature files are located
        glue = "com/duotify/stepDefintions", // path to the folder where step definitions are stored
        plugin = {
                 "pretty",  // displays more detailed aka prettier output on the console about the result of the scenario run
                 "html:target/built-in-report/cucumber-html-report.html",  // generates built-in html report
                 "json:target/cucumber.json"
        }







)
public class ParallelRunner {

}
