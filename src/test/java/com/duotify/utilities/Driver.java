package com.duotify.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    private static  ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    private Driver(){}


    public static synchronized WebDriver getDriver(){

        if(drivers.get() == null ){

            String browser = System.getProperty("browser");  //read the browser type from command line, if no browser is passed the returned value  will be null

            if(browser == null){ // if no browser is passed thru command line
                browser = ConfigReader.getProperty("browser"); // Read the browser type from config.properties file
            }


            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver());
                    break;
                case "chrome_headless":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    drivers.set(new FirefoxDriver());
                    break;
                case "firefox_headless":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    WebDriverManager.firefoxdriver().setup();
                    drivers.set(new FirefoxDriver(firefoxOptions));
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    drivers.set(new EdgeDriver());
                    break;
                case "edge_headless":
                    EdgeOptions edgeOptions =  new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    WebDriverManager.edgedriver().setup();
                    drivers.set(new EdgeDriver(edgeOptions));
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    drivers.set(new SafariDriver());
                    break;
                default:
                    throw new RuntimeException("Invalid browser");
            }

        }

        return drivers.get();

    }



    public static synchronized void quitDriver(){
        if(drivers.get() != null){
            drivers.get().quit();
            drivers.remove();
        }

    }


}
