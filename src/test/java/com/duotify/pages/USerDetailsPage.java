package com.duotify.pages;

import com.duotify.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class USerDetailsPage {

    public USerDetailsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(name = "email")
    public WebElement emailField;













}
