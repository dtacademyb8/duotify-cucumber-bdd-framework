package com.duotify.pages;

import com.duotify.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy (id = "hideLogin")
    private WebElement signUpPageLink;

    @FindBy (id = "username")
    private WebElement username;

    @FindBy (id = "firstName")
    private WebElement first;

    @FindBy (id = "lastName")
    private WebElement last;

    @FindBy (id = "email")
    private WebElement email;

    @FindBy (id = "email2")
    private WebElement email2;

    @FindBy (id = "password")
    private WebElement pass;

    @FindBy (id = "password2")
    private WebElement pass2;

    @FindBy (name = "registerButton")
    private WebElement signUpButton;


    public void clickOnSignUpPageLink(){
        signUpPageLink.click();
    }


    public String getTitle(){
       return Driver.getDriver().getTitle();
    }

    public void enterCredentialsAndClick(String s, String lara, String alissa, String s1, String lara12345) {

        username.sendKeys(s);
        first.sendKeys(lara);
        last.sendKeys(alissa);
        email.sendKeys(s1);
        email2.sendKeys(s1);
        pass.sendKeys(lara12345);
        pass2.sendKeys(lara12345);
        signUpButton.click();

    }
}
