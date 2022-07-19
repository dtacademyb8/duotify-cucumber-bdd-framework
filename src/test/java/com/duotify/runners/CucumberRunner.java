package com.duotify.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "not @ui",            //"@smoke and @signup" -> scenarios tagged with both @smoke and @signup
                                    // @smoke or @signup" -> scenarios tagged with either @smoke or @signup
                                    // @smoke or @signup and @regression ->  scenarios tagged with either smoke OR signup and regression (signup and regression evaluated first)
                                    //(@smoke or @signup) and @regression ->  scenarios tagged with either smoke or signup AND regression (smoke or signup evaluated first)
                                    // "not @ui" -> all scenarios that are NOT tagged with @ui
        features = "src/test/resources", // path to the folder where feature files are located
        glue = "com/duotify/stepDefintions" // path to the folder where step definitions are stored
//        ,dryRun = true // used for quickly generating step definition snippets without running the scenarios



)
public class CucumberRunner {

}
