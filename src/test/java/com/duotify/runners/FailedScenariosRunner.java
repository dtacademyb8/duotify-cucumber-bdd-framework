package com.duotify.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(


        features = "@target/failedScenarios.txt", //
        glue = "com/duotify/stepDefintions", //
//
        plugin = {
                 "pretty",  // displays more detailed aka prettier output on the console about the result of the scenario run
                 "html:target/built-in-report/cucumber-html-report.html",  // generates built-in html report
                 "json:target/cucumber.json",

        }







)
public class FailedScenariosRunner {

}
